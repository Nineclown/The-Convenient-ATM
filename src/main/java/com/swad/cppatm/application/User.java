package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.DataStoreError;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String userId;
    private String userName;
    private ArrayList<String> cardList = new ArrayList<>();
    private transient DataStore dataStore = new DataStore();

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void removeCard(String cardNumber) throws DataStoreError {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).equals(cardNumber)) {
                cardList.remove(i);
                break;
            }
        }
        this.saveUser();
    }

    public String[] getCardList() {
        String[] cards = new String[cardList.size()];
        cards = cardList.toArray(cards);
        return cards;
    }

    public void saveUser() throws DataStoreError {
        if (dataStore == null) {
            dataStore = new DataStore();
        }

        dataStore.saveUserData(this);
    }
}
