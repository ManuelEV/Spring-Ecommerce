package cl.dci.eshop.controller;


import cl.dci.eshop.auth.CustomUserDetails;
import cl.dci.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cl.dci.eshop.security.ApplicationUserRole.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getAllStudents() {
        String password = passwordEncoder.encode("password");
        CustomUserDetails user1 = new CustomUserDetails("admin",
                password,
        ADMIN);

        CustomUserDetails user2 = new CustomUserDetails("admintrainee",
                password,
                ADMINTRAINEE);
        CustomUserDetails user3 = new CustomUserDetails("student",
                password,
                STUDENT);

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);

        return "OK";
    }

}
