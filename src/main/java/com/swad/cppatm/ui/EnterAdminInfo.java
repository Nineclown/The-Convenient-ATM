package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Admin;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidAdminException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterAdminInfo extends JFrame {
    private JPanel enterAdminInfoPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPasswordField adminPwField;
    private JTextField adminContactField;
    private JLabel titleLabel;

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if (system.getState().getLocale() == Locale.en_US) {
            return en;
        } else {
            return ko;
        }
    }

    public void next(JFrame parentFrame, ATMSystem system) {
        String password = new String(adminPwField.getPassword());
        String contact = adminContactField.getText();

        if (!password.matches("^(\\d{4})$")) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "잘못된 비밀번호 형식입니다.", "Invalid Password format."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contact.matches("^(0\\d{8,10})$")) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "잘못된 전화번호 형식입니다.", "Invalid Contact format."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            system.enterAdminInfo(password, contact);
        } catch (DataStoreError er) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "관리자를 추가할 수 없습니다.", "Failed to add admin"),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Admin newAdmin = system.getAdmins()[system.getAdmins().length - 1];
        JOptionPane.showMessageDialog(parentFrame,
            setLocalizedString(system, "관리자가 추가되었습니다\n새로운 관리자 ID : " + newAdmin.getId(), "Admin added\nYour AdminId : " + newAdmin.getId()),
            "Info",
            JOptionPane.INFORMATION_MESSAGE);

        parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
        parentFrame.invalidate();
        parentFrame.validate();
    }

    public EnterAdminInfo(final JFrame parentFrame, final ATMSystem system) {
        titleLabel.setText(setLocalizedString(system, "새로운 사용자 추가", "Add New Admin"));

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        adminContactField.addKeyListener(new KeyAdapter() {
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
        return this.enterAdminInfoPanel;
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
        enterAdminInfoPanel = new JPanel();
        enterAdminInfoPanel.setLayout(new GridLayoutManager(4, 2, new Insets(30, 30, 30, 30), -1, -1));
        enterAdminInfoPanel.setBackground(new Color(-1));
        enterAdminInfoPanel.setMinimumSize(new Dimension(800, 600));
        enterAdminInfoPanel.setPreferredSize(new Dimension(800, 600));
        adminContactField = new JTextField();
        Font adminContactFieldFont = this.$$$getFont$$$("Malgun Gothic", -1, 48, adminContactField.getFont());
        if (adminContactFieldFont != null) adminContactField.setFont(adminContactFieldFont);
        enterAdminInfoPanel.add(adminContactField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(450, -1), null, 0, false));
        adminPwField = new JPasswordField();
        Font adminPwFieldFont = this.$$$getFont$$$("Malgun Gothic", -1, 48, adminPwField.getFont());
        if (adminPwFieldFont != null) adminPwField.setFont(adminPwFieldFont);
        enterAdminInfoPanel.add(adminPwField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(450, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Malgun Gothic", -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("비밀번호");
        enterAdminInfoPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Malgun Gothic", -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("연락처");
        enterAdminInfoPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        enterAdminInfoPanel.add(panel1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("확인");
        panel1.add(confirmButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(120, 60), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-10592674));
        Font cancelButtonFont = this.$$$getFont$$$("Malgun Gothic", -1, 20, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        cancelButton.setForeground(new Color(-1));
        cancelButton.setText("취소");
        panel1.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(120, 60), null, 0, false));
        titleLabel = new JLabel();
        titleLabel.setBackground(new Color(-1));
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 24, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setHorizontalTextPosition(0);
        titleLabel.setOpaque(true);
        titleLabel.setText("새로운 사용자 추가");
        enterAdminInfoPanel.add(titleLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, 1, null, new Dimension(100, -1), null, 0, false));
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
        return enterAdminInfoPanel;
    }
}
