package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.DataStoreError;

import java.util.ArrayList;

public class User {

    private String userId;
    private String userName;
    private ArrayList<String> cardList = new ArrayList<String>();
    private transient DataStore datastore = new DataStore();

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
        if (datastore == null) {
            datastore = new DataStore();
        }
        try {
            datastore.saveUserData(this);
        } catch (DataStoreError e) {
            throw e;
        }
    }

}
