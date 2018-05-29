package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.MultipleFunctionsExecuted;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AdminSelectFunction extends JFrame {
    private JButton addAdminButton;
    private JButton changeATMBalanceButton;
    private JButton toggleStateButton;
    private JButton removeAdminButton;
    private JPanel adminSelectFunctionPanel;
    private JButton queryATMBalanceButton;
    private JButton changeLocaleButton;
    private JLabel atmStateLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if (system.getState().getLocale() == Locale.en_US) {
            return en;
        } else {
            return ko;
        }
    }

    AdminSelectFunction(final JFrame parentFrame, final ATMSystem system) {
        system.removeFunctionSelection();

        atmStateLabel.setText(system.getState().available() ? "Active" : "Frozen");

        addAdminButton.setText(setLocalizedString(system, "관리자 추가", "Add Admin"));
        addAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.AddAdmin);
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    return;
                }

                parentFrame.setContentPane(new EnterAdminInfo(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        removeAdminButton.setText(setLocalizedString(system, "관리자 삭제", "Remove Admin"));
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.RemoveAdmin);
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    JOptionPane.showMessageDialog(parentFrame, "관리자를 삭제 할 수 없습니다.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(parentFrame, "Admin deleted", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        queryATMBalanceButton.setText(setLocalizedString(system, "지폐 보유량 조회", "Query ATM Balance"));
        queryATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.QueryATMBalance);
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    return;
                }
                parentFrame.setContentPane(new QueryATMBalance(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        toggleStateButton.setText(setLocalizedString(system, "상태 변경", "Toggle State"));
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String state;

                try {
                    system.selectFunction(FunctionType.ToggleATMState);
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    return;
                }

                state = system.getState().available() ? "active" : "frozen";

                JOptionPane.showMessageDialog(parentFrame, "System is now " + state, "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        changeATMBalanceButton.setText(setLocalizedString(system, "지폐 보유량 변경", "Change ATM Balance"));
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeATMBalance);
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    return;
                }

                parentFrame.setContentPane(new EnterATMBalance(parentFrame, system).getPanel());
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
                } catch (NoneOfFunctionSelected | MultipleFunctionsExecuted ex) {
                    return;
                }

                parentFrame.setContentPane(new ChangeLocale(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.adminSelectFunctionPanel;
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
        adminSelectFunctionPanel = new JPanel();
        adminSelectFunctionPanel.setLayout(new GridLayoutManager(3, 3, new Insets(30, 30, 30, 30), 10, 30, true, false));
        adminSelectFunctionPanel.setBackground(new Color(-1));
        adminSelectFunctionPanel.setForeground(new Color(-1));
        adminSelectFunctionPanel.setMinimumSize(new Dimension(800, 600));
        adminSelectFunctionPanel.setPreferredSize(new Dimension(800, 600));
        changeATMBalanceButton = new JButton();
        changeATMBalanceButton.setBackground(new Color(-10592674));
        Font changeATMBalanceButtonFont = this.$$$getFont$$$(null, -1, 20, changeATMBalanceButton.getFont());
        if (changeATMBalanceButtonFont != null) changeATMBalanceButton.setFont(changeATMBalanceButtonFont);
        changeATMBalanceButton.setForeground(new Color(-1));
        changeATMBalanceButton.setText("Change ATM Balance");
        adminSelectFunctionPanel.add(changeATMBalanceButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        changeLocaleButton = new JButton();
        changeLocaleButton.setBackground(new Color(-10592674));
        Font changeLocaleButtonFont = this.$$$getFont$$$(null, -1, 20, changeLocaleButton.getFont());
        if (changeLocaleButtonFont != null) changeLocaleButton.setFont(changeLocaleButtonFont);
        changeLocaleButton.setForeground(new Color(-1));
        changeLocaleButton.setText("Change Locale");
        adminSelectFunctionPanel.add(changeLocaleButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        addAdminButton = new JButton();
        addAdminButton.setBackground(new Color(-10592674));
        Font addAdminButtonFont = this.$$$getFont$$$(null, -1, 20, addAdminButton.getFont());
        if (addAdminButtonFont != null) addAdminButton.setFont(addAdminButtonFont);
        addAdminButton.setForeground(new Color(-1));
        addAdminButton.setText("Add Admin");
        adminSelectFunctionPanel.add(addAdminButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        atmStateLabel = new JLabel();
        atmStateLabel.setBackground(new Color(-15024996));
        Font atmStateLabelFont = this.$$$getFont$$$(null, -1, 24, atmStateLabel.getFont());
        if (atmStateLabelFont != null) atmStateLabel.setFont(atmStateLabelFont);
        atmStateLabel.setForeground(new Color(-1));
        atmStateLabel.setHorizontalAlignment(0);
        atmStateLabel.setHorizontalTextPosition(0);
        atmStateLabel.setOpaque(true);
        atmStateLabel.setText("");
        adminSelectFunctionPanel.add(atmStateLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        removeAdminButton = new JButton();
        removeAdminButton.setBackground(new Color(-10592674));
        Font removeAdminButtonFont = this.$$$getFont$$$(null, -1, 20, removeAdminButton.getFont());
        if (removeAdminButtonFont != null) removeAdminButton.setFont(removeAdminButtonFont);
        removeAdminButton.setForeground(new Color(-1));
        removeAdminButton.setText("Remove Admin");
        adminSelectFunctionPanel.add(removeAdminButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        queryATMBalanceButton = new JButton();
        queryATMBalanceButton.setBackground(new Color(-10592674));
        Font queryATMBalanceButtonFont = this.$$$getFont$$$(null, -1, 20, queryATMBalanceButton.getFont());
        if (queryATMBalanceButtonFont != null) queryATMBalanceButton.setFont(queryATMBalanceButtonFont);
        queryATMBalanceButton.setForeground(new Color(-1));
        queryATMBalanceButton.setText("Query ATM Balance");
        adminSelectFunctionPanel.add(queryATMBalanceButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        toggleStateButton = new JButton();
        toggleStateButton.setBackground(new Color(-10592674));
        Font toggleStateButtonFont = this.$$$getFont$$$(null, -1, 20, toggleStateButton.getFont());
        if (toggleStateButtonFont != null) toggleStateButton.setFont(toggleStateButtonFont);
        toggleStateButton.setForeground(new Color(-1));
        toggleStateButton.setText("Toggle State");
        adminSelectFunctionPanel.add(toggleStateButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
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
        return adminSelectFunctionPanel;
    }
}
