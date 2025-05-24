package JasonOOP.Controllers.Admin;

import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Rooms.General_Room;
import JasonOOP.Rooms.Meeting_Room;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Choice1_Controller implements Initializable {

    @FXML
    public Label data0, data1, data2, data3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String output0 = "", output1 = "", output2 = "", output3 = "";

        //General
        output0 += "General " + General_Room.Room_1.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(General_Room.Room_1.rating) + "\n\n";
        output2 += General_Room.Room_1.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('G',String.valueOf(General_Room.Room_1.getRoom_ID())) + "\n\n";

        output0 += "General " + General_Room.Room_2.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(General_Room.Room_2.rating) + "\n\n";
        output2 += General_Room.Room_2.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('G',String.valueOf(General_Room.Room_2.getRoom_ID())) + "\n\n";

        output0 += "General " + General_Room.Room_3.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(General_Room.Room_3.rating) + "\n\n";
        output2 += General_Room.Room_3.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('G',String.valueOf(General_Room.Room_3.getRoom_ID())) + "\n\n";

        //Meeting
        output0 += "Meeting " + Meeting_Room.Room_1.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(Meeting_Room.Room_1.rating) + "\n\n";
        output2 += Meeting_Room.Room_1.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('M',String.valueOf(Meeting_Room.Room_1.getRoom_ID())) + "\n\n";

        output0 += "Meeting " + Meeting_Room.Room_2.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(Meeting_Room.Room_2.rating) + "\n\n";
        output2 += Meeting_Room.Room_2.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('M',String.valueOf(Meeting_Room.Room_2.getRoom_ID())) + "\n\n";

        //Teaching
        output0 += "Teaching " + Teaching_Room.Room_1.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(Teaching_Room.Room_1.rating) + "\n\n";
        output2 += Teaching_Room.Room_1.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('T',String.valueOf(Teaching_Room.Room_1.getRoom_ID())) + "\n\n";

        output0 += "Teaching " + Teaching_Room.Room_2.getRoom_Name() + "\n\n";
        output1 += ROOM.generateStarRating(Teaching_Room.Room_2.rating) + "\n\n";
        output2 += Teaching_Room.Room_2.getRoom_ID() + "\n\n";
        output3 += slots.getRoomVisNumber('T',String.valueOf(Teaching_Room.Room_2.getRoom_ID())) + "\n\n";

        data0.setText(output0);
        data1.setText(output1);
        data2.setText(output2);
        data3.setText(output3);
    }

}
