package com.truckcompany.foodtruck.service;

import com.truckcompany.foodtruck.model.TruckCriteria;
import com.truckcompany.foodtruck.model.TruckData;
import com.truckcompany.foodtruck.repository.FoodTruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TruckService {

    @Autowired
    FoodTruckRepository foodTruckRepository;

    public List<TruckData> getTrucksByCriteria(TruckCriteria criteria) {
        return foodTruckRepository.getRawData(criteria);
    }
}
