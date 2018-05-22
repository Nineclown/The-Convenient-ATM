package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Admin;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterAdminInfo extends JFrame {
    private JPanel enterAdminInfoPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPasswordField adminPwField;
    private JTextField adminContactField;

    public EnterAdminInfo(final JFrame parentFrame, final ATMSystem system) {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        return this.enterAdminInfoPanel;
    }
}
