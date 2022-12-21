package com.example.warehousemanagement.security;

import com.example.warehousemanagement.entity.UserEntity;
import com.example.warehousemanagement.repository.UserRepository;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        return new User(userEntity.getUsername(),
            bCryptPasswordEncoder.encode(userEntity.getPassword()),
            new ArrayList<>());
    }
}
