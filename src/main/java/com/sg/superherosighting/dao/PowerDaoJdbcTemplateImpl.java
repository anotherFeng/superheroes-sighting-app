/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.PowerMapper;
import com.sg.superherosighting.model.Power;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class PowerDaoJdbcTemplateImpl implements PowerDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_POWER
            = "insert into power (power_name, power_description) "
            + "values(?,?)";
    
    private static final String SQL_SELECT_POWER
            = "select * from power where power_id = ?";
    
    private static final String SQL_SELECT_ALL_POWERS
            = "select * from power";
    
    private static final String SQL_UPDATE_POWER
            = "update power set power_name = ?, power_description = ? "
            + "where power_id = ?";
    
    private static final String SQL_DELETE_POWER
            = "delete from power where power_id = ?";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Power addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPower(),
                power.getDescription());
        int powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        power.setPowerId(powerId);
        return power;
    }

    @Override
    public Power getPower(int powerId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMapper(), powerId);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    public Power updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getPower(),
                power.getDescription(),
                power.getPowerId());
        return power;
    }

    @Override
    public void delPower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER,
                powerId);
    }
    

    
}
