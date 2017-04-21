/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public class OrganizationMapper implements RowMapper<Organization> {
    @Override
    public Organization mapRow(ResultSet rs, int i) throws SQLException{
        Organization org = new Organization();
        
        org.setOrgId(rs.getInt("organization_id"));
        org.setOrgName(rs.getString("organization_name"));
        org.setDescription(rs.getString("description"));
        return org;
    }
}
