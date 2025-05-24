package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.DataBase.userSetting;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccount_Controller {

    @FXML
    private AnchorPane CreateAccount_Pane;
    @FXML
    private ComboBox<String> typeCreateID;
    @FXML
    private TextField usernameCreateID;
    @FXML
    private PasswordField passwordCreateID;
    @FXML
    private Label errorCreate;

    @FXML
    void backToHello() throws IOException {
        errorCreate.setText("");
        new SceneSwitch(CreateAccount_Pane, "/com/example/gui/Login.fxml");
    }

    public void switchToScreen1() throws IOException {
        String type = typeCreateID.getValue();
        String username = usernameCreateID.getText();
        String password = passwordCreateID.getText();
        if (userSetting.search(username, password)) {
            errorCreate.setText("*The userdata you entered already exists*");
        } else if (!type.isEmpty() && username.length() > 3 && password.length() > 7) {
            errorCreate.setText("");
            userSetting.add(username, password, type, 1.0);
            userSetting.update();
            userSetting.search(username, password);
            log.add("signed up",userSetting.selected_username,userSetting.selected_id);
            switch (userSetting.selected_type) {
                case "Instructor" -> new SceneSwitch(CreateAccount_Pane, "/com/example/gui/Instructor.fxml");
                case "Formal" -> new SceneSwitch(CreateAccount_Pane, "/com/example/gui/Formal.fxml");
                case "General" -> new SceneSwitch(CreateAccount_Pane, "/com/example/gui/General.fxml");
            }
        } else if (username.length() <= 3 && password.length() <= 7) {
            errorCreate.setText("*You can't use this  username & password*");
        } else if (username.length() <= 3) {
            errorCreate.setText("*The username  you chose is  unavailable*");
        } else if (password.length() <= 7) {
            errorCreate.setText("*The password  you chose is  unavailable*");
        }

    }
}
