package com.vidanaestrada.application.implementation;

import com.vidanaestrada.application.TravelService;
import com.vidanaestrada.dto.TravelDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelServiceImp implements TravelService {

    @Override
    public void save(TravelDTO travelDTO) {
        moutTravel(travelDTO);
    }

    private void moutTravel(TravelDTO travelDTO) {
        setEstimatedCostTravel(travelDTO);
        setEstimatedProfitToTravel(travelDTO);
        setEstimatedMealsToTravel(travelDTO);
        setEstimatedStops(travelDTO);
    }

    private void setEstimatedProfitToTravel(TravelDTO travelDTO) {

    }

    private void setEstimatedCostTravel(TravelDTO travelDTO) {
        String responseData = doRequestToFreeTableFreight(travelDTO);

        String constTravel = StringUtils.substringBetween(responseData, "<h2 style=\"margin-top:5px;margin-bottom:5px;\">Total = R$ ", "</h2>");
        constTravel = constTravel.replace(",", "");

        travelDTO.setCost(Double.parseDouble(constTravel));

    }

    private String doRequestToFreeTableFreight(TravelDTO travelDTO) {
        final String uri = "https://www.tabelasdefrete.com.br/api/site27-05-20.php?category=2&type=2&axes=3&distance=100";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    private void setEstimatedMealsToTravel(TravelDTO travelDTO) {


    }


    private void setEstimatedStops(TravelDTO travelDTO) {

    }


}
