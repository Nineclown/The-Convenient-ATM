package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.AdminAlarmException;
import com.swad.cppatm.exceptions.OverflowBillException;

public class SystemBalance {
    //index 0부터 10까지 차례대로, 한화 1000,5000,1000,10000원, 미화 1,2,5,10,20,50,100 달러
    private int[] current = {500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500};
    private int topLimit;
    private int bottomLimit;
    private int upAlarmLimit;
    private int downAlarmLimit;

    SystemBalance() {
        this.topLimit = 1000;
        this.bottomLimit = 0;
        this.upAlarmLimit = 900;
        this.downAlarmLimit = 100;
    }

    public SystemBalance(int topLimit, int upAlarmLimit, int downAlarmLimit, int bottomLimit) {
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.upAlarmLimit = upAlarmLimit;
        this.downAlarmLimit = downAlarmLimit;
    }

    public void changeSystemBalance(int[] billAmount) throws OverflowBillException, AdminAlarmException {
        int[] temp = new int[11];
        boolean overflow = false;

        for (int i = 0; i < this.current.length; i++) {
            temp[i] = billAmount[i] + current[i];
            if (temp[i] < bottomLimit || temp[i] > topLimit) {
                overflow = true;
            }
        }

        if (overflow) {
            throw new OverflowBillException();
        } else {
            System.arraycopy(temp, 0, current, 0, temp.length);
        }

        for (int value : current) {
            if (value < downAlarmLimit || value > upAlarmLimit) {
                throw new AdminAlarmException();
            }
        }
    }

    public void setATMBalance(int[] billAmount) throws OverflowBillException {
        boolean isException = false;

        for (int bills : billAmount) {
            if (bills > topLimit || bills < bottomLimit) {
                isException = true;
            }
        }
        if (isException) {
            throw new OverflowBillException();
        } else {
            System.arraycopy(billAmount, 0, current, 0, billAmount.length);
        }
    }

    public int[] getATMBalance() {
        return this.current;
    }
}
