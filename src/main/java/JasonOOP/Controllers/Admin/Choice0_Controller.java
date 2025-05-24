package JasonOOP.Controllers.Admin;

import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Choice0_Controller implements Initializable {

    @FXML
    public Label total, data0, data1, data2, data3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        total.setText(total.getText() + String.valueOf(users.getMaxId() - 2023169999));
        String output0 = "", output1 = "", output2 = "", output3 = "";
        for (users person : userSetting.people) {
            output0 += person.getName() + "\n\n";
            output1 += person.getPassword() + "\n\n";
            output2 += person.getId() + "\n\n";
            output3 += person.getType() + "\n\n";
        }
        data0.setText(output0);
        data1.setText(output1);
        data2.setText(output2);
        data3.setText(output3);
    }

}
