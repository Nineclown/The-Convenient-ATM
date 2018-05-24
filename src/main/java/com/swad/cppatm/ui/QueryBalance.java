package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryBalance {
    private JTextField accountNumberField;
    private JTextField accountBalanceField;
    private JButton confirmButton;
    private JPanel queryBalancePanel;
    private JLabel titleLabel;
    private JLabel accountLabel;
    private JLabel currencyLabel;
    private JLabel balanceLabel;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    public QueryBalance(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "잔액을 확인하여 주십시오.", "Please check your balance"));
        accountLabel.setText(setLocalizedString(system, "계좌번호", "Account"));
        balanceLabel.setText(setLocalizedString(system, "잔액", "Balance"));
        currencyLabel.setText(setLocalizedString(system, "원", "WON"));
        accountNumberField.setText(system.getAccount().getAccountNo());
        accountBalanceField.setText(Integer.toString(system.getAccount().getBalance()));

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.queryBalancePanel;
    }
}
