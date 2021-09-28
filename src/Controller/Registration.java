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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static Controller.Login.stageDragable;


public class Registration {
    Stage stage;
    Scene scene;
    Parent root;
    static double xOffset, yOffset;
    Connection Conn;
    DatabaseConnection Db = new DatabaseConnection();

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private AnchorPane rootstage;
    @FXML
    private Button createaccount;
    @FXML
    private TextField Fullname;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField PhoneNo;
    @FXML
    private TextField password;
    @FXML
    private TextField Confirm;
    @FXML
    private Button Login;
    @FXML
    private Button red;
    @FXML
    private CheckBox Tick;

    private boolean validity() {
        if (Fullname.getText() == null || Fullname.getText().length() < 3) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Username");
            errorAlert.setContentText("Please enter a valid Username");
            errorAlert.showAndWait();
            return false;
        }

        if (EmailField.getText() == null || EmailField.getText().length() <= 10 || !checkEmail()) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Email");
            errorAlert.setContentText("Please enter a valid Email.");
            errorAlert.showAndWait();
            return false;
        }

        if (PhoneNo.getText() == null || PhoneNo.getText().length() < 10) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Phone Number");
            errorAlert.setContentText("Please enter a valid Phone No.");
            errorAlert.showAndWait();
            return false;
        }

        if (password.getText() == null || password.getText().length() < 8) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid Username");
            errorAlert.setContentText("Please enter a valid Username");
            errorAlert.showAndWait();
            return false;
        }
        if (!password.getText().equals(Confirm.getText())) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Password Error");
            errorAlert.setContentText("Password and Confirm Password doesn't match.");
            errorAlert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    private  boolean checkEmail(){
        String email = EmailField.getText().strip();
        StringBuilder checkDomain= new StringBuilder();
        StringBuilder checkDomainResult= new StringBuilder();
        checkDomain.append(email);
        checkDomain.reverse();
        for(int i = 0; i<10;i++){

            checkDomainResult.append(checkDomain.charAt(i));

        }
        return checkDomainResult.toString().equals("moc.liamg@");
    }

    @FXML
    private void register(ActionEvent e) throws SQLException {
        if (validity()) {
            if (adduser()) {
                switchtologin(e);
            } else {
                System.out.println("Database is not connected");
            }

        }

    }


    // Registration of User
    private boolean adduser() throws SQLException {

        Connection Conn = Db.getConnection();

        if (Conn != null) {

            PreparedStatement adduser = Conn.prepareStatement("insert into tbl_user(user_name,email,phone_No,password) values (?,?,?,?)");
            adduser.setString(1, Fullname.getText());
            adduser.setString(2, EmailField.getText());
            adduser.setString(3, PhoneNo.getText());
            adduser.setString(4, password.getText());
            adduser.executeUpdate();
            adduser.close();
            Conn.close();
            return true;

        } else {
            return false;
        }
    }

    @FXML
    private void switchtologin(ActionEvent singupevent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Frontend/FXML/Login.fxml"));
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


