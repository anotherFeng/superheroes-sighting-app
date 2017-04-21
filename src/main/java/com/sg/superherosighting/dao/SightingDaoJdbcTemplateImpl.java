/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.AddressMapper;
import com.sg.superherosighting.mapper.HeroMapper;
import com.sg.superherosighting.mapper.LocationMapper;
import com.sg.superherosighting.mapper.SightingMapper;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Sighting;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class SightingDaoJdbcTemplateImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (hero_id, location_id, sighting_date, description) "
            + "values(?,?,?,?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sighting_id = ?";

    private static final String SQL_GET_SIGHTING
            = "select * from sighting where sighting_id = ?";

    private static final String SQL_SELECT_SPECIFIC_HERO
            = "select h.hero_id, h.alias, h.first_name, h.last_name, h.description "
            + "from heroes h join sighting s on h.hero_id = s.hero_id "
            + "where s.sighting_id = ?";
    
    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set hero_id = ?, location_id = ?, sighting_date = ?, description = ? "
            + "where sighting_id = ?";

    private static final String SQL_SELECT_SPECIFIC_LOCATION
            = "select l.location_id, l.location_name, l.description, l.address_id, l.latitude, l.longitude "
            + "from location l join sighting s on l.location_id = s.location_id "
            + "where s.sighting_id = ?";

    private static final String SQL_SELECT_ADDRESS_BY_LOCATION
            = "select ad.address_id, ad.street, ad.city, ad.state, ad.country, ad.zip, ad.world "
            + "from address ad join location on ad.address_id = location.address_id "
            + "where location.location_id = ?";

    private static final String SQL_SELECT_LOCATIONS_BY_HERO
            = "select l.location_id, l.location_name, l.description, l.address_id, l.latitude, l.longitude "
            + "from location l join sighting s on hero_id "
            + "where l.location_id = s.location_id and s.hero_id = ?;";
    
    private static final String SQL_SELECT_SIGHTING_BY_HERO
            = "select * "
            + "from sighting s "
            + "where hero_id = ?;";
    
     private static final String SQL_SELECT_SIGHTING_BY_LOCATION
            = "select * "
            + "from sighting s "
            + "where location_id = ?;";
    
    private static final String SQL_SELECT_HEROES_BY_LOCATION
            = "select h.hero_id, h.alias, h.first_name, h.last_name, h.description "
            + "from heroes h join sighting s on location_id "
            + "where h.hero_id = s.hero_id and s.location_id = ?;";
    
    private static final String SQL_SELECT_ALL_SIGHTING
            = "select * from sighting";
    
    private static final String SQL_SELECT_LAST_TEN
            = "select * from sighting order by sighting_id desc limit 10";
    
    @Override
    public Sighting updateSighting(Sighting sighting){
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
                sighting.getDescription(),
                sighting.getSightingId());
        return sighting;
    }
    
    @Override
    public List<Sighting> getSightingsByHero(int heroId){
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_HERO, new SightingMapper(), heroId);
        return assembleSightings(sightings);
    }
    
    @Override
    public List<Sighting> getSightingsByLocation(int locationId){
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_LOCATION, new SightingMapper(), locationId);
        return assembleSightings(sightings);
    }
    
    @Override
    public List<Sighting> getLastTenSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_LAST_TEN, new SightingMapper());
        return assembleSightings(sightings);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingDate().toString(),
                sighting.getDescription()
        );
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
        return sighting;
    }

    @Override
    public void delSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sighting.getSightingId());
    }

    @Override
    public List<Hero> getHeroesByLocation(int locationId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_HEROES_BY_LOCATION, new HeroMapper(), locationId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Location> getLocationsByHero(int heroId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HERO, new LocationMapper(), heroId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Sighting getHeroLocationRelationship(int sightingId) {
        try {
            Sighting sighting = new Sighting();
            Location location = new Location();
            sighting = jdbcTemplate.queryForObject(SQL_GET_SIGHTING, new SightingMapper(), sightingId);
            location = jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_LOCATION, new LocationMapper(), sightingId);
            location.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION, new AddressMapper(), location.getLocationId()));
            sighting.setLocation(location);
            sighting.setHero(jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_HERO, new HeroMapper(), sightingId));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void delAllSightingBridges() {
        jdbcTemplate.update("truncate sighting");
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings =  jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING, new SightingMapper());
        return assembleSightings(sightings);
    }

    private List<Sighting> assembleSightings(List<Sighting> sightings){
        List<Sighting> newSightings = new ArrayList<>();
        for(Sighting currentSighting : sightings){
            Sighting sighting = new Sighting();
            Location location = new Location();
            sighting = jdbcTemplate.queryForObject(SQL_GET_SIGHTING, new SightingMapper(), currentSighting.getSightingId());
            location = jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_LOCATION, new LocationMapper(), currentSighting.getSightingId());
            location.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION, new AddressMapper(), location.getLocationId()));
            sighting.setLocation(location);
            sighting.setHero(jdbcTemplate.queryForObject(SQL_SELECT_SPECIFIC_HERO, new HeroMapper(), currentSighting.getSightingId())); 
            newSightings.add(sighting);
        }
        return newSightings;
    }
}
