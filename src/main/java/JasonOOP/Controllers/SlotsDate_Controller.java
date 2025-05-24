package JasonOOP.Controllers;

import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Functionality.SceneSwitch;
import JasonOOP.Rooms.ROOM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.AbstractMap;

public class SlotsDate_Controller {
    @FXML
    public DatePicker datePicker;
    @FXML
    public AnchorPane SlotsData_Pane;
    @FXML
    public Button checkID;
    @FXML
    public Label money, free, type, roomID, label01, label02, mainLabel;

    public static String slotID;
    int Capacity = 0;
    int counter = 0;
    int selected_freeHours = 0;

    @FXML
    public void initialize() {
        type.setText("");
        roomID.setText("");
        mainLabel.setText("");

        for (users person : userSetting.people) {
            if (person.getId() == Integer.parseInt(userSetting.selected_id)) {
                selected_freeHours = person.getFreeHours();
            }
        }
        label02.setText("Free hours: " + selected_freeHours);

        if (ROOM.Room.getRoom_ID() < 200) {
            label01.setText("General  " + ROOM.Room.getRoom_Name() + "  #" + ROOM.Room.getRoom_ID() + ",  Slot: " + Slots_Controller.selectedSlot + " ~ From  " + Slots_Controller.firstTime + "  to  " + Slots_Controller.secondTime);
        } else if (ROOM.Room.getRoom_ID() < 300) {
            label01.setText("Meeting  " + ROOM.Room.getRoom_Name() + "  #" + ROOM.Room.getRoom_ID() + ",  Slot: " + Slots_Controller.selectedSlot + " ~ From  " + Slots_Controller.firstTime + "  to  " + Slots_Controller.secondTime);
        } else if (ROOM.Room.getRoom_ID() < 400) {
            label01.setText("Teaching  " + ROOM.Room.getRoom_Name() + "  #" + ROOM.Room.getRoom_ID() + ",  Slot: " + Slots_Controller.selectedSlot + " ~ From  " + Slots_Controller.firstTime + "  to  " + Slots_Controller.secondTime);
        }

        LocalDate today = LocalDate.now();
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date == null || date.getMonth() != today.getMonth() || date.isBefore(today)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #cccccc;");
                }
            }
        });

        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null && (selectedDate.getMonth() != today.getMonth() || selectedDate.isBefore(today))) {
                datePicker.setValue(null);
            }
        });

    }

    public void backToHello() throws IOException {
        new SceneSwitch(SlotsData_Pane, "/com/example/gui/Slots.fxml");
    }

    public void clear() {
        datePicker.setDisable(false);
        checkID.setDisable(false);
        checkID.setStyle("-fx-background-radius: 4px;");
        datePicker.setValue(null);
        type.setText("");
        roomID.setText("");
        mainLabel.setText("");
        checkID.setText("Check");
        money.setText("");
        free.setText("");
    }

    public void checkSlot() {
        if (datePicker.getValue() != null) {
            if (checkID.getText().equals("Check")) {
                datePicker.setDisable(true);
                LocalDate selectedDate = datePicker.getValue();
                slotID = String.valueOf(label01.getText().charAt(0)) + ROOM.Room.getRoom_ID() + Slots_Controller.selectedSlot + selectedDate.getMonthValue() + selectedDate.getDayOfMonth();
                if (slotID.charAt(0) == 'G') {
                    Capacity = slots.capacityArray[0][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(slotID.substring(4, 6)) - 1];
                } else if (slotID.charAt(0) == 'M') {
                    Capacity = slots.capacityArray[1][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(slotID.substring(4, 6)) - 1];

                } else if (slotID.charAt(0) == 'T') {
                    Capacity = slots.capacityArray[2][Integer.parseInt(ROOM.Room.getRoom_Name().replaceAll("\\D+", "")) - 1][Integer.parseInt(slotID.substring(4, 6)) - 1];
                }
                if (ROOM.Room.checker(slotID).equals("true")) {
                    checkID.setText("Book");
                    type.setText("Available");
                    checkID.setDisable(false);
                    checkID.setStyle("-fx-background-radius: 4px;");
                } else if (ROOM.Room.checker(slotID).equals("full")) {
                    checkID.setText("Full");
                    checkID.setDisable(true);
                    checkID.setStyle("-fx-background-radius: 4px; -fx-opacity: 0.3");
                    type.setText("Not Available");
                } else {
                    checkID.setText("Book");
                    type.setText("Available");
                    checkID.setDisable(false);
                    checkID.setStyle("-fx-background-radius: 4px;");
                }
                roomID.setText("Slot ID: " + slotID);
            } else {
                if (!slots.incNumVis(slotID)) {
                    checkID.setText("Full");
                    type.setText("Not Available");
                    checkID.setDisable(true);
                    checkID.setStyle("-fx-background-radius: 4px; -fx-opacity: 0.3");
                }
                if (selected_freeHours > 0) {
                    money.setText("");
                    free.setText("FREE");
                    selected_freeHours -= 1;
                    label02.setText("Free hours: " + selected_freeHours);
                    for (users person : userSetting.people) {
                        if (person.getId() == Integer.parseInt(userSetting.selected_id)) {
                            person.removeFreeHours();
                        }
                    }
                    userSetting.update();
                } else {
                    counter++;
                    free.setText("");
                    money.setText("-" + (counter * slots.SLOT_getFees() * userSetting.selected_penalty) + "$");
                }
            }
            mainLabel.setText(String.valueOf(slots.getNum_of_vis(slotID) + "/" + String.valueOf(Capacity)));
        }
    }
}