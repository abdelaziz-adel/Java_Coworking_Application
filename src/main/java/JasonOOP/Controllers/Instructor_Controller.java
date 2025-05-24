package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class Instructor_Controller implements Initializable {

    @FXML
    public Label Instructortitle, Room1NameID, Room1Type, Room2Type, Room1Type2, Room2Type2, Room1ID, Room1Stars, Room2NameID, Room2ID, Room2Stars, alertTXT;
    @FXML
    public ImageView Groom01, Groom02;
    @FXML
    public Button join1, join2, Reward;
    @FXML
    public Pane PaneG0, PaneG1, overlay;
    @FXML
    private AnchorPane Instructor_Pane;

    private boolean found = false;
    private Pane panes[];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new Pane[]{PaneG0,PaneG1};
        for(int i = 0; i< welcome_Controller.T_ON.length;i++){
            if(!welcome_Controller.T_ON[i]){
                panes[i].setDisable(true);
            }
        }

        Reward.setDisable(true);
        Reward.setStyle("-fx-opacity: 0.3; -fx-padding: 7px 13px;");

        Room1ID.setText("ID: #" + String.valueOf(Teaching_Room.Room_1.getRoom_ID()));
        Room2ID.setText("ID: #" + String.valueOf(Teaching_Room.Room_2.getRoom_ID()));

        Room1Type.setText("Projector: " + Teaching_Room.Room_1.getProjector_type());
        Room2Type.setText("Projector: " + Teaching_Room.Room_2.getProjector_type());
        Room1Type2.setText("Board: " + Teaching_Room.Room_1.getBoard_type());
        Room2Type2.setText("Board: " + Teaching_Room.Room_2.getBoard_type());

        Room1NameID.setText(Teaching_Room.Room_1.getRoom_Name());
        Room2NameID.setText(Teaching_Room.Room_2.getRoom_Name());

        ROOM.applyHoverEffect(PaneG0, 1.009);
        ROOM.applyHoverEffect(PaneG1, 1.009);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0, 0, 0, 0.17));
        dropShadow.setRadius(8);
        Instructortitle.setEffect(dropShadow);
        Room1NameID.setEffect(dropShadow);
        Room2NameID.setEffect(dropShadow);

        Groom01.setImage(new Image("file:assets/InstructorRoom1.png"));
        Groom02.setImage(new Image("file:assets/InstructorRoom2.png"));

        Room1Stars.setText(ROOM.generateStarRating(Teaching_Room.Room_1.rating));
        Room2Stars.setText(ROOM.generateStarRating(Teaching_Room.Room_2.rating));

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
        new SceneSwitch(Instructor_Pane, "/com/example/gui/Welcome.fxml");
    }

    public void GoToRoom1() throws IOException {
        Teaching_Room.Room = new Teaching_Room(Teaching_Room.Room_1);
        new SceneSwitch(Instructor_Pane, "/com/example/gui/Slots.fxml");
    }

    public void GoToRoom2() throws IOException {
        Teaching_Room.Room = new Teaching_Room(Teaching_Room.Room_2);
        new SceneSwitch(Instructor_Pane, "/com/example/gui/Slots.fxml");
    }

    public void goToReservations() throws IOException {
        new SceneSwitch(Instructor_Pane, "/com/example/gui/Reservations.fxml");
    }

    public void goToNote() throws IOException {
        new SceneSwitch(Instructor_Pane, "/com/example/gui/VIPnote.fxml");
    }

    public void goToUpdateInfo() throws IOException {
        new SceneSwitch(Instructor_Pane, "/com/example/gui/UpdateInfo.fxml");
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

