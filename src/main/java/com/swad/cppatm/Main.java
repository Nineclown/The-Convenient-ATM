package com.swad.cppatm;

import com.swad.cppatm.ui.*;
import com.swad.cppatm.application.ATMSystem;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String [] args) {
        ATMSystem system = new ATMSystem();

        JFrame userFrame = new JFrame("C++, The Convenient ATM");
        userFrame.setContentPane(new SelectFunction(userFrame).getPanel());
        userFrame.setLocation(0, 0);
        userFrame.pack();
        userFrame.setVisible(true);

        JFrame adminFrame = new JFrame("Admin Interface for C++ ATM");
        adminFrame.setContentPane(new AdminSelectFunction(adminFrame, system).getPanel());
        adminFrame.setLocation(800, 0);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setVisible(true);
    }
}
