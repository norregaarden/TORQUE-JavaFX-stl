package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScenesDemo {

    public static void display() {
        Stage window = new Stage();
        window.setTitle
                ("ScenesDemo");

        System.out.println
                ("yee");

        VBox layout1 = new VBox(20);
        Scene scene1 = new Scene(layout1, 200, 200);
        StackPane layout2 = new StackPane();
        Scene scene2 = new Scene(layout2, 600, 600);

        ////
        // SCENE 1
        ////

        Label label1 = new Label
                ("I'm a label");

        Button button1 = new Button
                ("Go to scene2");
        button1.setOnAction(event -> {
            System.out.println
                    ("yoe");
            window.setScene(scene2);
        });

        Button alertButton = new Button
                ("Open popup");
        alertButton.setOnAction(e -> AlertBox.display
                ("This is popup", "Hi, pop"));

        Button confirmButton = new Button
                ("Modal dialog");
        confirmButton.setOnAction(e -> {
            boolean result = ConfirmBox.display
                    ("Modal title", "Are you sure?");
            System.out.println(result);
        });

        layout1.getChildren().addAll(label1, button1, alertButton, confirmButton);


        ////
        // SCENE 2
        ////

        Button button2 = new Button
                ("Back to scene1");
        button2.setOnAction(event -> {
            System.out.println
                    ("yeo");
            window.setScene(scene1);
        });

        layout2.getChildren().add(button2);

        window.setScene(scene1);
        window.show();
    }

}
