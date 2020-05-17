package rental.view;

import com.toedter.calendar.JDateChooser;
import rental.controller.MenuController;
import rental.controller.RentalController;
import rental.error.RentalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PenghasilanView extends JFrame implements ActionListener {
    private JLabel labelDateStart, labelDateStop, labelPenghasilan;
    private JDateChooser dateStart, dateStop;
    private JButton btnSubmit, btnReset, btnBack;
    private String penghasilan;

    public void openForm(){
        setTitle("Penghasilan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,275);

        labelDateStart = new JLabel(" Mulai Tanggal : ");
        labelDateStop = new JLabel(" Sampai Tanggal : ");
        labelPenghasilan = new JLabel("");
        dateStart = new JDateChooser();
        dateStop = new JDateChooser();
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelDateStart);
        add(labelDateStop);
        add(labelPenghasilan);
        add(dateStart);
        add(dateStop);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelDateStart.setBounds(10,10,120,20);
        dateStart.setBounds(130, 10, 190, 20);
        labelDateStop.setBounds(10,35,120,20);
        dateStop.setBounds(130, 35, 190, 20);
        labelPenghasilan.setBounds(10, 60, 300,20);
        btnSubmit.setBounds(75,175,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,175,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,210,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {

            if(dateStart.getDate() == null){
                setMessage("Tanggal awal harus diisi");
            }
            if(dateStop.getDate() == null){
                setMessage("Tanggal akhir harus diisi");
            }
            else{
                try{
                    Date start = dateStart.getDate();
                    Date stop = dateStop.getDate();
                    String tglAwal = new SimpleDateFormat("dd-MMM-yyyy").format(start); //menyesuaikan model
                    String tglAkhir = new SimpleDateFormat("yyyy-MM-dd").format(stop);
                    tglAwal = tglAwal + " 00:00:00";
                    tglAkhir = tglAkhir + " 23:59:59";
                    RentalController rental = new RentalController();
                    labelPenghasilan.setText("Penghasilan = Rp. "+rental.getPenghasilan(tglAwal, tglAkhir));
                }
                catch (RentalException ex){
                    setMessage(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            new MenuController().mainMenu();
        }
    }

    public void reset(){
        dateStart.setDate(null);
        dateStop.setDate(null);
        labelPenghasilan.setText("");
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}

