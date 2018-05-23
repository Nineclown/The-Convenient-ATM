package com.swad.cppatm.ui;
import com.sun.org.apache.xpath.internal.operations.Neg;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.AccountDoesNotExist;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidPasswordException;
import com.swad.cppatm.exceptions.NegativeBalanceError;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterPassword extends JFrame {
    private JPanel enterPasswordPanel;
    private JPasswordField passwordField;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton BSButton;
    private JButton a0Button;
    private JButton clearButton;
    private JButton confirmButton;

    public EnterPassword(final JFrame parentFrame, final ATMSystem system) {
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "3");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "6");
            }
        });
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "9");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() >= 4 ) {
                    return;
                }
                passwordField.setText(value + "0");
            }
        });
        BSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String value = new String(passwordField.getPassword());

                if ( value.length() == 0 ) {
                    return;
                }

                passwordField.setText(value.substring(0, value.length() - 1));
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ( passwordField.getPassword().length == 0 ) {
                    return;
                }

                passwordField.setText("");
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ( passwordField.getPassword().length != 4 ) {
                    JOptionPane.showMessageDialog(parentFrame, "Password is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ( !system.getAccount().isAccountEnabled() ) {
                    JOptionPane.showMessageDialog(parentFrame, "Account is frozen", "Error", JOptionPane.ERROR_MESSAGE);
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                try {
                    system.enterPassword(Integer.parseInt(new String(passwordField.getPassword())));
                } catch (AccountDoesNotExist | DataStoreError ex) {
                    JOptionPane.showMessageDialog(parentFrame, "???", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (InvalidPasswordException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Password is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (NegativeBalanceError ex){
                    JOptionPane.showMessageDialog(parentFrame, "잔액이 부족합니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                switch(system.getFunction()) {
                    case Withdraw:
                        parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                        break;
                    case ForeignWithdraw:
                        parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                        break;
                    case QueryTransactionList:
                        parentFrame.setContentPane(new QueryList(parentFrame, system).getPanel());
                        break;
                    case QueryBalance:
                        parentFrame.setContentPane(new QueryBalance(parentFrame, system).getPanel());
                        break;
                    case Transfer:
                        parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                        break;
                    case SplitPay:
                        if(system.getNumberUser() > 0){
                            parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                        }else{
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                        }
                        break;
                    default:
                        parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                }

                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterPasswordPanel;
    }
}
