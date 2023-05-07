package com.truckcompany.foodtruck.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Truck Data Definition based on schema at: https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat
 */
@Data
public class TruckCriteria {
    private String applicant;               // Name of permit holder
    private String facilityType;            // Type of facility permitted: truck or push cart
    private String locationDescription;     // Description of street segment or intersection location
    private String address;                 // Address
    private String blocklot;                // Block lot (parcel) number
    private String block;                   // Block number
    private String lot;                     // Lot number
    private String permit;                  // Permit number
    private String status;                  // Status of permit: Approved or Requested
    private String foodItems;               // A description of food items sold

    /**
     * Return filter criteria based on existence of values
     * @return
     */

    // TODO - test each scenario (time permitting)
    public String toFilterClause() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.hasText(applicant)) {
            sb.append(" and ").append("applicant like '%").append(sqlInjectionHandler(applicant)).append("%'");
        }
        if (StringUtils.hasText(facilityType)) {
            sb.append(" and ").append("facilityType like '%").append(sqlInjectionHandler(facilityType)).append("%'");
        }
        if (StringUtils.hasText(locationDescription)) {
            sb.append(" and ").append("locationDescription like '%").append(sqlInjectionHandler(locationDescription)).append("%'");
        }
        if (StringUtils.hasText(address)) {
            sb.append(" and ").append("address like '%").append(sqlInjectionHandler(address)).append("%'");
        }
        if (StringUtils.hasText(blocklot)) {
            sb.append(" and ").append("blocklot = '").append(sqlInjectionHandler(blocklot)).append("'");
        }
        if (StringUtils.hasText(block)) {
            sb.append(" and ").append("block = '").append(sqlInjectionHandler(block)).append("'");
        }
        if (StringUtils.hasText(lot)) {
            sb.append(" and ").append("lot = '").append(sqlInjectionHandler(lot)).append("'");
        }
        if (StringUtils.hasText(permit)) {
            sb.append(" and ").append("permit = '").append(sqlInjectionHandler(permit)).append("'");
        }
        if (StringUtils.hasText(status)) {
            sb.append(" and ").append("status = '").append(sqlInjectionHandler(status)).append("'");
        }
        if (StringUtils.hasText(foodItems)) {
            String items = sqlInjectionHandler(foodItems);
            sb.append(" and ").append("(");
            sb.append("foodItems like '%").append(items).append("%'");
            sb.append("and foodItems not like '%except for ").append(items).append("%'");
            sb.append(")");
        }

        return sb.toString();
    }

    /**
     * Perform poor man's SQL Injection prevention (TODO - use prepared statements if time permits for more thorough protection)
     * @param field
     * @return
     */
    private String sqlInjectionHandler(String field)
    {
        if ( StringUtils.hasText(field) ) {
            String val = field
                    .replace( "\\", " " )
                    .replace( "\"", " " )
                    .replace( "\'", " " )
                    .replace( "\t", " " )
                    .replace( "\r", " " )
                    .replace( "\n", " " )
                    .trim();
            return val;
        }

        return field;
    }
}
