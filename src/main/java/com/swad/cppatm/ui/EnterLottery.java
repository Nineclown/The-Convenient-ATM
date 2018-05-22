package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterLottery {
    private JPanel enterLotteryPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel titleLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public EnterLottery(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "로또 번호를 입력하여 주십시오.", "Plaese enter lottery number."));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
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
        return this.enterLotteryPanel;
    }
}
