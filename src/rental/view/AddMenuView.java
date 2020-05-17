package rental.view;

import rental.controller.KendaraanController;
import rental.controller.MenuController;
import rental.controller.MerkController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenuView extends JFrame implements ActionListener {
    private JButton btnMerk, btnKendaraan, btnBack;

    public AddMenuView(){
        setTitle("Menu");
        btnMerk = new JButton(" Tambah Merk ");
        btnKendaraan = new JButton(" Tambah Kendaraan ");
        btnBack = new JButton(" Kembali ");
        setLayout(new GridLayout(3,1));
        add(btnMerk);
        add(btnKendaraan);
        add(btnBack);
        pack();
        setResizable(false);
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnMerk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMerk.addActionListener(this);
        btnKendaraan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKendaraan.addActionListener(this);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnMerk){
            MerkController merk = new MerkController();
            merk.createMerk();
            dispose();
        }
        else if(e.getSource()==btnKendaraan){
            KendaraanController kendaraan = new KendaraanController();
            kendaraan.createKendaraan();
            dispose();
        }
        else{
            MenuController menu = new MenuController();
            menu.mainMenu();
            dispose();
        }
    }
}
