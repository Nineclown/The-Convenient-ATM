package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.exceptions.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterNumber extends JFrame {
    private JPanel enterNumberPanel;
    private JTextField numberField;
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
    private JLabel titleLabel;
    private JLabel currencyLabel;
    private boolean splitPayFlag = false;

    public EnterNumber(final JFrame parentFrame, final ATMSystem system) {
        if ( system.getFunction() == FunctionType.ForeignWithdraw ) {
            currencyLabel.setText("달러");
        }
        if ( system.getFunction() == FunctionType.SplitPay ) {
            titleLabel.setText("총 받아야 할 금액을 입력하여 주십시오.");
        }

        if ( system.getFunction() == FunctionType.Transfer ) {
            titleLabel.setText("보내실 금액을 입력하여 주십시오.");
        }
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "3");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "6");
            }
        });
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "9");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                numberField.setText(numberField.getText() + "0");
            }
        });
        BSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (numberField.getText().length() == 0) {
                    return;
                }

                numberField.setText(numberField.getText().substring(0, numberField.getText().length() - 1));
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (numberField.getText().length() == 0) {
                    return;
                }

                numberField.setText("");
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int insertNumber = Integer.parseInt(numberField.getText());

                switch (system.getFunction()) {
                    case Withdraw:
                        if(insertNumber > 100){
                            JOptionPane.showMessageDialog(parentFrame, "출금하려는 금액이 너무 큽니다. 다시 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            system.enterBillAmountToWithdraw(-insertNumber * 10000);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        } catch (OverflowBillException ex){
                            JOptionPane.showMessageDialog(parentFrame, "해당 거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Error", JOptionPane.ERROR_MESSAGE);

                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case ForeignWithdraw:
                        if(insertNumber > 5000){
                            JOptionPane.showMessageDialog(parentFrame, "출금하려는 금액이 너무 큽니다. 다시 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            system.enterBillAmountToWithdrawAsDollar(-insertNumber);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }catch (OverflowBillException ex){
                            JOptionPane.showMessageDialog(parentFrame, "해당 거래를 진행할 수 없습니다. 관리자에게 연락해주세요.", "Error", JOptionPane.ERROR_MESSAGE);

                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case Transfer:
                        try {
                            system.enterCashAmountToTransfer(insertNumber*10000);
                        } catch (DataStoreError | NegativeBalanceError ex) {
                            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
                            parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                            parentFrame.pack();
                            parentFrame.invalidate();
                            parentFrame.validate();
                            return;
                        }

                        parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                        break;
                    case SplitPay:
                        if(splitPayFlag){
                            try {
                                if(insertNumber > 10){
                                    JOptionPane.showMessageDialog(parentFrame, "사람 수가 너무 많습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                system.enterNumberOfUsers(insertNumber);
                                parentFrame.setContentPane(new RequestCardOrBankbook(parentFrame, system).getPanel());
                                parentFrame.pack();
                                parentFrame.invalidate();
                                parentFrame.validate();
                            }catch(TooFewUser ex1){
                                JOptionPane.showMessageDialog(parentFrame, "사람 수가 너무 적습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            system.enterTotalCashAmountToGet(insertNumber * 10000);
                            titleLabel.setText("총 이체 할 인원 수를 입력하여 주십시오.");
                            currencyLabel.setText("명");
                            numberField.setText("");
                            splitPayFlag = true;
                        }
                        break;
                    default:
                        parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                        break;
                }

                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterNumberPanel;
    }
}
