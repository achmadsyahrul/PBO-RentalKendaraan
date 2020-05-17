package rental.model;

import rental.database.DBConnect;

import javax.swing.*;
import java.sql.*;

public class CustomerModel {
    private Connection connection;
    private Statement statement;

    public CustomerModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public String[] searchData(String NIK){
        try{
            String[] data = {
                NIK, "", "", "", ""
            };
            statement = connection.createStatement();
            String query = "select * from customer where NIK = '" + NIK + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("NIK");
                data[1] = resultSet.getString("nama");
                data[2] = resultSet.getString("no_hp");
                data[3] = resultSet.getString("alamat");
                data[4] = resultSet.getString("id");
            }
            statement.close();
            connection.close();
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void submitData(String[] data){
        int row = numRows("NIK", data[0]);
        if(row==0){
            createData(data);
        }
        else{
            updateData(data);
        }
    }

    public void createData(String[] data){
        try{
            String query = " insert into customer (NIK, nama, no_hp, alamat) values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, data[0]);
            preparedStmt.setString (2, data[1]);
            preparedStmt.setString (3, data[2]);
            preparedStmt.setString (4, data[3]);

            preparedStmt.execute();
            connection.close();
            JOptionPane.showMessageDialog(null, "Input Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void updateData(String[] data){
        try{
            statement = connection.createStatement();
            String query = "update customer set NIK = ?, nama = ?, no_hp = ?, alamat = ? where id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, data[0]);
            preparedStmt.setString (2, data[1]);
            preparedStmt.setString (3, data[2]);
            preparedStmt.setString (4, data[3]);
            preparedStmt.setString(5, data[4]);
            preparedStmt.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public int numRows(String where, String value){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from customer where " + where + " = '" + value + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }
            return jmlData;

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
}
