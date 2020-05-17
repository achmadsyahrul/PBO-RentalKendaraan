package rental.model;

import rental.database.DBConnect;

import javax.swing.*;
import java.sql.*;

public class KendaraanModel extends MerkModel{
    private Connection connection;
    private Statement statement;

    public KendaraanModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void createKendaraan(String merk, String plat, String warna){
        try{
            String query = " insert into kendaraan (merk, plat, warna, status) values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, merk);
            preparedStmt.setString (2, plat);
            preparedStmt.setString (3, warna);
            preparedStmt.setInt (4,1);
            preparedStmt.execute();
            connection.close();
            JOptionPane.showMessageDialog(null, "Input Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[][] readKendaraan(String merk){
        try{
            int row=0;
            String[][] data = new String[numRows("kendaraan", merk)][4];
            String query = "select m.merk, plat, warna, status from kendaraan k left join merk m on m.merk = k.merk where m.merk = '" + merk + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("merk");
                data[row][1] = resultSet.getString("plat");
                data[row][2] = resultSet.getString("warna");
                if(resultSet.getString("status").equals("1")){
                    data[row][3] = "Tersedia";
                }
                else{
                    data[row][3] = "Disewakan";
                }
                row++;
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

    public String[] readKendaraan(String where, String value){
        try{
            String[] data = new String[5];
            statement = connection.createStatement();
            String query = "select * from kendaraan where " + where + " = '" + value + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("id");
                data[1] = resultSet.getString("merk");
                data[2] = resultSet.getString("plat");
                data[3] = resultSet.getString("warna");
                data[4] = resultSet.getString("status");
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

    public void updateKendaraan(String[] data){
        try{
            statement = connection.createStatement();
            String query = "update kendaraan set merk = ?, plat = ?, warna = ? where id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, data[1]);
            preparedStmt.setString (2, data[2]);
            preparedStmt.setString (3, data[3]);
            preparedStmt.setString (4, data[0]);
            preparedStmt.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteData(String id){
        try {
            statement = connection.createStatement();
            String query = "SET FOREIGN_KEY_CHECKS = 0";
            statement.executeQuery(query);

            query = " delete from kendaraan where id = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, id);

            preparedStmt.execute();
            query = "SET FOREIGN_KEY_CHECKS = 1";
            statement.executeQuery(query);
            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "Delete Berhasil");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public int numRows(String table) {
        return super.numRows(table);
    }

    public int numRows(String table, String where){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from " + table + " where merk = '" + where + "'";
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
