package rental.view;

import rental.controller.CustomerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFormView extends JFrame implements ActionListener {
    private JTextField fieldNIK, fieldNama, fieldNoHP, fieldAlamat;
    private JLabel labelNIK, labelNama, labelNoHP, labelAlamat;
    private JButton btnSubmit, btnReset, btnBack;

    private String id;
    public void openForm(String[] data){
        id = data[4];
        setTitle("Form Customer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,250);

        fieldNIK = new JTextField(data[0],10);
        fieldNama = new JTextField(data[1],10);
        fieldNoHP = new JTextField(data[2], 10);
        fieldAlamat = new JTextField(data[3], 10);
        labelNIK = new JLabel(" NIK : ");
        labelNama = new JLabel(" Nama : ");
        labelNoHP = new JLabel(" No HP : ");
        labelAlamat = new JLabel(" Alamat : ");
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelNIK);
        add(fieldNIK);
        add(labelNama);
        add(labelNoHP);
        add(fieldNama);
        add(fieldNoHP);
        add(labelAlamat);
        add(fieldAlamat);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelNIK.setBounds(10,10,120,20);
        fieldNIK.setBounds(130,10,190,20);
        fieldNIK.setEditable(false);
        labelNama.setBounds(10,35,120,20);
        fieldNama.setBounds(130, 35, 190, 20);
        labelNoHP.setBounds(10,60,120,20);
        fieldNoHP.setBounds(130,60,190,20);
        labelAlamat.setBounds(10, 85, 120, 20);
        fieldAlamat.setBounds(130,85,190,20);
        btnSubmit.setBounds(75,150,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,150,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,185,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {
            if (fieldNIK.getText().equals("")) {
                setMessage("NIK harus diisi");
            }
            if (fieldNama.getText().equals("")) {
                setMessage("Nama harus diisi");
            }
            if (fieldNoHP.getText().equals("")) {
                setMessage("No HP harus diisi");
            }
            if (fieldAlamat.getText().equals("")) {
                setMessage("Alamat harus diisi");
            }
            else {
                String[] data = {
                        fieldNIK.getText(), fieldNama.getText(), fieldNoHP.getText(), fieldAlamat.getText(), id
                };
                CustomerController c = new CustomerController();
                c.submitData(data);
                dispose();
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            CustomerController c = new CustomerController();
            c.searchData();
        }
    }

    public void reset(){
        fieldNama.setText("");
        fieldNoHP.setText("");
        fieldAlamat.setText("");
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
