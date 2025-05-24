package JasonOOP.DataBase;

import java.io.*;
import java.util.ArrayList;

public class userSetting {
    public static ArrayList<users> people = new ArrayList<>();
    private static final String filePath = "data.txt";
    public static String selected_username = "";
    public static String selected_password = "";
    public static String selected_id = "";
    public static String selected_type = "";
    public static double selected_penalty = 1.0;


    public static void read() {
        people.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String password = parts[1];
                int id = Integer.parseInt(parts[2]);
                String type = parts[3];
                double penalty = Double.parseDouble(parts[4]);
                int freeHours = Integer.parseInt(parts[5]);
                people.add(new users(name, password, id, type, penalty, freeHours));
            }
            if (!people.isEmpty()) {
                users.setMaxId(people.get(people.size() - 1).getMaxId());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void update() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (users person : people) {
                bw.write(person.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void add(String name, String password, String type, double penalty) {
        users newUser = new users(name, password, type, penalty);
        people.add(newUser);
    }

    public static void displayUsers() {
        System.out.println("Users:");
        for (users person : people) {
            System.out.println(person);
        }
    }

    public static boolean search(String username, String password) {
        for (users person : people) {
            if (person.getName().equals(username) && person.getPassword().equals(password)) {
                selected_username = person.getName();
                selected_password = person.getPassword();
                selected_id = String.valueOf(person.getId());
                selected_type = person.getType();
                selected_penalty = person.getPenalty();
                return true;
            }
        }
        return false;
    }

    public static void addPenalty() {
        for (users person : people) {
            if (person.getName().equals(selected_username) && person.getPassword().equals(selected_password)) {
                person.addPenalty();
                selected_penalty = person.getPenalty();
            }
        }
        update();
    }

    public static String GetType(String username, String password) {
        for (users person : people) {
            if (person.getName().equals(username) && person.getPassword().equals(password)) {
                return person.getType();
            }
        }
        return "";
    }

    public static double calcUserPrice(int num_slots) {
        return num_slots * slots.SLOT_getFees() * selected_penalty;
    }

}
