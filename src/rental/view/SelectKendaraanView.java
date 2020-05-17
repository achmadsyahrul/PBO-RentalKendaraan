package rental.view;

import rental.controller.CustomerController;
import rental.controller.RentalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectKendaraanView extends JFrame implements ActionListener {
    private JLabel labelTitle, labelMerk, labelPlat, labelWarna, isMerk, isWarna;
    private JComboBox cmbPlat;
    private JButton btnSubmit, btnCancel, btnShow;
    private String warna = "";
    private String[] data;
    public void openForm(String[] plat, String[] data){
        this.data = data;
        setTitle("Pilih Kendaraan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);

        labelTitle = new JLabel("Pilih Kendaraan");
        labelMerk = new JLabel(" Merk : ");
        isMerk = new JLabel(data[3]);
        labelPlat = new JLabel(" Plat : ");
        labelWarna = new JLabel(" Warna : ");
        isWarna = new JLabel(warna);
        cmbPlat = new JComboBox(plat);
        btnSubmit = new JButton("Pilih");
        btnCancel = new JButton("Batal");
        btnShow = new JButton("Lihat");

        setLayout(null);
        add(labelTitle);
        add(labelMerk);
        add(isMerk);
        add(labelPlat);
        add(cmbPlat);
        add(labelWarna);
        add(isWarna);
        add(btnSubmit);
        add(btnCancel);
        add(btnShow);

        labelTitle.setBounds(100,10,120,20);
        labelMerk.setBounds(10,35,120,20);
        isMerk.setBounds(100,35,190,20);
        labelPlat.setBounds(10,60,120,20);
        cmbPlat.setBounds(100,60,190,20);
        labelWarna.setBounds(10,85,120,20);
        isWarna.setBounds(100,85,190,20);
        btnSubmit.setBounds(120,125,90,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnCancel.setBounds(230,125,90,20);
        btnCancel.setBackground(Color.red);
        btnCancel.setForeground(Color.white);
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(this);
        btnShow.setBounds(10,125,90,20);
        btnShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnShow.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {
            if (cmbPlat.getSelectedIndex() == 0) {
                setMessage("Plat harus diisi");
            }
            else{
                String[] data = {
                        this.data[0], this.data[1], this.data[2], this.data[3], cmbPlat.getSelectedItem().toString()
                };
                RentalController r = new RentalController();
                r.rental(data);
                dispose();
            }
        }
        else if(e.getSource()==btnCancel){
            new CustomerController().searchData();
            dispose();
        }
        else{
            RentalController r = new RentalController();
            warna = r.getWarna(cmbPlat.getSelectedItem().toString());
            isWarna.setText(warna);
        }
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
