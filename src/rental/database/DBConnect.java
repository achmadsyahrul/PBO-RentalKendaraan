package rental.database;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect{
    private Connection connection;
    public DBConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rental_kendaraan?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Connection getConnection(){
        return  connection;
    }
}
