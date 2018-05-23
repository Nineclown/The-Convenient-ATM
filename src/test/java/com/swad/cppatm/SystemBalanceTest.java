package com.swad.cppatm;

import com.swad.cppatm.application.SystemBalance;
import com.swad.cppatm.exceptions.AdminAlarmException;
import com.swad.cppatm.exceptions.OverflowBillException;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class SystemBalanceTest {
    @Test(expected = AdminAlarmException.class)
    public void throwAdminAlarmExceptionTest() throws AdminAlarmException, OverflowBillException {
        int[] balance = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
        int[] input = {40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        SystemBalance systemBalance = new SystemBalance(100, 80, 20, 0);
        systemBalance.setATMBalance(balance);
        systemBalance.changeSystemBalance(input);
    }

    @Test(expected = OverflowBillException.class)
    public void throwOverflowBillExceptionTest() throws AdminAlarmException, OverflowBillException {
        int[] balance = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
        int[] input = {60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        SystemBalance systemBalance = new SystemBalance(100, 80, 20, 0);
        systemBalance.setATMBalance(balance);
        systemBalance.changeSystemBalance(input);
    }
}
