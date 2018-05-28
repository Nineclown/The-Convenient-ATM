package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.AccountDoesNotExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Pattern;

public class EnterDate {
    private JPanel enterDatePanel;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton cancelButton;
    private JButton confirmButton;

    public EnterDate(final JFrame parentFrame, final ATMSystem system) {

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Date start, end;
                if (!startDateField.getText().matches("^\\d{8}$") && !endDateField.getText().matches("^\\d{8}$")) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int startyear, startmonth, startdate;
                int endyear, endmonth, enddate;

                startyear = Integer.parseInt(startDateField.getText().substring(0, 4));
                startmonth = Integer.parseInt(startDateField.getText().substring(4, 6));
                startdate = Integer.parseInt(startDateField.getText().substring(6, 8));
                endyear = Integer.parseInt(endDateField.getText().substring(0, 4));
                endmonth = Integer.parseInt(endDateField.getText().substring(4, 6));
                enddate = Integer.parseInt(endDateField.getText().substring(6, 8));

                if (startmonth > 12 || endmonth > 12 || startdate > 31 || enddate > 31 || startyear < 1900 || endyear < 1900) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                start = new Date(startyear - 1900, startmonth - 1, startdate);
                end = new Date(endyear - 1900, endmonth - 1, enddate + 1);


                if (start.compareTo(end) >= 0) {
                    JOptionPane.showMessageDialog(parentFrame, "시작일이 끝나는 날보다 큽니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    system.enterPeriodToQuery(start, end);
                } catch (AccountDoesNotExist ex) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                parentFrame.setContentPane(new QueryList(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterDatePanel;
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
        enterDatePanel = new JPanel();
        enterDatePanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        enterDatePanel.setBackground(new Color(-1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("시작");
        enterDatePanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("끝");
        enterDatePanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startDateField = new JTextField();
        Font startDateFieldFont = this.$$$getFont$$$(null, -1, 20, startDateField.getFont());
        if (startDateFieldFont != null) startDateField.setFont(startDateFieldFont);
        enterDatePanel.add(startDateField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        endDateField = new JTextField();
        Font endDateFieldFont = this.$$$getFont$$$(null, -1, 20, endDateField.getFont());
        if (endDateFieldFont != null) endDateField.setFont(endDateFieldFont);
        enterDatePanel.add(endDateField, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterDatePanel.add(panel1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-10592674));
        Font cancelButtonFont = this.$$$getFont$$$(null, -1, 20, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        cancelButton.setForeground(new Color(-1));
        cancelButton.setText("취소");
        panel1.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 60), null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        panel1.add(confirmButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 60), null, 0, false));
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
        return enterDatePanel;
    }
}
