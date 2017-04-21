/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Sighting;
import java.util.List;

/**
 *
 * @author feng
 */
public interface SightingDao {
    public Sighting addSighting(Sighting sighting);
    
    public void delSighting(Sighting sighting);
    
    public Sighting updateSighting(Sighting sighting);
    
    public List<Hero> getHeroesByLocation(int locationId);
    
    public List<Location> getLocationsByHero(int heroId);
    
    public Sighting getHeroLocationRelationship(int sightingId);
    
    public void delAllSightingBridges();
    
    public List<Sighting> getSightingsByHero(int heroId);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getLastTenSightings();
    
    public List<Sighting> getSightingsByLocation(int locationId);
}
