package JasonOOP.Controllers;

import JasonOOP.Main;
import JasonOOP.Functionality.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class welcome_Controller implements Initializable {
    public static boolean G_ON[] = new boolean[3];
    public static boolean M_ON[] = new boolean[2];
    public static boolean T_ON[] = new boolean[2];

    @FXML
    public Label label02, label01;
    @FXML
    private AnchorPane WelcomeAnchorPane;
    @FXML
    private MediaView mainbg;

    private File file;
    public static Media media;
    public static MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        try {
//            new SceneSwitch(WelcomeAnchorPane, "/com/example/gui/Admin/Admin.fxml");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        if (mediaPlayer == null) {
            file = SelectColor_Controller.file;
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mainbg.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
            mediaPlayer.play();
            Main.no_repeat = false;
        }
        if (Main.no_repeat) {
            file = SelectColor_Controller.file;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            media = null;
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mainbg.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
            mediaPlayer.play();
            Main.no_repeat = false;
        }
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0,0,0,0.14));
        dropShadow.setRadius(8);
        label01.setEffect(dropShadow);
        label02.setEffect(dropShadow);
    }


    public void goToLogin() throws IOException {
        new SceneSwitch(WelcomeAnchorPane, "/com/example/gui/Login.fxml");
    }

    public void goToSelectColor() throws IOException {
        new SceneSwitch(WelcomeAnchorPane, "/com/example/gui/SelectColor.fxml");
    }

    public void goToLeaderboard() throws IOException {
        new SceneSwitch(WelcomeAnchorPane, "/com/example/gui/Leaderboard.fxml");
    }
}
