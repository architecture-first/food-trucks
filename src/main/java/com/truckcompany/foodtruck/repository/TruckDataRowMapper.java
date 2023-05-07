package com.truckcompany.foodtruck.repository;

import com.architecture.exception.ApplicationException;
import com.truckcompany.foodtruck.model.TruckData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TruckDataRowMapper implements RowMapper<TruckData> {
@Override
    public TruckData mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            TruckData rowData = new TruckData();
            rowData.setLocationid(rs.getLong("locationid"));
            rowData.setApplicant(rs.getString("applicant"));
            rowData.setFacilityType(rs.getString("facilityType"));
            rowData.setCnn(rs.getLong("cnn"));
            rowData.setLocationDescription(rs.getString("locationDescription"));
            rowData.setAddress(rs.getString("address"));
            rowData.setBlocklot(rs.getString("blocklot"));
            rowData.setBlock(rs.getString("block"));
            rowData.setLot(rs.getString("lot"));
            rowData.setPermit(rs.getString("permit"));
            rowData.setStatus(rs.getString("status"));
            rowData.setFoodItems(rs.getString("foodItems"));

            String decimalString = rs.getString("x");
            if (StringUtils.hasText(decimalString)) {
                rowData.setX(rs.getBigDecimal("x"));
            }

            decimalString = rs.getString("y");
            if (StringUtils.hasText(decimalString)) {
                rowData.setY(rs.getBigDecimal("y"));
            }

            decimalString = rs.getString("latitude");
            if (StringUtils.hasText(decimalString)) {
                rowData.setLatitude(rs.getBigDecimal("latitude"));
            }

            decimalString = rs.getString("longitude");
            if (StringUtils.hasText(decimalString)) {
                rowData.setLongitude(rs.getBigDecimal("longitude"));
            }

            rowData.setSchedule(rs.getString("schedule"));
            rowData.setDayshours(rs.getString("dayshours"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

            String dateString = rs.getString("noiSent");
            if (StringUtils.hasText(dateString)) {
                rowData.setNoiSent(LocalDateTime.parse(dateString, formatter));
            }

            dateString = rs.getString("approved");
            if (StringUtils.hasText(dateString)) {
                rowData.setApproved(LocalDateTime.parse(dateString, formatter));
            }

            rowData.setReceived(rs.getString("received"));
            rowData.setPriorPermit(rs.getLong("priorPermit"));

            dateString = rs.getString("expirationDate");
            if (StringUtils.hasText(dateString)) {
                rowData.setExpirationDate(LocalDateTime.parse(dateString, formatter));
            }

            rowData.setLocation(rs.getString("location"));

            return rowData;
        }
        catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
