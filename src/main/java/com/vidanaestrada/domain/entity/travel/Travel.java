package com.vidanaestrada.domain.entity.travel;


import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Travel {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="trucker_id")
    private Trucker trucker;

    @ManyToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="vehicle_id")
    private Vehicle vehicle;

    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin origin;

    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Destination destination;

    private long cost;
    private long profitableValue;
    private long averageTime;
    private int mealsNumber;
    private int estimatedStops;
    private long distance;
}
