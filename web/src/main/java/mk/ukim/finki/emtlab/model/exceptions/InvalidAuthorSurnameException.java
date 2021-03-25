package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidAuthorSurnameException extends RuntimeException {
    public InvalidAuthorSurnameException(String surname) {
        super("Invalid author surname: " + surname + " exception");
    }
}
