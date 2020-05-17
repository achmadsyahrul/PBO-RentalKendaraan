package rental.model;

import rental.database.DBConnect;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RentalModel {
    private Connection connection;
    private Statement statement;

    public RentalModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void insertRental(String[] data){
        try{
            String tarif = getData("tarif", "merk", "merk", data[3]);
            String id = getData("id", "kendaraan", "plat", data[4]);
            String query = " insert into menyewa (NIK, tgl_sewa, lama, tarif, id_kend) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, data[0]);
            preparedStmt.setString (2, data[1]);
            preparedStmt.setString (3, data[2]);
            preparedStmt.setString (4, tarif);
            preparedStmt.setString (5, id);
            preparedStmt.execute();
            updateStatus(id, "0");
            JOptionPane.showMessageDialog(null, "Input Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[][] readRental(){
        try{
            int row=0;
            String[][] data = new String[numRows("menyewa", "tgl_kembali")][7];
            String query = "select c.NIK, c.Nama, k.merk, k.plat, tgl_sewa, lama, tarif from menyewa m " +
                    "left join customer c on m.NIK = c.NIK " +
                    "left join kendaraan k on m.id_kend = k.id where m.tgl_kembali is null";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("NIK");
                data[row][1] = resultSet.getString("nama");
                data[row][2] = resultSet.getString("merk");
                data[row][3] = resultSet.getString("plat");
                data[row][4] = resultSet.getString("tgl_sewa");
                data[row][5] = resultSet.getString("lama");
                data[row][6] = resultSet.getString("tarif");
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

    public String[] readRental(String NIK, String plat){
        try{
            String id = getData("id", "kendaraan", "plat", plat); //idkendaraan
            String[] data = new String[5];
            int lama = 0;
            statement = connection.createStatement();
            String query = "select * from menyewa where NIK = '" + NIK + "' and id_kend = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("id");
                data[1] = resultSet.getString("id_kend");
                data[2] = resultSet.getString("tgl_sewa");
                lama = resultSet.getInt("lama"); //data3
                data[4] = resultSet.getString("tarif");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar ambil = Calendar.getInstance();
            Calendar harusKembali = Calendar.getInstance();
            try{
                ambil.setTime(format.parse(data[2]));
                harusKembali.setTime(format.parse(data[2]));
            }catch(ParseException e){
                e.printStackTrace();
            }
            harusKembali.add(Calendar.DAY_OF_MONTH, lama);
            format.applyPattern("dd-MMM-yyyy HH:mm:ss");
            data[2] = format.format(ambil.getTime());
            data[3] = format.format(harusKembali.getTime());

            statement.close();
            connection.close();
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void updateStatus(String id, String value){
        try{
            statement = connection.createStatement();
            String query = "update kendaraan set status = ? where id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, value);
            preparedStmt.setString (2, id);
            preparedStmt.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String getData(String column, String table, String where, String value){
        try{
            String data = new String();
            statement = connection.createStatement();
            String query = "select "+column+" from "+table+" where "+where+" = '" + value + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data = resultSet.getString(column);
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void updateRental(String[] data){
        try{
            int tarif = Integer.parseInt(data[4]);
            String dateStart = data[2]; //tanggal tepat waktu
            String dateStop = data[3]; //tanggal kembali
            long diff = getDiff(dateStart, dateStop);
            long denda =0;
            if(diff>0){
                long terlambat = diff / (60 * 60 * 1000); //selisih jam
                denda = (tarif/12)*terlambat;
            }
            long total = tarif + denda;

            statement = connection.createStatement();
            String query = "update menyewa set tgl_kembali = ?, denda = ?, total = ? where id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, dateStop);
            preparedStmt.setLong (2, denda);
            preparedStmt.setLong (3, total);
            preparedStmt.setString (4, data[0]);
            preparedStmt.execute();
            updateStatus(data[1], "1");
            JOptionPane.showMessageDialog(null,
                    "Pengembalian berhasil\n denda = "+denda+"\n total = "+total);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException num) {
            JOptionPane.showMessageDialog(null, num.getMessage());
        }
    }

    public long getDiff(String dateStart, String dateStop){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = input.parse(dateStop);
            input.applyPattern("dd-MMM-yyyy HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = d2.getTime() - d1.getTime();
        return diff;
    }

    public String getPenghasilan(String dateStart, String dateStop){
        try{
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(dateStart);
                d2 = input.parse(dateStop);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //supaya query sql tidak error
            dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1);
            dateStop = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d2);
            String data="";
            statement = connection.createStatement();
            String query = "select sum(total) as penghasilan from menyewa where tgl_kembali " +
                    "between '" + dateStart + "' and '" + dateStop + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data = resultSet.getString("penghasilan");
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String[] getPlat(String merk){
        try{
            int rows = 1;
            String[] data = new String[numRowsPlat(merk)+1];
            statement = connection.createStatement();
            String query = "select * from kendaraan where merk = '" + merk + "' and status = 1";
            ResultSet resultSet = statement.executeQuery(query);
            data[0] = "";
            while (resultSet.next()){
                data[rows] = resultSet.getString("plat");
                rows++;
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String getWarna(String where){
        try{
            String data = new String();
            statement = connection.createStatement();
            String query = "select * from kendaraan where plat = '" + where + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data = resultSet.getString("warna");
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public int numRows(String table, String where){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from " + table + " where " + where +" is null";
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

    public int numRowsPlat(String merk){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from kendaraan where merk = '" + merk + "' and status = '1'";
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
