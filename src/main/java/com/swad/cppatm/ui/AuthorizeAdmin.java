package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AuthorizeAdmin extends JFrame {
    private JPanel authorizeAdminPanel;
    private JButton confirmButton;
    private JTextField adminIdField;
    private JPasswordField adminPwField;
    private JLabel atmStateLabel;
    private JLabel adminIdLabel;
    private JLabel adminPwLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if (system.getState().getLocale() == Locale.en_US) {
            return en;
        } else {
            return ko;
        }
    }

    private void next(JFrame parentFrame, ATMSystem system) {
        String id = adminIdField.getText();
        String password = new String(adminPwField.getPassword());

        if (Arrays.asList(FunctionType.getUserFunctions()).contains(system.getFunction())) {
            JOptionPane.showMessageDialog(parentFrame, setLocalizedString(system,
                "사용자 기능과 관리자 기능은 동시에 실행할 수 없습니다.", "User and admin can't access atm simultaneously."), "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            system.authorizeAdmin(id, password);
        } catch (InvalidAdminException exception) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "아이디 또는 비밀번호가 틀립니다.", "Invalid ID or Password."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
        parentFrame.invalidate();
        parentFrame.validate();
    }

    public AuthorizeAdmin(final JFrame parentFrame, final ATMSystem system) {
        system.removeFunctionSelection();

        atmStateLabel.setText(system.getState().available() ? "Active" : "Frozen");

        adminIdLabel.setText(setLocalizedString(system, "아이디", "ID"));
        adminPwLabel.setText(setLocalizedString(system, "비밀번호", "Password"));

        confirmButton.setText(setLocalizedString(system, "확인", "Go"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        adminPwField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        next(parentFrame, system);
                }
            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
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
        authorizeAdminPanel = new JPanel();
        authorizeAdminPanel.setLayout(new GridLayoutManager(4, 2, new Insets(30, 30, 30, 30), -1, -1));
        authorizeAdminPanel.setBackground(new Color(-1));
        authorizeAdminPanel.setMinimumSize(new Dimension(800, 600));
        authorizeAdminPanel.setPreferredSize(new Dimension(800, 600));
        adminPwField = new JPasswordField();
        Font adminPwFieldFont = this.$$$getFont$$$("Malgun Gothic", -1, 48, adminPwField.getFont());
        if (adminPwFieldFont != null) adminPwField.setFont(adminPwFieldFont);
        authorizeAdminPanel.add(adminPwField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(450, -1), null, 0, false));
        adminIdLabel = new JLabel();
        Font adminIdLabelFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, adminIdLabel.getFont());
        if (adminIdLabelFont != null) adminIdLabel.setFont(adminIdLabelFont);
        adminIdLabel.setForeground(new Color(-16777216));
        adminIdLabel.setText("아이디");
        authorizeAdminPanel.add(adminIdLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adminPwLabel = new JLabel();
        Font adminPwLabelFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, adminPwLabel.getFont());
        if (adminPwLabelFont != null) adminPwLabel.setFont(adminPwLabelFont);
        adminPwLabel.setForeground(new Color(-16777216));
        adminPwLabel.setText("비밀번호");
        authorizeAdminPanel.add(adminPwLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        atmStateLabel = new JLabel();
        atmStateLabel.setBackground(new Color(-15024996));
        Font atmStateLabelFont = this.$$$getFont$$$(null, -1, 24, atmStateLabel.getFont());
        if (atmStateLabelFont != null) atmStateLabel.setFont(atmStateLabelFont);
        atmStateLabel.setForeground(new Color(-1));
        atmStateLabel.setHorizontalAlignment(0);
        atmStateLabel.setHorizontalTextPosition(0);
        atmStateLabel.setOpaque(true);
        atmStateLabel.setText("Active");
        authorizeAdminPanel.add(atmStateLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, 1, 1, null, new Dimension(100, -1), null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        authorizeAdminPanel.add(confirmButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, 1, 1, null, new Dimension(120, 60), null, 0, false));
        adminIdField = new JTextField();
        Font adminIdFieldFont = this.$$$getFont$$$("Malgun Gothic", -1, 48, adminIdField.getFont());
        if (adminIdFieldFont != null) adminIdField.setFont(adminIdFieldFont);
        authorizeAdminPanel.add(adminIdField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(450, -1), null, 0, false));
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
        return authorizeAdminPanel;
    }
}
