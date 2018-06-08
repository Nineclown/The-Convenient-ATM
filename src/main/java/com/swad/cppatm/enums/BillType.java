package com.swad.cppatm.enums;

public enum BillType {
    THOUSAND(0), FIVE_THOUSAND(1), TEN_THOUSAND(2), FIFTY_THOUSAND(3),
    DOLLAR_ONE(4), DOLLAR_TWO(5), DOLLAR_FIVE(6), DOLLAR_TEN(7), DOLLAR_TWENTY(8), DOLLAR_FIFTY(9), DOLLAR_HUNDRED(10);

    private final int value;

    private BillType(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }

    public static final int WON_SIZE = 4;
    public static final int DOLLAR_SIZE = 7;

    public static int count(BillType type, int billAmount) {
        switch (type) {
            case THOUSAND:
                return 1000 * billAmount;
            case FIVE_THOUSAND:
                return 5000 * billAmount;
            case TEN_THOUSAND:
                return 10000 * billAmount;
            case FIFTY_THOUSAND:
                return 50000 * billAmount;
            default:
                return 0;
        }
    }

    public static int dollarCount(BillType type, int billAmount) {
        switch(type) {
            case DOLLAR_ONE:
                return billAmount;
            case DOLLAR_TWO:
                return 2 * billAmount;
            case DOLLAR_FIVE:
                return 5 * billAmount;
            case DOLLAR_TEN:
                return 10 * billAmount;
            case DOLLAR_TWENTY:
                return 20 * billAmount;
            case DOLLAR_FIFTY:
                return 50 * billAmount;
            case DOLLAR_HUNDRED:
                return 100 * billAmount;
            default:
                return 0;
        }
    }
}
