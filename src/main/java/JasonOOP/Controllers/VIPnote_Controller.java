package JasonOOP.Controllers;

import JasonOOP.DataBase.userSetting;
import JasonOOP.Functionality.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VIPnote_Controller {
    @FXML
    public AnchorPane VIP_Pane;

    @FXML
    void backToHello() throws IOException {
        switch (userSetting.selected_type) {
            case "General" -> new SceneSwitch(VIP_Pane, "/com/example/gui/General.fxml");
            case "Formal" -> new SceneSwitch(VIP_Pane, "/com/example/gui/Formal.fxml");
            case "Instructor" -> new SceneSwitch(VIP_Pane, "/com/example/gui/Instructor.fxml");
        }
    }

    public void Join() throws IOException {
        new SceneSwitch(VIP_Pane, "/com/example/gui/Room.fxml");
    }
}
