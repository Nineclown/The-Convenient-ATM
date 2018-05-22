package com.swad.cppatm.ui;
import com.swad.cppatm.application.ATMSystem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterPassword extends JFrame {
    private JPanel enterPasswordPanel;
    private JPasswordField passwordField;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton BSButton;
    private JButton a0Button;
    private JButton clearButton;
    private JButton confirmButton;

    public EnterPassword(final JFrame parentFrame, final ATMSystem system) {
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "3");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "6");
            }
        });
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }
                passwordField.setText(passwordField.getPassword() + "9");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length >= 4 ) {
                    return;
                }

                passwordField.setText(passwordField.getPassword() + "0");
            }
        });
        BSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length == 0 ) {
                    return;
                }

                passwordField.setText(new String(passwordField.getPassword()).substring(0, passwordField.getPassword().length - 1));
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( passwordField.getPassword().length == 0 ) {
                    return;
                }

                passwordField.setText("");
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public JPanel getPanel() {
        return this.enterPasswordPanel;
    }
}
