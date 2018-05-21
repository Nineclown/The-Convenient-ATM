package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminSelectFunction extends JFrame {
    private JFrame parent;
    private JButton addAdminButton;
    private JButton changeATMBalanceButton;
    private JButton toggleStateButton;
    private JButton removeAdminButton;
    private JPanel adminSelectFunctionPanel;
    private JButton queryATMBalanceButton;
    private JButton changeLocaleButton;
    private JLabel atmStateLabel;

    public AdminSelectFunction(final JFrame parentFrame) {
        addAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        removeAdminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        queryATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        toggleStateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeATMBalanceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        changeLocaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("parentFrame" + parentFrame);
                parentFrame.setContentPane(new AuthorizeAdmin(parentFrame).getPanel());
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.adminSelectFunctionPanel;
    }
}
