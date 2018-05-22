package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;

import javax.swing.*;
import java.awt.event.*;

public class EnterBillAsDollar {
    private JPanel enterBillPanel;
    private JTextField dollarOneField;
    private JButton oneIncrease;
    private JButton twoDecrease;
    private JTextField dollarTwoField;
    private JButton fiveIncrease;
    private JTextField dollarFiveField;
    private JButton tenIncrease;
    private JTextField dollarTenField;
    private JButton oneDecrease;
    private JButton twoIncrease;
    private JButton fiveDecrease;
    private JButton tenDecrease;
    private JButton discardButton;
    private JButton confirmButton;
    private JTextField dollarTwentyField;
    private JTextField dollarFiftyField;
    private JTextField dollarHundredField;
    private JButton twentyIncrease;
    private JButton twentyDecrease;
    private JButton fiftyIncrease;
    private JButton fiftyDecrease;
    private JButton hundredIncrease;
    private JButton hundredDecrease;

    public EnterBillAsDollar(final JFrame parentFrame, final ATMSystem system) {
        dollarOneField.setText("0");
        dollarTwoField.setText("0");
        dollarFiveField.setText("0");
        dollarTenField.setText("0");
        dollarTwentyField.setText("0");
        dollarFiftyField.setText("0");
        dollarHundredField.setText("0");
        oneIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarOneField.getText());
                dollarOneField.setText(Integer.toString(value + 1));
            }
        });
        oneDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarOneField.getText());
                if ( value == 0 ) { return; }
                dollarOneField.setText(Integer.toString(value - 1));
            }
        });
        twoIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTwoField.getText());
                dollarTwoField.setText(Integer.toString(value + 1));
            }
        });
        twoDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTwoField.getText());
                if ( value == 0 ) { return; }
                dollarTwoField.setText(Integer.toString(value - 1));
            }
        });
        fiveIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarFiveField.getText());
                dollarFiveField.setText(Integer.toString(value + 1));
            }
        });
        fiveDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarFiveField.getText());
                if ( value == 0 ) { return; }
                dollarFiveField.setText(Integer.toString(value - 1));
            }
        });
        tenIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTenField.getText());
                dollarTenField.setText(Integer.toString(value + 1));
            }
        });
        tenDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTenField.getText());
                if ( value == 0 ) { return; }
                dollarTenField.setText(Integer.toString(value - 1));
            }
        });
        twentyIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTwentyField.getText());
                dollarTwentyField.setText(Integer.toString(value + 1));
            }
        });
        twentyDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarTwentyField.getText());
                if ( value == 0 ) { return; }
                dollarTwentyField.setText(Integer.toString(value - 1));
            }
        });
        fiftyIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarFiftyField.getText());
                dollarFiftyField.setText(Integer.toString(value + 1));
            }
        });
        fiftyDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarFiftyField.getText());
                if ( value == 0 ) { return; }
                dollarFiftyField.setText(Integer.toString(value - 1));
            }
        });
        hundredIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarHundredField.getText());
                dollarHundredField.setText(Integer.toString(value + 1));
            }
        });
        hundredDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int value = Integer.parseInt(dollarHundredField.getText());
                if ( value == 0 ) { return; }
                dollarHundredField.setText(Integer.toString(value - 1));
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        discardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ( system.getFunction() == FunctionType.ChangeATMBalance) {
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                }

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
