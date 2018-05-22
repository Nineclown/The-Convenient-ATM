package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.exceptions.AccountDoesNotExist;
import com.swad.cppatm.exceptions.DataStoreError;

import javax.swing.*;
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
    private ButtonGroup bankgroup;

    public RequestCardOrBankbook(final JFrame parentFrame, final ATMSystem system) {
        bankgroup = new ButtonGroup();
        bankgroup.add(buttonHana);
        bankgroup.add(buttonKookmin);
        bankgroup.add(buttonWoori);

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Bank bank;
                if(buttonHana.isSelected()){
                    bank = Bank.HANA;
                }else if(buttonWoori.isSelected()){
                    bank = Bank.WOORI;
                }else if(buttonKookmin.isSelected()){
                    bank = Bank.KOOKMIN;
                }else{
                    JOptionPane.showMessageDialog(parentFrame, "BankName is Invaild.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ( bankbookNumberField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(parentFrame, "No number at all." , "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String accountNo = bankbookNumberField.getText();

                try{
                    system.enterAccountInfo(bank, accountNo);
                }catch(DataStoreError ex){
                    JOptionPane.showMessageDialog(parentFrame, "Load Error", "Error", JOptionPane.ERROR_MESSAGE);
                }catch(AccountDoesNotExist ex){
                    JOptionPane.showMessageDialog(parentFrame, "Can't Find Account", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel(){
        return this.requestCardOrBankbookPanel;
    }
}
