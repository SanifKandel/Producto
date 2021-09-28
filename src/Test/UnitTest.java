package Test;

import DB.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;


public class UnitTest {


    DatabaseConnection Db = new DatabaseConnection();
    boolean user = false;


    @Before
    public void init() throws SQLException {
        Connection Conn = Db.getConnection();

        if (Conn != null) {
            PreparedStatement adduser = Conn.prepareStatement("select * from tbl_user WHERE email= ? AND password = ?");
            adduser.setString(1, "sanif@gmail.com");
            adduser.setString(2, "Sanif123");
            ResultSet r = adduser.executeQuery();
            if (r.next()) {
                adduser.close();
                user = true;
            } else {
                adduser.close();
                user = false;
            }

        }
    }

    @Test
    public void checkUser() {
        assertTrue(user);

    }

    @Test
    public void delete() throws SQLException {
        boolean actual = false;
        Connection Conn = Db.getConnection();
        PreparedStatement adduser = Conn.prepareStatement("DELETE FROM tbl_prdetails WHERE product_id  = ?");
        adduser.setString(1,"3");
        int save = adduser.executeUpdate();
        if (save != 0){
            actual =true;
        }
        assertTrue(actual);

    }

}


