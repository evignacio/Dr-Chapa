package com.vidanaestrada.application.implementation;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.vidanaestrada.application.TravelService;
import com.vidanaestrada.domain.entity.travel.Destination;
import com.vidanaestrada.domain.entity.travel.Origin;
import com.vidanaestrada.domain.entity.travel.Travel;
import com.vidanaestrada.domain.entity.trucker.Trucker;
import com.vidanaestrada.domain.entity.vehicle.Vehicle;
import com.vidanaestrada.domain.repository.TravelRepository;
import com.vidanaestrada.domain.repository.TruckerRepository;
import com.vidanaestrada.domain.repository.VehicleRepository;
import com.vidanaestrada.dto.TravelDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TravelServiceImp implements TravelService {

    private final int BASE_INTERVAL_FOR_MEALS = 3;
    private final int BASE_INTERVAL_FOR_SLEEP = 16;
    private final double BASE_PERCENTEGE_FOR_PROFIT = 0.55;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TruckerRepository truckerRepository;

    @Autowired
    private TravelRepository travelRepository;


    @Override
    public TravelDTO generateTripPlanning(TravelDTO travelDTO) {

        Optional<Trucker> truckerOpt = truckerRepository.findById(travelDTO.getTruckerId());

        if(!truckerOpt.isPresent())
            throw new RuntimeException("Trucker not found");

        Trucker trucker = truckerOpt.get();
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByTrucker(trucker);

        if(!vehicleOpt.isPresent())
            throw new RuntimeException("Vehicle not found");

        Vehicle vehicle = vehicleOpt.get();

        mountTravel(travelDTO, vehicle, trucker);

        Travel travel = Travel.builder()
                .averageTime(travelDTO.getAverageTime())
                .cost(travelDTO.getCost())
                .origin(new Origin(travelDTO.getDestinationPointX(), travelDTO.getDestinationPointY(), travelDTO.getDestinationDescription()))
                .destination(new Destination(travelDTO.getDestinationPointX(), travelDTO.getDestinationPointY(), travelDTO.getDestinationDescription()))
                .distance(travelDTO.getDistance())
                .profitableValue(travelDTO.getProfitableValue())
                .suggestionMealsNumber(travelDTO.getSuggestionMealsNumber())
                .suggestionSleepNumber(travelDTO.getSuggestionSleepNumber())
                .trucker(trucker)
                .vehicle(vehicle)
                .build();

        travelRepository.save(travel);

        return travelDTO;
    }

    private void mountTravel(TravelDTO travelDTO, Vehicle vehicle, Trucker trucker) {
        setEstimatedCostTravel(travelDTO, vehicle, trucker);
        setEstimatedProfitToTravel(travelDTO);
        setSuggestionMealsNumberToTravel(travelDTO);
        setSuggestionSleepNumberToTravel(travelDTO);
    }

    private void setEstimatedCostTravel(TravelDTO travelDTO, Vehicle vehicle, Trucker trucker) {
        String responseData = doRequestToFreeTableFreight(travelDTO.getDistance(), vehicle);

        String constTravel = StringUtils.substringBetween(responseData, "<h2 style=\"margin-top:5px;margin-bottom:5px;\">Total = R$ ", "</h2>");
        constTravel = constTravel.replace(",", "");

        travelDTO.setCost(Integer.parseInt(constTravel));
    }

    private String doRequestToFreeTableFreight(long distance, Vehicle vehicle) {
        final String uri = String.format(
                "https://www.tabelasdefrete.com.br/api/site27-05-20.php?category=%d&type=%d&axes=%d&distance=%d",
                vehicle.getTransportCategory().ordinal(),
                vehicle.getLoadtype().ordinal(),
                vehicle.getNumberAxes(),
                distance
        );



        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }


    private void setEstimatedProfitToTravel(TravelDTO travelDTO) {
        travelDTO.setProfitableValue((long) (Math.floor(travelDTO.getCost() + (travelDTO.getCost() * BASE_PERCENTEGE_FOR_PROFIT))));
    }



    private void setSuggestionMealsNumberToTravel(TravelDTO travelDTO) {
       travelDTO.setSuggestionMealsNumber(calculateHoursEstimated(travelDTO, BASE_INTERVAL_FOR_MEALS));
    }


    private void setSuggestionSleepNumberToTravel(TravelDTO travelDTO) {
        travelDTO.setSuggestionSleepNumber(calculateHoursEstimated(travelDTO, BASE_INTERVAL_FOR_SLEEP));
    }



    private int calculateHoursEstimated(TravelDTO travelDTO, int baseHoursForCalculate) {
        double hours = (double) (travelDTO.getAverageTime() /3600000);
        double roundedHours = Math.floor(hours);
        return (int) Math.floor(roundedHours/baseHoursForCalculate);
    }

}
