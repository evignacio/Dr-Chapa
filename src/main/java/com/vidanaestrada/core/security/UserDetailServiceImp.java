package com.vidanaestrada.core.security;

import com.vidanaestrada.core.exception.AutenticacaoException;
import com.vidanaestrada.domain.entity.user.User;
import com.vidanaestrada.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOpt =  userRepository.findByEmail(email);

        if(!userOpt.isPresent())
            throw new AutenticacaoException("user not found");

        User user = userOpt.get();
        return new AccountCredential(user.getEmail(), user.getPassword());
    }
}
