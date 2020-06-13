package com.vidanaestrada.domain.entity.vehicle;

import com.vidanaestrada.domain.entity.travel.Travel;
import com.vidanaestrada.domain.entity.trucker.Trucker;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne
    private Trucker trucker;

    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name ="vehicle_id")
    private List<Travel> listTravel;

}
