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
        atmStateLabel.setText(system.getState().available() ? "Active" : "Frozen");

        addAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.AddAdmin);
                } catch (NoneOfFunctionSelected ex) {
                    return;
                }

                parentFrame.setContentPane(new EnterAdminInfo(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.RemoveAdmin);
                } catch (NoneOfFunctionSelected ex) {
                    return;
                }

                JOptionPane.showMessageDialog(parentFrame, "Admin deleted", "Info", JOptionPane.INFORMATION_MESSAGE);

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
                } catch (NoneOfFunctionSelected ex) {
                    return;
                }
                parentFrame.setContentPane(new QueryATMBalance(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String state;

                try {
                    system.selectFunction(FunctionType.ToggleATMState);
                } catch (NoneOfFunctionSelected ex) {
                    return;
                }

                state = system.getState().available() ? "active" : "frozen";

                JOptionPane.showMessageDialog(parentFrame, "System is now " + state, "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeATMBalance);
                } catch (NoneOfFunctionSelected ex) {
                    return;
                }

                parentFrame.setContentPane(new EnterBill(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeLocale);
                } catch (NoneOfFunctionSelected ex) {
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
}
