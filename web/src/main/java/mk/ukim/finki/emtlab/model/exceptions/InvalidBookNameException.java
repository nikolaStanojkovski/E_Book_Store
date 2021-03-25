package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidBookNameException extends RuntimeException {
    public InvalidBookNameException(String name) {
        super("Invalid book name: " + name + " exception");
    }
}
