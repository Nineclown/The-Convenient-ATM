package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.UserDoestNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportLostCard extends JFrame {
    private JLabel cardNumberLabel1;
    private JCheckBox reportLostCheckBox1;
    private JTextField cardNumberField2;
    private JCheckBox reportLostCheckBox2;
    private JCheckBox reportLostCheckBox3;
    private JCheckBox reportLostCheckBox4;
    private JTextField cardNumberField3;
    private JTextField cardNumberField1;
    private JTextField cardNumberField4;
    private JTextField[] cardFields;
    private JCheckBox[] checkBox;
    private JLabel[] cardLabels;
    private JPanel reportLostCardPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private ButtonGroup buttongroup;
    private JLabel titleLabel;
    private JLabel cardNumberLabel2;
    private JLabel cardNumberLabel3;
    private JLabel cardNumberLabel4;

    private String setLocalizedString(ATMSystem system, String ko, String en) {
        return system.getState().getLocale() == Locale.en_US ? en : ko;
    }

    public ReportLostCard(final JFrame parentFrame, final ATMSystem system) {
        cardFields = new JTextField[4];
        cardLabels = new JLabel[4];
        checkBox = new JCheckBox[4];
        buttongroup = new ButtonGroup();


        cardFields[0] = cardNumberField1;
        cardFields[1] = cardNumberField2;
        cardFields[2] = cardNumberField3;
        cardFields[3] = cardNumberField4;

        checkBox[0] = reportLostCheckBox1;
        checkBox[1] = reportLostCheckBox2;
        checkBox[2] = reportLostCheckBox3;
        checkBox[3] = reportLostCheckBox4;

        for (int i = 0; i < checkBox.length; i++) {
            buttongroup.add(checkBox[i]);
        }

        cardLabels[0] = cardNumberLabel1;
        cardLabels[1] = cardNumberLabel2;
        cardLabels[2] = cardNumberLabel3;
        cardLabels[3] = cardNumberLabel4;

        String[] card = system.getUser().getCardList();
        int length = card.length;

        titleLabel.setText(setLocalizedString(system, "분실처리를 할 카드를 선택하여 주십시오", "Please select the card that you've lost"));

        for (int i = 0; i < length; i++) {
            cardLabels[i].setText(setLocalizedString(system, "카드번호", "Card"));
            if (!card[i].equals("")) {
                cardFields[i].setText(card[i].toString());
            }
        }

        confirmButton.setText(setLocalizedString(system, "확인", "Confirm"));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < checkBox.length; i++) {
                    if (!cardFields[i].getText().equals("") && checkBox[i].isSelected()) {
                        try {
                            system.selectCard(card[i]);
                            JOptionPane.showMessageDialog(parentFrame, card[i].toString() + " 카드를 중지처리하였습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
                            system.removeCard(card[i]);
                            system.saveUser();
                        } catch (DataStoreError ex) {
                        }
                        ;
                        int answer = JOptionPane.showConfirmDialog(parentFrame.getContentPane(), "재발급을 신청하시겠습니까?", "카드 재발급", JOptionPane.YES_NO_OPTION);
                        switch (answer) {
                            case JOptionPane.YES_OPTION:
                                try {
                                    system.askRenewCard(true);
                                } catch (DataStoreError ex) {
                                }
                                ;
                                JOptionPane.showMessageDialog(parentFrame, card[i].toString() + " 카드를 재발급 처리하였습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
                            case JOptionPane.NO_OPTION:
                                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                                parentFrame.invalidate();
                                parentFrame.validate();
                                break;
                        }
                    }
                }


            }
        });
        cancelButton.setText(setLocalizedString(system, "취소", "Cancel"));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Transaction is cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }


    public JPanel getPanel() {
        return this.reportLostCardPanel;
    }
}
