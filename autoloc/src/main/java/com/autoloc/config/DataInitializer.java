package com.autoloc.config;

import com.autoloc.enums.userRole;
import com.autoloc.model.Admin;
import com.autoloc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String email = "superadmin@autoloc.fr";
        if (!userRepository.existsByEmail(email)) {
            Admin superAdmin = new Admin();
            superAdmin.setFirstname("Super");
            superAdmin.setLastname("Admin");
            superAdmin.setEmail(email);
            superAdmin.setPassword(passwordEncoder.encode("Admin@1234"));
            superAdmin.setRole(userRole.SUPER_ADMIN);
            superAdmin.setActif(true);
            userRepository.save(superAdmin);
            System.out.println(">>> Super admin créé : " + email + " / Admin@1234");
        }
    }
}
