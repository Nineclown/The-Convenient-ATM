package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorizeAdmin extends JFrame {
    private JPanel authorizeAdminPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextField adminIdField;
    private JTextField adminPwField;

    public AuthorizeAdmin(final JFrame parentFrame) {
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
                parentFrame.setContentPane(new AdminSelectFunction(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
