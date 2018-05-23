package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.Transaction;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

public class QueryList extends JFrame {
    private JTextField accountBalanceField;
    private JTextField accountNumberField;
    private JButton confirmButton;
    private JPanel queryListPanel;
    private JList transactionList;

    /*
     // Create a JList that displays strings from an array

 String[] data = {"one", "two", "three", "four"};
 JList<String> myList = new JList<String>(data);

 // Create a JList that displays the superclasses of JList.class, by
 // creating it with a Vector populated with this data

     */

    public QueryList(final JFrame parentFrame, final ATMSystem system) {
        DefaultListModel transactionListModel = new DefaultListModel();
        transactionList.setModel(transactionListModel);
        Transaction[] transactions = system.getTransactionList();

        accountNumberField.setText(system.getAccount().getAccountNo());
        accountBalanceField.setText(Integer.toString(system.getAccount().getBalance()));

        for ( int i = 0 ; i < transactions.length ; i++ ) {
            transactionListModel.add(i, MessageFormat.format("날짜 : {0}, 금액 : {1}",
                transactions[i].getTime(),
                transactions[i].getAmount()));
        }

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.queryListPanel;
    }
}
