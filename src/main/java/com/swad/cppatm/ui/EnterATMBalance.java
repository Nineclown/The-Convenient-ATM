package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterATMBalance {
    private JButton confirmButton;
    private JPanel enterATMBalancePanel;
    private JButton discardButton;
    private JTextField dollar100;
    private JTextField won50000;
    private JTextField won10000;
    private JTextField won5000;
    private JTextField won1000;
    private JTextField dollar1;
    private JTextField dollar2;
    private JTextField dollar5;
    private JTextField dollar10;
    private JTextField dollar50;
    private JTextField dollar20;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public EnterATMBalance(final JFrame parentFrame, final ATMSystem system) {

        confirmButton.setText(setLocalizedString(system, "확인", "Insert"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int [] billAmount = new int[11];
                billAmount[0] = Integer.parseInt(won1000.getText());
                billAmount[1] = Integer.parseInt(won5000.getText());
                billAmount[2] = Integer.parseInt(won10000.getText());
                billAmount[3] = Integer.parseInt(won50000.getText());
                billAmount[4] = Integer.parseInt(dollar1.getText());
                billAmount[5] = Integer.parseInt(dollar2.getText());
                billAmount[6] = Integer.parseInt(dollar5.getText());
                billAmount[7] = Integer.parseInt(dollar10.getText());
                billAmount[8] = Integer.parseInt(dollar20.getText());
                billAmount[9] = Integer.parseInt(dollar50.getText());
                billAmount[10] = Integer.parseInt(dollar100.getText());

                try{
                    system.enterATMBalance(billAmount);
                }catch(OverflowBillException e1){
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "ATM이 수용 할 수 있는 지폐량을 벗어났습니다..", "ATM can't have too many or few money"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }catch(InvalidBillException e2){
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잘못된 입력입니다.", "Wrong Input"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }

                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        discardButton.setText(setLocalizedString(system, "취소", "Discard"));
        discardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterATMBalancePanel;
    }
}
