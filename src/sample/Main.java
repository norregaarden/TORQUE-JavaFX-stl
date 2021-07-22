package sample;

// brug det her til interaktiv 3d scene
// https://github.com/Eclion/3DViewer/tree/master/src/main/java/com/javafx/experiments
////

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display
                ("exit TORQUE", "Sure you want to exit?");
        if (answer == true) {
            System.out.println("program is closing");
            window.close();
        }
    }

    TextField outer_diameter_field;
    private double outer_diameter = 200;

    private double subScene3Dsize = 600;
    SubScene subScene3Dstl;
    BorderPane layout;
    Stage window;
    Scene scene;

    /*
     https://www.youtube.com/watch?v=yinIKzg7duc&list=PLhs1urmduZ295Ryetga7CNOqDymN_rhB_&index=5
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    */

    String stlPathString = "/Users/norregaarden/Documents/torque/temp.stl";

    public void generateTORQUE() {
        double outer_diameter = inputDouble(outer_diameter_field, this.outer_diameter);
        System.out.println("outer_diameter " + outer_diameter);

        JSolidTORQUE.stlMake(stlPathString, outer_diameter);
        layout.setCenter(SubScene3Dstl.display(stlPathString, 400, subScene3Dsize));
    }

    private double inputDouble(TextField input, double defaultValue) {
        try {
            double theDouble = Double.parseDouble(input.getText());
            return theDouble;
        }
        catch (NumberFormatException e) {
            System.out.println("could not parse '" + input.getText() + "' as double");
            return defaultValue;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            //e.consume();
            //closeProgram();
        });
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle
                ("TORQUE | make handwheel .stl");


/*        HBox topMenu = new HBox(20);
        Button top1 = new Button("top");
        topMenu.getChildren().addAll(top1);*/

        Label outer_diameter_label = new Label
                ("Outer diameter");
        outer_diameter_field = new TextField();

        Button generate = new Button
                ("Generate");
        generate.setOnAction(e -> generateTORQUE());

        VBox leftMenu = new VBox(20);
        leftMenu.setPadding(new Insets(40, 40, 40, 40));
        leftMenu.getChildren().addAll(
                outer_diameter_label,
                outer_diameter_field,
                generate
        );

        layout = new BorderPane();
        //borderPane.setTop(topMenu);
        layout.setLeft(leftMenu);
        generateTORQUE();

/*        Button scenesDemoButton = new Button
                ("ScenesDemo");
        scenesDemoButton.setOnAction(event -> {
            ScenesDemo.display();
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(scenesDemoButton);
        scene = new Scene(layout, 800, 600);*/

        scene = new Scene(layout, 1024, 768);
        window.setScene(scene);
        window.show();
    }

}
