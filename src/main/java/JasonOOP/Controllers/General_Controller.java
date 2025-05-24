package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Rooms.General_Room;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.ResourceBundle;

public class General_Controller implements Initializable {

    @FXML
    public Label Generaltitle, Room1NameID, Room3NameID, Room1ID, Room3ID, Room1Stars, Room3Stars, Room2NameID, Room2ID, Room2Stars, alertTXT;
    @FXML
    public ImageView Groom01, Groom02, Groom03;
    @FXML
    public Button join1, join2, join3, Reward;
    @FXML
    public Pane PaneG0, PaneG1, PaneG2, overlay;
    @FXML
    private AnchorPane General_Pane;

    private boolean found = false;
    private Pane panes[];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new Pane[]{PaneG0,PaneG1,PaneG2};
        for(int i = 0; i< welcome_Controller.G_ON.length;i++){
            if(!welcome_Controller.G_ON[i]){
                panes[i].setDisable(true);
            }
        }

        alertTXT.setText(alertTXT.getText() + userSetting.selected_username + "  \uD83C\uDF89");
        Reward.setDisable(true);
        Reward.setStyle("-fx-opacity: 0.3; -fx-padding: 7px 13px;");

        Room1ID.setText("ID: #" + String.valueOf(General_Room.Room_1.getRoom_ID()));
        Room2ID.setText("ID: #" + String.valueOf(General_Room.Room_2.getRoom_ID()));
        Room3ID.setText("ID: #" + String.valueOf(General_Room.Room_3.getRoom_ID()));

        Room1NameID.setText(General_Room.Room_1.getRoom_Name());
        Room2NameID.setText(General_Room.Room_2.getRoom_Name());
        Room3NameID.setText(General_Room.Room_3.getRoom_Name());

        ROOM.applyHoverEffect(PaneG0, 1.009);
        ROOM.applyHoverEffect(PaneG1, 1.009);
        ROOM.applyHoverEffect(PaneG2, 1.009);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0, 0, 0, 0.17));
        dropShadow.setRadius(8);
        Generaltitle.setEffect(dropShadow);
        Room1NameID.setEffect(dropShadow);
        Room2NameID.setEffect(dropShadow);
        Room3NameID.setEffect(dropShadow);

        Groom01.setImage(new Image("file:assets/GeneralRoom1.png"));
        Groom02.setImage(new Image("file:assets/GeneralRoom2.png"));
        Groom03.setImage(new Image("file:assets/GeneralRoom3.png"));

        Room1Stars.setText(ROOM.generateStarRating(General_Room.Room_1.rating));
        Room2Stars.setText(ROOM.generateStarRating(General_Room.Room_2.rating));
        Room3Stars.setText(ROOM.generateStarRating(General_Room.Room_3.rating));

        for (String str : log.data) {
            if (str.contains("reward") && str.contains(userSetting.selected_id)) {
                found = true;
                String dateStr = str.substring(str.indexOf("on") + 3);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                LocalDate givenDate = LocalDate.parse(dateStr, formatter);
                LocalDate currentDate = LocalDate.now();
                long daysDifference = ChronoUnit.DAYS.between(givenDate, currentDate);
                System.out.println("Days difference: " + daysDifference);
                if (daysDifference >= 7) {
                    Reward.setDisable(false);
                    Reward.setStyle("-fx-padding: 7px 13px;");
                    break;
                }
            }
        }
        if (!found) {
            Reward.setDisable(false);
            Reward.setStyle("-fx-padding: 7px 13px;");
        }
    }

    public void closeAlert() {
        overlay.setVisible(false);
    }

    @FXML
    void backToHello() throws IOException {
        new SceneSwitch(General_Pane, "/com/example/gui/Welcome.fxml");
    }

    public void GoToRoom1() throws IOException {
        ROOM.Room = new General_Room(General_Room.Room_1);
        new SceneSwitch(General_Pane, "/com/example/gui/Slots.fxml");
    }

    public void GoToRoom2() throws IOException {
        ROOM.Room = new General_Room(General_Room.Room_2);
        new SceneSwitch(General_Pane, "/com/example/gui/Slots.fxml");
    }

    public void GoToRoom3() throws IOException {
        ROOM.Room = new General_Room(General_Room.Room_3);
        new SceneSwitch(General_Pane, "/com/example/gui/Slots.fxml");
    }

    public void goToReservations() throws IOException {
        new SceneSwitch(General_Pane, "/com/example/gui/Reservations.fxml");
    }

    public void goToNote() throws IOException {
        new SceneSwitch(General_Pane, "/com/example/gui/VIPnote.fxml");
    }

    public void goToUpdateInfo() throws IOException {
        new SceneSwitch(General_Pane, "/com/example/gui/UpdateInfo.fxml");
    }

    public void goToReward() {
        overlay.setVisible(true);
        if (found) {
            log.data.removeIf(line -> line.contains(userSetting.selected_id) && line.contains("got his reward"));
            log.update();
        }
        log.add("got his reward", userSetting.selected_username, userSetting.selected_id);
        for (users person : userSetting.people) {
            if (person.getId() == Integer.parseInt(userSetting.selected_id)) {
                person.addFreeHours();
                userSetting.update();
            }
        }
        Reward.setDisable(true);
        Reward.setStyle("-fx-opacity: 0.3; -fx-padding: 7px 13px;");
    }
}
