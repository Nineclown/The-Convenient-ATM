package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryATMBalance {
    private JPanel queryATMBalancePanel;
    private JButton confirmButton;
    private JLabel thousand;
    private JLabel fiveThousand;
    private JLabel tenThousand;
    private JLabel fiftyThousand;
    private JLabel dollarOne;
    private JLabel dollarTwo;
    private JLabel dollarFive;
    private JLabel dollarTen;
    private JLabel dollarTwenty;
    private JLabel dollarFifty;
    private JLabel dollarHundred;

    public QueryATMBalance(final JFrame parentFrame, final ATMSystem system) {

        for ( int i = 0 ; i < system.getBalance().getATMBalance().length ; i++ ) {
            System.out.println(system.getBalance().getATMBalance()[i]);
        }

        thousand.setText(Integer.toString(system.getBalance().getATMBalance()[0]));
        fiveThousand.setText(Integer.toString(system.getBalance().getATMBalance()[1]));
        tenThousand.setText(Integer.toString(system.getBalance().getATMBalance()[2]));
        fiftyThousand.setText(Integer.toString(system.getBalance().getATMBalance()[3]));
        dollarOne.setText(Integer.toString(system.getBalance().getATMBalance()[4]));
        dollarTwo.setText(Integer.toString(system.getBalance().getATMBalance()[5]));
        dollarFive.setText(Integer.toString(system.getBalance().getATMBalance()[6]));
        dollarTen.setText(Integer.toString(system.getBalance().getATMBalance()[7]));
        dollarTwenty.setText(Integer.toString(system.getBalance().getATMBalance()[8]));
        dollarFifty.setText(Integer.toString(system.getBalance().getATMBalance()[9]));
        dollarHundred.setText(Integer.toString(system.getBalance().getATMBalance()[10]));

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.queryATMBalancePanel;
    }
}
