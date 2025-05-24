package JasonOOP.Rooms;

import JasonOOP.DataBase.slots;


public class Teaching_Room extends ROOM {

    public static final Teaching_Room Room_1 = new Teaching_Room(300, "Room 1", "4K", "LED", 4);

    public static final Teaching_Room Room_2 = new Teaching_Room(301, "Room 2", "HD", "LCD", 2);
    public static int number_of_visitors = 0;
    private String Projector_type;
    private String Board_type;

    public Teaching_Room(int Room_ID, String Room_Name, String projector_type, String board_type, int rating) {
        super(Room_ID, Room_Name, rating);
        this.Projector_type = projector_type;
        this.Board_type = board_type;
        setCapacity(10);
        for (int i = 0; i < super.Room_slots.length; i++) {
            super.Room_slots[i] = new slots("Teaching");
        }
    }

    public Teaching_Room(int Room_ID, String Room_Name, int rating) {
        super(Room_ID, Room_Name, rating);
        setCapacity(10);
        for (int i = 0; i < super.Room_slots.length; i++) {
            super.Room_slots[i] = new slots("Teaching");
        }
    }

    public Teaching_Room(Teaching_Room room) {
        super(room.getRoom_ID(), room.getRoom_Name(), room.rating);
        setCapacity(10);
        this.Projector_type = room.Projector_type;
        this.Board_type = room.Board_type;
        for (int i = 0; i < super.Room_slots.length; i++) {
            super.Room_slots[i] = new slots("Teaching");
        }
    }

    public void setProjector_type(String projector_type) {
        Projector_type = projector_type;
    }

    public void setBoard_type(String board_type) {
        Board_type = board_type;
    }

    public String getProjector_type() {
        return Projector_type;
    }

    public String getBoard_type() {
        return Board_type;
    }

    public  static double totalRoomPrice(){
        return Teaching_Room.number_of_visitors * slots.SLOT_getFees();
    }
}
