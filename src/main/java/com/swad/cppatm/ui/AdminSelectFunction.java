package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminSelectFunction extends JFrame {
    private JButton addAdminButton;
    private JButton changeATMBalanceButton;
    private JButton toggleStateButton;
    private JButton removeAdminButton;
    private JPanel adminSelectFunctionPanel;
    private JButton queryATMBalanceButton;
    private JButton changeLocaleButton;
    private JLabel atmStateLabel;

    public AdminSelectFunction(final JFrame parentFrame, final ATMSystem system) {
        atmStateLabel.setText(system.getState().available() ? "Active": "Frozen");

        addAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.AddAdmin).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.RemoveAdmin).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        queryATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.QueryATMBalance).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.ToggleATMState).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.ChangeATMBalance).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system, FunctionType.ChangeLocale).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.adminSelectFunctionPanel;
    }
}
