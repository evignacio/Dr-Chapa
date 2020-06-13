package com.vidanaestrada.domain.entity.user;

import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long Id;
    private String email;
    private String password;
}
