package com.vidanaestrada.application.implementation;

import com.vidanaestrada.application.UserService;
import com.vidanaestrada.domain.entity.user.User;
import com.vidanaestrada.dto.UserDTO;
import com.vidanaestrada.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDTO userDTO) {
        User user  = User.builder()
                .cpf(userDTO.getCpf())
                .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);
    }
}
