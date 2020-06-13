package com.vidanaestrada.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TravelDTO {

    private Long trackerId;
    private Long vehicleId;

    private String OriginPointX;
    private String OriginPointY;
    private String OriginDescription;

    private String DestinationPointX;
    private String DestinationPointY;
    private String DestinationDescription;

    private double cost;
    private double gain;
    private int mealsNumber;
    private int estimatedStops;
    private int averageTime;
}
