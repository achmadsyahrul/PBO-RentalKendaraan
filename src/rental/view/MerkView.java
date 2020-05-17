package rental.view;

import rental.controller.MenuController;
import rental.controller.MerkController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MerkView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;

    public MerkView(String[][] data){
        try{
            final String[] tableTitle = {"Merk", "Kategori", "Banyak","Tarif"};
            setTitle("Data Merk Kendaraan");
            setSize(600,250);

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
                    dispose();
                    String merk = table.getValueAt(table.getSelectedRow(), 0).toString();
                    MerkController merkController = new MerkController();
                    merkController.specificMerk("merk", merk);
                }
            });
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(350,200);
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
