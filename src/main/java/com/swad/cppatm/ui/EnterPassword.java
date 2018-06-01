package com.swad.cppatm.ui;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterPassword extends JFrame {
    private JPanel enterPasswordPanel;
    private JPasswordField passwordField;
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
    private JLabel amountLabel;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    public EnterPassword(final JFrame parentFrame, final ATMSystem system) {
        if (system.getFunction() == FunctionType.SplitPay) {
            amountLabel.setText("이체 금액 : " + system.getCashAmount());
        }

        titleLabel.setText(setLocalizedString(system, "비밀번호를 입력하여 주십시오.", "Please enter password."));
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "3");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "6");
            }
        });
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "9");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() >= 4) {
                    return;
                }
                passwordField.setText(value + "0");
            }
        });
        BSButton.setText(setLocalizedString(system, "정정", "BS"));
        BSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if (value.length() == 0) {
                    return;
                }

                passwordField.setText(value.substring(0, value.length() - 1));
            }
        });
        clearButton.setText(setLocalizedString(system, "비우기", "Clear"));
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (passwordField.getPassword().length == 0) {
                    return;
                }

                passwordField.setText("");
            }
        });
        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (passwordField.getPassword().length != 4) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잘못된 비밀번호 형식입니다.", "Invalid Password Format."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!system.getAccount().isAccountEnabled()) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "계좌가 정지되었습니다.", "Account is Frozen"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                try {
                    system.enterPassword(Integer.parseInt(new String(passwordField.getPassword())));
                } catch (AccountDoesNotExist | DataStoreError ex) {
                    JOptionPane.showMessageDialog(parentFrame, "???", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (InvalidPasswordException ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잘못된 비밀번호 입니다.", "Password is invalid."),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (NegativeBalanceError ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "잔액이 부족합니다.", "Not Enough Balance"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } catch (FrozenAccountException ex) {
                    JOptionPane.showMessageDialog(parentFrame,
                        setLocalizedString(system, "계좌가 정지되었습니다.", "Account is Frozen"),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                    switch (system.getFunction()) {
                    case Withdraw:
                        parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                        break;
                    case ForeignWithdraw:
                        parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                        break;
                    case QueryTransactionList:
                        parentFrame.setContentPane(new EnterDate(parentFrame, system).getPanel());
                        break;
                    case QueryBalance:
                        parentFrame.setContentPane(new QueryBalance(parentFrame, system).getPanel());
                        break;
                    case Transfer:
                        parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                        break;
                    case SplitPay:
                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    default:
                        parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                }

                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterPasswordPanel;
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
        enterPasswordPanel = new JPanel();
        enterPasswordPanel.setLayout(new GridLayoutManager(1, 2, new Insets(30, 30, 30, 30), 30, -1, true, false));
        enterPasswordPanel.setBackground(new Color(-1));
        enterPasswordPanel.setMinimumSize(new Dimension(800, 600));
        enterPasswordPanel.setPreferredSize(new Dimension(800, 600));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterPasswordPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        passwordField = new JPasswordField();
        Font passwordFieldFont = this.$$$getFont$$$(null, -1, 30, passwordField.getFont());
        if (passwordFieldFont != null) passwordField.setFont(passwordFieldFont);
        passwordField.setHorizontalAlignment(11);
        panel1.add(passwordField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        amountLabel = new JLabel();
        Font amountLabelFont = this.$$$getFont$$$(null, -1, 20, amountLabel.getFont());
        if (amountLabelFont != null) amountLabel.setFont(amountLabelFont);
        amountLabel.setForeground(new Color(-16777216));
        amountLabel.setText("");
        panel1.add(amountLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("비밀번호를 입력하여 주십시오.");
        panel1.add(titleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 10, 10, true, true));
        panel2.setBackground(new Color(-1));
        enterPasswordPanel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a1Button = new JButton();
        a1Button.setBackground(new Color(-1));
        Font a1ButtonFont = this.$$$getFont$$$(null, -1, 20, a1Button.getFont());
        if (a1ButtonFont != null) a1Button.setFont(a1ButtonFont);
        a1Button.setForeground(new Color(-11645362));
        a1Button.setText("1");
        panel2.add(a1Button, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a2Button = new JButton();
        a2Button.setBackground(new Color(-1));
        Font a2ButtonFont = this.$$$getFont$$$(null, -1, 20, a2Button.getFont());
        if (a2ButtonFont != null) a2Button.setFont(a2ButtonFont);
        a2Button.setForeground(new Color(-11645362));
        a2Button.setText("2");
        panel2.add(a2Button, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a3Button = new JButton();
        a3Button.setBackground(new Color(-1));
        Font a3ButtonFont = this.$$$getFont$$$(null, -1, 20, a3Button.getFont());
        if (a3ButtonFont != null) a3Button.setFont(a3ButtonFont);
        a3Button.setForeground(new Color(-11645362));
        a3Button.setText("3");
        panel2.add(a3Button, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a4Button = new JButton();
        a4Button.setBackground(new Color(-1));
        Font a4ButtonFont = this.$$$getFont$$$(null, -1, 20, a4Button.getFont());
        if (a4ButtonFont != null) a4Button.setFont(a4ButtonFont);
        a4Button.setForeground(new Color(-11645362));
        a4Button.setText("4");
        panel2.add(a4Button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a5Button = new JButton();
        a5Button.setBackground(new Color(-1));
        Font a5ButtonFont = this.$$$getFont$$$(null, -1, 20, a5Button.getFont());
        if (a5ButtonFont != null) a5Button.setFont(a5ButtonFont);
        a5Button.setForeground(new Color(-11645362));
        a5Button.setText("5");
        panel2.add(a5Button, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a6Button = new JButton();
        a6Button.setBackground(new Color(-1));
        Font a6ButtonFont = this.$$$getFont$$$(null, -1, 20, a6Button.getFont());
        if (a6ButtonFont != null) a6Button.setFont(a6ButtonFont);
        a6Button.setForeground(new Color(-11645362));
        a6Button.setText("6");
        panel2.add(a6Button, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a7Button = new JButton();
        a7Button.setBackground(new Color(-1));
        Font a7ButtonFont = this.$$$getFont$$$(null, -1, 20, a7Button.getFont());
        if (a7ButtonFont != null) a7Button.setFont(a7ButtonFont);
        a7Button.setForeground(new Color(-11645362));
        a7Button.setText("7");
        panel2.add(a7Button, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a8Button = new JButton();
        a8Button.setBackground(new Color(-1));
        Font a8ButtonFont = this.$$$getFont$$$(null, -1, 20, a8Button.getFont());
        if (a8ButtonFont != null) a8Button.setFont(a8ButtonFont);
        a8Button.setForeground(new Color(-11645362));
        a8Button.setText("8");
        panel2.add(a8Button, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a9Button = new JButton();
        a9Button.setBackground(new Color(-1));
        Font a9ButtonFont = this.$$$getFont$$$(null, -1, 20, a9Button.getFont());
        if (a9ButtonFont != null) a9Button.setFont(a9ButtonFont);
        a9Button.setForeground(new Color(-11645362));
        a9Button.setText("9");
        panel2.add(a9Button, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BSButton = new JButton();
        BSButton.setBackground(new Color(-1));
        Font BSButtonFont = this.$$$getFont$$$(null, -1, 20, BSButton.getFont());
        if (BSButtonFont != null) BSButton.setFont(BSButtonFont);
        BSButton.setForeground(new Color(-11645362));
        BSButton.setText("BS");
        panel2.add(BSButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a0Button = new JButton();
        a0Button.setBackground(new Color(-1));
        Font a0ButtonFont = this.$$$getFont$$$(null, -1, 20, a0Button.getFont());
        if (a0ButtonFont != null) a0Button.setFont(a0ButtonFont);
        a0Button.setForeground(new Color(-11645362));
        a0Button.setText("0");
        panel2.add(a0Button, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setBackground(new Color(-1));
        Font clearButtonFont = this.$$$getFont$$$(null, -1, 20, clearButton.getFont());
        if (clearButtonFont != null) clearButton.setFont(clearButtonFont);
        clearButton.setForeground(new Color(-11645362));
        clearButton.setText("Clear");
        panel2.add(clearButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-13330213));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Confirm");
        panel2.add(confirmButton, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return enterPasswordPanel;
    }
}
