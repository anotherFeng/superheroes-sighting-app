/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public class SightingMapper implements RowMapper<Sighting>{

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
        Sighting s = new Sighting();
        
        s.setSightingId(rs.getInt("sighting_id"));
        s.setSightingDate(rs.getTimestamp("sighting_date").toLocalDateTime());
        s.setDescription(rs.getString("description"));
        return s;
    }
}
