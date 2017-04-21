/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.AddressMapper;
import com.sg.superherosighting.model.Address;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class AddressDaoJdbcTemplateImpl implements AddressDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_ADDRESS
            = "insert into address (street, city, state, country, zip, world) "
            + "values (?,?,?,?,?,?)";
    
    private static final String SQL_SELECT_ADDRESS
            = "select * from address where address_id = ?";
    
    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from address";
    
    private static final String SQL_UPDATE_ADDRESS
            = "update address set street = ?, city = ?, state = ?, country = ?, zip = ?, world = ? "
            + "where address_id = ?";
            
    private static final String SQL_DELETE_ADDRESS
            = "delete from address where address_id = ?";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly= false)
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZip(),
                address.getWorld()
                );
        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        address.setAddressId(addressId);
        return address;
    }

    @Override
    public Address getAddress(int addressId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS, new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    @Override
    public Address updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZip(),
                address.getWorld(),
                address.getAddressId()
                );
        return address;
    }

    @Override
    public void delAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }
    
    
}
