package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterNumber extends JFrame {
    private JPanel enterNumberPanel;
    private JTextField numberField;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton BSButton;
    private JButton a0Button;
    private JButton clearButton;
    private JButton confirmButton;
    private JLabel titleLabel;
    private JLabel currencyLabel;
    private boolean splitPayFlag = false;

    public EnterNumber(final JFrame parentFrame, final ATMSystem system) {
        if (system.getFunction() == FunctionType.ForeignWithdraw) {
            currencyLabel.setText("달러");
        }
        if (system.getFunction() == FunctionType.SplitPay) {
            titleLabel.setText("총 받아야 할 금액을 입력하여 주십시오.");
        }

        if (system.getFunction() == FunctionType.Transfer) {
            titleLabel.setText("보내실 금액을 입력하여 주십시오.");
        }
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "3");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "6");
            }
        });
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "9");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "0");
            }
        });
        BSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (numberField.getText().length() == 0) {
                    return;
                }

                numberField.setText(numberField.getText().substring(0, numberField.getText().length() - 1));
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (numberField.getText().length() == 0) {
                    return;
                }

                numberField.setText("");
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int insertNumber = Integer.parseInt(numberField.getText());

                switch (system.getFunction()) {
                    case Withdraw:
                        if (insertNumber > 100) {
                            JOptionPane.showMessageDialog(parentFrame, "출금하려는 금액이 너무 큽니다. 다시 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            system.enterBillAmountToWithdraw(-insertNumber * 10000);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        } catch (OverflowBillException ex) {
                            JOptionPane.showMessageDialog(parentFrame, "해당 거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Error", JOptionPane.ERROR_MESSAGE);

                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case ForeignWithdraw:
                        if (insertNumber > 5000) {
                            JOptionPane.showMessageDialog(parentFrame, "출금하려는 금액이 너무 큽니다. 다시 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            system.enterBillAmountToWithdrawAsDollar(-insertNumber);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        } catch (OverflowBillException ex) {
                            JOptionPane.showMessageDialog(parentFrame, "해당 거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Error", JOptionPane.ERROR_MESSAGE);

                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case Transfer:
                        try {
                            system.enterCashAmountToTransfer(insertNumber * 10000);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case SplitPay:
                        if (splitPayFlag) {
                            try {
                                if (insertNumber > 10) {
                                    JOptionPane.showMessageDialog(parentFrame, "사람 수가 너무 많습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                system.enterNumberOfUsers(insertNumber);
                                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                                parentFrame.pack();
                                parentFrame.invalidate();
                                parentFrame.validate();
                            } catch (TooFewUser ex1) {
                                JOptionPane.showMessageDialog(parentFrame, "사람 수가 너무 적습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            system.enterTotalCashAmountToGet(insertNumber * 10000);
                            titleLabel.setText("총 이체 할 인원 수를 입력하여 주십시오.");
                            currencyLabel.setText("명");
                            numberField.setText("");
                            splitPayFlag = true;
                        }
                        break;
                    default:
                        parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                        break;
                }

                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterNumberPanel;
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
        enterNumberPanel = new JPanel();
        enterNumberPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(30, 30, 30, 30), 30, -1, true, false));
        enterNumberPanel.setBackground(new Color(-1));
        enterNumberPanel.setMinimumSize(new Dimension(800, 600));
        enterNumberPanel.setPreferredSize(new Dimension(800, 600));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterNumberPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberField = new JTextField();
        Font numberFieldFont = this.$$$getFont$$$(null, -1, 30, numberField.getFont());
        if (numberFieldFont != null) numberField.setFont(numberFieldFont);
        numberField.setHorizontalAlignment(11);
        panel1.add(numberField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        currencyLabel = new JLabel();
        Font currencyLabelFont = this.$$$getFont$$$(null, -1, 20, currencyLabel.getFont());
        if (currencyLabelFont != null) currencyLabel.setFont(currencyLabelFont);
        currencyLabel.setText("만원");
        panel1.add(currencyLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setText("찾으실 금액을 입력하여 주십시오.");
        panel1.add(titleLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 10, 10, true, true));
        panel2.setBackground(new Color(-1));
        enterNumberPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a1Button = new JButton();
        a1Button.setBackground(new Color(-1));
        Font a1ButtonFont = this.$$$getFont$$$(null, -1, 20, a1Button.getFont());
        if (a1ButtonFont != null) a1Button.setFont(a1ButtonFont);
        a1Button.setForeground(new Color(-11645362));
        a1Button.setText("1");
        panel2.add(a1Button, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a2Button = new JButton();
        a2Button.setBackground(new Color(-1));
        Font a2ButtonFont = this.$$$getFont$$$(null, -1, 20, a2Button.getFont());
        if (a2ButtonFont != null) a2Button.setFont(a2ButtonFont);
        a2Button.setForeground(new Color(-11645362));
        a2Button.setText("2");
        panel2.add(a2Button, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a3Button = new JButton();
        a3Button.setBackground(new Color(-1));
        Font a3ButtonFont = this.$$$getFont$$$(null, -1, 20, a3Button.getFont());
        if (a3ButtonFont != null) a3Button.setFont(a3ButtonFont);
        a3Button.setForeground(new Color(-11645362));
        a3Button.setText("3");
        panel2.add(a3Button, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a4Button = new JButton();
        a4Button.setBackground(new Color(-1));
        Font a4ButtonFont = this.$$$getFont$$$(null, -1, 20, a4Button.getFont());
        if (a4ButtonFont != null) a4Button.setFont(a4ButtonFont);
        a4Button.setForeground(new Color(-11645362));
        a4Button.setText("4");
        panel2.add(a4Button, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a5Button = new JButton();
        a5Button.setBackground(new Color(-1));
        Font a5ButtonFont = this.$$$getFont$$$(null, -1, 20, a5Button.getFont());
        if (a5ButtonFont != null) a5Button.setFont(a5ButtonFont);
        a5Button.setForeground(new Color(-11645362));
        a5Button.setText("5");
        panel2.add(a5Button, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a6Button = new JButton();
        a6Button.setBackground(new Color(-1));
        Font a6ButtonFont = this.$$$getFont$$$(null, -1, 20, a6Button.getFont());
        if (a6ButtonFont != null) a6Button.setFont(a6ButtonFont);
        a6Button.setForeground(new Color(-11645362));
        a6Button.setText("6");
        panel2.add(a6Button, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a7Button = new JButton();
        a7Button.setBackground(new Color(-1));
        Font a7ButtonFont = this.$$$getFont$$$(null, -1, 20, a7Button.getFont());
        if (a7ButtonFont != null) a7Button.setFont(a7ButtonFont);
        a7Button.setForeground(new Color(-11645362));
        a7Button.setText("7");
        panel2.add(a7Button, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a8Button = new JButton();
        a8Button.setBackground(new Color(-1));
        Font a8ButtonFont = this.$$$getFont$$$(null, -1, 20, a8Button.getFont());
        if (a8ButtonFont != null) a8Button.setFont(a8ButtonFont);
        a8Button.setForeground(new Color(-11645362));
        a8Button.setText("8");
        panel2.add(a8Button, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a9Button = new JButton();
        a9Button.setBackground(new Color(-1));
        Font a9ButtonFont = this.$$$getFont$$$(null, -1, 20, a9Button.getFont());
        if (a9ButtonFont != null) a9Button.setFont(a9ButtonFont);
        a9Button.setForeground(new Color(-11645362));
        a9Button.setText("9");
        panel2.add(a9Button, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BSButton = new JButton();
        BSButton.setBackground(new Color(-1));
        Font BSButtonFont = this.$$$getFont$$$(null, -1, 20, BSButton.getFont());
        if (BSButtonFont != null) BSButton.setFont(BSButtonFont);
        BSButton.setForeground(new Color(-11645362));
        BSButton.setText("BS");
        panel2.add(BSButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a0Button = new JButton();
        a0Button.setBackground(new Color(-1));
        Font a0ButtonFont = this.$$$getFont$$$(null, -1, 20, a0Button.getFont());
        if (a0ButtonFont != null) a0Button.setFont(a0ButtonFont);
        a0Button.setForeground(new Color(-11645362));
        a0Button.setText("0");
        panel2.add(a0Button, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setBackground(new Color(-1));
        Font clearButtonFont = this.$$$getFont$$$(null, -1, 20, clearButton.getFont());
        if (clearButtonFont != null) clearButton.setFont(clearButtonFont);
        clearButton.setForeground(new Color(-11645362));
        clearButton.setText("Clear");
        panel2.add(clearButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-13330213));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Confirm");
        panel2.add(confirmButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return enterNumberPanel;
    }
}
