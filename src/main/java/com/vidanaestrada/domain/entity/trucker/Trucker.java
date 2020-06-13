package com.vidanaestrada.domain.entity.trucker;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Trucker {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String password;

}
