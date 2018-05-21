package com.swad.cppatm.exceptions;

public class LotteryFailed extends Exception {
    public LotteryFailed() {

    }

    public LotteryFailed(String message) {
        super(message);
    }
}
