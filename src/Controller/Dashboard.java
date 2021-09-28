package Controller;

import DB.DatabaseConnection;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dashboard {
    Stage stage;
    Scene scene;
    Parent root;

    Connection Conn;
    DatabaseConnection Db = new DatabaseConnection();
    @FXML private AnchorPane rootstage;
    @FXML
    private TextField ProductIDField;
    @FXML
    private TextField ProductField;
    @FXML
    private TextField CategoryField;
    @FXML
    private TextField UnitCostField;
    @FXML
    private TextField QuantityField;
    @FXML
    private Button AddBtn;
    @FXML
    private Button Resetbtn;
    @FXML
    private Button Updatebtn;
    @FXML
    private Button Deletebtn;
    @FXML
    private Button red;
    @FXML
    private TableView TableView;
    @FXML
    private TableColumn IDlist;
    @FXML
    private TableColumn productlist;
    @FXML
    private TableColumn categorylist;
    @FXML
    private TableColumn unitlist;
    @FXML
    private TableColumn quantitylist;




    @FXML
    private boolean add() throws SQLException {

        Connection Conn = Db.getConnection();

        if (Conn != null) {
            PreparedStatement adduser = Conn.prepareStatement("insert into tbl_prdetails(product_id,product_name,category,unit_cost,quantity) values (?,?,?,?,?)");
            adduser.setString(1, ProductIDField.getText());
            adduser.setString(2, ProductField.getText());
            adduser.setString(3, CategoryField.getText());
            adduser.setString(4, UnitCostField.getText());
            adduser.setString(5, QuantityField.getText());
            adduser.executeUpdate();
            adduser.close();
            Conn.close();
            return true;

        } else {
            return false;
        }
    }
    @FXML
    private void clear() throws SQLException {
        ProductIDField.setText(" ");
        ProductField.setText(" ");
        CategoryField.setText(" ");
        UnitCostField.setText(" ");
        QuantityField.setText(" ");
    }

    @FXML
    public void onQuit(ActionEvent actionEvent){
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


}
