package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author HikmatD
 */
public class DBConnection {
    public Connection con;
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String pass = "root";
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

    public Connection mkDataBase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    public Connection geConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url + "StorKeeper2" + unicode, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");
        }

        return con;
    }
}
