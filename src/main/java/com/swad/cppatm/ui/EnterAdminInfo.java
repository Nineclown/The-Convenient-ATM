package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Admin;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterAdminInfo extends JFrame {
    private JPanel enterAdminInfoPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPasswordField adminPwField;
    private JTextField adminContactField;
    private JLabel titleLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public void next(JFrame parentFrame, ATMSystem system) {
        String password = new String(adminPwField.getPassword());
        String contact = adminContactField.getText();

        try {
            system.enterAdminInfo(password, contact);
        }catch(DataStoreError er){
            JOptionPane.showMessageDialog(parentFrame, "Failed to add Admin", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Admin newAdmin = system.getAdmins()[system.getAdmins().length-1];
        JOptionPane.showMessageDialog(parentFrame, "Admin added\nYour AdminId : " + newAdmin.getId(), "Info", JOptionPane.INFORMATION_MESSAGE);

        parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
        parentFrame.invalidate();
        parentFrame.validate();
    }

    public EnterAdminInfo(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "새로운 사용자 추가", "Add New Admin"));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        adminContactField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        next(parentFrame, system);
                }
            }
        });
    }

    public JPanel getPanel() {
        return this.enterAdminInfoPanel;
    }
}
