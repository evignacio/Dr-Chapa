package com.vidanaestrada.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TruckerDTO {
    private String name;
    private String cpf;
    private String phone;
    private String password;
}
