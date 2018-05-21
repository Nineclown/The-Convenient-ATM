package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFunction extends JFrame {
    private JPanel selectFunctionPanel;
    private JButton depositButton;
    private JButton queryBalanceButton;
    private JButton queryTransactionListButton;
    private JButton foreignWithdrawButton;
    private JButton foreignDepositButton;
    private JButton withdrawButton;
    private JButton reportLostCardButton;
    private JButton transferButton;
    private JButton splitPayButton;
    private JButton getLotteryPrizeButton;
    private JButton changeLocaleButton;
    private JTextField textField1;

    public JPanel getPanel() {
        return this.selectFunctionPanel;
    }

    public SelectFunction() {
        depositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        foreignDepositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
    }
}
