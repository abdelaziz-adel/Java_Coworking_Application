package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Leaderboard_Controller implements Initializable {

    @FXML
    public Label total, data0, data1, data2;
    @FXML
    public AnchorPane LeaderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int total_number=0;
        String output0 = "", output1 = "", output2 = "";
        for (users person : userSetting.people) {
            int numOfHours = 0;
            for (String line : log.data) {
                if (line.contains("Slot") && line.contains(String.valueOf(person.getId()))) {
                    numOfHours++;
                }
            }
            if (numOfHours != 0) {
                total_number++;
                output0 += person.getName() + "\n\n";
                output1 += person.getId() + "\n\n";
                output2 += String.valueOf(numOfHours) + "\n\n";
            }
        }

        String[] idArray = output0.split("\\n\\n");
        String[] nameArray = output1.split("\\n\\n");
        String[] hoursArray = output2.split("\\n\\n");

        Integer[] indices = new Integer[hoursArray.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(Integer.parseInt(hoursArray[b]), Integer.parseInt(hoursArray[a])));
        StringBuilder sortedOutput0 = new StringBuilder();
        StringBuilder sortedOutput1 = new StringBuilder();
        StringBuilder sortedOutput2 = new StringBuilder();

        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            if (i > 0) {
                sortedOutput0.append("\n\n");
                sortedOutput1.append("\n\n");
                sortedOutput2.append("\n\n");
            }
            sortedOutput0.append(idArray[index]);
            sortedOutput1.append(nameArray[index]);
            sortedOutput2.append(hoursArray[index]);
        }
        total.setText(total.getText() + String.valueOf(total_number));
        data0.setText(sortedOutput0.toString());
        data1.setText(sortedOutput1.toString());
        data2.setText(sortedOutput2.toString());
    }


    public void backToWelcome() throws IOException {
        new SceneSwitch(LeaderPane, "/com/example/gui/Welcome.fxml");
    }
}