package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.DataBase.userSetting;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login_Controller {

    @FXML
    private AnchorPane HelloScenePane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorPU;

    public void switchToScreen1() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            errorPU.setText("");
            new SceneSwitch(HelloScenePane, "/com/example/gui/Admin/Admin.fxml");
        } else if (userSetting.search(username, password)) {
            errorPU.setText("");
            log.add("logged in",userSetting.selected_username,userSetting.selected_id);
            switch (userSetting.selected_type) {
                case "Instructor" -> new SceneSwitch(HelloScenePane, "/com/example/gui/Instructor.fxml");
                case "Formal" -> new SceneSwitch(HelloScenePane, "/com/example/gui/Formal.fxml");
                case "General" -> new SceneSwitch(HelloScenePane, "/com/example/gui/General.fxml");
            }
        } else {
            passwordField.clear();
            errorPU.setText("*Wrong username or password*");
        }
    }

    public void handleLabelClick(MouseEvent mouseEvent) throws IOException {
        new SceneSwitch(HelloScenePane, "/com/example/gui/CreateAccount.fxml");
    }

    public void backToWelcome() throws IOException {
        new SceneSwitch(HelloScenePane, "/com/example/gui/Welcome.fxml");
    }
}
