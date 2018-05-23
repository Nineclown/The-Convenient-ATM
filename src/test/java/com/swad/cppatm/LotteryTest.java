package com.swad.cppatm;

import com.swad.cppatm.application.Lottery;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class LotteryTest {
    
    @Test
    public void checkResultTest() {
        int[] num = {1, 2, 3, 4, 5, 7};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 0);

        int[] num1 = {6, 10, 18, 4, 5, 7};
        lot = new Lottery(807, num1);
        assertEquals(lot.checkResult(), 5000);

        int[] num2 = {6, 10, 18, 25, 5, 7};
        lot = new Lottery(807, num2);
        assertEquals(lot.checkResult(), 50000);

        int[] num3 = {6, 10, 18, 25, 34, 7};
        lot = new Lottery(807, num3);
        assertEquals(lot.checkResult(), 500000);

        int[] num4 = {6, 10, 18, 25, 34, 33};
        lot = new Lottery(807, num4);
        assertEquals(lot.checkResult(), 5000000);

        int[] num5 = {6, 10, 18, 25, 34, 35};
        lot = new Lottery(807, num5);
        assertEquals(lot.checkResult(), 50000000);
    }
}
