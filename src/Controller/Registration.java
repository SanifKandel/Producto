package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registration {
    @FXML
    private Button createaccount;
    private TextField Fullname;
    private TextField Email;
    private TextField PhoneNo;
    private PasswordField Password;
    private PasswordField Confirm;
    private Text Login;
    private Circle red;

    public void redOnAction (ActionEvent event){
        Stage stage = (Stage) red.getScene().getWindow();
        stage.close();
    }






}
