/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Power;
import java.util.List;

/**
 *
 * @author feng
 */
public interface PowerDao {
    public Power addPower(Power power);
    
    public Power getPower(int powerId);
    
    public List<Power> getAllPowers();
    
    public Power updatePower(Power power);
    
    public void delPower(int powerId);
}
