package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFunction extends JFrame {
    private JPanel selectFunctionPanel;
    private JButton depositButton;
    private JButton queryBalanceButton;
    private JButton queryTransactionListButton;
    private JButton foreignWithdrawButton;
    private JButton foreignDepositButton;
    private JButton withdrawButton;
    private JButton reportLostCardButton;
    private JButton transferButton;
    private JButton splitPayButton;
    private JButton getLotteryPrizeButton;
    private JButton changeLocaleButton;

    public SelectFunction(final JFrame parentFrame, final ATMSystem system) {
        depositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.Deposit);
                } catch (NoneOfFunctionSelected exception) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        foreignDepositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ForeignDeposit);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        withdrawButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.Withdraw);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        queryBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.QueryBalance);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        foreignWithdrawButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ForeignWithdraw);

                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        queryTransactionListButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.QueryTransactionList);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        reportLostCardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ReportLostCard);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        getLotteryPrizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.GetLotteryPrize);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeLocale);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        transferButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.Transfer);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        splitPayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.SplitPay);
                } catch (NoneOfFunctionSelected ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        "ATM is Freezed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.selectFunctionPanel;
    }
}
