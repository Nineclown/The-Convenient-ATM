package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminSelectFunction extends JFrame {
    private JFrame parent;
    private JButton addAdminButton;
    private JButton changeATMBalanceButton;
    private JButton toggleStateButton;
    private JButton removeAdminButton;
    private JPanel adminSelectFunctionPanel;
    private JButton queryATMBalanceButton;
    private JButton changeLocaleButton;
    private JLabel atmStateLabel;

    public AdminSelectFunction(final JFrame parentFrame, final ATMSystem system) {
        addAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.AddAdmin);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.RemoveAdmin);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        queryATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.QueryATMBalance);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ToggleATMState);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeATMBalance);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeLocale);
                } catch (NoneOfFunctionSelected exception) {
                    return;
                }
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.adminSelectFunctionPanel;
    }
}
