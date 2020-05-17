package rental.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import rental.controller.RentalController;

public class PengembalianFormView extends JFrame implements ActionListener {
    private JLabel labelAmbil, isAmbil, labelTepatWaktu, isTepatWaktu, labelTgl, labelWaktu;
    private JDateChooser tglKembali;
    private JSpinner h,m,s;
    private JButton btnSubmit, btnReset, btnBack;
    private String id, idKend, tglTepat, tarif;

    public void openForm(String[] data){
        id = data[0];
        idKend = data[1];
        tglTepat = data[3];
        tarif = data[4];
        setTitle("Pengembalian");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,275);

        labelAmbil = new JLabel(" Tanggal Ambil : ");
        isAmbil = new JLabel(data[2]);
        labelTepatWaktu = new JLabel(" Berlaku Sampai : ");
        isTepatWaktu = new JLabel(data[3]);
        labelTgl = new JLabel(" Tanggal Kembali : ");
        labelWaktu = new JLabel(" Waktu Kembali : ");
        tglKembali = new JDateChooser();
        h = new JSpinner(new SpinnerNumberModel(0,0,23,1));
        m = new JSpinner(new SpinnerNumberModel(0,0,59,1));
        s = new JSpinner(new SpinnerNumberModel(0,0,59,1));
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelAmbil);
        add(isAmbil);
        add(labelTepatWaktu);
        add(isTepatWaktu);
        add(labelTgl);
        add(tglKembali);
        add(labelWaktu);
        add(h); add(m); add(s);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelAmbil.setBounds(10,10,120,20);
        isAmbil.setBounds(130, 10, 190, 20);
        labelTepatWaktu.setBounds(10,35,120,20);
        isTepatWaktu.setBounds(130, 35, 190, 20);
        labelTgl.setBounds(10,60,120,20);
        tglKembali.setBounds(130,60,190,20);
        tglKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelWaktu.setBounds(10, 85, 120, 20);
        h.setBounds(130,85,40,20);
        m.setBounds(170,85,40,20);
        s.setBounds(210,85,40,20);
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

            if(tglKembali.getDate() == null){
                setMessage("Tanggal kembali harus diisi");
            }
            else{
                try{
                    Date tanggal = tglKembali.getDate();
                    String tgl = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
                    tgl = tgl + " " + h.getValue() + ":" + m.getValue() + ":" + s.getValue();
                    String[] data = {
                            id, idKend, tglTepat, tgl, tarif
                    };
                    RentalController rental = new RentalController();
                    rental.pengembalian(data);
                    dispose();
                }
                catch (Exception ex){
                    setMessage(ex.toString());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            RentalController r = new RentalController();
            r.readRental();
        }
    }

    public void reset(){
        tglKembali.setDate(null);
        h.setValue(0); m.setValue(0); s.setValue(0);
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
