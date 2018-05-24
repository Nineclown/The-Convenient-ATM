package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrintResult {
    private JTextField accountNumberField;
    private JTextField accountBalanceField;
    private JButton confirmButton;
    private JPanel printResultPanel;

    public PrintResult(final JFrame parentFrame, final ATMSystem system) {
        accountNumberField.setText(system.getAccount().getAccountNo());
        accountBalanceField.setText(Integer.toString(system.getAccount().getBalance()));

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(system.getFunction() == FunctionType.SplitPay) {
                    if (system.getNumberUser() > 0) {
                        parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                    } else {
                        parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    }
                } else {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                }
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.printResultPanel;
    }
}
