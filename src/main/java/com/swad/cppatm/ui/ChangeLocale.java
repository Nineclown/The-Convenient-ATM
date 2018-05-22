package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Locale;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeLocale {
    private JPanel changeLocalePanel;
    private JButton koreanButton;
    private JButton englishButton;

    public ChangeLocale(final JFrame parentFrame, final ATMSystem system) {
        koreanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                system.changeLocale(Locale.ko_KR);

                if (system.getCurrentAdmin() == null) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                }

                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        englishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                system.changeLocale(Locale.en_US);
                if (system.getCurrentAdmin() == null) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                }

                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.changeLocalePanel;
    }
}
