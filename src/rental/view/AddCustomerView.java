package rental.view;

import rental.controller.CustomerController;
import rental.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerView extends JFrame implements ActionListener {
    private JTextField fieldNIK;
    private JLabel labelNIK;

    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(){
        setTitle("Customer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);

        fieldNIK = new JTextField(10);
        labelNIK = new JLabel(" NIK ");
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelNIK);
        add(fieldNIK);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelNIK.setBounds(10,10,120,20);
        fieldNIK.setBounds(130,10,190,20);
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
            if (fieldNIK.getText().equals("")) {
                setMessage("NIK harus diisi");
            }
            else {
                CustomerController customerController = new CustomerController();
                customerController.searchData(fieldNIK.getText());
                dispose();
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else{
            dispose();
            MenuController menu = new MenuController();
            menu.mainMenu();
        }
    }

    public void reset(){
        fieldNIK.setText("");
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
