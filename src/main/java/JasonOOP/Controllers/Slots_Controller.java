package JasonOOP.Controllers;

import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Slots_Controller implements Initializable {
    @FXML
    public Pane pane01, pane02, pane03, pane04, pane05, pane06, pane07, pane08, pane09, pane10, pane11, pane12;
    @FXML
    public AnchorPane Slots_Pane;
    @FXML
    public Label AvSlots;

    public static String firstTime;
    public static String secondTime;
    public static String selectedSlot;

    private Pane[] panes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new Pane[]{pane01, pane02, pane03, pane04, pane05, pane06, pane07, pane08, pane09, pane10, pane11, pane12};

        updatePanesBasedOnRoomType();

        AvSlots.setText(AvSlots.getText() + ROOM.Room.getRoom_Name());
        applyHoverEffectToPanes();
    }

    private void updatePanesBasedOnRoomType() {
        int roomIndex = Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1;
        int typeIndex = getTypeIndex(userSetting.selected_type);

        if (typeIndex >= 0) {
            for (int i = 0; i < 12; i++) {
                if (slots.capacityArray[typeIndex][roomIndex][i] > 0) {
                    panes[i].setDisable(false);
                } else {
                    panes[i].setDisable(true);
                }
            }
        }
    }

    private int getTypeIndex(String selectedType) {
        switch (selectedType) {
            case "General":
                return 0;
            case "Formal":
                return 1;
            case "Instructor":
                return 2;
            default:
                return -1;
        }
    }

    private void applyHoverEffectToPanes() {
        for (Pane pane : panes) {
            ROOM.applyHoverEffect(pane, 1.02);
        }
    }

    public void goToCheck(MouseEvent event) throws IOException {
        Label checked_label = (Label) event.getSource();
        selectedSlot = checked_label.getId().replaceAll("\\D+", "");
        String[] parts = checked_label.getText().split(" : ");
        firstTime = parts[0];
        secondTime = parts[1];
        new SceneSwitch(Slots_Pane, "/com/example/gui/SlotsDate.fxml");
    }

    public void backToHello() throws IOException {
        if (slots.selected_user_on_reservations) {
            new SceneSwitch(Slots_Pane, "/com/example/gui/Reservations.fxml");
            slots.selected_user_on_reservations = false;
        } else {
            if (ROOM.Room.getRoom_ID() < 200) {
                new SceneSwitch(Slots_Pane, "/com/example/gui/General.fxml");
            } else if (ROOM.Room.getRoom_ID() < 300) {
                new SceneSwitch(Slots_Pane, "/com/example/gui/Formal.fxml");
            } else if (ROOM.Room.getRoom_ID() < 400) {
                new SceneSwitch(Slots_Pane, "/com/example/gui/Instructor.fxml");
            }
        }
    }
}
