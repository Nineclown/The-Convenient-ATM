package com.swad.cppatm.ui;

import com.swad.cppatm.application.ATMSystem;
import com.swad.cppatm.exceptions.AccountDoesNotExist;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Pattern;

public class EnterDate {
    private JPanel enterDatePanel;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton cancelButton;
    private JButton confirmButton;

    public EnterDate(final JFrame parentFrame, final ATMSystem system) {

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Date start, end;
                if ( !startDateField.getText().matches("^\\d{8}$") && !endDateField.getText().matches("^\\d{8}$") ) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int startyear, startmonth, startdate;
                int endyear, endmonth, enddate;

                startyear = Integer.parseInt(startDateField.getText().substring(0, 4));
                startmonth = Integer.parseInt(startDateField.getText().substring(4, 6));
                startdate = Integer.parseInt(startDateField.getText().substring(6, 8));
                endyear = Integer.parseInt(endDateField.getText().substring(0, 4));
                endmonth = Integer.parseInt(endDateField.getText().substring(4, 6));
                enddate = Integer.parseInt(endDateField.getText().substring(6, 8));

                if(startmonth > 12 || endmonth > 12 || startdate >31 || enddate>31 || startyear <1900 || endyear < 1900){
                    JOptionPane.showMessageDialog(parentFrame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                start = new Date(startyear-1900, startmonth-1, startdate);
                end = new Date(endyear-1900, endmonth-1, enddate);

                if(start.compareTo(end) >= 0){
                    JOptionPane.showMessageDialog(parentFrame, "시작일이 끝나는 날보다 큽니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    system.enterPeriodToQuery(start, end);
                } catch (AccountDoesNotExist ex) {
                    parentFrame.setContentPane(new SelectFunction(parentFrame, system).getPanel());
                    parentFrame.pack();
                    parentFrame.invalidate();
                    parentFrame.validate();
                    return;
                }

                parentFrame.setContentPane(new QueryList(parentFrame, system).getPanel());
                parentFrame.pack();
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return this.enterDatePanel;
    }
}
