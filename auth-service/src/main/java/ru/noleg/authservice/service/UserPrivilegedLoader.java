package ru.noleg.authservice.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.noleg.authservice.entity.Role;
import ru.noleg.authservice.entity.User;
import ru.noleg.authservice.repository.UserRepository;

@Component
public class UserPrivilegedLoader implements CommandLineRunner {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "user";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserPrivilegedLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        initUsers(ADMIN_USERNAME, ADMIN_PASSWORD, Role.ROLE_ADMIN);
        initUsers(USER_USERNAME, USER_PASSWORD, Role.ROLE_USER);
    }

    private void initUsers(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("User with username " + username + " already exists.");
            return;
        }

        User user = new User(username, passwordEncoder.encode(password), role);
        userRepository.save(user);
    }
}
