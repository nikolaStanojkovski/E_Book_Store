package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidCountryIdException extends RuntimeException {

    public InvalidCountryIdException(Long id) {
        super("Invalid country id: " + id + " exception");
    }

}
