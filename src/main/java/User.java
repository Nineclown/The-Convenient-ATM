import java.util.ArrayList;
import java.util.Iterator;


public class User {

    private String userId;
    private String userName;
    private ArrayList<String> cardList = new ArrayList<String>();

    public void User(String userId){
        ;
    }

    public void removeCard(String cardNumber)
    {
        for(int i = 0; i<cardList.size();i++)
        {
            if(cardList.get(i).equals(cardNumber))
            {
                cardList.remove(i);
                break;
            }
        }
    }
    //DB에 저장하는 부분인가? 모르겠어서 놔둠
    public String[] getCardList(){
        String[] cards = new String[cardList.size()];
        cards = cardList.toArray(cards);
        return cards;
    }

}
