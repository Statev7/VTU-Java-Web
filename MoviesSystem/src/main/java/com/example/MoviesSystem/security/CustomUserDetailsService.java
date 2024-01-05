package com.example.MoviesSystem.security;

import com.example.MoviesSystem.data.models.ApplicationUser;
import com.example.MoviesSystem.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.userRepository.findByUsername(username);

        if(applicationUser == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(applicationUser.getRole().getName()));

        User authUser = new User(applicationUser.getUsername(), applicationUser.getPassword(), roles);

        return authUser;
    }
}
