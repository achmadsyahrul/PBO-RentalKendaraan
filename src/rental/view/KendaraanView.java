package rental.view;

import rental.controller.KendaraanController;
import rental.controller.MerkController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KendaraanView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;
    private String merk, plat;
    public KendaraanView(String[][] data){
        try{
            merk=data[0][0];
            final String[] tableTitle = {"Merk", "Plat", "Warna","Status"};
            setTitle("Data Kendaraan");
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
                    plat = table.getValueAt(table.getSelectedRow(), 1).toString();
                    KendaraanController kendaraanController = new KendaraanController();
                    kendaraanController.specificKendaraan("plat", plat);
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
            MerkController merkController = new MerkController();
            merkController.specificMerk("merk", merk);
            dispose();
        }
    }
}
