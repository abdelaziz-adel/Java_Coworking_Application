package JasonOOP.Controllers;

import JasonOOP.DataBase.userSetting;
import JasonOOP.Functionality.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UpdateInfo_Controller {

    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private AnchorPane updateInfoPane;

    @FXML
    public void initialize() {
        // Pre-fill the fields with the current user's info
        usernameField.setText(userSetting.selected_username);
        passwordField.setText(userSetting.selected_password);
    }

    @FXML
    public void saveUpdatedInfo() {
        String newUsername = usernameField.getText();
        String newPassword = passwordField.getText();

        // Update user data in the list
        for (var user : userSetting.people) {
            if (user.getName().equals(userSetting.selected_username) &&
                    user.getPassword().equals(userSetting.selected_password)) {
                userSetting.selected_username = newUsername;
                userSetting.selected_password = newPassword;

                userSetting.people.remove(user);
                userSetting.people.add(new JasonOOP.DataBase.users(newUsername, newPassword, user.getId(), user.getType(), user.getPenalty(), user.getFreeHours()));
                break;
            }
        }

        // Save updates to the file
        userSetting.update();
        System.out.println("User information updated successfully!");
    }

    @FXML
    void backToHello() throws IOException {
        switch (userSetting.selected_type) {
            case "General" -> new SceneSwitch(updateInfoPane, "/com/example/gui/General.fxml");
            case "Formal" -> new SceneSwitch(updateInfoPane, "/com/example/gui/Formal.fxml");
            case "Instructor" -> new SceneSwitch(updateInfoPane, "/com/example/gui/Instructor.fxml");
        }
    }

}
