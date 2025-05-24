package JasonOOP.Rooms;

import JasonOOP.DataBase.slots;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public abstract class ROOM {
    public static ROOM Room;

    //variables
    private int Room_Capacity;

    private int Room_ID;

    public double roomTotPrice=0;

    private String Room_Name;

    public slots[] Room_slots = new slots[12];

    public int rating;

    public static ArrayList <AbstractMap.SimpleEntry<String,Integer>> slotIDs = new ArrayList<>();

    //constructors
    public ROOM(int Room_ID, String Room_Name, int rating) {
        this.Room_ID = Room_ID;
        this.Room_Name = Room_Name;
        this.rating = rating;
    }

    //geters & seters

    public static int convertTimeToSeconds(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(timeString, formatter);

        int totalSeconds = time.toSecondOfDay();
        return totalSeconds;
    }



    public void setCapacity(int Room_capacity) {
        Room_Capacity = Room_capacity;
    }

    public void setID(int Room_ID) {
        this.Room_ID = Room_ID;
    }

    public void setRoom_Name(String room_Name) {
        Room_Name = room_Name;
    }

    public int getCapacity() {
        return Room_Capacity;
    }


    public String getRoom_Name() {
        return Room_Name;
    }
    public int getRoom_ID() {
        return Room_ID;
    }



    //functions
    /// to add a slot to array of slots
    public void addSlot(slots slot) {
        for (int i = 0; i < Room_slots.length; i++) {
            if (Room_slots[i] == null) {
                Room_slots[i] = slot;
                System.out.println("Slot added successfully.");
                return;
            }
        }
        System.out.println("No space for new Slots.");
    }


    /// to display all data about rooms
    public void displayRoomData() {
        System.out.println("General Room: " + Room_Name);
        System.out.println("Room ID: " + Room_ID);
        System.out.println("Capacity: " + Room_Capacity);
    }

    public static String generateStarRating(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }

    public static void applyHoverEffect(Pane pane,double num) {
        pane.setOnMouseEntered(event -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), pane);
            scaleUp.setToX(num);
            scaleUp.setToY(num);
            scaleUp.play();
        });

        pane.setOnMouseExited(event -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), pane);
            scaleDown.setToX(1);
            scaleDown.setToY(1);
            scaleDown.play();
        });
    }

    public String checker(String id) {
        for (slots slot : Room_slots) {
            int Capacity = 0;
            for (AbstractMap.SimpleEntry<String, Integer> pair : ROOM.slotIDs) {
                if(id.charAt(0) == 'G'){
                    Capacity = slots.capacityArray[0][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", ""))-1][Integer.parseInt(id.substring(4,6))-1];
                } else if(id.charAt(0) == 'M'){
                    Capacity = slots.capacityArray[1][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", ""))-1][Integer.parseInt(id.substring(4,6))-1];
                }else if(id.charAt(0) == 'T'){
                    Capacity = slots.capacityArray[2][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", ""))-1][Integer.parseInt(id.substring(4,6))-1];
                }
                if (pair.getKey().equals(id) && slot != null) {
                    if (pair.getValue() < Capacity) {
                        return "true";
                    } else {
                        return "full";
                    }
                }
            }
        }
        slots.add(id, 0);
        return "false";
    }

}