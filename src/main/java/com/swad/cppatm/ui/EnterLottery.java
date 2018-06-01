package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Lottery;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.LotteryFailed;
import com.swad.cppatm.ui.components.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class EnterLottery {
    private JPanel enterLotteryPanel;
    private JNumberTextField textField1;
    private JNumberTextField textField2;
    private JNumberTextField textField3;
    private JNumberTextField textField4;
    private JNumberTextField textField5;
    private JNumberTextField textField6;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel titleLabel;
    private JNumberTextField weekField;
    private JLabel weekLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if (system.getState().getLocale() == Locale.en_US) {
            return en;
        } else {
            return ko;
        }
    }

    public EnterLottery(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "로또 번호를 입력하여 주십시오.", "Plaese enter lottery number."));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int week;
                int[] values;

                try {
                    week = weekField.getNumber();
                    values = new int[]{
                        textField1.getNumber(),
                        textField2.getNumber(),
                        textField3.getNumber(),
                        textField4.getNumber(),
                        textField5.getNumber(),
                        textField6.getNumber()
                    };
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "올바르지 않은 번호입니다.", "Incorrect number."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long weekMax = (new Date().getTime() - new Date(102, 10, 30).getTime()) / (7 * 24 * 60 * 60 * 1000);

                if (week <= 0 || week > weekMax) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잘못된 주차입니다.", "Incorrect week No."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < values.length; i++) {
                    if (values[i] <= 0 || values[i] > 45) {
                        JOptionPane.showMessageDialog(parentFrame,
                            setLocalizedString(system, "올바르지 않은 번호입니다.", "Incorrect number."),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for (int j = i + 1; j < values.length; j++) {
                        if (values[i] == values[j]) {
                            JOptionPane.showMessageDialog(parentFrame,
                                setLocalizedString(system, "올바르지 않은 번호입니다.", "Incorrect number."),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                Lottery lottery = new Lottery(week, values);

                try {
                    system.enterLottery(lottery);
                } catch (LotteryFailed ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "당첨되지 않았습니다.", "You lose."),
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }
                JOptionPane.showMessageDialog(parentFrame,
                    setLocalizedString(system, "당첨금 : ", "You lose.") + system.getToTransaction().getAmount(),
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);


                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
        cancelButton.addMouseListener(new MouseAdapter() {
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
        return this.enterLotteryPanel;
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
        enterLotteryPanel = new JPanel();
        enterLotteryPanel.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1, true, false));
        enterLotteryPanel.setBackground(new Color(-1));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-10592674));
        cancelButton.setEnabled(true);
        Font cancelButtonFont = this.$$$getFont$$$(null, -1, 20, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        cancelButton.setForeground(new Color(-1));
        cancelButton.setText("취소");
        enterLotteryPanel.add(cancelButton, new GridConstraints(3, 3, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        confirmButton.setEnabled(true);
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        enterLotteryPanel.add(confirmButton, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("로또 번호를 입력하여 주십시오.");
        enterLotteryPanel.add(titleLabel, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        weekLabel = new JLabel();
        Font weekLabelFont = this.$$$getFont$$$(null, -1, 20, weekLabel.getFont());
        if (weekLabelFont != null) weekLabel.setFont(weekLabelFont);
        weekLabel.setForeground(new Color(-16777216));
        weekLabel.setText("주차");
        enterLotteryPanel.add(weekLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JNumberTextField();
        Font textField1Font = this.$$$getFont$$$(null, -1, 20, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        enterLotteryPanel.add(textField1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField2 = new JNumberTextField();
        Font textField2Font = this.$$$getFont$$$(null, -1, 20, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        enterLotteryPanel.add(textField2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField3 = new JNumberTextField();
        Font textField3Font = this.$$$getFont$$$(null, -1, 20, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        enterLotteryPanel.add(textField3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField4 = new JNumberTextField();
        Font textField4Font = this.$$$getFont$$$(null, -1, 20, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        enterLotteryPanel.add(textField4, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField5 = new JNumberTextField();
        Font textField5Font = this.$$$getFont$$$(null, -1, 20, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        enterLotteryPanel.add(textField5, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField6 = new JNumberTextField();
        Font textField6Font = this.$$$getFont$$$(null, -1, 20, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        enterLotteryPanel.add(textField6, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        weekField = new JNumberTextField();
        Font weekFieldFont = this.$$$getFont$$$(null, -1, 20, weekField.getFont());
        if (weekFieldFont != null) weekField.setFont(weekFieldFont);
        enterLotteryPanel.add(weekField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        return enterLotteryPanel;
    }
}
