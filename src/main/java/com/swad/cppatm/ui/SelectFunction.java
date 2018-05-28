package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.*;
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
        if (system.getState().getLocale() == Locale.en_US) {
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
        queryBalanceButton.setText(setLocalizedString(system, "잔액 조회", "Query Balance"));
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
                parentFrame.setContentPane(new EnterUserID(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
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
                parentFrame.setContentPane(new EnterLottery(parentFrame, system).getPanel());
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        selectFunctionPanel = new JPanel();
        selectFunctionPanel.setLayout(new GridLayoutManager(6, 3, new Insets(30, 30, 30, 30), 10, 10));
        selectFunctionPanel.setBackground(new Color(-1));
        selectFunctionPanel.setForeground(new Color(-1));
        selectFunctionPanel.setMinimumSize(new Dimension(800, 600));
        selectFunctionPanel.setPreferredSize(new Dimension(800, 600));
        foreignWithdrawButton = new JButton();
        foreignWithdrawButton.setBackground(new Color(-10592674));
        foreignWithdrawButton.setBorderPainted(true);
        foreignWithdrawButton.setContentAreaFilled(true);
        foreignWithdrawButton.setFocusPainted(false);
        Font foreignWithdrawButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, foreignWithdrawButton.getFont());
        if (foreignWithdrawButtonFont != null) foreignWithdrawButton.setFont(foreignWithdrawButtonFont);
        foreignWithdrawButton.setForeground(new Color(-1));
        foreignWithdrawButton.setHideActionText(true);
        foreignWithdrawButton.setText("Foreign Withdraw");
        selectFunctionPanel.add(foreignWithdrawButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        depositButton = new JButton();
        depositButton.setBackground(new Color(-10592674));
        depositButton.setBorderPainted(true);
        depositButton.setContentAreaFilled(true);
        depositButton.setFocusPainted(false);
        Font depositButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, depositButton.getFont());
        if (depositButtonFont != null) depositButton.setFont(depositButtonFont);
        depositButton.setForeground(new Color(-1));
        depositButton.setHideActionText(true);
        depositButton.setText("Deposit");
        selectFunctionPanel.add(depositButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        queryBalanceButton = new JButton();
        queryBalanceButton.setBackground(new Color(-10592674));
        queryBalanceButton.setBorderPainted(true);
        queryBalanceButton.setContentAreaFilled(true);
        queryBalanceButton.setFocusPainted(false);
        Font queryBalanceButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, queryBalanceButton.getFont());
        if (queryBalanceButtonFont != null) queryBalanceButton.setFont(queryBalanceButtonFont);
        queryBalanceButton.setForeground(new Color(-1));
        queryBalanceButton.setHideActionText(true);
        queryBalanceButton.setText("Query Balance");
        selectFunctionPanel.add(queryBalanceButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        queryTransactionListButton = new JButton();
        queryTransactionListButton.setBackground(new Color(-10592674));
        queryTransactionListButton.setBorderPainted(true);
        queryTransactionListButton.setContentAreaFilled(true);
        queryTransactionListButton.setFocusPainted(false);
        Font queryTransactionListButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 16, queryTransactionListButton.getFont());
        if (queryTransactionListButtonFont != null) queryTransactionListButton.setFont(queryTransactionListButtonFont);
        queryTransactionListButton.setForeground(new Color(-1));
        queryTransactionListButton.setHideActionText(true);
        queryTransactionListButton.setText("Query Transaction List");
        selectFunctionPanel.add(queryTransactionListButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        foreignDepositButton = new JButton();
        foreignDepositButton.setBackground(new Color(-10592674));
        foreignDepositButton.setBorderPainted(true);
        foreignDepositButton.setContentAreaFilled(true);
        foreignDepositButton.setFocusPainted(false);
        Font foreignDepositButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, foreignDepositButton.getFont());
        if (foreignDepositButtonFont != null) foreignDepositButton.setFont(foreignDepositButtonFont);
        foreignDepositButton.setForeground(new Color(-1));
        foreignDepositButton.setHideActionText(true);
        foreignDepositButton.setText("Foreign Deposit");
        selectFunctionPanel.add(foreignDepositButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        withdrawButton = new JButton();
        withdrawButton.setBackground(new Color(-10592674));
        withdrawButton.setBorderPainted(true);
        withdrawButton.setContentAreaFilled(true);
        withdrawButton.setFocusPainted(false);
        Font withdrawButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, withdrawButton.getFont());
        if (withdrawButtonFont != null) withdrawButton.setFont(withdrawButtonFont);
        withdrawButton.setForeground(new Color(-1));
        withdrawButton.setHideActionText(true);
        withdrawButton.setText("Withdraw");
        selectFunctionPanel.add(withdrawButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        reportLostCardButton = new JButton();
        reportLostCardButton.setBackground(new Color(-10592674));
        reportLostCardButton.setBorderPainted(true);
        reportLostCardButton.setContentAreaFilled(true);
        reportLostCardButton.setFocusPainted(false);
        Font reportLostCardButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, reportLostCardButton.getFont());
        if (reportLostCardButtonFont != null) reportLostCardButton.setFont(reportLostCardButtonFont);
        reportLostCardButton.setForeground(new Color(-1));
        reportLostCardButton.setHideActionText(true);
        reportLostCardButton.setText("Report Lost Card");
        selectFunctionPanel.add(reportLostCardButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        transferButton = new JButton();
        transferButton.setBackground(new Color(-10592674));
        transferButton.setBorderPainted(true);
        transferButton.setContentAreaFilled(true);
        transferButton.setFocusPainted(false);
        Font transferButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, transferButton.getFont());
        if (transferButtonFont != null) transferButton.setFont(transferButtonFont);
        transferButton.setForeground(new Color(-1));
        transferButton.setHideActionText(true);
        transferButton.setText("Transfer");
        selectFunctionPanel.add(transferButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        splitPayButton = new JButton();
        splitPayButton.setBackground(new Color(-10592674));
        splitPayButton.setBorderPainted(true);
        splitPayButton.setContentAreaFilled(true);
        splitPayButton.setFocusPainted(false);
        Font splitPayButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, splitPayButton.getFont());
        if (splitPayButtonFont != null) splitPayButton.setFont(splitPayButtonFont);
        splitPayButton.setForeground(new Color(-1));
        splitPayButton.setHideActionText(true);
        splitPayButton.setText("Split Pay");
        selectFunctionPanel.add(splitPayButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        getLotteryPrizeButton = new JButton();
        getLotteryPrizeButton.setBackground(new Color(-10592674));
        getLotteryPrizeButton.setBorderPainted(true);
        getLotteryPrizeButton.setContentAreaFilled(true);
        getLotteryPrizeButton.setFocusPainted(false);
        Font getLotteryPrizeButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, getLotteryPrizeButton.getFont());
        if (getLotteryPrizeButtonFont != null) getLotteryPrizeButton.setFont(getLotteryPrizeButtonFont);
        getLotteryPrizeButton.setForeground(new Color(-1));
        getLotteryPrizeButton.setHideActionText(true);
        getLotteryPrizeButton.setText("Get Lottery Prize");
        selectFunctionPanel.add(getLotteryPrizeButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        changeLocaleButton = new JButton();
        changeLocaleButton.setBackground(new Color(-10592674));
        changeLocaleButton.setBorderPainted(true);
        changeLocaleButton.setContentAreaFilled(true);
        changeLocaleButton.setFocusPainted(false);
        Font changeLocaleButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, changeLocaleButton.getFont());
        if (changeLocaleButtonFont != null) changeLocaleButton.setFont(changeLocaleButtonFont);
        changeLocaleButton.setForeground(new Color(-1));
        changeLocaleButton.setHideActionText(true);
        changeLocaleButton.setText("Change Locale");
        selectFunctionPanel.add(changeLocaleButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, -1), new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setAutoscrolls(false);
        label1.setBackground(new Color(-15024996));
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$("Malgun Gothic", -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setOpaque(true);
        label1.setText("T2");
        selectFunctionPanel.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        selectFunctionPanel.add(spacer1, new GridConstraints(0, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(100, -1), new Dimension(200, -1), new Dimension(300, -1), 1, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return selectFunctionPanel;
    }
}
