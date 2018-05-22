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

            }
        });
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        queryATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                system.getState().toggleSystem();
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return this.adminSelectFunctionPanel;
    }
}
