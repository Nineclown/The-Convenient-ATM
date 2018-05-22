package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;

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
        oneIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        oneDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        twoIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        twoDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        fiveIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        fiveDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        tenIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        tenDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        tenIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        tenDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        twentyIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        twentyDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        fiftyIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        fiftyDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        hundredIncrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        hundredDecrease.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
                if ( system.getCurrentAdmin() != null ) {
                    parentFrame.setContentPane(new AdminSelectFunction(parentFrame, system).getPanel());
                    parentFrame.invalidate();
                    parentFrame.validate();
                }
            }
        });
    }

    public JPanel getPanel() {
        return this.enterBillPanel;
    }
}
