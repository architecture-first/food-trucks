package com.truckcompany.foodtruck.repository;

import com.truckcompany.foodtruck.model.TruckCriteria;
import com.truckcompany.foodtruck.model.TruckData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Returns Food Truck related data
 */
@Repository
@Slf4j
public class FoodTruckRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO - Change this stem to include actual column names if time permits
    private static String SELECT_STEM = "SELECT * FROM Mobile_Food_Facility_Permit";
    private static String WHERE_STEM =  "WHERE facilitytype = 'Truck' and status = 'APPROVED'";

    /**
     * Return raw data of result set
     * TODO - If time is available the technique would consist of a prepared statement if the driver accepted it.
     * @return
     */
    public List<TruckData> getRawData(TruckCriteria truckCriteria) {
        // TODO - If there was more time this would be converted into a Java stream
        List<TruckData> rawData = jdbcTemplate.query(generateQuery(truckCriteria),
                new TruckDataRowMapper());

        return rawData;
    }


    /**
     * Generates a query based on the input
     * @return a SQL query string
     */
    private String generateQuery(TruckCriteria truckCriteria) {
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT_STEM)
                .append(" ")
                .append(WHERE_STEM);

        String criteria = truckCriteria.toFilterClause();
        if (StringUtils.hasText(criteria)) {
            sb.append(criteria);
        }

        return sb.toString();
    }
}
