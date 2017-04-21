/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Organization;
import java.util.List;

/**
 *
 * @author feng
 */
public interface OrganizationDao {
    public Organization addOrg(Organization org);
    
    public Organization getOrg(int orgId);
    
    public List<Organization> getAllOrgs();
    
    public Organization updateOrg(Organization org);
    
    public void delOrg(int orgId);
    
    public List<Hero> getHeroesByOrg(int orgId);
    
    
}
