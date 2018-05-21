public class InvalidBillException extends Exception {
    // Parameterless Constructor
    public InvalidBillException() {}

    // Constructor that accepts a message
    public InvalidBillException(String message)
    {
        super(message);
    }
}
