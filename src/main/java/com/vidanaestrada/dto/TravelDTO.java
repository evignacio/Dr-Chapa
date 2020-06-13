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

    private Long truckerId;
    private Long vehicleId;

    private long OriginPointX;
    private long OriginPointY;
    private String OriginDescription;

    private long DestinationPointX;
    private long DestinationPointY;
    private String DestinationDescription;

    private long cost;
    private long profitableValue;
    private int mealsNumber;
    private int estimatedStops;
    private long averageTime;
    private long distance;
}
