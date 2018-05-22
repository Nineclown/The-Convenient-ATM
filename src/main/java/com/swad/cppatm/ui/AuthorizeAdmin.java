package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.InvalidAdminException;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorizeAdmin extends JFrame {
    private JPanel authorizeAdminPanel;
    private JButton confirmButton;
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
                    return;
                }

                if(system.getCurrentAdmin() != null){
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                    parentFrame.invalidate();
                    parentFrame.validate();
                }



            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
