package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrintResult {
    private JTextField accountNumberField;
    private JTextField accountBalanceField;
    private JButton confirmButton;
    private JPanel printResultPanel;
    private JLabel accountLabel;
    private JLabel balanceLabel;
    private JLabel titleLabel;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    PrintResult(final JFrame parentFrame, final ATMSystem system) {
        accountNumberField.setText(system.getAccount().getAccountNo());
        accountBalanceField.setText(Integer.toString(system.getAccount().getBalance()));

        titleLabel.setText(setLocalizedString(system, "요청하신 거래가 정상적으로 처리되었습니다.", "Transaction is processed correctly."));
        accountLabel.setText(setLocalizedString(system, "계좌번호", "Account No"));
        balanceLabel.setText(setLocalizedString(system, "잔액", "Current Balance"));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (system.getFunction() == FunctionType.SPLIT_PAY) {
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
        printResultPanel = new JPanel();
        printResultPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        printResultPanel.setBackground(new Color(-1));
        printResultPanel.setMinimumSize(new Dimension(800, 600));
        printResultPanel.setPreferredSize(new Dimension(800, 600));
        accountLabel = new JLabel();
        Font accountLabelFont = this.$$$getFont$$$(null, -1, 20, accountLabel.getFont());
        if (accountLabelFont != null) accountLabel.setFont(accountLabelFont);
        accountLabel.setForeground(new Color(-16777216));
        accountLabel.setText("계좌번호");
        printResultPanel.add(accountLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        balanceLabel = new JLabel();
        Font balanceLabelFont = this.$$$getFont$$$(null, -1, 20, balanceLabel.getFont());
        if (balanceLabelFont != null) balanceLabel.setFont(balanceLabelFont);
        balanceLabel.setForeground(new Color(-16777216));
        balanceLabel.setText("잔액");
        printResultPanel.add(balanceLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountNumberField = new JTextField();
        accountNumberField.setEditable(false);
        Font accountNumberFieldFont = this.$$$getFont$$$(null, -1, 20, accountNumberField.getFont());
        if (accountNumberFieldFont != null) accountNumberField.setFont(accountNumberFieldFont);
        printResultPanel.add(accountNumberField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        accountBalanceField = new JTextField();
        accountBalanceField.setEditable(false);
        Font accountBalanceFieldFont = this.$$$getFont$$$(null, -1, 20, accountBalanceField.getFont());
        if (accountBalanceFieldFont != null) accountBalanceField.setFont(accountBalanceFieldFont);
        printResultPanel.add(accountBalanceField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        printResultPanel.add(confirmButton, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("요청하신 거래가 정상적으로 처리되었습니다.");
        printResultPanel.add(titleLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return printResultPanel;
    }
}
