package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;

import javax.swing.*;
import java.awt.event.*;

public class EnterBill {
    private JPanel enterBillPanel;
    private JTextField thousandField;
    private JTextField fiveThousandField;
    private JTextField tenThousandField;
    private JTextField fiftyThousandField;
    private JButton discardButton;
    private JButton confirmButton;
    private JLabel thousandLabel;
    private JLabel fiveThousandLabel;
    private JLabel tenThousandLabel;
    private JLabel fiftyThousandLabel;
    private JLabel titleLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public EnterBill(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "지폐를 입력하여 주십시오.", "Please Insert bills."));
        thousandField.setText("0");
        fiveThousandField.setText("0");
        tenThousandField.setText("0");
        fiftyThousandField.setText("0");


        confirmButton.setText(setLocalizedString(system, "입력", "Insert"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int[] values = new int[11];

                values[0] = Integer.parseInt(thousandField.getText());
                values[1] = Integer.parseInt(fiveThousandField.getText());
                values[2] = Integer.parseInt(tenThousandField.getText());
                values[3] = Integer.parseInt(fiftyThousandField.getText());

                if(values[0] +  values[1] + values[2] + values[3] > 100){
                    JOptionPane.showMessageDialog(parentFrame, "지폐의 수가 너무 많습니다, 일부만 다시 넣어주세요", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                switch (system.getFunction()) {
                    case Deposit:
                        try {
                            system.enterBill(values);
                        } catch (DataStoreError | InvalidBillException ex) {
                            ex.printStackTrace();
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        } catch (OverflowBillException ex){
                            JOptionPane.showMessageDialog(parentFrame, "해당 거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Error", JOptionPane.ERROR_MESSAGE);

                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }
                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        parentFrame.pack();
                        parentFrame.invalidate();
                        parentFrame.validate();
                        break;
                }
            }
        });
        discardButton.setText(setLocalizedString(system, "취소", "Discard"));
        discardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (system.getFunction() == FunctionType.ChangeATMBalance) {
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());

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
        return this.enterBillPanel;
    }
}
