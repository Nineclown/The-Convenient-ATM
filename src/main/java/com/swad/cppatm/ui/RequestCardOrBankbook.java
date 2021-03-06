package com.swad.cppatm.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.FunctionType;
import com.swad.cppatm.enums.Locale;
import com.swad.cppatm.exceptions.*;
import com.swad.cppatm.ui.components.JNumberTextField;

import javax.swing.*;
import java.awt.*;
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
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "은행 선택이 잘 못 되었습니다.", "Bank name is invalid."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!bankbookNumberField.getText().matches("^\\d{13,14}$")) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "잘못된 계좌번호 형식입니다.", "Invalid bankbook format."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String accountNo = bankbookNumberField.getText();

        try {
            system.enterAccountInfo(bank, accountNo);
        } catch (NoneOfFunctionSelected ex) {
        } catch (DataStoreError ex) {
            JOptionPane.showMessageDialog(parentFrame, ex.getClass().getSimpleName(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (AccountDoesNotExist ex) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "계좌를 찾을 수 없습니다.", "Can't Find Account."),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        } catch (FrozenAccountException ex) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "정지된 계좌입니다.", "Account is Frozen"),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        } catch (SameAccountTransferException ex) {
            JOptionPane.showMessageDialog(parentFrame,
                setLocalizedString(system, "자신에게 이체 할 수 없습니다..", "Can't transfer to yourself"),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }


        switch (system.getFunction()) {
            case DEPOSIT:
                parentFrame.setContentPane(new EnterBill(parentFrame, system).getPanel());
                break;
            case FOREIGN_DEPOSIT:
                parentFrame.setContentPane(new EnterBillAsDollar(parentFrame, system).getPanel());
                break;
            case SPLIT_PAY:
                if (system.getFromTransaction().getAccount() != null) {
                    parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                }

                break;
            case WITHDRAW:
            case FOREIGN_WITHDRAW:
            case QUERY_BALANCE:
            case QUERY_TRANSACTION_LIST:
                parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                break;
            case TRANSFER:
                if (system.getToTransaction().getAccount() != null) {
                    parentFrame.setContentPane(new EnterNumber(parentFrame, system).getPanel());
                } else {
                    parentFrame.setContentPane(new EnterPassword(parentFrame, system).getPanel());
                }
                break;
            case GET_LOTTERY_PRIZE:
                parentFrame.setContentPane(new PrintResult(parentFrame, system).getPanel());
                break;
            default:
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
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

        if (system.getFunction() == FunctionType.TRANSFER && system.getFromTransaction().getAccount() != null) {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        requestCardOrBankbookPanel = new JPanel();
        requestCardOrBankbookPanel.setLayout(new GridLayoutManager(4, 5, new Insets(30, 30, 30, 30), -1, -1));
        requestCardOrBankbookPanel.setBackground(new Color(-1));
        requestCardOrBankbookPanel.setForeground(new Color(-11645362));
        requestCardOrBankbookPanel.setMinimumSize(new Dimension(800, 600));
        requestCardOrBankbookPanel.setPreferredSize(new Dimension(800, 600));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setText("카드 또는 통장을 넣어주십시오.");
        requestCardOrBankbookPanel.add(titleLabel, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bankLabel = new JLabel();
        Font bankLabelFont = this.$$$getFont$$$(null, -1, 20, bankLabel.getFont());
        if (bankLabelFont != null) bankLabel.setFont(bankLabelFont);
        bankLabel.setForeground(new Color(-16777216));
        bankLabel.setText("은행");
        requestCardOrBankbookPanel.add(bankLabel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberLabel = new JLabel();
        Font numberLabelFont = this.$$$getFont$$$(null, -1, 20, numberLabel.getFont());
        if (numberLabelFont != null) numberLabel.setFont(numberLabelFont);
        numberLabel.setForeground(new Color(-16777216));
        numberLabel.setText("통장번호");
        requestCardOrBankbookPanel.add(numberLabel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 30, 30));
        panel1.setBackground(new Color(-1));
        panel1.setForeground(new Color(-1));
        requestCardOrBankbookPanel.add(panel1, new GridConstraints(3, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setBackground(new Color(-10592674));
        Font confirmButtonFont = this.$$$getFont$$$(null, -1, 20, confirmButton.getFont());
        if (confirmButtonFont != null) confirmButton.setFont(confirmButtonFont);
        confirmButton.setForeground(new Color(-1));
        confirmButton.setText("Confirm");
        panel1.add(confirmButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-10592674));
        Font cancelButtonFont = this.$$$getFont$$$(null, -1, 20, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        cancelButton.setForeground(new Color(-1));
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 0, false));
        buttonHana = new JRadioButton();
        buttonHana.setBackground(new Color(-1));
        Font buttonHanaFont = this.$$$getFont$$$(null, -1, 20, buttonHana.getFont());
        if (buttonHanaFont != null) buttonHana.setFont(buttonHanaFont);
        buttonHana.setText("하나은행");
        requestCardOrBankbookPanel.add(buttonHana, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttonKookmin = new JRadioButton();
        buttonKookmin.setBackground(new Color(-1));
        Font buttonKookminFont = this.$$$getFont$$$(null, -1, 20, buttonKookmin.getFont());
        if (buttonKookminFont != null) buttonKookmin.setFont(buttonKookminFont);
        buttonKookmin.setText("국민은행");
        requestCardOrBankbookPanel.add(buttonKookmin, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttonWoori = new JRadioButton();
        buttonWoori.setBackground(new Color(-1));
        Font buttonWooriFont = this.$$$getFont$$$(null, -1, 20, buttonWoori.getFont());
        if (buttonWooriFont != null) buttonWoori.setFont(buttonWooriFont);
        buttonWoori.setText("우리은행");
        requestCardOrBankbookPanel.add(buttonWoori, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bankbookNumberField = new JNumberTextField();
        Font bankbookNumberFieldFont = this.$$$getFont$$$(null, -1, 20, bankbookNumberField.getFont());
        if (bankbookNumberFieldFont != null) bankbookNumberField.setFont(bankbookNumberFieldFont);
        requestCardOrBankbookPanel.add(bankbookNumberField, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return requestCardOrBankbookPanel;
    }
}
