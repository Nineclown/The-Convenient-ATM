package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.AccountDoesNotExist;
import com.swad.cppatm.exceptions.DataStoreError;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestCardOrBankbook {
    private JPanel requestCardOrBankbookPanel;
    private JTextField bankbookNumberField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JRadioButton buttonHana;
    private JRadioButton buttonKookmin;
    private JRadioButton buttonWoori;
    private JLabel titieLabel;
    private ButtonGroup bankgroup;

    public void next(JFrame parentFrame, ATMSystem system) {
        Bank bank;
        if (buttonHana.isSelected()) {
            bank = Bank.HANA;
        } else if (buttonWoori.isSelected()) {
            bank = Bank.WOORI;
        } else if (buttonKookmin.isSelected()) {
            bank = Bank.KOOKMIN;
        } else {
            JOptionPane.showMessageDialog(parentFrame, "BankName is Invaild.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (bankbookNumberField.getText().length() == 0) {
            JOptionPane.showMessageDialog(parentFrame, "No number at all.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String accountNo = bankbookNumberField.getText();

        try {
            system.enterAccountInfo(bank, accountNo);
        } catch (DataStoreError ex) {
            JOptionPane.showMessageDialog(parentFrame, "Load Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (AccountDoesNotExist ex) {
            JOptionPane.showMessageDialog(parentFrame, "Can't Find Account", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch(system.getFunction()) {
            case Deposit:
                parentFrame.setContentPane(new EnterBill(parentFrame, system).getPanel());
                break;
            case ForeignDeposit:
                parentFrame.setContentPane(new EnterBillAsDollar(parentFrame, system).getPanel());
                break;
            case SplitPay:
                parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                break;
            case Withdraw:
            case ForeignWithdraw:
            case QueryBalance:
            case QueryTransactionList:
                parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                break;
            case Transfer:
                if ( system.getToTransaction().getAccount() != null ) {
                    parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                }
                break;
            default:
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                JOptionPane.showMessageDialog(parentFrame, "unknown error", "Error", JOptionPane.ERROR_MESSAGE);
        }

        parentFrame.pack();
        parentFrame.invalidate();
        parentFrame.validate();
    }

    public RequestCardOrBankbook(final JFrame parentFrame, final ATMSystem system) {
        if ( system.getFunction() == FunctionType.Transfer && system.getFromTransaction().getAccount() != null ) {
            this.titieLabel.setText("상대방의 계좌 번호를 입력하여 주십시오.");
        }
        bankgroup = new ButtonGroup();
        bankgroup.add(buttonHana);
        bankgroup.add(buttonKookmin);
        bankgroup.add(buttonWoori);

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        bankbookNumberField.addKeyListener(new KeyAdapter() {
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
        return this.requestCardOrBankbookPanel;
    }
}
