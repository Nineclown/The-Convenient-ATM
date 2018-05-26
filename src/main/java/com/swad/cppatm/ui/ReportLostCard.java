package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.UserDoestNotExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportLostCard extends JFrame {
    private JLabel cardNumberLabel1;
    private JCheckBox reportLostCheckBox1;
    private JTextField cardNumberField2;
    private JCheckBox reportLostCheckBox2;
    private JCheckBox reportLostCheckBox3;
    private JCheckBox reportLostCheckBox4;
    private JTextField cardNumberField3;
    private JTextField cardNumberField1;
    private JTextField cardNumberField4;
    private JTextField[] cardFields;
    private JCheckBox[] checkBox;
    private JLabel[] cardLabels;
    private JPanel reportLostCardPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private ButtonGroup buttongroup;
    private JLabel titleLabel;
    private JLabel cardNumberLabel2;
    private JLabel cardNumberLabel3;
    private JLabel cardNumberLabel4;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    public ReportLostCard(final JFrame parentFrame, final ATMSystem system) {
        cardFields = new JTextField[4];
        cardLabels = new JLabel[4];
        checkBox = new JCheckBox[4];
        buttongroup = new ButtonGroup();


        cardFields[0] = cardNumberField1;
        cardFields[1] = cardNumberField2;
        cardFields[2] = cardNumberField3;
        cardFields[3] = cardNumberField4;

        checkBox[0] = reportLostCheckBox1;
        checkBox[1] = reportLostCheckBox2;
        checkBox[2] = reportLostCheckBox3;
        checkBox[3] = reportLostCheckBox4;

        for (int i = 0; i < checkBox.length; i++) {
            buttongroup.add(checkBox[i]);
        }

        cardLabels[0] = cardNumberLabel1;
        cardLabels[1] = cardNumberLabel2;
        cardLabels[2] = cardNumberLabel3;
        cardLabels[3] = cardNumberLabel4;

        String[] card = system.getUser().getCardList();
        int length = card.length;

        titleLabel.setText(setLocalizedString(system, "분실처리를 할 카드를 선택하여 주십시오", "Please select the card that you've lost"));

        for (int i = 0; i < length; i++) {
            cardLabels[i].setText(setLocalizedString(system, "카드번호", "Card"));
            if (!card[i].equals("")) {
                cardFields[i].setText(card[i].toString());
            }
        }

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < checkBox.length; i++) {
                    if (!cardFields[i].getText().equals("") && checkBox[i].isSelected()) {
                        try {
                            system.selectCard(card[i]);
                            JOptionPane.showMessageDialog(parentFrame, card[i].toString() + " 카드를 중지처리하였습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
                            system.removeCard(card[i]);
                            system.saveUser();
                        } catch (DataStoreError ex) {
                        }
                        ;
                        int answer = JOptionPane.showConfirmDialog(parentFrame.getContentPane(), "재발급을 신청하시겠습니까?", "카드 재발급", JOptionPane.YES_NO_OPTION);
                        switch (answer) {
                            case JOptionPane.YES_OPTION:
                                try {
                                    system.askRenewCard(true);
                                } catch (DataStoreError ex) {
                                }
                                ;
                                JOptionPane.showMessageDialog(parentFrame, card[i].toString() + " 카드를 재발급 처리하였습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
                            case JOptionPane.NO_OPTION:
                                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                                parentFrame.invalidate();
                                parentFrame.validate();
                                break;
                        }
                    }
                }


            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }


    public JPanel getPanel() {
        return this.reportLostCardPanel;
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
        reportLostCardPanel = new JPanel();
        reportLostCardPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(15, 7, new Insets(0, 0, 0, 0), -1, -1));
        reportLostCardPanel.setBackground(new Color(-1));
        reportLostCardPanel.setMinimumSize(new Dimension(800, 600));
        reportLostCardPanel.setPreferredSize(new Dimension(800, 600));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("분실처리를 할 카드를 선택하여 주십시오");
        reportLostCardPanel.add(titleLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cardNumberLabel4 = new JLabel();
        Font cardNumberLabel4Font = this.$$$getFont$$$(null, -1, 20, cardNumberLabel4.getFont());
        if (cardNumberLabel4Font != null) cardNumberLabel4.setFont(cardNumberLabel4Font);
        cardNumberLabel4.setForeground(new Color(-16777216));
        cardNumberLabel4.setText("카드번호");
        reportLostCardPanel.add(cardNumberLabel4, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        cardNumberLabel3 = new JLabel();
        Font cardNumberLabel3Font = this.$$$getFont$$$(null, -1, 20, cardNumberLabel3.getFont());
        if (cardNumberLabel3Font != null) cardNumberLabel3.setFont(cardNumberLabel3Font);
        cardNumberLabel3.setForeground(new Color(-16777216));
        cardNumberLabel3.setText("카드번호");
        reportLostCardPanel.add(cardNumberLabel3, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        cardNumberLabel1 = new JLabel();
        Font cardNumberLabel1Font = this.$$$getFont$$$(null, -1, 20, cardNumberLabel1.getFont());
        if (cardNumberLabel1Font != null) cardNumberLabel1.setFont(cardNumberLabel1Font);
        cardNumberLabel1.setForeground(new Color(-16777216));
        cardNumberLabel1.setText("카드번호");
        reportLostCardPanel.add(cardNumberLabel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        cardNumberLabel2 = new JLabel();
        Font cardNumberLabel2Font = this.$$$getFont$$$(null, -1, 20, cardNumberLabel2.getFont());
        if (cardNumberLabel2Font != null) cardNumberLabel2.setFont(cardNumberLabel2Font);
        cardNumberLabel2.setForeground(new Color(-16777216));
        cardNumberLabel2.setText("카드번호");
        reportLostCardPanel.add(cardNumberLabel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        cardNumberField4 = new JTextField();
        Font cardNumberField4Font = this.$$$getFont$$$(null, -1, 20, cardNumberField4.getFont());
        if (cardNumberField4Font != null) cardNumberField4.setFont(cardNumberField4Font);
        reportLostCardPanel.add(cardNumberField4, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(150, -1), null, 0, false));
        cardNumberField3 = new JTextField();
        Font cardNumberField3Font = this.$$$getFont$$$(null, -1, 20, cardNumberField3.getFont());
        if (cardNumberField3Font != null) cardNumberField3.setFont(cardNumberField3Font);
        reportLostCardPanel.add(cardNumberField3, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(150, -1), null, 0, false));
        cardNumberField2 = new JTextField();
        Font cardNumberField2Font = this.$$$getFont$$$(null, -1, 20, cardNumberField2.getFont());
        if (cardNumberField2Font != null) cardNumberField2.setFont(cardNumberField2Font);
        cardNumberField2.setText("");
        reportLostCardPanel.add(cardNumberField2, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(150, -1), null, 0, false));
        cardNumberField1 = new JTextField();
        Font cardNumberField1Font = this.$$$getFont$$$(null, -1, 20, cardNumberField1.getFont());
        if (cardNumberField1Font != null) cardNumberField1.setFont(cardNumberField1Font);
        cardNumberField1.setText("");
        reportLostCardPanel.add(cardNumberField1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(150, -1), null, 0, false));
        reportLostCheckBox4 = new JCheckBox();
        reportLostCheckBox4.setBackground(new Color(-1));
        reportLostCheckBox4.setEnabled(true);
        Font reportLostCheckBox4Font = this.$$$getFont$$$(null, -1, 20, reportLostCheckBox4.getFont());
        if (reportLostCheckBox4Font != null) reportLostCheckBox4.setFont(reportLostCheckBox4Font);
        reportLostCheckBox4.setForeground(new Color(-16777216));
        reportLostCheckBox4.setText("분실");
        reportLostCardPanel.add(reportLostCheckBox4, new com.intellij.uiDesigner.core.GridConstraints(7, 6, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        reportLostCheckBox3 = new JCheckBox();
        reportLostCheckBox3.setBackground(new Color(-1));
        reportLostCheckBox3.setEnabled(true);
        Font reportLostCheckBox3Font = this.$$$getFont$$$(null, -1, 20, reportLostCheckBox3.getFont());
        if (reportLostCheckBox3Font != null) reportLostCheckBox3.setFont(reportLostCheckBox3Font);
        reportLostCheckBox3.setForeground(new Color(-16777216));
        reportLostCheckBox3.setText("분실");
        reportLostCardPanel.add(reportLostCheckBox3, new com.intellij.uiDesigner.core.GridConstraints(5, 6, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        reportLostCheckBox2 = new JCheckBox();
        reportLostCheckBox2.setBackground(new Color(-1));
        reportLostCheckBox2.setEnabled(true);
        Font reportLostCheckBox2Font = this.$$$getFont$$$(null, -1, 20, reportLostCheckBox2.getFont());
        if (reportLostCheckBox2Font != null) reportLostCheckBox2.setFont(reportLostCheckBox2Font);
        reportLostCheckBox2.setForeground(new Color(-16777216));
        reportLostCheckBox2.setText("분실");
        reportLostCardPanel.add(reportLostCheckBox2, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        reportLostCheckBox1 = new JCheckBox();
        reportLostCheckBox1.setBackground(new Color(-1));
        reportLostCheckBox1.setEnabled(true);
        Font reportLostCheckBox1Font = this.$$$getFont$$$(null, -1, 20, reportLostCheckBox1.getFont());
        if (reportLostCheckBox1Font != null) reportLostCheckBox1.setFont(reportLostCheckBox1Font);
        reportLostCheckBox1.setForeground(new Color(-16777216));
        reportLostCheckBox1.setHorizontalAlignment(10);
        reportLostCheckBox1.setText("분실");
        reportLostCheckBox1.setVerticalAlignment(0);
        reportLostCardPanel.add(reportLostCheckBox1, new com.intellij.uiDesigner.core.GridConstraints(1, 6, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        reportLostCardPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        panel1.add(confirmButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-10592674));
        Font cancelButtonFont = this.$$$getFont$$$(null, -1, 20, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        cancelButton.setForeground(new Color(-1));
        cancelButton.setText("취소");
        panel1.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 60), null, 0, false));
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
        return reportLostCardPanel;
    }
}
