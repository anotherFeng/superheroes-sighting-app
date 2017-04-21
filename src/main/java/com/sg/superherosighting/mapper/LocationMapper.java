/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public class LocationMapper implements RowMapper<Location>{
    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException{
        Location l = new Location();
        l.setLocationId(rs.getInt("location_id"));
        l.setLocationName(rs.getString("location_name"));
        l.setDescription(rs.getString("description"));
        l.setLatitude(rs.getBigDecimal("latitude"));
        l.setLongitude(rs.getBigDecimal("longitude"));
        return l;
    }

}
