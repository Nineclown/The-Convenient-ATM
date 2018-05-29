package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;
import com.swad.cppatm.ui.components.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnterBillAsDollar {
    private JPanel enterBillPanel;
    private JNumberTextField dollarOneField;
    private JNumberTextField dollarTwoField;
    private JNumberTextField dollarFiveField;
    private JNumberTextField dollarTenField;
    private JNumberTextField dollarFiftyField;
    private JNumberTextField dollarHundredField;
    private JNumberTextField dollarTwentyField;
    private JButton discardButton;
    private JButton confirmButton;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    EnterBillAsDollar(final JFrame parentFrame, final ATMSystem system) {
        dollarOneField.setText("0");
        dollarTwoField.setText("0");
        dollarFiveField.setText("0");
        dollarTenField.setText("0");
        dollarTwentyField.setText("0");
        dollarFiftyField.setText("0");
        dollarHundredField.setText("0");

        confirmButton.setText(setLocalizedString(system, "입력", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int[] values = new int[11];

                values[4] = dollarOneField.getNumber();
                values[5] = dollarTwoField.getNumber();
                values[6] = dollarFiveField.getNumber();
                values[7] = dollarTenField.getNumber();
                values[8] = dollarTwentyField.getNumber();
                values[9] = dollarFiftyField.getNumber();
                values[10] = dollarHundredField.getNumber();

                if (values[4] + values[5] + values[6] + values[7] + values[8] + values[9] + values[10] > 100) {
                    JOptionPane.showMessageDialog(parentFrame, "지폐의 수가 너무 많습니다, 일부만 다시 넣어주세요", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (values[4] + values[5] + values[6] + values[7] + values[8] + values[9] + values[10] == 0) {
                    JOptionPane.showMessageDialog(parentFrame, "0원을 입금할 수 없습니다", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    system.enterBillAsDollar(values);
                } catch (DataStoreError | InvalidBillException ex) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                } catch (OverflowBillException e2) {
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
            }
        });
        discardButton.setText(setLocalizedString(system, "취소", "Discard"));
        discardButton.addMouseListener(new MouseAdapter() {
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
        return this.enterBillPanel;
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
        enterBillPanel = new JPanel();
        enterBillPanel.setLayout(new GridLayoutManager(16, 3, new Insets(0, 0, 0, 0), -1, -1));
        enterBillPanel.setBackground(new Color(-1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("지폐를 입력하여 주십시오.");
        enterBillPanel.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollarOneField = new JNumberTextField();
        Font dollarOneFieldFont = this.$$$getFont$$$(null, -1, 20, dollarOneField.getFont());
        if (dollarOneFieldFont != null) dollarOneField.setFont(dollarOneFieldFont);
        dollarOneField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarOneField, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("$1");
        enterBillPanel.add(label2, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollarTwoField = new JNumberTextField();
        Font dollarTwoFieldFont = this.$$$getFont$$$(null, -1, 20, dollarTwoField.getFont());
        if (dollarTwoFieldFont != null) dollarTwoField.setFont(dollarTwoFieldFont);
        dollarTwoField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarTwoField, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 20, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-16777216));
        label3.setText("$2");
        enterBillPanel.add(label3, new GridConstraints(3, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 20, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-16777216));
        label4.setText("$5");
        enterBillPanel.add(label4, new GridConstraints(5, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollarFiveField = new JNumberTextField();
        Font dollarFiveFieldFont = this.$$$getFont$$$(null, -1, 20, dollarFiveField.getFont());
        if (dollarFiveFieldFont != null) dollarFiveField.setFont(dollarFiveFieldFont);
        dollarFiveField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarFiveField, new GridConstraints(5, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 20, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-16777216));
        label5.setText("$10");
        enterBillPanel.add(label5, new GridConstraints(7, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollarTenField = new JNumberTextField();
        Font dollarTenFieldFont = this.$$$getFont$$$(null, -1, 20, dollarTenField.getFont());
        if (dollarTenFieldFont != null) dollarTenField.setFont(dollarTenFieldFont);
        dollarTenField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarTenField, new GridConstraints(7, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-16777216));
        label6.setText("$20");
        enterBillPanel.add(label6, new GridConstraints(9, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 20, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-16777216));
        label7.setText("$50");
        enterBillPanel.add(label7, new GridConstraints(11, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$(null, -1, 20, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-16777216));
        label8.setText("$100");
        enterBillPanel.add(label8, new GridConstraints(13, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollarTwentyField = new JNumberTextField();
        Font dollarTwentyFieldFont = this.$$$getFont$$$(null, -1, 20, dollarTwentyField.getFont());
        if (dollarTwentyFieldFont != null) dollarTwentyField.setFont(dollarTwentyFieldFont);
        dollarTwentyField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarTwentyField, new GridConstraints(9, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        dollarFiftyField = new JNumberTextField();
        Font dollarFiftyFieldFont = this.$$$getFont$$$(null, -1, 20, dollarFiftyField.getFont());
        if (dollarFiftyFieldFont != null) dollarFiftyField.setFont(dollarFiftyFieldFont);
        dollarFiftyField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarFiftyField, new GridConstraints(11, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        dollarHundredField = new JNumberTextField();
        Font dollarHundredFieldFont = this.$$$getFont$$$(null, -1, 20, dollarHundredField.getFont());
        if (dollarHundredFieldFont != null) dollarHundredField.setFont(dollarHundredFieldFont);
        dollarHundredField.setForeground(new Color(-16777216));
        enterBillPanel.add(dollarHundredField, new GridConstraints(13, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterBillPanel.add(panel1, new GridConstraints(15, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Button");
        panel1.add(confirmButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        discardButton = new JButton();
        discardButton.setBackground(new Color(-10592674));
        Font discardButtonFont = this.$$$getFont$$$(null, -1, 20, discardButton.getFont());
        if (discardButtonFont != null) discardButton.setFont(discardButtonFont);
        discardButton.setForeground(new Color(-1));
        discardButton.setText("Button");
        panel1.add(discardButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
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
        return enterBillPanel;
    }
}
