package com.vidanaestrada.domain.entity.travel;

import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@MappedSuperclass
public class Coordinates {
    private long pointX;
    private long pointY;
    private String description;

    public Coordinates(long pointX, long pointY, String description) {
        pointX = pointX;
        pointY = pointY;
        description = description;
    }
}
