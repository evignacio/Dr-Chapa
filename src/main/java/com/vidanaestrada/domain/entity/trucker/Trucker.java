package com.vidanaestrada.domain.entity.trucker;

import com.vidanaestrada.domain.entity.travel.Travel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Travel> listTravel;

}
