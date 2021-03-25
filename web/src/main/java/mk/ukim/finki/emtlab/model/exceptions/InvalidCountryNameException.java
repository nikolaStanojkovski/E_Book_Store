package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidCountryNameException extends RuntimeException {

    public InvalidCountryNameException(String name) {
        super("Invalid country name: " + name + " exception");
    }
}
