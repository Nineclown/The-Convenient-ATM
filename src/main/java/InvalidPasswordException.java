public class InvalidPasswordException extends Exception {
    // Parameterless Constructor
    public InvalidPasswordException() {}

    // Constructor that accepts a message
    public InvalidPasswordException(String message)
    {
        super(message);
    }
}
