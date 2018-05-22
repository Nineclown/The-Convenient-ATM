package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.enums.FunctionType;
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

    public EnterBill(final JFrame parentFrame, final ATMSystem system) {
        thousandField.setText("0");
        fiveThousandField.setText("0");
        tenThousandField.setText("0");
        fiftyThousandField.setText("0");
        thousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(thousandField.getText());
                thousandField.setText(Integer.toString(value + 1));
            }
        });
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
        fiveThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiveThousandField.getText());
                fiveThousandField.setText(Integer.toString(value + 1));
            }
        });
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
        tenThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(tenThousandField.getText());
                tenThousandField.setText(Integer.toString(value + 1));
            }
        });
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
        fiftyThousandIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(fiftyThousandField.getText());
                fiftyThousandField.setText(Integer.toString(value + 1));
            }
        });
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
