/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Members;
import com.sg.superherosighting.model.Organization;
import java.util.List;

/**
 *
 * @author feng
 */
public interface HeroOrganizationBridgeDao {
    public Members addMembersBridge(Members member);
    
    public List<Members> getMembersByOrg(int orgId);
    
    public void delMembersBridge(int memberId);
    
    public void delMembersBridge(int heroId, int orgId);
            
    public List<Hero> getHeroesByOrg(int orgId);
    
    public List<Organization> getOrgsByHero(int heroId);
    
    public Members getHeroOrgRelation(int memberId);
    
    public void delAllMemberBridges();
    
    public List<Members> getMembersByHero(int heroId);
    
    public Members getHeroOrgRelation(int heroId, int orgId);
    
}
