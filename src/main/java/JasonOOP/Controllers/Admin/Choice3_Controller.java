package JasonOOP.Controllers.Admin;

import JasonOOP.DataBase.log;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Rooms.General_Room;
import JasonOOP.Rooms.Meeting_Room;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ResourceBundle;

public class Choice3_Controller implements Initializable {

    @FXML
    public Label data0, data1, data2, data3, data4, data5, total, total1, total2, total3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String output0 = "", output1 = "", output2 = "", output3 = "",
                output4 = "", output5 = "", output6 = "", output7 = "", output8 = "";
        for (int i = 1; i <= 3; i++) {
            output0 += "Room " + String.valueOf(i) + "\n\n";
        }
        for (int i = 1; i <= 2; i++) {
            output2 += "Room " + String.valueOf(i) + "\n\n";
            output4 += "Room " + String.valueOf(i) + "\n\n";
        }
        output6 += String.valueOf(General_Room.totalRoomPrice()) + " $ ";
        output7 += String.valueOf(Meeting_Room.totalRoomPrice()) + " $ ";
        output8 += String.valueOf(Teaching_Room.totalRoomPrice()) + " $ ";

        double Groom[] = new double[3];
        double Mroom[] = new double[2];
        double Troom[] = new double[2];
        for (AbstractMap.SimpleEntry<String, Integer> entry : ROOM.slotIDs) {
            if (entry.getKey().charAt(0) == 'G') {
                if (entry.getKey().startsWith("100", 1)) Groom[0] += entry.getValue();
                else if (entry.getKey().startsWith("101", 1)) Groom[1] += entry.getValue();
                else if (entry.getKey().startsWith("102", 1)) Groom[2] += entry.getValue();
            } else if (entry.getKey().charAt(0) == 'M') {
                if (entry.getKey().startsWith("200", 1)) Mroom[0] += entry.getValue();
                else if (entry.getKey().startsWith("201", 1)) Mroom[1] += entry.getValue();
            } else if (entry.getKey().charAt(0) == 'T') {
                if (entry.getKey().startsWith("300", 1)) Troom[0] += entry.getValue();
                else if (entry.getKey().startsWith("301", 1)) Troom[1] += entry.getValue();
            }
        }

        for (double i : Groom) {
            output1 += String.valueOf(i * 20) + " $" + "\n\n";
        }
        for (double i : Mroom) {
            output3 += String.valueOf(i * 20) + " $" + "\n\n";
        }
        for (double i : Troom) {
            output5 += String.valueOf(i * 20) + " $" + "\n\n";
        }
        data0.setText(output0);
        data1.setText(output1);
        data2.setText(output2);
        data3.setText(output3);
        data4.setText(output4);
        data5.setText(output5);
        total1.setText(output6);
        total2.setText(output7);
        total3.setText(output8);
        total.setText(total.getText() + String.valueOf(General_Room.totalRoomPrice() + Meeting_Room.totalRoomPrice() + Teaching_Room.totalRoomPrice()) + " $ ");
    }
}