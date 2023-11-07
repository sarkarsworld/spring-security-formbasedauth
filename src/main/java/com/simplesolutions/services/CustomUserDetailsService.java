package com.simplesolutions.services;

import com.simplesolutions.models.CustomUserDetails;
import com.simplesolutions.models.User;
import com.simplesolutions.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.getByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User Name not found");
        }

        return new CustomUserDetails(user);
    }
}
