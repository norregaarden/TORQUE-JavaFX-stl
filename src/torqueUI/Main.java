package torqueUI;

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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import test.ConfirmBox;

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
    TextField outer_width_field;
    private double outer_width = 15;
    TextField spoke_width_field;
    private double spoke_width = 10;
    TextField inner_diameter_field;
    private double inner_diameter = 30;
    TextField number_of_spokes_field;
    private int number_of_spokes = 5;
    TextField thickness_field;
    private double thickness = 20;
    TextField precision_field;
    private double precision = 0.1;

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

        double outer_width = inputDouble(outer_width_field, this.outer_width);
        System.out.println("outer_width " + outer_width);

        double spoke_width = inputDouble(spoke_width_field, this.spoke_width);
        System.out.println("outer_width " + spoke_width);

        double inner_diameter = inputDouble(inner_diameter_field, this.inner_diameter);
        System.out.println("outer_width " + inner_diameter);

        int number_of_spokes = inputInt(number_of_spokes_field, this.number_of_spokes);
        System.out.println("outer_width " + number_of_spokes);

        double thickness = inputDouble(thickness_field, this.thickness);
        System.out.println("outer_width " + thickness);

        double precision = inputDouble(precision_field, this.precision);
        System.out.println("outer_width " + precision);

        JSolidTORQUE.stlMake(stlPathString,
                outer_diameter,
                outer_width,
                spoke_width,
                inner_diameter,
                number_of_spokes,
                thickness,
                precision);

        layout.setCenter(SubScene3Dstl.display(stlPathString, -10, subScene3Dsize));
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

    private int inputInt(TextField input, int defaultValue) {
        try {
            int theInt = Integer.parseInt(input.getText());
            return theInt;
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
                ("TORQUE | handwheel .stl");


/*        HBox topMenu = new HBox(20);
        Button top1 = new Button("top");
        topMenu.getChildren().addAll(top1);*/

        int row = 1;

        Label heading = new Label
                ("TORQUE");
        heading.setFont(Font.font("Serif", 32));
        GridPane.setConstraints(heading, 0, row);
        row++;

        Label subtext = new Label
                ("handwheel .stl");
        subtext.setFont(Font.font("Serif"));
        GridPane.setConstraints(subtext, 0, row);
        row++;
        row++;
        row++;

        Label outer_diameter_label = new Label
                ("Outer diameter");
        GridPane.setConstraints(outer_diameter_label, 0, row);
        outer_diameter_field = new TextField();
        outer_diameter_field.setText(String.valueOf(outer_diameter));
        GridPane.setConstraints(outer_diameter_field, 1, row);
        row++;

        Label outer_width_label = new Label
                ("Outer width");
        GridPane.setConstraints(outer_width_label, 0, row);
        outer_width_field = new TextField();
        outer_width_field.setText(String.valueOf(outer_width));
        GridPane.setConstraints(outer_width_field, 1, row);
        row++;

        Label spoke_width_label = new Label
                ("Spoke width");
        GridPane.setConstraints(spoke_width_label, 0, row);
        spoke_width_field = new TextField();
        spoke_width_field.setText(String.valueOf(spoke_width));
        GridPane.setConstraints(spoke_width_field, 1, row);
        row++;

        Label inner_diameter_label = new Label
                ("Inner diameter");
        GridPane.setConstraints(inner_diameter_label, 0, row);
        inner_diameter_field = new TextField();
        inner_diameter_field.setText(String.valueOf(inner_diameter));
        GridPane.setConstraints(inner_diameter_field, 1, row);
        row++;

        Label number_of_spokes_label = new Label
                ("Number of spokes");
        GridPane.setConstraints(number_of_spokes_label, 0, row);
        number_of_spokes_field = new TextField();
        number_of_spokes_field.setText(String.valueOf(number_of_spokes));
        GridPane.setConstraints(number_of_spokes_field, 1, row);
        row++;

        Label thickness_label = new Label
                ("Thickness");
        GridPane.setConstraints(thickness_label, 0, row);
        thickness_field = new TextField();
        thickness_field.setText(String.valueOf(thickness));
        GridPane.setConstraints(thickness_field, 1, row);
        row++;

        Label precision_label = new Label
                ("Precision");
        GridPane.setConstraints(precision_label, 0, row);
        precision_field = new TextField();
        precision_field.setText(String.valueOf(precision));
        GridPane.setConstraints(precision_field, 1, row);
        row++;

        Button generate_button = new Button
                ("Generate");
        GridPane.setConstraints(generate_button, 1, row+3);
        generate_button.setOnAction(e -> generateTORQUE());

        GridPane mainForm = new GridPane();
        mainForm.setVgap(10);
        mainForm.setHgap(20);
        mainForm.setPadding(new Insets(40, 40, 40, 40));
        mainForm.getChildren().addAll(
                heading,
                subtext,
                outer_diameter_label,
                outer_diameter_field,
                outer_width_label,
                outer_width_field,
                spoke_width_label,
                spoke_width_field,
                inner_diameter_label,
                inner_diameter_field,
                number_of_spokes_label,
                number_of_spokes_field,
                thickness_label,
                thickness_field,
                precision_label,
                precision_field,
                generate_button
        );

        layout = new BorderPane();
        layout.setLeft(mainForm);
        generateTORQUE();

        scene = new Scene(layout, 1024, 768);
        window.setScene(scene);
        window.show();
    }

}
