package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryList extends JFrame {
    private JTextField accountBalanceField;
    private JTextField accountNumberField;
    private JList transactionList;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel queryListPanel;

    public QueryList() {
        prevButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public JPanel getPanel() {
        return this.queryListPanel;
    }
}
