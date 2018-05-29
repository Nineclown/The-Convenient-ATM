package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.Main;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeLocale {
    private JPanel changeLocalePanel;
    private JButton koreanButton;
    private JButton englishButton;

    ChangeLocale(final JFrame parentFrame, final ATMSystem system) {
        Main application = Main.getInstance();

        System.out.println("ChangeLocale() : applicaton is " + application);
        System.out.println("ChangeLocale(): userFrame is " + System.identityHashCode(application.userFrame));
        System.out.println("ChangeLocale(): adminFrame is " + System.identityHashCode(application.adminFrame));

        koreanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                system.changeLocale(Locale.ko_KR);

                application.initializeScreen();
            }
        });
        englishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                system.changeLocale(Locale.en_US);

                application.initializeScreen();
            }
        });
    }

    public JPanel getPanel() {
        return this.changeLocalePanel;
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
        changeLocalePanel = new JPanel();
        changeLocalePanel.setLayout(new GridLayoutManager(2, 2, new Insets(50, 50, 70, 30), 100, 30, true, false));
        changeLocalePanel.setBackground(new Color(-1));
        changeLocalePanel.setMinimumSize(new Dimension(800, 600));
        changeLocalePanel.setPreferredSize(new Dimension(800, 600));
        final JLabel label1 = new JLabel();
        label1.setAutoscrolls(false);
        Font label1Font = this.$$$getFont$$$(null, -1, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-11645362));
        label1.setOpaque(false);
        label1.setText("사용할 언어를 선택하여 주십시오.");
        changeLocalePanel.add(label1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        koreanButton = new JButton();
        koreanButton.setBackground(new Color(-2039584));
        koreanButton.setBorderPainted(false);
        koreanButton.setDefaultCapable(true);
        koreanButton.setDoubleBuffered(false);
        Font koreanButtonFont = this.$$$getFont$$$(null, -1, 20, koreanButton.getFont());
        if (koreanButtonFont != null) koreanButton.setFont(koreanButtonFont);
        koreanButton.setForeground(new Color(-11645362));
        koreanButton.setHideActionText(false);
        koreanButton.setText("한국어");
        changeLocalePanel.add(koreanButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        englishButton = new JButton();
        englishButton.setBackground(new Color(-2039584));
        englishButton.setBorderPainted(false);
        englishButton.setDefaultCapable(true);
        englishButton.setDoubleBuffered(false);
        Font englishButtonFont = this.$$$getFont$$$(null, -1, 20, englishButton.getFont());
        if (englishButtonFont != null) englishButton.setFont(englishButtonFont);
        englishButton.setForeground(new Color(-11645362));
        englishButton.setHideActionText(false);
        englishButton.setText("English");
        changeLocalePanel.add(englishButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return changeLocalePanel;
    }
}
