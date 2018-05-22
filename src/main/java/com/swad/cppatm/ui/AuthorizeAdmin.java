package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorizeAdmin extends JFrame {
    private JPanel authorizeAdminPanel;
    private JButton confirmButton;
    private JTextField adminIdField;
    private JPasswordField adminPwField;
    private JLabel atmStateLabel;
    private JLabel adminIdLabel;
    private JLabel adminPwLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public void next(JFrame parentFrame, ATMSystem system) {
        String id = adminIdField.getText();
        String password = new String(adminPwField.getPassword());

        try {
            system.authorizeAdmin(id, password);
        } catch (InvalidAdminException exception) {
            JOptionPane.showMessageDialog(parentFrame,
                "Invalid ID or Password.",
                "Authentication Failed",
                JOptionPane.ERROR_MESSAGE);

            return;
        }

        parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
        parentFrame.invalidate();
        parentFrame.validate();
    }

    public AuthorizeAdmin(final JFrame parentFrame, final ATMSystem system) {
        atmStateLabel.setText(system.getState().available() ? "Active": "Frozen");

        adminIdLabel.setText(setLocalizedString(system, "아이디", "ID"));
        adminPwLabel.setText(setLocalizedString(system, "비밀번호", "Password"));

        confirmButton.setText(setLocalizedString(system, "확인", "Go"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        adminPwField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_ENTER :
                        next(parentFrame, system);
                }
            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
