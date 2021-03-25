package mk.ukim.finki.emtlab.service.implementation;

import mk.ukim.finki.emtlab.model.User;
import mk.ukim.finki.emtlab.model.dto.UserDto;
import mk.ukim.finki.emtlab.model.exceptions.InvalidPasswordException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.emtlab.model.exceptions.UsernameNotFoundException;
import mk.ukim.finki.emtlab.repository.UserRepository;
import mk.ukim.finki.emtlab.service.UserService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(UserDto userDto) throws UsernameNotFoundException, CredentialException {
        User user = this.userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDto.getUsername()));

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty() ||
                userDto.getPassword() == null || userDto.getPassword().isEmpty())
            throw new CredentialException();

        if (!user.getUsername().equals(userDto.getUsername()))
            throw new InvalidUsernameException(userDto.getUsername());

        String encodedPassword = userDto.getPassword();
        if (!user.getPassword().equals(encodedPassword))
            throw new InvalidPasswordException(userDto.getPassword());

        return Optional.of(user);
    }

    @Override
    public Optional<User> register(User user) throws CredentialException {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getName() == null || user.getName().isEmpty() ||
                user.getSurname() == null || user.getSurname().isEmpty())
            throw new CredentialException();

        User registerUser = new User(user.getUsername(), user.getPassword(),
                user.getName(), user.getSurname(), user.getRole());
        this.userRepository.save(registerUser);

        return Optional.of(registerUser);
    }
}
