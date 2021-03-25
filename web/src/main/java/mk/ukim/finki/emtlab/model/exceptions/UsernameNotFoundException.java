package mk.ukim.finki.emtlab.model.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("User with username: " + username + " doesn't exist in our storage");
    }
}
