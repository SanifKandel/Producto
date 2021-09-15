package Controller;

import DB.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Registration {
    Connection Conn;
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

    public void redOnAction (ActionEvent event){
        Stage stage = (Stage) red.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void register(){
        if(true){
            try {
                System.out.println("Success");
                adduser();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Falseee");
        }
    }

    private void adduser() throws SQLException {

//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/producto","root","#J@v@978");
            if(Conn != null){
            PreparedStatement adduser = Conn.prepareStatement("insert into tbl_user values (?,?,?,?)");
            adduser.setString(1,Fullname.getText());
            adduser.setString(2,EmailField.getText());
            adduser.setString(3,PhoneNo.getText());
            adduser.setString(4,password.getText());
            adduser.close();
            Conn.close();

            }
            else{
                System.out.println("Database is not connected");
            }


    }
}


//        if(fname.equals(""))
//        {
//            .showMessageDialog(null, "Add A Username");
//        }
//
//        else if(pass.equals(""))
//        {
//            JOptionPane.showMessageDialog(null, "Add A Password");
//        }
//        else if(!pass.equals(re_pass))
//        {
//            JOptionPane.showMessageDialog(null, "Retype The Password Again");
//        }

//        else if(checkUsername(uname))
//        {
//            JOptionPane.showMessageDialog(null, "This Username Already Exist");
//        }
//
//        else{
//
//            PreparedStatement ps;
//            String query = "INSERT INTO `the_app_users`(`u_fname`, `u_lname`, `u_uname`, `u_pass`, `u_bdate`, `u_address`) VALUES (?,?,?,?,?,?)";
//
//            try {
//                ps = DatabaseConnection.getConnection().prepareStatement(query);
//
//                ps.setString(1, fname);
//                ps.setString(2, );
//                ps.setString(3, uname);
//                ps.setString(4, pass);
//
//                if(bdate != null)
//                {
//                    ps.setString(5, bdate);
//                }else{
//                    ps.setNull(5, 0);
//                }
//                ps.setString(6, address);
//
//                if(ps.executeUpdate() > 0)
//                {
//                    JOptionPane.showMessageDialog(null, "New User Add");
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//
//
//
//
//}
