package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.UserDoestNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterUserID {
    private JPanel enterUserIDPanel;
    private JLabel Title;
    private JLabel 주민등록번호;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField idField;

    public EnterUserID(final JFrame parentFrame, final ATMSystem system){
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String userId = idField.getText();
                if(userId.length() < 13)
                {
                    JOptionPane.showMessageDialog(parentFrame, "INVALID ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try
                {
                    system.enterUserId(userId);
                }catch(UserDoestNotExist exception)
                {
                    JOptionPane.showMessageDialog(parentFrame, "CAN't FIND A USER", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new ReportLostCard(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel()
    {
        return this.enterUserIDPanel;
    }

}
