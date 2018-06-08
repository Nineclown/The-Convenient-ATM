package com.swad.cppatm;

import com.swad.cppatm.ui.*;
import com.swad.cppatm.application.ATMSystem;
import javax.swing.*;

public class Main {
    private JFrame userFrame;
    private JFrame adminFrame;
    private ATMSystem system;
    private static Main application;

    private Main() {
        super();
        userFrame = new JFrame("C++, The Convenient ATM");
        adminFrame = new JFrame("Admin Interface for C++ ATM");
    }

    public static Main getInstance() {
        if ( application == null ) {
            application = new Main();
        }

        return application;
    }

    public void initializeScreen() {
        this.userFrame.setVisible(false);
        this.userFrame.pack();
        this.userFrame.setContentPane(new SelectFunction(application.userFrame, system).getPanel());
        this.userFrame.invalidate();
        this.userFrame.validate();
        this.userFrame.setVisible(true);

        this.adminFrame.setVisible(false);
        this.adminFrame.pack();
        this.adminFrame.setContentPane(new AdminSelectFunction(application.adminFrame, system).getPanel());
        this.adminFrame.invalidate();
        this.adminFrame.validate();
        this.adminFrame.setVisible(true);
    }

    public static void main(String [] args) {
        application = Main.getInstance();

        application.system = new ATMSystem();

        application.userFrame.setContentPane(new SelectFunction(application.userFrame, application.system).getPanel());
        application.userFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        application.userFrame.setLocation(100, 100);
        application.userFrame.pack();
        application.userFrame.setVisible(true);

        application.adminFrame.setContentPane(new AuthorizeAdmin(application.adminFrame, application.system).getPanel());
        application.adminFrame.setLocation(1000, 100);
        application.adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.adminFrame.pack();
        application.adminFrame.setVisible(true);
    }
}
