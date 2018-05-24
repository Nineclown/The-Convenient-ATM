package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.AdminAlarmException;
import com.swad.cppatm.exceptions.OverflowBillException;

public class SystemBalance {
    //index 0부터 10까지 차례대로, 한화 1000,5000,1000,10000원, 미화 1,2,5,10,20,50,100 달러
    private int[] current = {500,500,500,500,500,500,500,500,500,500,500};
    private int topLimit;
    private int bottomLimit;
    private int upAlarmLimit;
    private int downAlarmLimit;

    public SystemBalance() {
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
        int min = topLimit;
        int max = bottomLimit;


        for (int i = 0; i < this.current.length; i++) {
            this.current[i] += billAmount[i];
            if(min > current[i]){
                min = current[i];
            }
            if(max < current[i]){
                max = current[i];
            }
        }

        if(min < bottomLimit || max > topLimit){
            throw new OverflowBillException();
        }else if(min < downAlarmLimit || max > upAlarmLimit){
            throw new AdminAlarmException();
        }


    }

    public void setATMBalance(int[] billAmount) throws  OverflowBillException{
        for (int i = 0; i < current.length; i++) {
            current[i] = billAmount[i];
            if(current[i] >  topLimit || current[i] < bottomLimit){
                throw new OverflowBillException();
            }
        }
    }

    public int[] getATMBalance() {
        return this.current;
    }
}
