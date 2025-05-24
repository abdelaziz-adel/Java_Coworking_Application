package JasonOOP.Controllers.Admin;

import JasonOOP.Rooms.ROOM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ResourceBundle;

public class Choice2_Controller implements Initializable {

    @FXML
    public Label total, data0, data1, data2, data3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String output0 = "", output1 = "", output2 = "", output3 = "";

        int Total = 0;
        for (AbstractMap.SimpleEntry<String, Integer> entry : ROOM.slotIDs) {
            String id = entry.getKey();
            int visitors = entry.getValue();

            if ((id.startsWith("G") && visitors < 20) || (!id.startsWith("G") && visitors < 10)) {
                Total++;
                output0 += id.substring(4, 6) + "\n\n";
                if (id.charAt(0) == 'G') {
                    output1 += "General#" + id.substring(1, 4) + "\n\n";
                } else if (id.charAt(0) == 'M') {
                    output1 += "Meeting#" + id.substring(1, 4) + "\n\n";
                } else if (id.charAt(0) == 'T') {
                    output1 += "Teaching#" + id.substring(1, 4) + "\n\n";
                }
                output2 += id + "\n\n";
                output3 += visitors + "\n\n";
            }
        }

        total.setText(total.getText() + String.valueOf(Total));
        data0.setText(output0);
        data1.setText(output1);
        data2.setText(output2);
        data3.setText(output3);
    }
}
