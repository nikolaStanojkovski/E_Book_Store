package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidAuthorNameException extends RuntimeException {
    public InvalidAuthorNameException(String name) {
        super("Invalid author name: " + name + " exception");
    }
}
