public class Lottery {
    private int week = 0;
    private int[] numbers  = new int[7];

    public int checkResult()
    {
        //당첨 번호 개수
        int correctNum = 0;
        int price = 0;
        int []resultNumbers = new int[7];

        // 당첨 번호를 어디서 받아와야하는지 모르겠네
        // int [] resultNumbers = getNumbers(week);
        for(int i = 0; i<numbers.length;i++)
        {
            if(numbers[i] == resultNumbers[i])
            {
                correctNum +=1;
            }
        }
        //당첨금
        switch(correctNum)
        {
            case 0:
            case 1: price = 0;
                break;
            case 2: price = 5000;
                break;
            case 3: price = 50000;
                break;
            case 4: price = 1200000;
                break;
            case 5: price = 48000000;
                break;
            case 6: price = 2000000000;
                break;
        }
        return price;
    }

}
