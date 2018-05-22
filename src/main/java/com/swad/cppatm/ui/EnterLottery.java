package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Lottery;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.LotteryFailed;

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
    private JTextField weekField;
    private JLabel weekLabel;

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
                int week;
                int[] values;

                try {
                    week = Integer.parseInt(weekField.getText());
                    values = new int[]{
                        Integer.parseInt(textField1.getText()),
                        Integer.parseInt(textField2.getText()),
                        Integer.parseInt(textField3.getText()),
                        Integer.parseInt(textField4.getText()),
                        Integer.parseInt(textField5.getText()),
                        Integer.parseInt(textField6.getText()),
                    };
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "올바르지 않은 번호입니다.", "Incorrect number."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Lottery lottery = new Lottery(week, values);

                try {
                    system.enterLottery(lottery);
                } catch (LotteryFailed ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "당첨되지 않았습니다.", "You lose."),
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
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
