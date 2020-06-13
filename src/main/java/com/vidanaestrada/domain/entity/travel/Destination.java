package com.vidanaestrada.domain.entity.travel;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Destination extends Coordinates {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "destination")
    private Travel travel;

    public Destination(long pointX, long pointY, String description) {
        super(pointX, pointY, description);
    }
}
