/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Location;
import java.util.List;

/**
 *
 * @author feng
 */
public interface LocationDao {
    public Location addLocation(Location location);
    
    public Location getLocation(int locationId);
    
    public List<Location> getAllLocations();
    
    public Location updateLocation(Location location);
    
    public void deleteLocation(int locationId);
    
}
