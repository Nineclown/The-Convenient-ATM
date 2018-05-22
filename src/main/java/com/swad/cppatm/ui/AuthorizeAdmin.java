package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.*;
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
                String id = adminIdField.getText();
                String password = new String(adminPwField.getPassword());
                Container panel;

                try {
                    system.authorizeAdmin(id, password);
                } catch (InvalidAdminException exception) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "Invalid ID or Password.",
                        "Authentication Failed",
                        JOptionPane.ERROR_MESSAGE);

                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                    parentFrame.invalidate();
                    parentFrame.validate();

                    return;
                }

                switch (system.getFunction()) {
                    case QueryATMBalance:
                        panel = new QueryBalance(parentFrame, system).getPanel();
                        break;
                    case ChangeATMBalance:
                        panel = new EnterBill(parentFrame, system).getPanel();
                        break;
                    case ChangeLocale:
                        panel = new ChangeLocale(parentFrame, system).getPanel();
                        break;
                    default:
                        panel = new AdminSelectFunction(parentFrame, system).getPanel();
                }

                parentFrame.setContentPane(panel);
                parentFrame.invalidate();
                parentFrame.validate();
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
