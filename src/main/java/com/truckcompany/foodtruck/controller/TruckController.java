package com.truckcompany.foodtruck.controller;

import com.truckcompany.foodtruck.model.TruckCriteria;
import com.truckcompany.foodtruck.model.TruckData;
import com.truckcompany.foodtruck.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Returns read-only food truck information
 */
@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    TruckService truckService;

    @GetMapping("/ping")
    public String ping() {return "successful";}

    /**
     * Return food truck information based on criteria passed.
     * for example: rice%20plates will return food truck data that serves rice dishes
     *
     * Note: This is a simple query and does not support parenthesis for compound statements, ANDs or ORs.
     * @param criteria The string criteria containing the desired food items
     * @return Returns truck data that contains the desired food items
     */
    @GetMapping("/byfooditem/{criteria}")
    public List<TruckData> byFoodItem(@PathVariable("criteria") String criteria) {
        TruckCriteria truckCriteria = new TruckCriteria();
        truckCriteria.setFoodItems(criteria);
        return truckService.getTrucksByCriteria(truckCriteria);
    }

    /**
     * Return food truck information based on criteria passed.
     *
     * @param truckCriteria Criteria that is used to filter data as a series of conditions, similar to JPA filtering
     * @return Returns truck data that contains the desired criteria
     */
    @PostMapping("/findtrucks")
    public List<TruckData> byFoodItem(@RequestBody TruckCriteria truckCriteria) {
        return truckService.getTrucksByCriteria(truckCriteria);
    }
}
