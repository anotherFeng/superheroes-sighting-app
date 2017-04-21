/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.service;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.HeroPowerBridge;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Members;
import com.sg.superherosighting.model.Organization;
import com.sg.superherosighting.model.Power;
import com.sg.superherosighting.model.Sighting;
import java.util.List;

/**
 *
 * @author feng
 */
public interface ServiceLayer {
    
    public List<Members> getMembersByOrg(int orgId);
    
    public Sighting updateSighting(Sighting sighting);
    
    public Members getHeroOrgRelation(int heroId, int orgId);
    
    public List<Sighting> getSightingsByLocation(int orgId);
    
    public List<Members> getMembersByHero(int heroId);
    
    public List<Sighting> getSightingsByHero(int heroId);
        
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getLastTenSightings();
    
    public void assembleHero(Hero hero, String[] powers, Members member);
    
    public Organization assembleOrganization(Organization org, Address add);
    
    public void assembleLocation(Location location, Address add);

    public Address addAddress(Address address);

    public Address getAddress(int addressId);

    public List<Address> getAllAddresses();

    public Address updateAddress(Address address);

    public void delAddress(int addressId);

    public Hero addHero(Hero hero);

    public Hero getHero(int heroId);

    public List<Hero> getAllHeroes();

    public Hero updateHero(Hero hero);

    public void deleteHero(int heroId);

    public Members addMembersBridge(Members member);

    public void delMembersBridge(int memberId);

    public List<Hero> getHeroesByOrg(int orgId);

    public List<Organization> getOrgsByHero(int heroId);

    public Members getHeroOrgRelation(int memberId);

    public void delAllMemberBridges();

    public void addHeroPowerBridge(int heroId, int powerId);

    public void delHeroPowerBridge(int heroId, int powerId);

    public List<Hero> getHeroesByPower(int powerId);

    public List<Power> getPowersByHero(int heroId);

    public HeroPowerBridge getHeroPowerRelation(int heroId, int powerId);

    public void delAllHPBridges();

    public Location addLocation(Location location);

    public Location getLocation(int locationId);

    public List<Location> getAllLocations();

    public Location updateLocation(Location location);

    public void deleteLocation(int locationId);

    public Organization addOrg(Organization org);

    public Organization getOrg(int orgId);

    public List<Organization> getAllOrgs();

    public Organization updateOrg(Organization org);

    public void delOrg(int orgId);

    public Power addPower(Power power);

    public Power getPower(int powerId);

    public List<Power> getAllPowers();

    public Power updatePower(Power power);

    public void delPower(int powerId);

    public Sighting addSighting(Sighting sighting);

    public void delSighting(Sighting sighting);

    public List<Hero> getHeroesByLocation(int locationId);

    public List<Location> getLocationsByHero(int heroId);

    public Sighting getHeroLocationRelationship(int sightingId);

    public void delAllSightingBridges();


}
