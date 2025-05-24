package JasonOOP;

import JasonOOP.Controllers.SelectColor_Controller;
import JasonOOP.DataBase.log;
import JasonOOP.DataBase.slots;
import JasonOOP.DataBase.userSetting;
import JasonOOP.DataBase.users;
import JasonOOP.Rooms.General_Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;

import static JasonOOP.Controllers.welcome_Controller.*;

public class Main extends Application {
    public static boolean no_repeat = true;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/gui/Welcome.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/gui/css/Style.css").toExternalForm());
        change();
        Image icon = new Image("file:assets/logo.png");
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Jason's Co-Working Space");
        stage.setScene(scene);
        stage.show();
    }


    public static void change() {
        String color0 = "#2d5cb4";
        String color1 = "#5fb135";
        String color2 = "#1e69a0";
        String color3 = "#2c5f7e";

        String color0_2 = "#4b80c9";
        String color1_2 = "#7bbf47";
        String color2_2 = "#3d88b4";
        String color3_2 = "#4a7b91";

        if (SelectColor_Controller.file.toString().equals("assets\\MainBackground2.mp4")) {
            scene.getRoot().setStyle("-color0: " + color0 + "; -color0_2: " + color0_2 + ";");
        } else if (SelectColor_Controller.file.toString().equals("assets\\MainBackground.mp4")) {
            scene.getRoot().setStyle("-color0: " + color1 + "; -color0_2: " + color1_2 + ";");
        } else if (SelectColor_Controller.file.toString().equals("assets\\MainBackground3.mp4")) {
            scene.getRoot().setStyle("-color0: " + color2 + "; -color0_2: " + color2_2 + ";");
        } else if (SelectColor_Controller.file.toString().equals("assets\\MainBackground1.mp4")) {
            scene.getRoot().setStyle("-color0: " + color3 + "; -color0_2: " + color3_2 + ";");
        }
    }


    public static void main(String[] args) {
        //DataBase
        for(int i = 0;i<3;i++){
            G_ON[i] = true;
            if(i != 2){
                M_ON[i] = true;
                T_ON[i] = true;
            }
        }
        userSetting.read();
        log.read();
        slots.read();
        slots.read2();
        userSetting.displayUsers();
        int size = users.getMaxId() - 2023169999;
        System.out.println(size);
        System.out.println(slots.displayTotalVisNumber());

        launch();
    }
}
