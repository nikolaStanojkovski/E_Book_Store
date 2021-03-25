package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidBookIdException extends RuntimeException {

    public InvalidBookIdException(Long id) {
        super("Invalid book id: " + id + " exception");
    }
}
