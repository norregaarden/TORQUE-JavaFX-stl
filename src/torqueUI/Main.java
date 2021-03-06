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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import test.ConfirmBox;

import java.io.File;

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
    private double outer_diameter = 4.984;

    TextField outer_width_field;
    private double outer_width = 0.561;

    TextField spoke_width_field;
    private double spoke_width = 0.443;

    TextField inner_radius_field;
    private double inner_radius = 0.586;

    TextField number_of_spokes_field;
    private int number_of_spokes = 4;

    TextField thickness_field;
    private double thickness = 0.560;

    TextField precision_field;
    private double precision = 0.004;

    TextField file_dir_field;
    String file_dir = System.getProperty("user.dir");

    TextField file_name_field;
    String file_name = "test.stl";

    String error_string = "";
    Label error_label;

/*    TextField file_dir_field;
    String file_dir = System.getProperty("user.dir");*/


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

    String stlPathString;
    //String stlPathString = "C:\\Users\\soere\\Google Drive\\PROJECTS\\NAVSEA Handwheel\\";
    //String stlPathString = "C:\\temp.stl";

    public void generateTORQUE() {
        double outer_diameter = inputDouble(outer_diameter_field, this.outer_diameter);
        //System.out.println("outer_diameter " + outer_diameter);

        double outer_width = inputDouble(outer_width_field, this.outer_width);
        //System.out.println("outer_width " + outer_width);

        double spoke_width = inputDouble(spoke_width_field, this.spoke_width);
        //System.out.println("outer_width " + spoke_width);

        double inner_radius = inputDouble(inner_radius_field, this.inner_radius);
        //System.out.println("outer_width " + inner_radius);

        int number_of_spokes = inputInt(number_of_spokes_field, this.number_of_spokes);
        //System.out.println("outer_width " + number_of_spokes);

        double thickness = inputDouble(thickness_field, this.thickness);
        //System.out.println("outer_width " + thickness);

        double precision = inputDouble(precision_field, this.precision);
        //System.out.println("outer_width " + precision);

        //String pathString = "";
        if (file_dir.contains("\\")) {
            stlPathString = file_dir_field.getText() + "\\" + file_name_field.getText();
        } else {
            stlPathString = file_dir_field.getText() + "/" + file_name_field.getText();
        }

        error_string = JSolidTORQUE.stlMake(stlPathString,
                outer_diameter,
                outer_width,
                spoke_width,
                inner_radius,
                number_of_spokes,
                thickness,
                precision);

        error_label.setText(error_string);

        SubScene3Dstl ss3d = new SubScene3Dstl();
        layout.setCenter(ss3d.display(stlPathString, -outer_diameter*3, subScene3Dsize));
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

        ////
        // MAIN FORM (left)
        ////
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
                ("Outer Race Diameter [in]");
        GridPane.setConstraints(outer_diameter_label, 0, row);
        outer_diameter_field = new TextField();
        outer_diameter_field.setText(String.valueOf(outer_diameter));
        GridPane.setConstraints(outer_diameter_field, 1, row);
        row++;

        Label outer_width_label = new Label
                ("Outer Race Width [in]");
        GridPane.setConstraints(outer_width_label, 0, row);
        outer_width_field = new TextField();
        outer_width_field.setText(String.valueOf(outer_width));
        GridPane.setConstraints(outer_width_field, 1, row);
        row++;

        Label spoke_width_label = new Label
                ("Spoke Width [in]");
        GridPane.setConstraints(spoke_width_label, 0, row);
        spoke_width_field = new TextField();
        spoke_width_field.setText(String.valueOf(spoke_width));
        GridPane.setConstraints(spoke_width_field, 1, row);
        row++;

        Label inner_radius_label = new Label
                ("Inner Race Radius [in]");
        GridPane.setConstraints(inner_radius_label, 0, row);
        inner_radius_field = new TextField();
        inner_radius_field.setText(String.valueOf(inner_radius));
        GridPane.setConstraints(inner_radius_field, 1, row);
        row++;

        Label number_of_spokes_label = new Label
                ("No. of Spokes");
        GridPane.setConstraints(number_of_spokes_label, 0, row);
        number_of_spokes_field = new TextField();
        number_of_spokes_field.setText(String.valueOf(number_of_spokes));
        GridPane.setConstraints(number_of_spokes_field, 1, row);
        row++;

        Label thickness_label = new Label
                ("Spoke Thickness [in]");
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

/*        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose file directory");
        File defaultDirectory = new File();
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(primaryStage);*/

        Button generate_button = new Button
                ("Generate");
        GridPane.setConstraints(generate_button, 1, row+3);
        generate_button.setOnAction(e -> generateTORQUE());

        error_label = new Label("");
        GridPane.setConstraints(error_label, 0, row+5, 2, 2);
        error_label.setTextFill(Color.DARKRED);

        GridPane mainForm = new GridPane();
        mainForm.setVgap(10);
        mainForm.setHgap(20);
        mainForm.getChildren().addAll(
                heading,
                subtext,
                outer_diameter_label,
                outer_diameter_field,
                outer_width_label,
                outer_width_field,
                spoke_width_label,
                spoke_width_field,
                inner_radius_label,
                inner_radius_field,
                number_of_spokes_label,
                number_of_spokes_field,
                thickness_label,
                thickness_field,
                precision_label,
                precision_field,
                generate_button,
                error_label
        );



        ////
        // BOTTOM
        ////
        GridPane bottomForm = new GridPane();
        bottomForm.setPadding(new Insets(30, 0, 0, 0));

        Label file_dir_label = new Label("File directory: ");
        file_dir_field = new TextField(file_dir);
        file_dir_field.setMaxWidth(400);
        bottomForm.setConstraints(file_dir_label, 1, 0, 2, 1);
        bottomForm.setConstraints(file_dir_field, 3, 0, 8, 1);

        Label spacer = new Label("");
        spacer.setPadding(new Insets(0, 40, 0, 40));
        bottomForm.setConstraints(spacer, 11, 0, 2, 1);

        Label file_name_label = new Label("File name: ");
        file_name_field = new TextField(file_name);
        bottomForm.setConstraints(file_name_label, 13, 0, 2, 1);
        bottomForm.setConstraints(file_name_field, 15, 0, 4, 1);

        bottomForm.setVgap(10);
        bottomForm.setHgap(20);
        bottomForm.getChildren().addAll(
                file_dir_label,
                file_dir_field,
                file_name_label,
                file_name_field
        );



        layout = new BorderPane();
        layout.setPadding(new Insets(40, 0, 40, 40));
        layout.setLeft(mainForm);
        layout.setBottom(bottomForm);
        generateTORQUE();

        scene = new Scene(layout, 1024, 768);
        layout.requestFocus();
        window.setScene(scene);
        window.show();
    }

}
