package Controller;

import DB.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Circle red;
    @FXML
    private CheckBox Tick;

    public void redOnAction(ActionEvent event) {
        Stage stage = (Stage) red.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void register(ActionEvent e) throws SQLException {

        //Alert a = new Alert(Alert.AlertType.NONE);
//        if (adduser()) {
//            switchtologin(e);
//        }

//         else {
//            if (Fullname.getText().isEmpty()) {
//                a.setAlertType(Alert.AlertType.ERROR);
//            }
//            if (EmailField.getText().isEmpty()) {
//                a.setAlertType(Alert.AlertType.ERROR);
//            }
//            if (PhoneNo.getText().isEmpty()) {
//                a.setAlertType(Alert.AlertType.ERROR);
//            }
//            if (password.getText().isEmpty()) {
//                a.setAlertType(Alert.AlertType.ERROR);
//            }
//        if(emailField.getText().isEmpty()) {
//            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
//            return;
//        }
//        if(passwordField.getText().isEmpty()) {
//            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
//            return;


        if (adduser()) {
            switchtologin(e);

        } else {
            System.out.println("Falseee");
        }
    }


    private boolean adduser() throws SQLException {

        Connection Conn = Db.getConnection();

        if (Conn != null) {
            PreparedStatement adduser = Conn.prepareStatement("insert into tbl_user(user_name,email,phone_No,password) values (?,?,?,?)");
            adduser.setString(1, Fullname.getText());
            adduser.setString(2, EmailField.getText());
            adduser.setString(3, PhoneNo.getText());
            if (password.getText().equals(Confirm.getText())) {
                adduser.setString(4, password.getText());
            }
            else{
                System.out.println("Make epual");
            }
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


