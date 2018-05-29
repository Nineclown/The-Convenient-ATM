package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;
import com.swad.cppatm.ui.components.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterATMBalance {
    private JButton confirmButton;
    private JPanel enterATMBalancePanel;
    private JButton discardButton;
    private JNumberTextField won50000;
    private JNumberTextField won10000;
    private JNumberTextField won5000;
    private JNumberTextField won1000;
    private JNumberTextField dollar1;
    private JNumberTextField dollar2;
    private JNumberTextField dollar5;
    private JNumberTextField dollar10;
    private JNumberTextField dollar20;
    private JNumberTextField dollar50;
    private JNumberTextField dollar100;
    private JLabel titleLabel;
    private JNumberTextField bills[];

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    EnterATMBalance(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "ATM 현금 입력", "Insert ATM Balance"));
        confirmButton.setText(setLocalizedString(system, "확인", "Insert"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int[] billAmount = new int[11];
                bills = new JNumberTextField[11];
                bills[0] = won1000;
                bills[1] = won5000;
                bills[2] = won10000;
                bills[3] = won50000;
                bills[4] = dollar1;
                bills[5] = dollar2;
                bills[6] = dollar5;
                bills[7] = dollar10;
                bills[8] = dollar20;
                bills[9] = dollar50;
                bills[10] = dollar100;

                for (int i = 0; i < 11; i++) {
                    billAmount[i] = bills[i].getNumber();
                }

                try {
                    system.enterATMBalance(billAmount);
                } catch (OverflowBillException e1) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "ATM이 수용 할 수 있는 지폐량을 벗어났습니다..", "ATM can't have too many or few money"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } catch (InvalidBillException e2) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잘못된 입력입니다.", "Wrong Input"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }

                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        discardButton.setText(setLocalizedString(system, "취소", "Discard"));
        discardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterATMBalancePanel;
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
        enterATMBalancePanel = new JPanel();
        enterATMBalancePanel.setLayout(new GridLayoutManager(9, 4, new Insets(30, 30, 30, 30), -1, -1, false, true));
        enterATMBalancePanel.setBackground(new Color(-1));
        enterATMBalancePanel.setMinimumSize(new Dimension(800, 600));
        enterATMBalancePanel.setPreferredSize(new Dimension(800, 600));
        discardButton = new JButton();
        discardButton.setBackground(new Color(-10592674));
        Font discardButtonFont = this.$$$getFont$$$(null, -1, 24, discardButton.getFont());
        if (discardButtonFont != null) discardButton.setFont(discardButtonFont);
        discardButton.setForeground(new Color(-1));
        discardButton.setText("Button");
        enterATMBalancePanel.add(discardButton, new GridConstraints(8, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        won50000 = new JNumberTextField();
        Font won50000Font = this.$$$getFont$$$(null, -1, 20, won50000.getFont());
        if (won50000Font != null) won50000.setFont(won50000Font);
        won50000.setHorizontalAlignment(11);
        enterATMBalancePanel.add(won50000, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        won10000 = new JNumberTextField();
        Font won10000Font = this.$$$getFont$$$(null, -1, 20, won10000.getFont());
        if (won10000Font != null) won10000.setFont(won10000Font);
        won10000.setHorizontalAlignment(11);
        enterATMBalancePanel.add(won10000, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        won5000 = new JNumberTextField();
        Font won5000Font = this.$$$getFont$$$(null, -1, 20, won5000.getFont());
        if (won5000Font != null) won5000.setFont(won5000Font);
        won5000.setHorizontalAlignment(11);
        enterATMBalancePanel.add(won5000, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        won1000 = new JNumberTextField();
        Font won1000Font = this.$$$getFont$$$(null, -1, 20, won1000.getFont());
        if (won1000Font != null) won1000.setFont(won1000Font);
        won1000.setHorizontalAlignment(11);
        enterATMBalancePanel.add(won1000, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dollar1 = new JNumberTextField();
        Font dollar1Font = this.$$$getFont$$$(null, -1, 20, dollar1.getFont());
        if (dollar1Font != null) dollar1.setFont(dollar1Font);
        dollar1.setHorizontalAlignment(11);
        enterATMBalancePanel.add(dollar1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dollar2 = new JNumberTextField();
        Font dollar2Font = this.$$$getFont$$$(null, -1, 20, dollar2.getFont());
        if (dollar2Font != null) dollar2.setFont(dollar2Font);
        dollar2.setHorizontalAlignment(11);
        enterATMBalancePanel.add(dollar2, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dollar5 = new JNumberTextField();
        Font dollar5Font = this.$$$getFont$$$(null, -1, 20, dollar5.getFont());
        if (dollar5Font != null) dollar5.setFont(dollar5Font);
        dollar5.setHorizontalAlignment(11);
        dollar5.setText("");
        enterATMBalancePanel.add(dollar5, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dollar10 = new JNumberTextField();
        Font dollar10Font = this.$$$getFont$$$(null, -1, 20, dollar10.getFont());
        if (dollar10Font != null) dollar10.setFont(dollar10Font);
        dollar10.setHorizontalAlignment(11);
        dollar10.setText("");
        enterATMBalancePanel.add(dollar10, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("₩1000");
        enterATMBalancePanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 24, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Button");
        enterATMBalancePanel.add(confirmButton, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("₩5000");
        enterATMBalancePanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 20, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-16777216));
        label3.setText("₩10000");
        enterATMBalancePanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 20, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-16777216));
        label4.setText("₩50000");
        enterATMBalancePanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 20, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-16777216));
        label5.setText("$1");
        enterATMBalancePanel.add(label5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollar100 = new JNumberTextField();
        Font dollar100Font = this.$$$getFont$$$(null, -1, 20, dollar100.getFont());
        if (dollar100Font != null) dollar100.setFont(dollar100Font);
        dollar100.setHorizontalAlignment(11);
        dollar100.setText("");
        enterATMBalancePanel.add(dollar100, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$(null, -1, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-16777216));
        label6.setText("$100");
        enterATMBalancePanel.add(label6, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollar50 = new JNumberTextField();
        Font dollar50Font = this.$$$getFont$$$(null, -1, 20, dollar50.getFont());
        if (dollar50Font != null) dollar50.setFont(dollar50Font);
        dollar50.setHorizontalAlignment(11);
        enterATMBalancePanel.add(dollar50, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 20, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-16777216));
        label7.setText("$50");
        enterATMBalancePanel.add(label7, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dollar20 = new JNumberTextField();
        Font dollar20Font = this.$$$getFont$$$(null, -1, 20, dollar20.getFont());
        if (dollar20Font != null) dollar20.setFont(dollar20Font);
        dollar20.setHorizontalAlignment(11);
        enterATMBalancePanel.add(dollar20, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("Input ATM Balance");
        enterATMBalancePanel.add(titleLabel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$(null, -1, 20, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-16777216));
        label8.setText("$2");
        enterATMBalancePanel.add(label8, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, -1, 20, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setForeground(new Color(-16777216));
        label9.setText("$5");
        enterATMBalancePanel.add(label9, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, -1, 20, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setForeground(new Color(-16777216));
        label10.setText("$10");
        enterATMBalancePanel.add(label10, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, -1, 20, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setForeground(new Color(-16777216));
        label11.setText("$20");
        enterATMBalancePanel.add(label11, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return enterATMBalancePanel;
    }
}
