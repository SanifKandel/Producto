package Controller;

import DB.DatabaseConnection;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    DatabaseConnection Db = new DatabaseConnection();

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    Stage stage;
    Scene scene;
    Parent root;
    static double xOffset, yOffset;

    @FXML
    private AnchorPane rootstage;
    @FXML
    private Button login;
    @FXML
    private TextField EmailField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Button red;
    @FXML
    private Circle yellow;
//    @FXML private Button ForgetPassword;
//    @FXML private Button CreateAccount;


    public void redOnAction(ActionEvent event) {
        stage = (Stage) red.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchtosignup(ActionEvent singupevent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Frontend/FXML/Registration.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) singupevent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stageDragable(root, stage);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onlogin(ActionEvent logine) throws SQLException {

        if (logvalidity() && user()) {
            stage = (Stage) ((Node) logine.getSource()).getScene().getWindow();
            stage.close();
            switchtoDashboard(logine);
        }

    }

    private boolean logvalidity() {
        if (EmailField.getText() == null || EmailField.getText().length() <= 10) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Email");
            errorAlert.setContentText("Please enter a valid Email");
            errorAlert.showAndWait();
            return false;
        }

        if (PassField.getText() == null || PassField.getText().length() < 8) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Password");
            errorAlert.setContentText("Please enter a valid Password.");
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }


    private boolean user() throws SQLException {
        Connection Conn = Db.getConnection();

        if (Conn != null) {
            PreparedStatement adduser = Conn.prepareStatement("select * from tbl_user WHERE email=? AND password =?");
            adduser.setString(1, EmailField.getText());
            adduser.setString(2, PassField.getText());

            ResultSet r = adduser.executeQuery();
            if (r.next()) {
                adduser.close();
                Conn.close();
                return true;
            } else {
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("User Not Found");
                errorAlert.setContentText("Please register your account");
                errorAlert.showAndWait();
                adduser.close();
                return false;
            }
        }
        return false;
    }

    // Method ma event dyacha vaneh argument k diney


    @FXML
    private void switchtoDashboard(ActionEvent dashboard) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Frontend/FXML/Dashboard.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) dashboard.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stageDragable(root, stage);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onQuit(ActionEvent actionEvent) {
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(.4), rootstage);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(.4), rootstage);

        scaleTransition.setInterpolator(Interpolator.EASE_IN);

        scaleTransition.setByX(.05);


        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);

        scaleTransition.play();
        fadeTransition.play();

        fadeTransition.setOnFinished(actionEvent1 -> {
            scaleTransition.stop();
            fadeTransition.stop();
            stage.close();
            Platform.exit();
            System.exit(0);
        });
    }

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


}

