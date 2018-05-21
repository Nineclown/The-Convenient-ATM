package ui;

import javax.swing.*;

public class AuthorizeAdmin extends JFrame {
    private JTextField adminIdField;
    private JTextField adminPwField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel authorizeAdminPanel;

    public JPanel getPanel() {
        return this.authorizeAdminPanel;
    }
}
