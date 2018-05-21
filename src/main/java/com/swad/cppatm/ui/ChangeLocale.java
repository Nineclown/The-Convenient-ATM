package com.swad.cppatm.ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeLocale {
    private JPanel changeLocalePanel;
    private JButton koreanButton;
    private JButton englishButton;

    public ChangeLocale() {
        koreanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        englishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public JPanel getPanel() {
        return this.changeLocalePanel;
    }
}
