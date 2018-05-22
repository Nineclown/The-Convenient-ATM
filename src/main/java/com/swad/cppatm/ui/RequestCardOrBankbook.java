package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestCardOrBankbook {
    private JPanel requestCardOrBankbookPanel;
    private JTextField bankbookNumberField;
    private JTextField cardNumberField;
    private JButton confirmButton;
    private JButton cancelButton;

    public RequestCardOrBankbook(final JFrame parentFrame, final ATMSystem system) {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( bankbookNumberField.getText().length() > 0 && cardNumberField.getText().length() > 0 ) {
                    JOptionPane.showMessageDialog(parentFrame, "Only one of the number is accepted.", "Error", JOptionPane.ERROR_MESSAGE);
                    bankbookNumberField.setText("");
                    cardNumberField.setText("");
                    return;
                }

                if ( bankbookNumberField.getText().length() == 0 && cardNumberField.getText().length() == 0 ) {
                    JOptionPane.showMessageDialog(parentFrame, "No number at all." , "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel(){
        return this.requestCardOrBankbookPanel;
    }
}
