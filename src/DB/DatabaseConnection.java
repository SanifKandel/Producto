package DB;
import java.sql.*;

public class DatabaseConnection {
     Connection Conn;


    public  Connection getConnection () throws SQLException {

        if (Conn == null) {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/producto", "root", "#J@v@978");

        }
        return Conn;
    }



}
