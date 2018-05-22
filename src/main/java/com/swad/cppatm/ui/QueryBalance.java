package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryBalance {
    private JTextField accountNumberField;
    private JTextField accountBalanceField;
    private JButton confirmButton;
    private JPanel queryBalancePanel;

    public QueryBalance(final JFrame parentFrame, final ATMSystem system) {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.queryBalancePanel;
    }
}
