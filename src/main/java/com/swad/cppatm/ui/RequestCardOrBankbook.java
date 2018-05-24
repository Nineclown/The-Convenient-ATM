package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.AccountDoesNotExist;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.FrozenAccountException;
import com.swad.cppatm.exceptions.NoneOfFunctionSelected;

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
    private JLabel titleLabel;
    private JLabel bankLabel;
    private JLabel numberLabel;
    private ButtonGroup bankgroup;


    public String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

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
        } catch (NoneOfFunctionSelected ex) {
        } catch (DataStoreError ex) {
            JOptionPane.showMessageDialog(parentFrame, "Load Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (AccountDoesNotExist ex) {
            JOptionPane.showMessageDialog(parentFrame, "Can't Find Account", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (FrozenAccountException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Account is frozen", "Error", JOptionPane.ERROR_MESSAGE);
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
                if( system.getFromTransaction().getAccount() != null){
                    parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                }else{
                    parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                }

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
            case GetLotteryPrize:
                parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
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
        titleLabel.setText(setLocalizedString(system, "카드 또는 통장을 넣어주십시오.", "Please insert card or bankbook."));
        bankLabel.setText(setLocalizedString(system, "은행", "Bank"));
        numberLabel.setText(setLocalizedString(system, "통장번호", "Account Number"));

        buttonHana.setText(setLocalizedString(system, "하나은행", "Hana Bank"));
        buttonWoori.setText(setLocalizedString(system, "우리은행", "Woori Bank"));
        buttonKookmin.setText(setLocalizedString(system, "국민은행", "Kookmin Bank"));

        if ( system.getFunction() == FunctionType.Transfer && system.getFromTransaction().getAccount() != null ) {
            this.titleLabel.setText(setLocalizedString(system, "상대방의 계좌 번호를 입력하여 주십시오.", "Please enter account number."));
        }

        bankgroup = new ButtonGroup();
        bankgroup.add(buttonHana);
        bankgroup.add(buttonKookmin);
        bankgroup.add(buttonWoori);

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                next(parentFrame, system);
            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
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
