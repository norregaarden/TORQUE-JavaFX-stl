package sample;

// https://www.youtube.com/watch?v=yinIKzg7duc&list=PLhs1urmduZ295Ryetga7CNOqDymN_rhB_&index=5

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import com.perunlabs.jsolid.d3.Solid;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.perunlabs.jsolid.JSolid.*;

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

    Stage window;
    Scene scene;

    //private double anchorX, anchorY;
    //private double anchorAngleX = 0;
    //private double anchorAngleY = 0;
    //private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    //private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    String stlPathString = "/Users/norregaarden/Documents/torque/temp.stl";

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle
                ("TORQUE | make handwheel .stl");

        JSolidTORQUE.stlMake(stlPathString);


        HBox topMenu = new HBox();
        Button top1 = new Button("top");
        topMenu.getChildren().addAll(top1);

        VBox leftMenu = new VBox();
        Button left1 = new Button("left");
        leftMenu.getChildren().addAll(left1);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);
        borderPane.setCenter(SubScene3Dstl.display(stlPathString, 400, 500));

/*        Button scenesDemoButton = new Button
                ("ScenesDemo");
        scenesDemoButton.setOnAction(event -> {
            ScenesDemo.display();
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(scenesDemoButton);
        scene = new Scene(layout, 800, 600);*/

        scene = new Scene(borderPane, 800, 600);
        window.setScene(scene);
        window.show();
    }

}
