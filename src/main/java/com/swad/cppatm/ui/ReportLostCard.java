package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.UserDoestNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportLostCard extends JFrame {
    private JLabel 카드번호;
    private JCheckBox reportLostCheckBox1;
    private JTextField cardNumberField2;
    private JCheckBox reportLostCheckBox2;
    private JCheckBox reportLostCheckBox3;
    private JCheckBox reportLostCheckBox4;
    private JTextField cardNumberField3;
    private JTextField cardNumberField1;
    private JTextField cardNumberField4;
    private JTextField[] cardFields;
    private JCheckBox[] checkBox;
    private JPanel reportLostCardPanel;
    private JButton cancelButton;
    private JButton confirmButton;


    public ReportLostCard(final JFrame parentFrame, final ATMSystem system) {
        cardFields = new JTextField[4];
        checkBox = new JCheckBox[4];

        cardFields[0] = cardNumberField1;
        cardFields[1] = cardNumberField2;
        cardFields[2] = cardNumberField3;
        cardFields[3] = cardNumberField4;

        checkBox[0] = reportLostCheckBox1;
        checkBox[1] = reportLostCheckBox2;
        checkBox[2] = reportLostCheckBox3;
        checkBox[3] = reportLostCheckBox4;

        String[] card = system.getCardList();
        int length = card.length;

        for (int i = 0; i < length; i++) {
            if (card[i] != null) {
                cardFields[i].setText(card[0].toString());
            }
        }
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }


    public JPanel getPanel() {
        return this.reportLostCardPanel;
    }
}
