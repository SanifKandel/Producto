package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login {
    @FXML
    private Button login;
    private TextField Email;
    private PasswordField Password;
    private Circle red;
    private Circle yellow;
    private Text ForgetPassword;
    private Text CreateAccount;


    public void redOnAction (ActionEvent event){
        Stage stage = (Stage) red.getScene().getWindow();
        stage.close();
    }

    public void Hawa(){
        System.out.println("Hawa");
    }






}
