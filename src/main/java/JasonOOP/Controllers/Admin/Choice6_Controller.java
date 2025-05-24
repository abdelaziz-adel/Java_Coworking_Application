package JasonOOP.Controllers.Admin;

import JasonOOP.DataBase.log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Choice6_Controller implements Initializable {

    @FXML
    public VBox labelContainer;
    @FXML
    public Label total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        total.setText(total.getText() + String.valueOf(log.data.size()));

        for (String line : log.data) {
            addNewLabel(line);
        }

    }

    public void addNewLabel(String labelText) {
        Label newLabel = new Label("  " + labelText);
        newLabel.setStyle("-fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.35), transparent); -fx-font-size: 17");
        newLabel.setPrefWidth(970);
        newLabel.setPrefHeight(49);
        Pane newPane = new Pane();
        newPane.setPrefWidth(970);
        newPane.setPrefHeight(49);
        newPane.getChildren().add(newLabel);
        labelContainer.getChildren().add(newPane);
        newPane = new Pane();
        newPane.setPrefHeight(3);
        labelContainer.getChildren().add(newPane);
    }


}
