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
        adduser.setString(1,"18");
        int save = adduser.executeUpdate();
        if (save != 0){
            actual =true;
        }
        assertTrue(actual);
    }

    @Test
    public void register() throws SQLException {
        boolean actual = false;
        Connection Conn = Db.getConnection();
        PreparedStatement adduser = Conn.prepareStatement("insert into tbl_prdetails(product_id,product_name,category,unit_cost,quantity) values (?,?,?,?,?)");
        adduser.setString(1, "19");
        adduser.setString(2,"Earphone");
        adduser.setString(3, "Gadgets");
        adduser.setString(4, "50");
        adduser.setString(5, "20");
        int save = adduser.executeUpdate();
        if (save != 0){
            actual =true;
        }
        assertTrue(actual);
    }

    @Test
    public void update() throws SQLException {
        boolean actual = false;
        Connection Conn = Db.getConnection();
        PreparedStatement adduser = Conn.prepareStatement("Update tbl_prdetails set product_id = ?,  product_name = ?,category = ?,unit_cost = ?,quantity = ? WHERE product_id= 7" );
        adduser.setString(1, "8");
        adduser.setString(2,"MacBook Pro");
        adduser.setString(3, "Gadgets");
        adduser.setString(4, "1,90,000");
        adduser.setString(5, "20");
        int save = adduser.executeUpdate();
        if (save != 0){
            actual =true;
        }
        assertTrue(actual);
    }



}


