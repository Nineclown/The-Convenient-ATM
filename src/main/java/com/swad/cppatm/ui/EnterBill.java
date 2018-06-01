package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;
import com.swad.cppatm.ui.components.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnterBill {
    private JPanel enterBillPanel;
    private JNumberTextField thousandField;
    private JNumberTextField fiveThousandField;
    private JNumberTextField tenThousandField;
    private JNumberTextField fiftyThousandField;
    private JButton discardButton;
    private JButton confirmButton;
    private JLabel thousandLabel;
    private JLabel fiveThousandLabel;
    private JLabel tenThousandLabel;
    private JLabel fiftyThousandLabel;
    private JLabel titleLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if (system.getState().getLocale() == Locale.en_US) {
            return en;
        } else {
            return ko;
        }
    }

    EnterBill(final JFrame parentFrame, final ATMSystem system) {
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

                values[0] = thousandField.getNumber();
                values[1] = fiveThousandField.getNumber();
                values[2] = tenThousandField.getNumber();
                values[3] = fiftyThousandField.getNumber();

                if (values[0] + values[1] + values[2] + values[3] > 100) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "지폐의 수가 너무 많습니다, 일부만 다시 넣어주세요.", "Too many bills, please insert some of bills again."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (values[0] + values[1] + values[2] + values[3] == 0) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "0원을 입금할 수 없습니다.", "Can't Deposit 0 won."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
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
                        } catch (OverflowBillException ex) {
                            JOptionPane.showMessageDialog(parentFrame,
                                setLocalizedString(system, "거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Can't process transaction, please contact to admin"),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);

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
                if (parentFrame.getTitle().equals("Admin Interface for C++ ATM")) {
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
        enterBillPanel.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        enterBillPanel.setBackground(new Color(-1));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("지폐를 입력하여 주십시오.");
        enterBillPanel.add(titleLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thousandField = new JNumberTextField();
        Font thousandFieldFont = this.$$$getFont$$$(null, -1, 24, thousandField.getFont());
        if (thousandFieldFont != null) thousandField.setFont(thousandFieldFont);
        enterBillPanel.add(thousandField, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        thousandLabel = new JLabel();
        Font thousandLabelFont = this.$$$getFont$$$(null, -1, 20, thousandLabel.getFont());
        if (thousandLabelFont != null) thousandLabel.setFont(thousandLabelFont);
        thousandLabel.setForeground(new Color(-16777216));
        thousandLabel.setText("₩ 1000");
        enterBillPanel.add(thousandLabel, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fiveThousandField = new JNumberTextField();
        Font fiveThousandFieldFont = this.$$$getFont$$$(null, -1, 24, fiveThousandField.getFont());
        if (fiveThousandFieldFont != null) fiveThousandField.setFont(fiveThousandFieldFont);
        enterBillPanel.add(fiveThousandField, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        fiveThousandLabel = new JLabel();
        Font fiveThousandLabelFont = this.$$$getFont$$$(null, -1, 20, fiveThousandLabel.getFont());
        if (fiveThousandLabelFont != null) fiveThousandLabel.setFont(fiveThousandLabelFont);
        fiveThousandLabel.setForeground(new Color(-16777216));
        fiveThousandLabel.setText("₩ 5000");
        enterBillPanel.add(fiveThousandLabel, new GridConstraints(3, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tenThousandLabel = new JLabel();
        Font tenThousandLabelFont = this.$$$getFont$$$(null, -1, 20, tenThousandLabel.getFont());
        if (tenThousandLabelFont != null) tenThousandLabel.setFont(tenThousandLabelFont);
        tenThousandLabel.setForeground(new Color(-16777216));
        tenThousandLabel.setText("₩ 10000");
        enterBillPanel.add(tenThousandLabel, new GridConstraints(5, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tenThousandField = new JNumberTextField();
        Font tenThousandFieldFont = this.$$$getFont$$$(null, -1, 24, tenThousandField.getFont());
        if (tenThousandFieldFont != null) tenThousandField.setFont(tenThousandFieldFont);
        enterBillPanel.add(tenThousandField, new GridConstraints(5, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        fiftyThousandLabel = new JLabel();
        Font fiftyThousandLabelFont = this.$$$getFont$$$(null, -1, 20, fiftyThousandLabel.getFont());
        if (fiftyThousandLabelFont != null) fiftyThousandLabel.setFont(fiftyThousandLabelFont);
        fiftyThousandLabel.setForeground(new Color(-16777216));
        fiftyThousandLabel.setText("₩ 50000");
        enterBillPanel.add(fiftyThousandLabel, new GridConstraints(7, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fiftyThousandField = new JNumberTextField();
        Font fiftyThousandFieldFont = this.$$$getFont$$$(null, -1, 24, fiftyThousandField.getFont());
        if (fiftyThousandFieldFont != null) fiftyThousandField.setFont(fiftyThousandFieldFont);
        enterBillPanel.add(fiftyThousandField, new GridConstraints(7, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterBillPanel.add(panel1, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 24, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Button");
        panel1.add(confirmButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        discardButton = new JButton();
        discardButton.setBackground(new Color(-10592674));
        Font discardButtonFont = this.$$$getFont$$$(null, -1, 24, discardButton.getFont());
        if (discardButtonFont != null) discardButton.setFont(discardButtonFont);
        discardButton.setForeground(new Color(-1));
        discardButton.setText("Button");
        panel1.add(discardButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
