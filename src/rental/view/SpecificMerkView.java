package rental.view;

import rental.controller.KendaraanController;
import rental.controller.MerkController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificMerkView extends JFrame implements ActionListener {
    private JLabel labelMerk, labelKategori, labelTarif, labelKendaraan, isMerk, isKategori, isTarif;
    private JButton btnKendaraan, btnUpdate, btnDelete, btnBack;
    private String data[] = new String [3];

    public void openDetail(String[] data){
        this.data = data;
        setTitle("Merk " + data[0]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);
        labelMerk = new JLabel(" Merk : ");
        labelKategori = new JLabel(" Kategori : ");
        labelTarif = new JLabel(" Tarif : ");
        labelKendaraan = new JLabel(" Semua Kendaraan : ");
        isMerk = new JLabel(data[0]);
        isKategori = new JLabel(data[1]);
        isTarif = new JLabel(data[2]);
        btnKendaraan = new JButton("Show");
        btnUpdate = new JButton("Edit");
        btnDelete = new JButton("Hapus");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelMerk);
        add(isMerk);
        add(labelKategori);
        add(isKategori);
        add(labelTarif);
        add(isTarif);
        add(labelKendaraan);
        add(btnKendaraan);
        add(btnUpdate);
        add(btnDelete);
        add(btnBack);

        labelMerk.setBounds(10,10,120,20);
        isMerk.setBounds(130,10,190,20);
        labelKategori.setBounds(10,35,120,20);
        isKategori.setBounds(130,35,190,20);
        labelTarif.setBounds(10,60,120,20);
        isTarif.setBounds(130,60,190,20);
        labelKendaraan.setBounds(10, 85, 130, 20);
        btnKendaraan.setBounds(150, 85, 90, 20);
        btnKendaraan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKendaraan.addActionListener(this);
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
            MerkController merkController = new MerkController();
            merkController.updateMerk("merk",data[0]);
            dispose();
        }
        else if(e.getSource()==btnDelete){
            MerkController merkController = new MerkController();
            merkController.deleteMerk(data[0]);
            dispose();
        }
        else if(e.getSource()==btnKendaraan){
            KendaraanController kendaraan = new KendaraanController();
            kendaraan.readKendaraan(data[0]);
            dispose();
        }
        else{
            MerkController merkController = new MerkController();
            merkController.readMerk();
            dispose();
        }
    }
}
