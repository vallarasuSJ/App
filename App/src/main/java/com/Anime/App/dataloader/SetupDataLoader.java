package com.Anime.App.dataloader;

import com.Anime.App.model.AppUser;
import com.Anime.App.model.Role;
import com.Anime.App.repository.RoleRepository;
import com.Anime.App.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        //create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);

        //create users
        createUserIfNotFound("user", "user","user@gmail.com", userRole);
        createUserIfNotFound("admin", "admin","admin@gmail.com", adminRole);

        alreadySetup = true;
    }

    @Transactional
    private void createUserIfNotFound(String username, final String password,final String email,final Role role) {
        Optional<AppUser> optionalAppUser =userRepository.findByUsername(username);
        if(optionalAppUser.isEmpty()){
            AppUser appUser=new AppUser();
            appUser.setUsername(username);
            appUser.setPassword(bCryptPasswordEncoder.encode(password));
            appUser.setEmail(email);
            appUser.setRoles(role);
            userRepository.save(appUser);
        }
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role=roleRepository.findByName(username);
        if (role == null) {
            role=new Role();
            role.setName(username);
            role= roleRepository.save(role);
        }
        return role;

    }
}
