public class SystemBalance  {
    //index 0부터 10까지 차례대로, 한화 1000,5000,1000,10000원, 미화 1,2,5,10,20,50,100 달러
    private int[] current = new int[11];
    private int topLimit;
    private int bottomLimit;
    private int upAlarmLimit;
    private int downAlarmLimit;

    public void changeSystemBalance(int[] billAmount)
    {
        int tempCurrent;
        try {
            for (int i = 0; i < current.length; i++) {
                tempCurrent = current[i] + billAmount[i];
                if (tempCurrent >= topLimit || tempCurrent <= bottomLimit) {
                    // 오류
                    throw new Exception ();
                } else if (tempCurrent >= upAlarmLimit || tempCurrent <= downAlarmLimit) {
                    //관리자에게 알람
                } else {
                    current[i] = tempCurrent;
                }
            }
        }catch(Exception e)
        {
            java.lang.System.out.println("ATM 지폐 보유량을 초과하였습니다");
        }
    }
    public void setATMBalance(int[] billAmount)
    {
        for(int i = 0; i<current.length;i++)
        {
            current[i] = billAmount[i];
        }
    }

    public int[] getATMBalance()
    {
        return this.current;
    }



}
