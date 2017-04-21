/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */

public class PowerMapper implements RowMapper<com.sg.superherosighting.model.Power> {

    @Override
    public com.sg.superherosighting.model.Power mapRow(ResultSet rs, int i) throws SQLException {
        Power p = new Power();
        p.setPower(rs.getString("power_name"));
        p.setDescription(rs.getString("power_description"));
        p.setPowerId(rs.getInt("power_id"));
        return p;
    }
}
