package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
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

    public String setLocalizedString(ATMSystem system, String ko, String en) {
        if ( system.getState().getLocale() == Locale.en_US ) {
            return en;
        } else {
            return ko;
        }
    }

    public AdminSelectFunction(final JFrame parentFrame, final ATMSystem system) {
        atmStateLabel.setText(system.getState().available() ? "Active" : "Frozen");

        addAdminButton.setText(setLocalizedString(system, "관리자 추가", "Add Admin"));
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

        removeAdminButton.setText(setLocalizedString(system, "관리자 삭제", "Remove Admin"));
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.RemoveAdmin);
                } catch (NoneOfFunctionSelected ex) {
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
                } catch (NoneOfFunctionSelected ex) {
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

        changeATMBalanceButton.setText(setLocalizedString(system, "지폐 보유량 변경", "Change ATM Balance"));
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    system.selectFunction(FunctionType.ChangeATMBalance);
                } catch (NoneOfFunctionSelected ex) {
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
                } catch (NoneOfFunctionSelected ex) {
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
