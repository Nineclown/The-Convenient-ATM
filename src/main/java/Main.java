import ui.*;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String [] args) {
        ATMSystem system = new ATMSystem();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame userFrame = new JFrame("C++, The Convenient ATM");
        userFrame.setContentPane(new SelectFunction(userFrame).getPanel());
        userFrame.setLocation(0, 0);
        userFrame.pack();
        userFrame.setVisible(true);

        JFrame adminFrame = new JFrame("Admin Interface for C++ ATM");
        adminFrame.setContentPane(new AdminSelectFunction(adminFrame).getPanel());
        adminFrame.setLocation(800, 0);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setVisible(true);
    }
}
