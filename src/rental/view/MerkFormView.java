package rental.view;

import rental.controller.MenuController;
import rental.controller.MerkController;
import rental.error.RentalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MerkFormView extends JFrame implements ActionListener {
    private JTextField fieldMerk, fieldTarif;
    private JLabel labelMerk, labelKategori, labelTarif;
    private JComboBox cmbKategori;
    private String[] namaKategori =
            {"","Mobil","Motor"};
    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(){
        setTitle("Tambah Merk");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);

        fieldMerk = new JTextField(10);
        fieldTarif = new JTextField(10);
        labelMerk = new JLabel(" Merk ");
        labelKategori = new JLabel(" Kategori ");
        labelTarif = new JLabel(" Tarif ");
        cmbKategori = new JComboBox(namaKategori);
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelMerk);
        add(fieldMerk);
        add(labelKategori);
        add(cmbKategori);
        add(labelTarif);
        add(fieldTarif);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelMerk.setBounds(10,10,120,20);
        fieldMerk.setBounds(130,10,190,20);
        labelKategori.setBounds(10,35,120,20);
        cmbKategori.setBounds(130,35,190,20);
        labelTarif.setBounds(10,60,120,20);
        fieldTarif.setBounds(130,60,190,20);
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
            if (fieldMerk.getText().equals("")) {
                setMessage("Merk harus diisi");
            }
            if (cmbKategori.getSelectedIndex() == 0) {
                setMessage("Kategori harus diisi");
            }
            if (fieldTarif.getText().equals("")) {
                setMessage("Tarif harus diisi");
            }
            else {
                try {
                    int tarif = Integer.parseInt(fieldTarif.getText());
                    MerkController merk = new MerkController();
                    merk.cekTarif(tarif);
                    merk.createMerk(fieldMerk.getText(), cmbKategori.getSelectedItem().toString(), tarif);
                } catch (NumberFormatException num) {
                    setMessage("Tarif harus bilangan");
                } catch (RentalException ex){
                    setMessage(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            MenuController menu = new MenuController();
            menu.add();
        }
    }

    public void reset(){
        fieldMerk.setText("");
        fieldTarif.setText("");
        cmbKategori.setSelectedIndex(0);
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
