public class OverflowBillException extends Exception {
    // Parameterless Constructor
    public OverflowBillException() {}

    // Constructor that accepts a message
    public OverflowBillException(String message)
    {
        super(message);
    }
}
