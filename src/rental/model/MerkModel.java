package rental.model;

import rental.controller.MerkController;
import rental.database.DBConnect;

import javax.swing.*;
import java.sql.*;

public class MerkModel {
    private Connection connection;
    private Statement statement;

    public MerkModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void createMerk(String merk, String kategori, int tarif){
        try{
            String query = " insert into merk (merk, kategori, tarif) values (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, merk);
            preparedStmt.setString (2, kategori);
            preparedStmt.setInt (3, tarif);
            preparedStmt.execute();
            connection.close();
            JOptionPane.showMessageDialog(null, "Input Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[] getMerk(){
        try{
            int row=1;
            String[] data = new String[numRows("merk")+1];
            String query = "select merk from merk";
            ResultSet resultSet = statement.executeQuery(query);
            data[0] = "";
            while (resultSet.next()){
                data[row] = resultSet.getString("merk");
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

    public String[][] readMerk(){
        try{
            int row=0;
            String[][] data = new String[numRows("merk")][4];
            String query = "select m.merk, kategori, count(m.merk) as banyak, tarif from kendaraan k left join merk m on m.merk = k.merk group by merk";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("merk");
                data[row][1] = resultSet.getString("kategori");
                data[row][2] = resultSet.getString("banyak");
                data[row][3] = resultSet.getString("tarif");
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

    public String[] readMerk(String column, String where){
        try{
            String[] data = new String[4];
            statement = connection.createStatement();
            String query = "select * from merk where " + column + " = '" + where + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("merk");
                data[1] = resultSet.getString("kategori");
                data[2] = resultSet.getString("tarif");
                data[3] = resultSet.getString("id");
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

    public void updateMerk(String merk, String kategori, int tarif, String id){
        try{
            statement = connection.createStatement();
            String query = "SET FOREIGN_KEY_CHECKS = 0";
            statement.executeQuery(query);
            query = "update merk m left join kendaraan k on m.merk = k.merk set m.merk = ?, kategori = ?, tarif = ?, k.merk = ? where m.id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, merk);
            preparedStmt.setString (2, kategori);
            preparedStmt.setInt (3, tarif);
            preparedStmt.setString (4, merk);
            preparedStmt.setString(5, id);
            preparedStmt.execute();
            query = "SET FOREIGN_KEY_CHECKS = 1";
            statement.executeQuery(query);
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteMerk(String merk){
        try {
            String query = " delete from merk where merk = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, merk);

            preparedStmt.execute();

            connection.close();

            JOptionPane.showMessageDialog(null, "Delete Berhasil");

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal! Hapus dulu kendaraan dengan merk " + merk);
            MerkController merkController = new MerkController();
            merkController.specificMerk("merk", merk);
        }
    }

    public int numRows(String table){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from " + table;
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
