package com.example.MoviesSystem.features.users.services.implementation;

import com.example.MoviesSystem.common.GlobalConstants;
import com.example.MoviesSystem.data.models.ApplicationRole;
import com.example.MoviesSystem.data.models.ApplicationUser;
import com.example.MoviesSystem.data.repositories.RoleRepository;
import com.example.MoviesSystem.data.repositories.UserRepository;
import com.example.MoviesSystem.features.users.models.UserFormModel;
import com.example.MoviesSystem.features.users.services.contracts.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(UserFormModel model) {
        ApplicationRole role = this.roleRepository.findByName(GlobalConstants.USER_ROLE);

        ApplicationUser userToCreate = ApplicationUser
                .builder()
                .username(model.getUsername())
                .password(passwordEncoder.encode(model.getPassword()))
                .role(role)
                .build();

        this.userRepository.save(userToCreate);
    }

    @Override
    public boolean isExistByUsername(String username) {
        ApplicationUser user = this.userRepository.findByUsername(username);

        return user != null;
    }

    @Override
    public String getRoleNameByUsername(String username) {
        ApplicationUser user = this.userRepository.findByUsername(username);

        return user.getRole().getName();
    }
}
