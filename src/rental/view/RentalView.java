package rental.view;

import rental.controller.MenuController;
import rental.controller.MerkController;
import rental.controller.RentalController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;

    public RentalView(String[][] data){
        try{
            final String[] tableTitle = {"NIK", "Nama", "Merk", "Plat", "Tanggal", "Lama Sewa", "Tarif"};
            setTitle("Data Rental");
            setSize(900,375);

            btnBack = new JButton(" Kembali ");
            table = new JTable(data,tableTitle);
            table.setBounds(30,40,400,600);
            JScrollPane sp=new JScrollPane(table);
            sp.setPreferredSize(new Dimension(500,80));
            this.getContentPane().add(BorderLayout.CENTER, sp);

            this.getContentPane().add(BorderLayout.SOUTH, btnBack);
            btnBack.addActionListener(this);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            table.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    String NIK = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String plat = table.getValueAt(table.getSelectedRow(), 3).toString();
                    RentalController r = new RentalController();
                    r.pengembalian(NIK, plat);
                    dispose();
                }
            });
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(200,150);
            setVisible(true);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            MenuController menu = new MenuController();
            menu.mainMenu();
            dispose();
        }
    }
}
