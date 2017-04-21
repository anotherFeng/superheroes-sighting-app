/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.AddressMapper;
import com.sg.superherosighting.mapper.OrganizationMapper;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Organization;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_ORG
            = "insert into organization (organization_name, description, address_id) "
            + "values(?,?,?)";
    
    private static final String SQL_SELECT_ORG
            = "select * from organization where organization_id = ?";
    
    private static final String SQL_SELECT_ALL_ORGS
            = "select * from organization";
    
    private static final String SQL_UPDATE_ORG
            = "update organization set organization_name = ?, description = ?, address_id = ?"
            + "where organization_id = ?";
    
    private static final String SQL_DELETE_ORG
            = "delete from organization where organization_id = ?";

    private static final String SQL_SELECT_ADDRESS_BY_ORG
        = "select ad.address_id, ad.street, ad.city, ad.state, ad.country, ad.zip, ad.world " 
        + "from address ad join organization on ad.address_id = organization.address_id "
        + "where organization.organization_id = ?";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrg(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORG,
                org.getOrgName(),
                org.getDescription(),
                org.getAddress().getAddressId()
                );
        int orgId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        org.setOrgId(orgId);
        return org;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrg(int orgId) {
        try{
            Organization org=  jdbcTemplate.queryForObject(SQL_SELECT_ORG, new OrganizationMapper(), orgId);
            org.setAddress(jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_ORG, new AddressMapper(), orgId));
            return org;
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
        
    }

    @Override
    public List<Organization> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGS, new OrganizationMapper());
    }

    @Override
    public Organization updateOrg(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getOrgId(),
                org.getOrgName(),
                org.getDescription(),
                org.getAddress().getAddress()
                );
        return org;
    }

    @Override
    public void delOrg(int orgId) {
        jdbcTemplate.update(SQL_DELETE_ORG, orgId);
    }

    @Override
    public List<Hero> getHeroesByOrg(int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
