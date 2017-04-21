/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.mapper.HeroMapper;
import com.sg.superherosighting.model.Hero;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author feng
 */
public class HeroDaoJdbcTemplateImpl implements HeroDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_HERO
            = "insert into heroes (alias, first_name, last_name, description) "
            + "values(?,?,?,?)";
    
    private static final String SQL_SELECT_HERO
            = "select * from heroes where hero_id = ?";
    
    private static final String SQL_SELECT_ALL_HEROES
            = "select * from heroes";
    
    private static final String SQL_UPDATE_HERO
            = "update heroes set alias = ?, first_name = ?, last_name = ?, description = ? "
            + "where hero_id = ?";
    
    private static final String SQL_DELETE_HERO
            = "delete from heroes where hero_id = ?";
    
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly= false)
    public Hero addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getAlias(),
                hero.getFirstName(),
                hero.getLastName(),
                hero.getDescription());
        int heroId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(heroId);
        return hero;
    }

    @Override
    public Hero getHero(int heroId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO, new HeroMapper(), heroId);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROES, new HeroMapper());
    }

    @Override
    public Hero updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getAlias(),
                hero.getFirstName(),
                hero.getLastName(),
                hero.getDescription(),
                hero.getHeroId());
        return hero;
    }

    @Override
    public void deleteHero(int heroId) {
        jdbcTemplate.update(SQL_DELETE_HERO, heroId);
    }
}
