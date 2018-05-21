package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorizeAdmin extends JFrame {
    private JTextField adminIdField;
    private JTextField adminPwField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel authorizeAdminPanel;

    public AuthorizeAdmin() {
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
