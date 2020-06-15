package com.vidanaestrada.domain.entity.travel;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Origin extends Coordinates {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "origin")
    private Travel travel;

    public Origin(long pointX, long pointY, String description) {
        super(pointX, pointY, description);
    }

}
