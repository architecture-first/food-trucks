package com.truckcompany.foodtruck.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Truck Data Definition based on schema at: https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat
 */
@Data
public class TruckData {
    private Long locationid;                // Location id of facility
    private String applicant;               // Name of permit holder
    private String facilityType;            // Type of facility permitted: truck or push cart
    private Long cnn;                       // CNN of street segment or intersection location
    private String locationDescription;     // Description of street segment or intersection location
    private String address;                 // Address
    private String blocklot;                // Block lot (parcel) number
    private String block;                   // Block number
    private String lot;                     // Lot number
    private String permit;                  // Permit number
    private String status;                  // Status of permit: Approved or Requested
    private String foodItems;               // A description of food items sold
    private BigDecimal x;                   // CA State Plane III
    private BigDecimal y;                   // CA State Plane III
    private BigDecimal latitude;            // WGS84
    private BigDecimal longitude;           // WGS84
    private String schedule;                // URL link to Schedule for facility
    private String dayshours;               // abbreviated text of schedule
    private LocalDateTime noiSent;              // Date notice of intent sent
    private LocalDateTime approved;             // Date permit approved by DPW
    private String received;                // Date permit application received from applicant (format YYYYMMDD)
    private Long priorPermit;               // prior existing permit with SFFD
    private LocalDateTime expirationDate;       // Date permit expires
    private String location;                // Location formatted for mapping
    private String firePreventionDistricts;
    private String policeDistricts;
    private String supervisorDistricts;
    private String zipCodes;
    private String neighborhoods;
}
