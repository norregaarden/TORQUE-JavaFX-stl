package torqueUI;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class SubScene3Dstl {

    private double cameraZ = 0;
    private double rotate = 0;

    public BorderPane display(String stlPathString, double cameraZarg, double size) {
        StlMeshImporter stlImporter = new StlMeshImporter();
        System.out.println(stlPathString);

        //Label errorLabel = new Label("");
        //errorLabel.setTextFill(Color.DARKRED);
        try {
            stlImporter.read(stlPathString);
        }
        catch (ImportException e) {
            System.out.println("cannot import stl");
        }
        TriangleMesh stlMesh = stlImporter.getImport();
        MeshView meshView = new MeshView(stlMesh);
        meshView.setDrawMode(DrawMode.LINE);
        //meshView.setDrawMode(DrawMode.FILL);
        PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.DARKGREEN);
        meshView.setMaterial(greenMaterial);
        //meshView.setTranslateX(100);
        //meshView.setTranslateY(100);
        //meshView.setTranslateZ(-500);
        //root.getChildren().add(meshView);

        //StackPane threeDee = new StackPane();
        Group threeDee = new Group();
        //threeDee.getChildren().addAll(meshView, new Label("center"));
        threeDee.getChildren().addAll(meshView);
        SubScene subScene3d = new SubScene(threeDee, size, size);
        subScene3d.setFill(Color.SILVER);
        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(1000000);
        subScene3d.setCamera(camera);
        meshView.translateXProperty().set(size/2);
        meshView.translateYProperty().set(size/2);
        camera.translateXProperty().set(size/2);
        camera.translateYProperty().set(size/2);
        cameraZ = cameraZarg;
        camera.translateZProperty().set(cameraZ);

        ////
        // BUTTONS
        ////
        GridPane controls3d = new GridPane();
        controls3d.setPadding(new Insets(0, 0, 0, 40));
        controls3d.setAlignment(Pos.CENTER);

        Button zoomOut = new Button
                ("-");
        zoomOut.setOnAction(e -> {
            cameraZ *= 1.1;
            camera.translateZProperty().set(cameraZ);
        });
        controls3d.setConstraints(zoomOut, 0, 0, 1 , 1);

        Label zoomLabel = new Label
                ("zoom");
        zoomLabel.setPadding(new Insets(0, 10, 0, 10));
        controls3d.setConstraints(zoomLabel, 1, 0, 2 , 1);

        Button zoomIn = new Button
                ("+");
        zoomIn.setOnAction(e -> {
            cameraZ *= 0.9;
            camera.translateZProperty().set(cameraZ);
        });
        controls3d.setConstraints(zoomIn, 3, 0, 1 , 1);

        Label spacer = new Label("");
        spacer.setPadding(new Insets(0, 40, 0, 40));
        controls3d.setConstraints(spacer, 4, 0, 2 , 1);

        Button rotUp = new Button
                ("-");
        rotUp.setOnAction(e -> {
            Transform transform = new Rotate(8, new Point3D(1, 0, 0));
            //meshView.getTransforms().removeAll();
            meshView.getTransforms().add(transform);
        });
        controls3d.setConstraints(rotUp, 6, 0, 1 , 1);

        Label rotLabel = new Label
                ("rotate");
        rotLabel.setPadding(new Insets(0, 10, 0, 10));
        controls3d.setConstraints(rotLabel, 7, 0, 2 , 1);

        Button rotDown = new Button
                ("+");
        rotDown.setOnAction(e -> {
            Transform transform = new Rotate(-8, new Point3D(1, 0, 0));
            //meshView.getTransforms().removeAll();
            meshView.getTransforms().add(transform);
        });
        controls3d.setConstraints(rotDown, 9, 0, 1 , 1);

        controls3d.getChildren().addAll(
                zoomOut,
                zoomLabel,
                zoomIn,
                spacer,
                rotUp,
                rotLabel,
                rotDown
        );

        BorderPane ss3dLayout = new BorderPane();
        //ss3dLayout.setTop(errorLabel);
        ss3dLayout.setCenter(subScene3d);
        ss3dLayout.setBottom(controls3d);
        //return subScene3d;
        return ss3dLayout;
    }
}
