package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidBillException;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.awt.event.*;

public class EnterBill {
    private JPanel enterBillPanel;
    private JTextField thousandField;
    private JButton thousandIncrease;
    private JButton fiveThousandDecrease;
    private JTextField fiveThousandField;
    private JButton tenThousandIncrease;
    private JTextField tenThousandField;
    private JButton fiftyThousandIncrease;
    private JTextField fiftyThousandField;
    private JButton thousandDecrease;
    private JButton fiveThousandIncrease;
    private JButton tenThousandDecrease;
    private JButton fiftyThousandDecrease;
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

        thousandIncrease.setText(setLocalizedString(system, "증가", "Increase"));
        thousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(thousandField.getText());
                thousandField.setText(Integer.toString(value + 1));
            }
        });
        thousandDecrease.setText(setLocalizedString(system, "감소", "Decrease"));
        thousandDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(thousandField.getText());

                if (value == 0) {
                    return;
                }

                thousandField.setText(Integer.toString(value - 1));
            }
        });
        fiveThousandIncrease.setText(setLocalizedString(system, "증가", "Increase"));
        fiveThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiveThousandField.getText());
                fiveThousandField.setText(Integer.toString(value + 1));
            }
        });
        fiveThousandDecrease.setText(setLocalizedString(system, "감소", "Decrease"));
        fiveThousandDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiveThousandField.getText());

                if (value == 0) {
                    return;
                }

                fiveThousandField.setText(Integer.toString(value - 1));
            }
        });
        tenThousandIncrease.setText(setLocalizedString(system, "증가", "Increase"));
        tenThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(tenThousandField.getText());
                tenThousandField.setText(Integer.toString(value + 1));
            }
        });
        tenThousandDecrease.setText(setLocalizedString(system, "감소", "Decrease"));
        tenThousandDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(tenThousandField.getText());

                if (value == 0) {
                    return;
                }

                tenThousandField.setText(Integer.toString(value - 1));
            }
        });
        fiftyThousandIncrease.setText(setLocalizedString(system, "증가", "Increase"));
        fiftyThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiftyThousandField.getText());
                fiftyThousandField.setText(Integer.toString(value + 1));
            }
        });
        fiftyThousandDecrease.setText(setLocalizedString(system, "감소", "Decrease"));
        fiftyThousandDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiftyThousandField.getText());

                if (value == 0) {
                    return;
                }

                fiftyThousandField.setText(Integer.toString(value - 1));
            }
        });
        confirmButton.setText(setLocalizedString(system, "입력", "Insert"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int[] values = {
                    Integer.parseInt(thousandField.getText()),
                    Integer.parseInt(fiveThousandField.getText()),
                    Integer.parseInt(tenThousandField.getText()),
                    Integer.parseInt(fiftyThousandField.getText()),
                };

                switch (system.getFunction()) {
                    case Deposit:
                        try {
                            system.enterBill(values);
                        } catch (DataStoreError | InvalidBillException ex) {
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
                    case ChangeATMBalance:
                        try {
                            system.enterATMBalance(ArrayUtils.addAll(values, new int[]{0,0,0,0,0,0,0,}));
                        } catch (Exception ex){
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }
                        parentFrame.setContentPane(new EnterBillAsDollar(parentFrame, system).getPanel());
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
