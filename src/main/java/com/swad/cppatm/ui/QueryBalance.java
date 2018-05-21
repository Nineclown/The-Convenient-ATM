package com.swad.cppatm.ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryBalance {
    private JTextField accountNumberField;
    private JTextField accountBalanceField;
    private JButton confirmButton;
    private JPanel queryBalancePanel;

    public QueryBalance() {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public JPanel getPanel() {
        return this.queryBalancePanel;
    }
}
