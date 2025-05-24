package JasonOOP.Controllers.Admin;

import JasonOOP.Controllers.welcome_Controller;
import JasonOOP.DataBase.log;
import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.Rooms.ROOM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Choice4_Controller implements Initializable {

    @FXML
    public TextField roomTypeField1;
    @FXML
    private TextField userIdField, roomTypeField, roomNumberField, slotNumberField;
    @FXML
    private Button deleteButton1, deleteButton2;
    @FXML
    private Label statusLabel1, statusLabel2, statusLabel21;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        deleteButton1.setOnAction(event -> deleteUser());
        deleteButton2.setOnAction(event -> deleteSlot());
    }

    private void deleteUser() {
        String idText = userIdField.getText();
        if (idText.isEmpty()) {
            statusLabel1.setText("Status: Please enter a User ID.");
            return;
        }

        try {
            int userId = Integer.parseInt(idText);
            boolean deleted = userSetting.people.removeIf(user -> user.getId() == userId);

            if (deleted) {
                userSetting.update();
                statusLabel1.setText("Status: User with ID " + userId + " has been deleted successfully.");
            } else {
                statusLabel1.setText("Status: User with ID " + userId + " not found.");
            }
        } catch (NumberFormatException e) {
            statusLabel1.setText("Status: Invalid ID. Please enter a valid number.");
        }
    }

    private void deleteSlot() {
        String roomTypeText = roomTypeField.getText();
        String roomNumText = roomNumberField.getText();
        String slotNumberText = slotNumberField.getText();

        if (roomTypeText.isEmpty()) {
            statusLabel2.setText("Status: Please enter a Room Type.");
            return;
        }
        if (roomNumText.isEmpty()) {
            statusLabel2.setText("Status: Please enter a Room Number.");
            return;
        }
        if (slotNumberText.isEmpty()) {
            statusLabel2.setText("Status: Please enter a Slot Number.");
            return;
        }

        try {
            int slot_id = Integer.parseInt(slotNumberText);
            int room_id = Integer.parseInt(roomNumText);

            if (slot_id <= 0) {
                statusLabel2.setText("Status: Slot number is out of range.");
                return;
            }
            if (room_id <= 0 || room_id > 3) {
                statusLabel2.setText("Status: Room number is out of range.");
                return;
            }

            removeSlot(roomTypeText, room_id, slot_id);
            slots.update();
            log.update();

        } catch (NumberFormatException e) {
            statusLabel2.setText("Status: Invalid Slot Number. Please enter a valid numeric value.");
        } catch (NullPointerException e) {
            statusLabel2.setText("Status: Slot does not exist. Check Room configuration.");
        } catch (Exception e) {
            statusLabel2.setText("Status: An unexpected error occurred. " + e.getMessage());
        }
    }

    private void removeSlot(String roomTypeText, int room_id, int slot_id) {
        String roomTypeNormalized = roomTypeText.toLowerCase();
        if (!roomTypeNormalized.equals("general") && !roomTypeNormalized.equals("meeting") && !roomTypeNormalized.equals("teaching")) {
            statusLabel2.setText("Status: Invalid Room Type. Valid types are General, Meeting, Teaching.");
            return;
        }

        String slotNUM = (slot_id < 10) ? "0" + slot_id : String.valueOf(slot_id);
        int roomOffset = getRoomOffset(roomTypeNormalized, room_id);
        String slotID = generateSlotID(roomTypeNormalized, room_id, slotNUM, roomOffset);

        slots.remove2(roomTypeNormalized, room_id - 1, slot_id - 1);
        ROOM.slotIDs.removeIf(entry -> entry.getKey().contains(slotID));
        log.data.removeIf(entry -> entry.contains(slotID));
        statusLabel2.setText("Status: Slot " + slot_id + " in Room '" + capitalize(roomTypeText) + "' has been removed successfully.");
    }

    private int getRoomOffset(String roomTypeText, int room_id) {
        switch (roomTypeText) {
            case "meeting":
                return 99 + 100;
            case "teaching":
                return 99 + 100 + 100;
            default: // general
                return 99;
        }
    }

    private String generateSlotID(String roomTypeText, int room_id, String slotNUM, int roomOffset) {
        return roomTypeText.toUpperCase().charAt(0) + String.valueOf(room_id + roomOffset) + slotNUM;
    }

    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public void deleteRoom() {
        String roomIDText = roomTypeField1.getText();
        if(Integer.parseInt(roomIDText) >= 100 && Integer.parseInt(roomIDText) < 200){
            welcome_Controller.G_ON[Integer.parseInt(roomIDText)-100] = false;
            System.out.println(welcome_Controller.G_ON[0]);
            statusLabel21.setText("Status: Room #" + roomIDText + " is deleted.");
        } else if(Integer.parseInt(roomIDText) >= 200 && Integer.parseInt(roomIDText) < 300){
            welcome_Controller.M_ON[Integer.parseInt(roomIDText)-200] = false;
            statusLabel21.setText("Status: Room #" + roomIDText + " is deleted.");
        } else if(Integer.parseInt(roomIDText) >= 300 && Integer.parseInt(roomIDText) < 400){
            welcome_Controller.T_ON[Integer.parseInt(roomIDText)-300] = false;
            statusLabel21.setText("Status: Room #" + roomIDText + " is deleted.");
        }
        else {
            statusLabel21.setText("Status: Wrong room number");
        }
    }
}
