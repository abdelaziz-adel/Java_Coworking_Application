package JasonOOP.Controllers.Admin;

import JasonOOP.Functionality.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin_Controller implements Initializable {

    @FXML
    public Pane pane0, pane1, pane2, pane3, pane4, pane5, pane6;
    @FXML
    private AnchorPane Admin_Pane, Choice_Pane;

    private Pane[] panes;

    @FXML
    void backToHello() throws IOException {
        new SceneSwitch(Admin_Pane, "/com/example/gui/Welcome.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new Pane[]{pane0, pane1, pane2, pane3, pane4, pane5, pane6};

        try {
            goToChoice(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToChoice(MouseEvent event) throws IOException {
        String paneId = ((Node) event.getSource()).getId();
        int n = Integer.parseInt(paneId.replaceAll("\\D", ""));
        goToChoice(n);
    }


    public void goToChoice(int n) throws IOException {
        new SceneSwitch(Choice_Pane, "/com/example/gui/Admin/Choice" + n + ".fxml");
        for (int i = 0; i < panes.length; i++) {
            if (i == n) {
                panes[i].setStyle("-fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.17), transparent);");
            } else {
                panes[i].setStyle("");
            }
        }
    }
}
