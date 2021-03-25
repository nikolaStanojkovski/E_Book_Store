package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String password) {
        super("Invalid password: " + password);
    }
}
