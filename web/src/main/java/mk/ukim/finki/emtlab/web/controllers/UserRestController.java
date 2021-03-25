package mk.ukim.finki.emtlab.web.controllers;

import mk.ukim.finki.emtlab.model.User;
import mk.ukim.finki.emtlab.model.dto.UserDto;
import mk.ukim.finki.emtlab.model.enumerations.Role;
import mk.ukim.finki.emtlab.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) throws CredentialException {
        return this.userService.login(userDto)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User registerUser) throws CredentialException {
        return this.userService.register(registerUser)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
