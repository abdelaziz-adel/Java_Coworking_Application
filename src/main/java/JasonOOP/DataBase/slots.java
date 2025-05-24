package JasonOOP.DataBase;

import JasonOOP.Rooms.General_Room;
import JasonOOP.Rooms.Meeting_Room;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;

import java.io.*;
import java.util.AbstractMap;


public class slots {

    private static double slot_fees = 20.0;

    public String roomType;

    public slots(String roomType){
        this.roomType = roomType;
    }

    public static double SLOT_getFees() {
        return slot_fees;
    }

    private static final String filePath = "slots.txt";
    private static final String filePath2 = "capacity.txt";
    public static int capacityArray[][][] = new int[3][3][12];

    //Reservations
    public static int selected_user_reservations = 0;
    public static String selected_user_reservations_slot_id = "";
    public static boolean selected_user_on_reservations = false;
    public static boolean selected_user_back_from_update = false;

    public static void read() {
        ROOM.slotIDs.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                ROOM.slotIDs.add(new AbstractMap.SimpleEntry<>(parts[0], Integer.valueOf(parts[1])));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        updateTotalVisNumber();
    }

    public static void add(String id, int num) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(id + "," + String.valueOf(num));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        read();
    }

    public static void update() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (AbstractMap.SimpleEntry<String, Integer> entry : ROOM.slotIDs) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        updateTotalVisNumber();
    }

    public static void cancelSlot(String id) {
        for (AbstractMap.SimpleEntry<String, Integer> entry : ROOM.slotIDs) {
            if (entry.getKey().equals(id)) {
                entry.setValue(entry.getValue() - 1);
            }
        }
    }

    public static boolean incNumVis(String id) {
        int Capacity = 0;
        for (AbstractMap.SimpleEntry<String, Integer> slotId : ROOM.slotIDs) {
            if (slotId.getKey().equals(id)) {
                if (id.charAt(0) == 'G') {
                    Capacity = capacityArray[0][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(id.substring(4, 6)) - 1];
                } else if (id.charAt(0) == 'M') {
                    Capacity = capacityArray[1][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(id.substring(4, 6)) - 1];

                } else if (id.charAt(0) == 'T') {
                    Capacity = capacityArray[2][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(id.substring(4, 6)) - 1];
                }
                if (slotId.getValue() < Capacity) {
                    log.add_slots(id);
                    slotId.setValue(slotId.getValue() + 1);
                    update();
                    return slotId.getValue() != Capacity;
                }
            }
        }
        return false;
    }

    public static int getNum_of_vis(String id) {
        for (AbstractMap.SimpleEntry<String, Integer> slotId : ROOM.slotIDs) {
            if (slotId.getKey().equals(id)) {
                return slotId.getValue();
            }
        }
        return -1;
    }

    public static void updateTotalVisNumber() {
        General_Room.number_of_visitors = 0;
        Meeting_Room.number_of_visitors = 0;
        Teaching_Room.number_of_visitors = 0;
        for (AbstractMap.SimpleEntry<String, Integer> slotId : ROOM.slotIDs) {
            if (slotId.getKey().charAt(0) == 'G') {
                General_Room.number_of_visitors += slotId.getValue();
            } else if (slotId.getKey().charAt(0) == 'M') {
                Meeting_Room.number_of_visitors += slotId.getValue();
            } else if (slotId.getKey().charAt(0) == 'T') {
                Teaching_Room.number_of_visitors += slotId.getValue();
            }
        }
    }

    public static String displayTotalVisNumber() {
        String output = "";
        output += "General," + General_Room.number_of_visitors + "\n";
        output += "Meeting," + Meeting_Room.number_of_visitors + "\n";
        output += "Teaching," + Teaching_Room.number_of_visitors + "\n";
        return output;
    }

    public static int getRoomVisNumber(char prifx, String roomID) {
        int num = 0;
        for (AbstractMap.SimpleEntry<String, Integer> slotId : ROOM.slotIDs) {
            if (slotId.getKey().charAt(0) == prifx && slotId.getKey().substring(1, 4).equals(roomID)) {
                num += slotId.getValue();
            }
        }
        return num;
    }

    public static void read2() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int row = 0, col = 0;
                switch (parts[0]) {
                    case "G1":
                        row = 0;
                        col = 0;
                        break;
                    case "G2":
                        row = 0;
                        col = 1;
                        break;
                    case "G3":
                        row = 0;
                        col = 2;
                        break;
                    case "M1":
                        row = 1;
                        col = 0;
                        break;
                    case "M2":
                        row = 1;
                        col = 1;
                        break;
                    case "T1":
                        row = 2;
                        col = 0;
                        break;
                    case "T2":
                        row = 2;
                        col = 1;
                        break;
                    default:
                        return;
                }
                for (int i = 0; i < 12; i++) {
                    capacityArray[row][col][i] = Integer.parseInt(parts[i + 1]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void add2(String roomType, int roomNum, int slotNum) {
        switch (roomType) {
            case "general" -> {
                capacityArray[0][roomNum][slotNum]++;
            }
            case "meeting" -> {
                capacityArray[1][roomNum][slotNum]++;
            }
            case "teaching" -> {
                capacityArray[2][roomNum][slotNum]++;
            }
        }
        update2();
    }

    public static void remove2(String roomType, int roomNum, int slotNum) {
        switch (roomType) {
            case "general" -> {
                capacityArray[0][roomNum][slotNum] = 0;
            }
            case "meeting" -> {
                capacityArray[1][roomNum][slotNum] = 0;
            }
            case "teaching" -> {
                capacityArray[2][roomNum][slotNum] = 0;
            }
        }
        update2();
    }

    public static void update2() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath2))) {
            String[] prefixes = {"G", "M", "T"};

            for (int row = 0; row < capacityArray.length; row++) {
                for (int col = 0; (col < capacityArray[row].length && row == 0) || (col < capacityArray[row].length - 1); col++) {
                    StringBuilder line = new StringBuilder();
                    line.append(prefixes[row]).append(col + 1);

                    for (int i = 0; i < capacityArray[row][col].length; i++) {
                        line.append(",").append(capacityArray[row][col][i]);
                    }
                    bw.write(line.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
