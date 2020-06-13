package com.vidanaestrada.domain.entity.travel;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Destination extends Coordinates {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "destination")
    private Travel travel;
}
