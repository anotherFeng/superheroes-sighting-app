/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.AddressMapper;
import com.sg.superherosighting.mapper.LocationMapper;
import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Location;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class LocationDaoJdbcTemplateImpl implements LocationDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_LOCATION
            = "insert into location (location_name, description, address_id, latitude, longitude) "
            + "values(?,?,?,?,?)";
    
    private static final String SQL_SELECT_LOCATION
            = "select * from location where location_id = ?";
    
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";
    
    private static final String SQL_UPDATE_LOCATION
            = "update location set location_name = ?, description = ?, address_id =?, latitude = ?, longitude = ? "
            + "where location_id = ?";
    
    private static final String SQL_DELETE_LOCATION
            = "delete from location where location_id = ?";
    
    private static final String SQL_SELECT_ADDRESS_BY_LOCATION
            = "select ad.address_id, ad.street, ad.city, ad.state, ad.country, ad.zip, ad.world " 
            + "from address ad join location on ad.address_id = location.address_id "
            + "where location.location_id = ?";
    
       
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress().getAddressId(),
                location.getLatitude(),
                location.getLongitude());
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
        return location;
    }

    @Override
    public Location getLocation(int locationId) {
        try{
            Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
            location.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION, new AddressMapper(), location.getLocationId()));
            return location;
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations =  jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        for(Location location : locations){
            Address address = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION, new AddressMapper(), location.getLocationId());
            location.setAddress(address);
        }
        return locations;
    }

    @Override
    public Location updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress().getAddressId(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
        return location;
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    
}
