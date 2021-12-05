package manager.model.exceptions;

public class PasswordTooLongException extends Exception{
    public PasswordTooLongException(String errorMessage) {
        super(errorMessage);
    }
}
