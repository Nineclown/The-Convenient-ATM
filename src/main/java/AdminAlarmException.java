public class AdminAlarmException extends Exception {
    // Parameterless Constructor
    public AdminAlarmException() {}

    // Constructor that accepts a message
    public AdminAlarmException(String message)
    {
        super(message);
    }
}
