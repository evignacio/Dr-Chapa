package com.vidanaestrada.domain.entity.vehicle;

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
public class Vehicle {
    @Id @GeneratedValue
    private Long id;
    private VEHICLE_CATEGORY transportCategory;
    private int numberAxes;
}
