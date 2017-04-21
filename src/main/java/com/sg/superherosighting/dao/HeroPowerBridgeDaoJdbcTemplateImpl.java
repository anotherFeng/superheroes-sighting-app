/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.HeroMapper;
import com.sg.superherosighting.mapper.PowerMapper;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.HeroPowerBridge;
import com.sg.superherosighting.model.Power;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author feng
 */
public class HeroPowerBridgeDaoJdbcTemplateImpl implements HeroPowerBridgeDao{

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_HERO_POWER
            = "insert into hero_power_bridge (hero_id, power_id) values(?,?)";
            
    private static final String SQL_DELETE_HERO_POWER
            = "delete from hero_power_bridge where (hero_id = ? and power_id =?)";
    
    private static final String SQL_SELECT_HERO
            = "select * from heroes where hero_id = ?";
    
    private static final String SQL_SELECT_HERO_BY_POWER_ID
            = "select h.hero_id, h.alias, h.first_name, h.last_name, h.description "
            + "from heroes h join hero_power_bridge hp on power_id "
            + "where h.hero_id = hp.hero_id and hp.power_id = ?;";
    
    private static final String SQL_SELECT_POWER
            = "select * from power where power_id = ?";
            
    private static final String SQL_SELECT_POWER_BY_HERO_ID
            = "select p.power_id, p.power_name, p.power_description "
            + "from power p join hero_power_bridge hp on hero_id "
            + "where p.power_id = hp.power_id and hp.hero_id = ?;";
    
    private static final String SQL_SELECT_POWER_BY_HERO_ID_EXCLUDE
            = "select p.power_id, p.power_name, p.power_description "
            + "from power p join hero_power_bridge hp on p.power_id = hp.power_id"
            + "where not hp.hero_id = ? ";
    
    private static final String SQL_SELECT_ALL_POWERS
            = "select * from power";
    
    private static final String SQL_SELECT_HERO_POWER_BRIDGE_BY_HERO_ID_AND_POWER_ID
            = "select * from hero_power_bridge where (hero_id = ? and power_id = ?)";
    

    @Override
    public void addHeroPowerBridge(int heroId, int powerId) {
        jdbcTemplate.update(SQL_INSERT_HERO_POWER, heroId, powerId);
    }

    @Override
    public void delHeroPowerBridge(int heroId, int powerId) {
        jdbcTemplate.update(SQL_DELETE_HERO_POWER, heroId, powerId);
    }

    @Override
    public List<Hero> getHeroesByPower(int powerId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_HERO_BY_POWER_ID, new HeroMapper(), powerId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Power> getPowersByHero(int heroId) {
        try{
            return jdbcTemplate.query(SQL_SELECT_POWER_BY_HERO_ID, new PowerMapper(), heroId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public HeroPowerBridge getHeroPowerRelation(int heroId, int powerId) {
        try{
            HeroPowerBridge hpb = new HeroPowerBridge();
            hpb.setHero(jdbcTemplate.queryForObject(SQL_SELECT_HERO, new HeroMapper(), heroId));
            hpb.setPower(jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMapper(), powerId));
            return hpb;
                } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void delAllHPBridges() {
        jdbcTemplate.update("truncate hero_power_bridge");
    }
    
}
