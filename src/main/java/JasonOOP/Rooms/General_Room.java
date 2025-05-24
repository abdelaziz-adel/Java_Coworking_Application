package JasonOOP.Rooms;

import JasonOOP.DataBase.slots;

public class General_Room extends ROOM {

    public static General_Room Room_1 = new General_Room(100, "Room 1", 4);
    public static General_Room Room_2 = new General_Room(101, "Room 2", 5);
    public static General_Room Room_3 = new General_Room(102, "Room 3", 3);

    public static int number_of_visitors = 0;

    public General_Room(General_Room room) {
        super(room.getRoom_ID(), room.getRoom_Name(), room.rating);
        setCapacity(20);
        for (int i = 0; i < super.Room_slots.length; i++) {
            super.Room_slots[i] = new slots("General");
        }
    }

    public General_Room(int Room_ID, String Room_Name, int rating) {
        super(Room_ID, Room_Name, rating);
        setCapacity(20);
        for (int i = 0; i < super.Room_slots.length; i++) {
            super.Room_slots[i] = new slots("General");
        }
    }

    public static double totalRoomPrice() {
        return General_Room.number_of_visitors * slots.SLOT_getFees();
    }

}
