package com.vidanaestrada.domain.repository;

import com.vidanaestrada.domain.entity.trucker.Trucker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckerRepository extends JpaRepository<Trucker, Long> {
    Optional<Trucker> findByCpf(String cpf);
}
