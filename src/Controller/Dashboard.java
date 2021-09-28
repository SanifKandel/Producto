package Controller;

import Classes.Products;
import DB.DatabaseConnection;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Dashboard implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;


    Connection Conn;
    DatabaseConnection Db = new DatabaseConnection();
    @FXML
    private AnchorPane rootstage;
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
    public TableView<Products> tableView;


    @FXML
    private TableColumn<Products, Integer> IDlist;
    @FXML
    private TableColumn<Products, String> productlist;
    @FXML
    private TableColumn<Products, String> categorylist;
    @FXML
    private TableColumn<Products, String> unitlist;
    @FXML
    private TableColumn<Products, Integer> quantitylist;


    // This Method is for Add button
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
            showProduct();
            adduser.close();
            return true;

        } else {
            return false;
        }
    }

    // This Method is for Reset button
    @FXML
    private void clear() throws SQLException {
        ProductIDField.setText(" ");
        ProductField.setText(" ");
        CategoryField.setText(" ");
        UnitCostField.setText(" ");
        QuantityField.setText(" ");
    }

    // This Method is for delete button

    @FXML
    private void delete() throws SQLException {
        Statement st;
        Products selected;
        Connection Conn = Db.getConnection();
        selected = tableView.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM tbl_prdetails WHERE product_id  =" + selected.getId();
        try {
            st = Conn.prepareStatement(query);
            st.execute(query);
            showProduct();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    // This method is to select a row in Tableview
    @FXML
    private void select() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;

        }
        ProductIDField.setText(IDlist.getCellData(index).toString());
        ProductField.setText(productlist.getCellData(index));
        CategoryField.setText(categorylist.getCellData(index));
        UnitCostField.setText(unitlist.getCellData(index));
        QuantityField.setText(quantitylist.getCellData(index).toString());
    }

    //This method is for update button
    @FXML
    private boolean update() throws SQLException {
        Connection Conn = Db.getConnection();
        if (Conn != null) {

            PreparedStatement updateuser = Conn.prepareStatement("Update tbl_prdetails set product_id = ?,  product_name = ?,category = ?,unit_cost = ?,quantity = ? WHERE product_id=" + ProductIDField.getText());
            updateuser.setString(1, ProductIDField.getText());
            updateuser.setString(2, ProductField.getText());
            updateuser.setString(3, CategoryField.getText());
            updateuser.setString(4, UnitCostField.getText());
            updateuser.setString(5, QuantityField.getText());
            updateuser.executeUpdate();
            showProduct();
            updateuser.close();
            return true;
        } else {
            return false;

        }
    }


    // This method is to display data in Table View
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showProduct();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public ObservableList<Products> getProductList() throws SQLException {
        ObservableList<Products> productList = FXCollections.observableArrayList();
        Connection Conn = Db.getConnection();
        String query = "SELECT * FROM tbl_prdetails ";
        Statement st;
        ResultSet rs;

        try {
            st = Conn.createStatement();
            rs = st.executeQuery(query);
            Products products;
            while (rs.next()) {
                products = new Products(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("category"), rs.getString("unit_cost"), rs.getInt("quantity"));
                productList.add(products);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void showProduct() throws SQLException {
        ObservableList<Products> list = getProductList();

        IDlist.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        productlist.setCellValueFactory(new PropertyValueFactory<Products, String>("p_name"));
        categorylist.setCellValueFactory(new PropertyValueFactory<Products, String>("ctg"));
        unitlist.setCellValueFactory(new PropertyValueFactory<Products, String>("u_cost"));
        quantitylist.setCellValueFactory(new PropertyValueFactory<Products, Integer>("qnt"));

        tableView.setItems(list);
    }


    // This method is used by close button/icon to quit the window.
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


}
