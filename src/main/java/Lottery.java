public class Lottery {
    private int week = 0;
    private int[] numbers  = new int[7];

    public int checkResult()
    {
        //당첨 번호 개수
        int correctNum = 0;
        int price = 0;
        // 당첨 번호를 어디서 받아와야하는지 모르겠네
        int [] resultNumbers = getNumbers(week);
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
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
        return price;
    }

//    받아오는것 보류
    public int[] getNumbers(int week)
    {
        int [] result = new int [7];
        // 여기에 result 받아오는 걸 넣어야할듯
        return result;
    }




}
