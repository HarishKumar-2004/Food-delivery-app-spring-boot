package com.example.Food_app.services;

import com.example.Food_app.domain.SecuredUser;
import com.example.Food_app.repository.SecuredUserRepository;
import com.example.Food_app.utility.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecuredUserService implements UserDetailsService {
    @Autowired
    private SecuredUserRepository securedUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securedUserRepository.findByUsername(username);
    }

    public SecuredUser save(SecuredUser securedUser, String typeOfUser){
        String encryptedPwd = passwordEncoder.encode(securedUser.getPassword()); // encrypt the pwd before saving it in db
        String authorities = utils.getAuthoritiesForUsers().get(typeOfUser); // get student authorities from utils class
        securedUser.setPassword(encryptedPwd);
        securedUser.setAuthorities(authorities);
        return securedUserRepository.save(securedUser);
    }


}
