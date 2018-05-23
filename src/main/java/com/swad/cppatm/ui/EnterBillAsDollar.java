package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.InvalidBillException;
import com.swad.cppatm.exceptions.OverflowBillException;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class EnterBillAsDollar {
    private JPanel enterBillPanel;
    private JTextField dollarOneField;
    private JTextField dollarTwoField;
    private JTextField dollarFiveField;
    private JTextField dollarTenField;
    private JButton discardButton;
    private JButton confirmButton;
    private JTextField dollarTwentyField;
    private JTextField dollarFiftyField;
    private JTextField dollarHundredField;

    public EnterBillAsDollar(final JFrame parentFrame, final ATMSystem system) {
        dollarOneField.setText("0");
        dollarTwoField.setText("0");
        dollarFiveField.setText("0");
        dollarTenField.setText("0");
        dollarTwentyField.setText("0");
        dollarFiftyField.setText("0");
        dollarHundredField.setText("0");

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int[] values = new int[11];

                values[4] = Integer.parseInt(dollarOneField.getText());
                values[5] = Integer.parseInt(dollarTwoField.getText());
                values[6] = Integer.parseInt(dollarFiveField.getText());
                values[7] = Integer.parseInt(dollarTenField.getText());
                values[8] = Integer.parseInt(dollarTwentyField.getText());
                values[9] = Integer.parseInt(dollarFiftyField.getText());
                values[10] = Integer.parseInt(dollarHundredField.getText());

                try {
                    system.enterBillAsDollar(values);
                    } catch (DataStoreError | InvalidBillException ex) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }catch (OverflowBillException e2){
                    //
                }

                parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        discardButton.addMouseListener(new MouseAdapter() {
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
        return this.enterBillPanel;
    }
}
