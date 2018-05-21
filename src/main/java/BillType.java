public enum BillType {
    Thousand(0), FiveThousand(1), TenThousand(2), FiftyThousand(3),
    DollarOne(4), DollarTwo(5), DollarTen(6), DollarFifty(7), DollarHundred(8);

    private final int value;

    private BillType(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }

    public static int wonSize = 4;
    public static int dollarSize = 5;

    public static int count(BillType type, int billAmount) {
        switch (type) {
            case Thousand:
                return 1000 * billAmount;
            case FiveThousand:
                return 5000 * billAmount;
            case TenThousand:
                return 10000 * billAmount;
            case FiftyThousand:
                return 50000 * billAmount;
            default:
                return 0;
        }
    }

    public static int dollarCount(BillType type, int billAmount) {
        switch(type) {
            case DollarOne:
                return billAmount;
            case DollarTwo:
                return 2 * billAmount;
            case DollarTen:
                return 10 * billAmount;
            case DollarFifty:
                return 50 * billAmount;
            case DollarHundred:
                return 100 * billAmount;
            default:
                return 0;
        }
    }
}
