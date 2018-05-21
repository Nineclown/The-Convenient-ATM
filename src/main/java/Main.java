import ui.*;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String [] args) {
        ATMSystem system = new ATMSystem();

        SelectFunction selectFunctionPanel = new SelectFunction();
        AdminSelectFunction adminSelectFunctionPanel = new AdminSelectFunction();

        JFrame userFrame = new JFrame("C++, The Convenient ATM");
        userFrame.setContentPane(new SelectFunction().getPanel());
        userFrame.setSize(800, 600);
        userFrame.setLocation(0, 0);
        userFrame.pack();
        userFrame.setVisible(true);

        JFrame adminFrame = new JFrame("Admin Interface for C++ ATM");
        adminFrame.setContentPane(new AdminSelectFunction().getPanel());
        adminFrame.setSize(800, 600);
        adminFrame.setLocation(800, 0);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);
    }
}
