package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArrayCheck extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Data;
    private JTextField cekArray;
    private int[] arrayData;
    private JLabel lblOutput;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ArrayCheck frame = new ArrayCheck();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ArrayCheck() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 508, 542);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel MasukData = new JLabel("Masukkan Data");
        MasukData.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        MasukData.setBounds(70, 75, 274, 27);
        contentPane.add(MasukData);

        Data = new JTextField();
        Data.setBounds(68, 101, 276, 40);
        contentPane.add(Data);
        Data.setColumns(10);

        lblOutput = new JLabel("");
        lblOutput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        lblOutput.setBounds(70, 160, 350, 32);
        contentPane.add(lblOutput);

        cekArray = new JTextField();
        cekArray.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        cekArray.setBounds(70, 304, 274, 40);
        contentPane.add(cekArray);
        cekArray.setColumns(10);

        JLabel lblCheckArrayKe = new JLabel("Check array ke -");
        lblCheckArrayKe.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        lblCheckArrayKe.setBounds(70, 276, 274, 27);
        contentPane.add(lblCheckArrayKe);

        JLabel lblHasil = new JLabel("");
        lblHasil.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        lblHasil.setBounds(70, 383, 350, 32);
        contentPane.add(lblHasil);

        JLabel lblHasil_1 = new JLabel("Hasil :");
        lblHasil_1.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 14));
        lblHasil_1.setBounds(70, 360, 64, 33);
        contentPane.add(lblHasil_1);

        JButton btnSave = new JButton("SIMPAN");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String masuk = Data.getText();
                String[] split = masuk.split(",");
                arrayData = new int[split.length];

                try {
                    for (int i = 0; i < split.length; i++) {
                        arrayData[i] = Integer.parseInt(split[i].trim());
                    }
                    StringBuilder output = new StringBuilder();
                    for (int num : arrayData) {
                        output.append(num).append(", ");
                    }
                    if (output.length() > 0) {
                        output.setLength(output.length() - 2); 
                    }
                    lblOutput.setText("Array Output: " + output.toString());

                } catch (NumberFormatException ex) {
                    lblOutput.setText("Error: Masukkan angka yang valid");
                }
            }
        });
        btnSave.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnSave.setBounds(366, 101, 106, 40);
        contentPane.add(btnSave);

        JButton btnCheck = new JButton("CHECK");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (arrayData == null || arrayData.length == 0) {
                    lblHasil.setText("Error: Data belum dimasukkan");
                    return;
                }
                try {
                    int index = Integer.parseInt(cekArray.getText().trim());
                    if (index < 0 || index >= arrayData.length) {
                        lblHasil.setText("Error: Indeks di luar batas array");
                    } else {
                        int value = arrayData[index];
                        lblHasil.setText("Hasil: Elemen ke-" + index + " adalah " + value);
                    }
                } catch (NumberFormatException ex) {
                    lblHasil.setText("Error: Masukkan indeks yang valid");
                }
            }
        });
        btnCheck.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnCheck.setBounds(366, 304, 106, 40);
        contentPane.add(btnCheck);
    }
}

