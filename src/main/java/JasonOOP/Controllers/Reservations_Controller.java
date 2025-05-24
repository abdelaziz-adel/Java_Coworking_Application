package JasonOOP.Controllers;

import JasonOOP.DataBase.log;
import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.General_Room;
import JasonOOP.Rooms.Meeting_Room;
import JasonOOP.Rooms.ROOM;
import JasonOOP.Rooms.Teaching_Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.zone.ZoneRulesProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reservations_Controller implements Initializable {
    @FXML
    public AnchorPane reservationsPane;
    @FXML
    public Label total, empty, alertTXT, alertRealTXT;
    @FXML
    public VBox labelContainer;
    @FXML
    public Button cancelID;
    @FXML
    private Pane totalPane, overlay, overlay2;

    public ArrayList<String> SlotIDs = new ArrayList<>();

    @FXML
    void backToHello() throws IOException {
        switch (userSetting.selected_type) {
            case "General" -> new SceneSwitch(reservationsPane, "/com/example/gui/General.fxml");
            case "Formal" -> new SceneSwitch(reservationsPane, "/com/example/gui/Formal.fxml");
            case "Instructor" -> new SceneSwitch(reservationsPane, "/com/example/gui/Instructor.fxml");
        }
    }

    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalPane.setVisible(false);
        for (String line : log.data) {
            if (line.contains("Slot") && line.contains(userSetting.selected_id)) {
                count++;
                empty.setText("");
                totalPane.setVisible(true);
                Pattern pattern = Pattern.compile("Slot #(\\w+)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                String slotId = matcher.group(1);
                SlotIDs.add(slotId);
                if (slotId.charAt(0) == 'G') {
                    addNewLabel(count + "- General Room " + slotId.substring(1, 4) + " ,Slot " + slotId.substring(4, 6) + " at " + slotId.substring(6, 8) + "/" + slotId.substring(8));
                } else if (slotId.charAt(0) == 'M') {
                    addNewLabel(count + "- Meeting Room " + slotId.substring(1, 4) + " ,Slot " + slotId.substring(4, 6) + " at " + slotId.substring(6, 8) + "/" + slotId.substring(8));
                } else if (slotId.charAt(0) == 'T') {
                    addNewLabel(count + "- Teaching Room " + slotId.substring(1, 4) + " ,Slot " + slotId.substring(4, 6) + " at " + slotId.substring(6, 8) + "/" + slotId.substring(8));
                }
            }
        }
        if (count > slots.selected_user_reservations && slots.selected_user_back_from_update) {
            alertTXT.setText(slots.selected_user_reservations_slot_id);
            try {
                cancelAlert();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            slots.selected_user_back_from_update = false;
            slots.selected_user_reservations = count;
        } else if (slots.selected_user_back_from_update) {
            slots.selected_user_back_from_update = false;
        }
        if (empty.getText().isEmpty()) {
            cancelID.setDisable(false);
            cancelID.setStyle("-fx-padding: 7px 13px;");
        } else {
            cancelID.setDisable(true);
            cancelID.setStyle("-fx-opacity: 0.3; -fx-padding: 7px 13px;");
        }
        total.setText(total.getText() + count);
    }

    public void addNewLabel(String labelText) {
        Label newLabel = new Label("  " + labelText);
        newLabel.setStyle("-fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.35), transparent); -fx-font-size: 17");
        newLabel.setPrefWidth(970);
        newLabel.setPrefHeight(49);
        newLabel.setOnMouseClicked(mouseEvent -> {
            overlay.setVisible(true);
            alertTXT.setText(labelText);
            alertRealTXT.setText(labelText.split("- ")[1]);
        });
        Pane newPane = new Pane();
        newPane.setPrefWidth(970);
        newPane.setPrefHeight(49);
        newPane.getChildren().add(newLabel);
        labelContainer.getChildren().add(newPane);
        newPane = new Pane();
        newPane.setPrefHeight(3);
        labelContainer.getChildren().add(newPane);
    }

    public void cancel() {
        overlay2.setVisible(true);
    }

    public void cancelAlert() throws IOException {
        if (count == 1) {
            cancel();
            overlay.setVisible(false);
        } else {
            String selectedSlotId = SlotIDs.get(Integer.parseInt(alertTXT.getText().split("-")[0]) - 1);
            slots.cancelSlot(selectedSlotId);
            if (!slots.selected_user_back_from_update) {
                userSetting.addPenalty();
            }
            Iterator<String> iterator = log.data.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                if (str.contains("Slot") && str.contains(userSetting.selected_username) && str.contains(selectedSlotId)) {
                    iterator.remove();
                    break;
                }
            }
            userSetting.update();
            log.update();
            slots.update();
            overlay.setVisible(false);
            new SceneSwitch(reservationsPane, "/com/example/gui/Reservations.fxml");
        }
    }

    public void closeAlert() {
        overlay.setVisible(false);
        overlay2.setVisible(false);
    }

    public void updateAlert() throws IOException {
        slots.selected_user_reservations = count;
        slots.selected_user_reservations_slot_id = alertTXT.getText();

        String room = alertTXT.getText().split("- ")[1];
        Pattern pattern = Pattern.compile("Room\\s(\\d+)");
        Matcher matcher = pattern.matcher(room);
        String roomNumber = "";
        if (matcher.find()) {
            roomNumber = matcher.group(1);
        }
        room = room.charAt(0) + roomNumber;
        switch (room) {
            case "G100" -> switchRoom(new General_Room(General_Room.Room_1));
            case "G101" -> switchRoom(new General_Room(General_Room.Room_2));
            case "G102" -> switchRoom(new General_Room(General_Room.Room_3));
            case "M200" -> switchRoom(new Meeting_Room(Meeting_Room.Room_1));
            case "M201" -> switchRoom(new Meeting_Room(Meeting_Room.Room_2));
            case "T200" -> switchRoom(new Teaching_Room(Teaching_Room.Room_1));
            case "T201" -> switchRoom(new Teaching_Room(Teaching_Room.Room_2));
        }
    }

    private void switchRoom(ROOM roomInstance) throws IOException {
        ROOM.Room = roomInstance;
        slots.selected_user_on_reservations = true;
        slots.selected_user_back_from_update = true;
        new SceneSwitch(reservationsPane, "/com/example/gui/Slots.fxml");
    }

    public void updateAlert2() {
        for (String str : SlotIDs) {
            slots.cancelSlot(str);
        }
        userSetting.addPenalty();
        totalPane.setVisible(false);
        empty.setText("Empty");
        log.data.removeIf(str -> str.contains("Slot") && str.contains(userSetting.selected_id));
        labelContainer.getChildren().clear();
        userSetting.update();
        log.update();
        slots.update();
        cancelID.setDisable(true);
        cancelID.setStyle("-fx-opacity: 0.3; -fx-padding: 7px 13px;");
        overlay2.setVisible(false);
    }
}
