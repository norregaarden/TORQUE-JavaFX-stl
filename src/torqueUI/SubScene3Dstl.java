package torqueUI;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class SubScene3Dstl {
    public static SubScene display(String stlPathString, double cameraZ, double size) {
        StlMeshImporter stlImporter = new StlMeshImporter();
        try {
            stlImporter.read(stlPathString);
        }
        catch (ImportException e) {
            System.out.println("cannot import stl");
        }
        TriangleMesh stlMesh = stlImporter.getImport();
        MeshView meshView = new MeshView(stlMesh);
        meshView.setDrawMode(DrawMode.FILL);
        PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.AQUAMARINE);
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
        Camera camera = new PerspectiveCamera();
        subScene3d.setCamera(camera);
        camera.translateXProperty().set(-size/2);
        camera.translateYProperty().set(-size/2);
        camera.translateZProperty().set(cameraZ);

        return subScene3d;
    }
}
