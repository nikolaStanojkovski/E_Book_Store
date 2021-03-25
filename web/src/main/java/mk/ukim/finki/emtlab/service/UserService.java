package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.User;
import mk.ukim.finki.emtlab.model.dto.UserDto;
import mk.ukim.finki.emtlab.model.enumerations.Role;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

public interface UserService {

    Optional<User> login(UserDto userDto) throws CredentialException;

    Optional<User> register(User user) throws CredentialException;
}
