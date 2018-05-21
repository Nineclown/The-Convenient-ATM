public class AccountDoesNotExist extends Exception {
    // Parameterless Constructor
    public AccountDoesNotExist() {}

    // Constructor that accepts a message
    public AccountDoesNotExist(String message)
    {
        super(message);
    }
}
