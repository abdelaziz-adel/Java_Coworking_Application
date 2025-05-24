package JasonOOP.Controllers;

import JasonOOP.Main;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectColor_Controller implements Initializable {
    @FXML
    private AnchorPane SelectColor_Pane;
    @FXML
    private ImageView Id0I, Id1I, Id2I, Id3I;
    @FXML
    private StackPane stackPane0, stackPane1, stackPane2, stackPane3;

    public static File file = new File("assets/MainBackground2.mp4");
    private File file2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file2 = file;
        checkUpdate();

        Id0I.setImage(new Image("file:assets/0.png"));
        Id1I.setImage(new Image("file:assets/1.png"));
        Id2I.setImage(new Image("file:assets/2.png"));
        Id3I.setImage(new Image("file:assets/3.png"));

        ROOM.applyHoverEffect(stackPane0,1.009);
        ROOM.applyHoverEffect(stackPane1,1.009);
        ROOM.applyHoverEffect(stackPane2,1.009);
        ROOM.applyHoverEffect(stackPane3,1.009);
    }


    public void backToWelcome() throws IOException {
        file = file2;
        new SceneSwitch(SelectColor_Pane, "/com/example/gui/Welcome.fxml");
    }

    private void checkUpdate() {
        if (file.toString().equals("assets\\MainBackground.mp4")) {
            stackPane0.setStyle("-fx-border-width: 0px;");
            stackPane1.setStyle("-fx-border-width: 2px;");
            stackPane2.setStyle("-fx-border-width: 0px;");
            stackPane3.setStyle("-fx-border-width: 0px;");
        } else if (file.toString().equals("assets\\MainBackground1.mp4")) {
            stackPane0.setStyle("-fx-border-width: 0px;");
            stackPane1.setStyle("-fx-border-width: 0px;");
            stackPane2.setStyle("-fx-border-width: 0px;");
            stackPane3.setStyle("-fx-border-width: 2px;");
        } else if (file.toString().equals("assets\\MainBackground2.mp4")) {
            stackPane0.setStyle("-fx-border-width: 2px;");
            stackPane1.setStyle("-fx-border-width: 0px;");
            stackPane2.setStyle("-fx-border-width: 0px;");
            stackPane3.setStyle("-fx-border-width: 0px;");
        } else if (file.toString().equals("assets\\MainBackground3.mp4")) {
            stackPane0.setStyle("-fx-border-width: 0px;");
            stackPane1.setStyle("-fx-border-width: 0px;");
            stackPane2.setStyle("-fx-border-width: 2px;");
            stackPane3.setStyle("-fx-border-width: 0px;");
        }
    }

    public void photo0isClicked() {
        file = new File("assets/MainBackground2.mp4");
        checkUpdate();
    }

    public void photo1isClicked() {
        file = new File("assets/MainBackground.mp4");
        checkUpdate();
    }

    public void photo2isClicked() {
        file = new File("assets/MainBackground3.mp4");
        checkUpdate();
    }

    public void photo3isClicked() {
        file = new File("assets/MainBackground1.mp4");
        checkUpdate();
    }

    public void backToWelcomeDone(ActionEvent actionEvent) throws IOException {
        Main.no_repeat = true;
        Main.change();
        new SceneSwitch(SelectColor_Pane, "/com/example/gui/Welcome.fxml");
    }
}
