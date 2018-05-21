import javax.swing.*;
import ui.AdminSelectFunction;

public class AdminMain extends JFrame {
    public static void main(String [] args) {
        JFrame frame = new JFrame("AdminSelectFunction");
        frame.setContentPane(new AdminSelectFunction().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
