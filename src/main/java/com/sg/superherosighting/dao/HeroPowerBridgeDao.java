/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.HeroPowerBridge;
import com.sg.superherosighting.model.Power;
import java.util.List;

/**
 *
 * @author feng
 */
public interface HeroPowerBridgeDao {
    public void addHeroPowerBridge(int heroId, int powerId);
    
    public void delHeroPowerBridge(int heroId, int powerId);
        
    public List<Hero> getHeroesByPower(int powerId);
    
    public List<Power> getPowersByHero(int heroId);
    
    public HeroPowerBridge getHeroPowerRelation(int heroId, int powerId);
    
    public void delAllHPBridges();
}
