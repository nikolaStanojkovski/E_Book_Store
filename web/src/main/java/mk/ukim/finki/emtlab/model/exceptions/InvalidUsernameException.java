package mk.ukim.finki.emtlab.model.exceptions;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException(String username) {
        super("Invalid username: " + username);
    }

}
