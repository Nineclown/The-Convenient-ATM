package com.swad.cppatm.ui.components;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * A {@link JTextField} that skips all non-digit keys. The user is only able to enter numbers.
 *
 * @author Michi Gysel <michi@scythe.ch>
 *
 */
public class JNumberTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar()) || ev.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            super.processKeyEvent(ev);
        }
        ev.consume();
    }

    /**
     * As the user is not even able to enter a dot ("."), only integers (whole numbers) may be entered.
     */
    public Integer getNumber() {
        Integer result = null;
        String text = getText();
        if (text != null && !"".equals(text)) {
            result = Integer.valueOf(text);
        }
        return result;
    }
}
