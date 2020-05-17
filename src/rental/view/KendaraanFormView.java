package rental.view;

import rental.controller.KendaraanController;
import rental.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KendaraanFormView extends JFrame implements ActionListener {
    private JTextField fieldPlat, fieldWarna;
    private JLabel labelMerk, labelPlat, labelWarna;
    private JComboBox cmbMerk;
    private String[] namaMerk;
    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(String[] merk){
        namaMerk = merk;
        setTitle("Tambah Kendaraan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);

        fieldPlat = new JTextField(10);
        fieldWarna = new JTextField(10);
        labelMerk = new JLabel(" Merk ");
        labelPlat = new JLabel(" Plat ");
        labelWarna = new JLabel(" Warna ");
        cmbMerk = new JComboBox(namaMerk);
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelMerk);
        add(fieldPlat);
        add(labelPlat);
        add(cmbMerk);
        add(labelWarna);
        add(fieldWarna);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelMerk.setBounds(10,10,120,20);
        cmbMerk.setBounds(130,10,190,20);
        labelPlat.setBounds(10,35,120,20);
        fieldPlat.setBounds(130,35,190,20);
        labelWarna.setBounds(10,60,120,20);
        fieldWarna.setBounds(130,60,190,20);
        btnSubmit.setBounds(75,100,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,100,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,135,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {
            if (cmbMerk.getSelectedIndex() == 0) {
                setMessage("Merk harus diisi");
            }
            if (fieldPlat.getText().equals("")) {
                setMessage("Plat harus diisi");
            }
            if (fieldWarna.getText().equals("")) {
                setMessage("Warna harus diisi");
            }
            else{
                KendaraanController kendaraan = new KendaraanController();
                kendaraan.createKendaraan(cmbMerk.getSelectedItem().toString(),fieldPlat.getText(),fieldWarna.getText());
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else{
            dispose();
            MenuController menu = new MenuController();
            menu.add();
        }
    }

    public void reset() {
        fieldPlat.setText("");
        fieldWarna.setText("");
        cmbMerk.setSelectedIndex(0);
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
