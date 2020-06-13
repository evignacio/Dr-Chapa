package com.vidanaestrada.domain.entity.travel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Coordinates {
    private String pointX;
    private String pointY;
    private String description;
}
