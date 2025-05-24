package JasonOOP.Controllers;

import JasonOOP.DataBase.userSetting;
import JasonOOP.Functionality.LiveHourCounter;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.General_Room;
import JasonOOP.Rooms.Meeting_Room;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Room_Controller implements Initializable {

    @FXML
    public AnchorPane GeneralRoom1_Pane;
    @FXML
    public Label Room1ID, label01, usernameLabel, dateLabel, timeLabel, liveTimeLabel, liveLabel, slotLabel, ratingLabel, seconds, Label1, Label2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label01.setText("VIP Room");

        Room1ID.setText("ID: #VIP   Slots: ∞");
        usernameLabel.setText("Name: " + userSetting.selected_username + " #" + userSetting.selected_id);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateLabel.setText("Date: " + today.format(formatter));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("h:mm a");
        timeLabel.setText("Join time: " + now.format(formatter2));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0, 0, 0, 0.4));
        dropShadow.setRadius(8);
        usernameLabel.setEffect(dropShadow);
        dateLabel.setEffect(dropShadow);
        liveTimeLabel.setEffect(dropShadow);
        liveLabel.setEffect(dropShadow);

        slotLabel.setText("There is ∞ Slot Left");

        ratingLabel.setText("Rating: " + ROOM.generateStarRating(5));

        LiveHourCounter clock = new LiveHourCounter();
        clock.startTimer(liveTimeLabel, seconds);

    }

    public void backToGeneral() throws IOException {
        switch (userSetting.selected_type) {
            case "General" -> new SceneSwitch(GeneralRoom1_Pane, "/com/example/gui/General.fxml");
            case "Formal" -> new SceneSwitch(GeneralRoom1_Pane, "/com/example/gui/Formal.fxml");
            case "Instructor" -> new SceneSwitch(GeneralRoom1_Pane, "/com/example/gui/Instructor.fxml");
        }
    }

}
