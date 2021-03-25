package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidAuthorIdException extends RuntimeException {

    public InvalidAuthorIdException(Long id) {
        super("Invalid author id: " + id + " exception");
    }
}
