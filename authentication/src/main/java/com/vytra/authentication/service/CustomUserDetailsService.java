package com.vytra.authentication.service;

import com.vytra.authentication.entity.security.User;
import com.vytra.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service(value = "userDetailsService")
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    Optional<User> get(Long id){
        return userRepository.findById(id);
    }

    Optional<User> getByName(String name){
        return userRepository.findByUsername(name);
    }


    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        /** Aqui se manejan los filtros de seguridad**/
        User user = getByName(input).orElse(null);
        if(user == null){throw new BadCredentialsException("Bad Credentials"); }
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }
}
