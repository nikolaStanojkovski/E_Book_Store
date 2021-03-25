package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidCountryContinentException extends RuntimeException {

    public InvalidCountryContinentException(String continent) {
        super("Invalid country continent: " + continent + " exception");
    }
}
