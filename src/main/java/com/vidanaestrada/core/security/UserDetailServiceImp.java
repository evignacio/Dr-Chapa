package com.vidanaestrada.core.security;

import com.vidanaestrada.core.exception.AutenticacaoException;
import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.domain.repository.TruckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private TruckerRepository truckerRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<Trucker> userOpt =  truckerRepository.findByCpf(cpf);

        if(!userOpt.isPresent())
            throw new AutenticacaoException("user not found");

        Trucker trucker = userOpt.get();
        return new AccountCredential(trucker.getId(), trucker.getCpf(), trucker.getPassword());
    }
}
