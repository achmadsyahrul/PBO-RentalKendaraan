package rental.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import rental.controller.CustomerController;
import rental.controller.RentalController;
import rental.error.RentalException;

public class MenyewaFormView extends JFrame implements ActionListener {
    private JTextField fieldNIK;
    private JLabel labelNIK, labelTgl, labelMerk, labelLama, labelWaktu;
    private JDateChooser tglSewa;
    private JComboBox cmbMerk;
    private JSpinner h,m,s, lama;
    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(String NIK, String[] merk){
        setTitle("Menyewa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,275);

        fieldNIK = new JTextField(NIK,10);
        labelNIK = new JLabel(" NIK ");
        labelLama = new JLabel(" Lama Sewa (hari)");
        labelTgl = new JLabel(" Tanggal Ambil ");
        labelWaktu = new JLabel(" Waktu Ambil ");
        labelMerk = new JLabel("Merk Kendaraan ");
        tglSewa = new JDateChooser();
        cmbMerk = new JComboBox(merk);
        h = new JSpinner(new SpinnerNumberModel(0,0,23,1));
        m = new JSpinner(new SpinnerNumberModel(0,0,59,1));
        s = new JSpinner(new SpinnerNumberModel(0,0,59,1));
        lama = new JSpinner(new SpinnerNumberModel());
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelNIK);
        add(fieldNIK);
        add(labelLama);
        add(lama);
        add(labelTgl);
        add(tglSewa);
        add(labelWaktu);
        add(h); add(m); add(s);
        add(labelMerk);
        add(cmbMerk);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelNIK.setBounds(10,10,120,20);
        fieldNIK.setBounds(130,10,190,20);
        fieldNIK.setEditable(false);
        labelLama.setBounds(10,35,120,20);
        lama.setBounds(130,35,190,20);
        labelTgl.setBounds(10,60,120,20);
        tglSewa.setBounds(130,60,190,20);
        tglSewa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelWaktu.setBounds(10, 85, 120, 20);
        h.setBounds(130,85,40,20);
        m.setBounds(170,85,40,20);
        s.setBounds(210,85,40,20);
        labelMerk.setBounds(10, 110, 120, 20);
        cmbMerk.setBounds(130,110,190,20);
        btnSubmit.setBounds(75,175,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,175,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,210,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {

            if(fieldNIK.getText().equals("")){
                setMessage("NIK harus diisi");
            }
            if(lama.getValue().equals("0")){
                setMessage("Lama sewa harus diisi");
            }
            if(tglSewa.getDate() == null){
                setMessage("Tanggal sewa harus diisi");
            }
            if(cmbMerk.getSelectedIndex()==0){
                setMessage("Merk Kendaraan harus diisi");
            }
            else{
                try{
                    RentalController rental = new RentalController();
                    Date tanggal = tglSewa.getDate();
                    int lamaSewa = Integer.parseInt(lama.getValue().toString());
                    rental.cekLama(lamaSewa);
                    String tgl = new SimpleDateFormat("yyyy-MM-dd").format(tanggal);
                    tgl = tgl + " " + h.getValue() + ":" + m.getValue() + ":" + s.getValue();
                    String[] data = {
                            fieldNIK.getText(), tgl, lama.getValue().toString(), cmbMerk.getSelectedItem().toString()
                    };

                    rental.selectKendaraan(data);
                    dispose();
                }
                catch (NumberFormatException num){
                    setMessage(num.toString());
                }
                catch (RentalException ex){
                    setMessage(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            CustomerController customerController = new CustomerController();
            customerController.searchData(fieldNIK.getText());
        }
    }

    public void reset(){
        lama.setValue("0");
        tglSewa.setDate(null);
        cmbMerk.setSelectedIndex(0);
        h.setValue(0); m.setValue(0); s.setValue(0);
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
