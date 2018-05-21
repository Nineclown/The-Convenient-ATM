package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorizeAdmin extends JFrame {
    private JPanel authorizeAdminPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField adminIdField;
    private JPasswordField adminPwField;
    private JLabel atmStateLabel;

    public AuthorizeAdmin(final JFrame parentFrame, final ATMSystem system) {
        atmStateLabel.setText(system.getState().available() ? "Active": "Frozen");

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
