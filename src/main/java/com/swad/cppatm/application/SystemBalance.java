package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.AdminAlarmException;
import com.swad.cppatm.exceptions.OverflowBillException;

public class SystemBalance {
    //index 0부터 10까지 차례대로, 한화 1000,5000,1000,10000원, 미화 1,2,5,10,20,50,100 달러
    private int[] current = {100,100,100,100,100,100,100,100,100,100,100};
    private int topLimit;
    private int bottomLimit;
    private int upAlarmLimit;
    private int downAlarmLimit;

    public SystemBalance() {
        this.topLimit = 1000000;
        this.bottomLimit = 10;
        this.upAlarmLimit = 999999;
        this.downAlarmLimit = 11;
    }

    public SystemBalance(int topLimit, int upAlarmLimit, int downAlarmLimit, int bottomLimit) {
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.upAlarmLimit = upAlarmLimit;
        this.downAlarmLimit = downAlarmLimit;
    }

    public void changeSystemBalance(int[] billAmount) throws OverflowBillException, AdminAlarmException {
        int tempCurrent;

        for (int i = 0; i < this.current.length; i++) {
            tempCurrent = this.current[i] + billAmount[i];
            if (tempCurrent >= topLimit || tempCurrent <= bottomLimit) {
                throw new OverflowBillException();
            } else if (tempCurrent >= upAlarmLimit || tempCurrent <= downAlarmLimit) {
                current[i] = tempCurrent;
                throw new AdminAlarmException();
            } else {
                current[i] = tempCurrent;
            }
        }

    }

    public void setATMBalance(int[] billAmount) {
        for (int i = 0; i < current.length; i++) {
            current[i] = billAmount[i];
        }
    }

    public int[] getATMBalance() {
        return this.current;
    }


}
