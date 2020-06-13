package com.vidanaestrada.application.implementation;

import com.vidanaestrada.application.TruckerService;
import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.dto.TruckerDTO;
import com.vidanaestrada.domain.repository.TruckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TruckerServiceImp implements TruckerService {

    @Autowired
    private TruckerRepository truckerRepository;

    @Override
    public void save(TruckerDTO truckerDTO) {
        Trucker trucker = Trucker.builder()
                .name(truckerDTO.getName())
                .cpf(truckerDTO.getCpf())
                .password(new BCryptPasswordEncoder().encode(truckerDTO.getPassword()))
                .build();

        truckerRepository.save(trucker);
    }
}
