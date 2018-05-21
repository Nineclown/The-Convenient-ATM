import ui.SelectFunction;

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String [] args) {
        JFrame frame = new JFrame("SelectFunction");
        frame.setContentPane(new SelectFunction().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
