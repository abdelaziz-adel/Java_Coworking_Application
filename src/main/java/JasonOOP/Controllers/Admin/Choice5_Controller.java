package JasonOOP.Controllers.Admin;

import JasonOOP.DataBase.slots;
import JasonOOP.Rooms.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

//import static JasonOOP.Rooms.ROOM.Room_slots;

public class Choice5_Controller implements Initializable {

    @FXML
    private TextField roomTypeField, slotNumberField, roomNumberField;
    @FXML
    private Button addSlotButton;
    @FXML
    private Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addSlotButton.setOnAction(event -> addSlot());
    }

    private void addSlot() {
        String roomNumText = roomNumberField.getText();
        String roomTypeText = roomTypeField.getText();
        String slotNumberText = slotNumberField.getText();

        if (roomTypeText.isEmpty()) {
            statusLabel.setText("Status: Please enter a Room Type.");
            return;
        }
        if (roomNumText.isEmpty()) {
            statusLabel.setText("Status: Please enter a Room Number.");
            return;
        }
        if (slotNumberText.isEmpty()) {
            statusLabel.setText("Status: Please enter a Slot Number.");
            return;
        }

        try {
            int slot_num = Integer.parseInt(slotNumberText);
            int room_num = Integer.parseInt(roomNumText);
            if (slot_num <= 0) {
                statusLabel.setText("Status: Slot number is out of range.");
                return;
            }
            if (room_num <= 0||room_num>3) {
                statusLabel.setText("Status: Room number is out of range.");
                return;
            }


            switch (roomTypeText.toLowerCase()) {
                case "general":
                case "meeting":
                case "teaching":
                    slots.add2(roomTypeText.toLowerCase(), room_num-1, slot_num-1);
                    break;
                default:
                    statusLabel.setText("Status: Invalid Room Type. Valid types are General, Meeting, Teaching.");
                    return;
            }

            statusLabel.setText("Status: Slot " + slot_num
                    + " in Room '" + roomTypeText.substring(0, 1).toUpperCase() + roomTypeText.substring(1).toLowerCase()
                    + "' has been incremented successfully. "
            );

        } catch (NumberFormatException e) {
            statusLabel.setText("Status: Invalid Slot Number. Please enter a valid numeric value.");
        } catch (NullPointerException e) {
            statusLabel.setText("Status: Slot does not exist. Check Room configuration.");
        } catch (Exception e) {
            statusLabel.setText("Status: An unexpected error occurred. " + e.getMessage());
        }
    }


}