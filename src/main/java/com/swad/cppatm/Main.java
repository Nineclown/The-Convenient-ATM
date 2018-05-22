package com.swad.cppatm;

import com.swad.cppatm.ui.*;
import com.swad.cppatm.application.ATMSystem;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String [] args) {
        ATMSystem system = new ATMSystem();

        //test
        system.enterAdminInfo("1234", "010-1234-1234");


        JFrame userFrame = new JFrame("C++, The Convenient ATM");
        userFrame.setContentPane(new SelectFunction(userFrame, system).getPanel());
        userFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        userFrame.setLocation(100, 100);
        userFrame.pack();
        userFrame.setVisible(true);

        JFrame adminFrame = new JFrame("Admin Interface for C++ ATM");
        adminFrame.setContentPane(new AuthorizeAdmin(adminFrame, system).getPanel());
        adminFrame.setLocation(1000, 100);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setVisible(true);
    }
}
