package rental.view;

import rental.controller.CustomerController;
import rental.controller.MenuController;
import rental.controller.MerkController;
import rental.controller.RentalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {
    private JLabel labelMenu;
    private JButton btnAdd, btnMerk, btnSewa, btnPengembalian, btnHasil;

    public MenuView(){
        setTitle("Menu");
        labelMenu = new JLabel("Main Menu");
        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
        btnAdd = new JButton(" Tambah Kendaraan ");
        btnMerk = new JButton(" Merk Kendaraan ");
        btnSewa = new JButton(" Sewa Kendaraan ");
        btnPengembalian = new JButton(" Pengembalian ");
        btnHasil = new JButton(" Penghasilan ");
        setLayout(new GridLayout(6,1));
        add(labelMenu);
        add(btnAdd);
        add(btnMerk);
        add(btnSewa);
        add(btnPengembalian);
        add(btnHasil);
        pack();
        setResizable(false);
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMerk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSewa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPengembalian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHasil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(this);
        btnMerk.addActionListener(this);
        btnSewa.addActionListener(this);
        btnPengembalian.addActionListener(this);
        btnHasil.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnAdd){
            MenuController menu = new MenuController();
            menu.add();
            dispose();
        }
        else if(e.getSource()== btnMerk){
            MerkController merk = new MerkController();
            merk.readMerk();
            dispose();
        }
        else if(e.getSource()== btnSewa){
            CustomerController customerController = new CustomerController();
            customerController.searchData();
            dispose();
        }
        else if(e.getSource()== btnPengembalian){
            RentalController rentalController = new RentalController();
            rentalController.readRental();
            dispose();
        }
        else{
            dispose();
            new PenghasilanView().openForm();
        }
    }
}
