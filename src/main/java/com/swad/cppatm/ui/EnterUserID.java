package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.UserDoestNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterUserID {
    private JPanel enterUserIDPanel;
    private JLabel titleLabel;
    private JLabel residentNumberLabel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField idField;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    public EnterUserID(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "주민등록번호를 입력해주십시오", "Please enter your resident ID."));
        residentNumberLabel.setText(setLocalizedString(system, "주민등록번호", "ID"));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String userId = idField.getText();
                if (userId.length() < 13) {
                    JOptionPane.showMessageDialog(parentFrame, "INVALID ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    system.enterUserId(userId);
                } catch (UserDoestNotExist exception) {
                    JOptionPane.showMessageDialog(parentFrame, "CAN'T FIND A USER", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new ReportLostCard(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Canecel"));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterUserIDPanel;
    }
}
