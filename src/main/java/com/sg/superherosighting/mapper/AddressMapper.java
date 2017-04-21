/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public class AddressMapper implements RowMapper<Address>{

    @Override
    public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address add = new Address();
            add.setAddressId(rs.getInt("address_id"));
            add.setAddress(rs.getString("street"));
            add.setCity(rs.getString("city"));
            add.setState(rs.getString("state"));
            add.setCountry(rs.getString("country"));
            add.setZip(rs.getString("zip"));
            add.setWorld(rs.getString("world"));
            return add;
    }


}
