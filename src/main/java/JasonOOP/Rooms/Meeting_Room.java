package JasonOOP.Rooms;

import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;

import java.util.AbstractMap;


public class Meeting_Room extends ROOM {

    public static final Meeting_Room Room_1 = new Meeting_Room(200,"Room 1",5);
    public static final Meeting_Room Room_2 = new Meeting_Room(201,"Room 2",4);

    public static int number_of_visitors = 0;

    public Meeting_Room(int Room_ID,String Room_Name,int rating) {
        super(Room_ID,Room_Name,rating);
        setCapacity (10);
        for(int i = 0;i<super.Room_slots.length;i++){
            super.Room_slots[i] = new slots("Meeting");
        }
    }
    public Meeting_Room(Meeting_Room room) {
        super(room.getRoom_ID(),room.getRoom_Name(), room.rating);
        setCapacity (10);
        for(int i = 0;i<super.Room_slots.length;i++){
            super.Room_slots[i] = new slots("Meeting");
        }
    }

    public  static double totalRoomPrice(){
        return Meeting_Room.number_of_visitors * slots.SLOT_getFees();
    }

}
