/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import java.util.List;

/**
 *
 * @author feng
 */
public interface HeroDao {
    public Hero addHero(Hero hero);
    
    public Hero getHero(int heroId);
    
    public List<Hero> getAllHeroes();
    
    public Hero updateHero(Hero hero);
    
    public void deleteHero(int heroId);

}
