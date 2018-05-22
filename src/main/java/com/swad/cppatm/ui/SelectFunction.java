package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
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

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public SelectFunction(final JFrame parentFrame, final ATMSystem system) {
        depositButton.setText(setLocalizedString(system, "입금", "Deposit"));
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
        foreignDepositButton.setText(setLocalizedString(system, "외화 입금", "Foreign Deposit"));
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
        withdrawButton.setText(setLocalizedString(system, "출금", "Withdraw"));
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
        queryBalanceButton.setText(setLocalizedString(system, "입금", "Deposit"));
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
        foreignWithdrawButton.setText(setLocalizedString(system, "외화 출금", "Foreign Withdraw"));
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
        queryTransactionListButton.setText(setLocalizedString(system, "거래내역 조회", "Query Transaction List"));
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
        reportLostCardButton.setText(setLocalizedString(system, "카드 분실신고", "Report Lost Card"));
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
        getLotteryPrizeButton.setText(setLocalizedString(system, "복권 당첨금 수령", "Get Lottery Prize"));
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
                parentFrame.setContentPane(new ChangeLocale(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeLocaleButton.setText(setLocalizedString(system, "언어 변경", "Change Locale"));
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

                parentFrame.setContentPane(new ChangeLocale(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        transferButton.setText(setLocalizedString(system, "송금", "Transfer"));
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
        splitPayButton.setText(setLocalizedString(system, "분할결제", "Split Pay"));
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
