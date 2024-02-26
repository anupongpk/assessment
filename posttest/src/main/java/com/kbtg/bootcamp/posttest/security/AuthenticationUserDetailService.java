package com.kbtg.bootcamp.posttest.security;

import com.kbtg.bootcamp.posttest.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuthenticationUserDetailService implements UserDetailsService {

    List<CustomUserDetail> userDetails  = new ArrayList<>();

    public AuthenticationUserDetailService(UserRepository userRepository) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Mock up user detail of ADMIN
        CustomUserDetail admin = new CustomUserDetail("admin", encoder.encode("password"));
        admin.setRoles(List.of("ADMIN"));
        userDetails.add(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userDetails.stream().filter(user -> user.getUsername()
                .equals(username))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("No user was found"));
    }

}
