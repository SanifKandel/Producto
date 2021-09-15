package DB;
import java.sql.*;

public class DatabaseConnection {
    Connection Conn;
    Statement st;
    int ans;

    public DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/producto","root","#J@v@978");
//            if (Conn!= null){
//                System.out.println("Database is connected");
//            }
            st = Conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DatabaseConnection();
    }

}
