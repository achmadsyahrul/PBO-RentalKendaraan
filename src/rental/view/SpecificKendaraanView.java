package rental.view;

import rental.controller.KendaraanController;
import rental.controller.MerkController;
import rental.error.RentalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificKendaraanView extends JFrame implements ActionListener {
    private JLabel labelMerk, labelPlat, labelWarna, isMerk, isPlat, isWarna;
    private JButton btnUpdate, btnDelete, btnBack;
    private String data[] = new String [5];

    public void openDetail(String[] data){
        this.data = data; // {id, merk, plat, warna, status}
        setTitle(data[1] + " Plat " + data[2]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);
        labelMerk = new JLabel(" Merk : ");
        labelPlat = new JLabel(" Plat : ");
        labelWarna = new JLabel(" Warna : ");
        isMerk = new JLabel(data[1]);
        isPlat = new JLabel(data[2]);
        isWarna = new JLabel(data[3]);
        btnUpdate = new JButton("Edit");
        btnDelete = new JButton("Hapus");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelMerk);
        add(isMerk);
        add(labelPlat);
        add(isPlat);
        add(labelWarna);
        add(isWarna);
        add(btnUpdate);
        add(btnDelete);
        add(btnBack);

        labelMerk.setBounds(10,10,120,20);
        isMerk.setBounds(130,10,190,20);
        labelPlat.setBounds(10,35,120,20);
        isPlat.setBounds(130,35,190,20);
        labelWarna.setBounds(10,60,120,20);
        isWarna.setBounds(130,60,190,20);
        btnBack.setBounds(10,135,90,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);
        btnUpdate.setBounds(110,135,90,20);
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.setBackground(Color.blue);
        btnUpdate.setForeground(Color.white);
        btnUpdate.addActionListener(this);
        btnDelete.setBounds(210,135,90,20);
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.setBackground(Color.red);
        btnDelete.setForeground(Color.white);
        btnDelete.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnUpdate){
            KendaraanController kc = new KendaraanController();
            kc.updateKendaraan(data); //{id, merk, plat, warna, status}
            dispose();
        }
        else if(e.getSource()==btnDelete){
            KendaraanController controller = new KendaraanController();
            try {
                controller.deleteData(data); //{id, merk, plat, warna, status}
                dispose();
                controller.readKendaraan(data[1]);
            } catch (RentalException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else{
            KendaraanController controller = new KendaraanController();
            controller.readKendaraan(data[1]);
            dispose();
        }
    }
}

