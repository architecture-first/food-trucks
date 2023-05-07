package com.truckcompany.foodtruck;

import com.truckcompany.foodtruck.model.TruckCriteria;
import com.truckcompany.foodtruck.model.TruckData;
import com.truckcompany.foodtruck.repository.FoodTruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@SpringBootTest
class FoodTruckApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FoodTruckRepository foodTruckRepository;


    /**
     * Simple test to ensure that the file is readable and queryable
     */
    @Test
    public void testReadCsv() {
        AtomicLong count = new AtomicLong(0L);
        jdbcTemplate.query("SELECT * FROM Mobile_Food_Facility_Permit WHERE facilitytype = 'Truck'", rs -> {
            String applicant = rs.getString("applicant");
            String facilitytype = rs.getString("facilitytype");
            log.info(String.format("applicant: %s; facilitytype: %s", applicant, facilitytype));
            count.getAndAdd(1L);
        });

        Assert.isTrue(count.get() > 0, "results must exist");
    }

    /**
     * Test loading of data through the repository with no criteria
     */
    @Test
    public void testRepositoryQuery() {
        List<TruckData> data = foodTruckRepository.getRawData(new TruckCriteria());

        Assert.isTrue(!data.isEmpty(), "data should not be empty");
    }

    /**
     * Test loading of data through the repository with criteria
     */
    @Test
    public void testRepositoryQueryByFoodItem() {
        TruckCriteria criteria = new TruckCriteria();
        criteria.setFoodItems("hot dogs");
        List<TruckData> data = foodTruckRepository.getRawData(criteria);

        Assert.isTrue(!data.isEmpty(), "data should not be empty");
    }


}
