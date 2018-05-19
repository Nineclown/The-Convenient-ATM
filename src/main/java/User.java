import java.util.ArrayList;
import java.util.Iterator;


public class User {

    private String userId;
    private String userName;
    private ArrayList<String> cardList = new ArrayList<String>();

    public void User(String userId){
        ;
    }


    //DB에 저장하는 부분인가? 모르겠어서 놔둠
    public String[] getCardList(){
        String[] cards = new String[cardList.size()];
        cards = cardList.toArray(cards);
        return cards;
    }

}
