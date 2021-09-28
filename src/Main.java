import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    static double xOffset, yOffset;


    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Frontend/FXML/Login.fxml")));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            stageDragable(root, primaryStage);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method help to drag the stage
    public static void stageDragable(Parent root, Stage stage) {
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}