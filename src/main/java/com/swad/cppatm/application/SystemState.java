package com.swad.cppatm.application;

import com.swad.cppatm.enums.Locale;

public class SystemState {
    //true 가 정상작동
    private boolean state = true;
    private Locale locale = Locale.ko_KR;

    public boolean available() {
        return this.state;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void changeSystemLocale(Locale locale) {
        this.locale = locale;
    }

    public void toggleSystem() {
        this.state = !state;
    }

    public Locale getLocale(){return this.locale;}
}
