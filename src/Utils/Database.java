package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TH_Tuan5","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
