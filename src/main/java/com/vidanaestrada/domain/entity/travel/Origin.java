package com.vidanaestrada.domain.entity.travel;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Origin extends Coordinates {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "origin")
    private Travel travel;

}
