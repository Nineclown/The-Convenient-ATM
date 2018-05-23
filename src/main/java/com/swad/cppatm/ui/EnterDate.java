package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.AccountDoesNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Pattern;

public class EnterDate {
    private JPanel enterDatePanel;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton cancelButton;
    private JButton confirmButton;

    public EnterDate(final JFrame parentFrame, final ATMSystem system) {

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Date start, end;
                if ( !startDateField.getText().matches("^\\d{8}$") && !endDateField.getText().matches("^\\d{8}$") ) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                start = new Date(
                    Integer.parseInt(startDateField.getText().substring(0, 4)),
                    Integer.parseInt(startDateField.getText().substring(4, 6)),
                    Integer.parseInt(startDateField.getText().substring(6, 8))
                );

                end = new Date(
                    Integer.parseInt(endDateField.getText().substring(0, 4)),
                    Integer.parseInt(endDateField.getText().substring(4, 6)),
                    Integer.parseInt(endDateField.getText().substring(6, 8))
                );

                try {
                    system.enterPeriodToQuery(start, end);
                } catch (AccountDoesNotExist ex) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                parentFrame.setContentPane(new QueryList(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterDatePanel;
    }
}
