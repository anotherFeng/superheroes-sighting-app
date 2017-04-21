/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.mapper;

import com.sg.superherosighting.model.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author feng
 */
public final class HeroMapper implements RowMapper<Hero> {
    @Override
    public Hero mapRow(ResultSet rs, int i) throws SQLException{
        Hero hero = new Hero();
        hero.setAlias(rs.getString("alias"));
        hero.setFirstName(rs.getString("first_name"));
        hero.setLastName(rs.getString("last_name"));
        hero.setDescription(rs.getString("description"));
        hero.setHeroId(rs.getInt("hero_id"));
        return hero;
    }
}
