/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.service;

import com.sg.superherosighting.dao.AddressDao;
import com.sg.superherosighting.dao.HeroDao;
import com.sg.superherosighting.dao.HeroOrganizationBridgeDao;
import com.sg.superherosighting.dao.HeroPowerBridgeDao;
import com.sg.superherosighting.dao.LocationDao;
import com.sg.superherosighting.dao.OrganizationDao;
import com.sg.superherosighting.dao.PowerDao;
import com.sg.superherosighting.dao.SightingDao;
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
public class ServiceLayerImpl implements ServiceLayer{
    
    AddressDao addressDao;
    HeroDao heroDao;
    HeroOrganizationBridgeDao heroOrgBridgeDao;
    HeroPowerBridgeDao heroPowerBridgeDao;
    LocationDao locationDao;
    OrganizationDao orgDao;
    PowerDao powerDao;
    SightingDao sightingDao;
    
    public ServiceLayerImpl(AddressDao addressDao,
            HeroDao heroDao, 
            HeroOrganizationBridgeDao heroOrgBridgeDao,
            HeroPowerBridgeDao heroPowerBridgeDao,
            LocationDao locationDao,
            OrganizationDao orgDao,
            PowerDao powerDao,
            SightingDao sightigDao){
        this.addressDao = addressDao;
        this.heroDao = heroDao;
        this.heroOrgBridgeDao = heroOrgBridgeDao;
        this.heroPowerBridgeDao = heroPowerBridgeDao;
        this.locationDao = locationDao;
        this.orgDao = orgDao;
        this.powerDao = powerDao;
        this.sightingDao = sightigDao;
    }
    
    @Override
    public void assembleHero(Hero hero, String[] powers, Members member ){
        heroDao.addHero(hero);
        for (String power : powers) {
            heroPowerBridgeDao.addHeroPowerBridge(hero.getHeroId(), Integer.parseInt(power));
        }
        if(member.getOrg() != null){
            heroOrgBridgeDao.addMembersBridge(member);
        }
    }
    
    @Override
    public Organization assembleOrganization(Organization org, Address add){
        Address address = addressDao.addAddress(add);
        org.setAddress(address);
        org = orgDao.addOrg(org);
        return org;
    }
    
    @Override
    public void assembleLocation(Location location, Address add){
        Address address = addressDao.addAddress(add);
        location.setAddress(address);
        locationDao.addLocation(location);
    }
    
    @Override
    public void deleteHero(int heroId) {
        List<Power> powers = heroPowerBridgeDao.getPowersByHero(heroId);
        List<Sighting> sightings = sightingDao.getSightingsByHero(heroId);
        List<Members> members = heroOrgBridgeDao.getMembersByHero(heroId);
        if(!powers.isEmpty()){
            for(Power power : powers){
                heroPowerBridgeDao.delHeroPowerBridge(heroId, power.getPowerId());
            }
        }
        if(!sightings.isEmpty()){
            for(Sighting sighting: sightings){
                sightingDao.delSighting(sighting);
            }
        }
        if(!members.isEmpty()){
            for(Members member : members){
                heroOrgBridgeDao.delMembersBridge(member.getMemberId());
            }
        }
        heroDao.deleteHero(heroId);
    }
    
    
    @Override
    public void deleteLocation(int locationId) {
        List<Sighting> sightings = sightingDao.getSightingsByLocation(locationId);
        if(!sightings.isEmpty()){
            for(Sighting sighting : sightings){
                sightingDao.delSighting(sighting);
            }
        }
        locationDao.deleteLocation(locationId);
    }
    
    @Override
    public void delOrg(int orgId) {
        List<Members> members = heroOrgBridgeDao.getMembersByOrg(orgId);
        if(!members.isEmpty()){
            for(Members member : members){
                heroOrgBridgeDao.delMembersBridge(member.getMemberId());
            }
        }
        orgDao.delOrg(orgId);
    }
    
    @Override
    public void delPower(int powerId) {
        List<Hero> heroes = heroPowerBridgeDao.getHeroesByPower(powerId);
        if(!heroes.isEmpty()){
            for(Hero hero : heroes){
                heroPowerBridgeDao.delHeroPowerBridge(hero.getHeroId(), powerId);
            }
        }
        powerDao.delPower(powerId);
    }
    
    @Override
    public List<Members> getMembersByOrg(int orgId){
        return heroOrgBridgeDao.getMembersByOrg(orgId);
    }
    
    @Override
    public Hero updateHero(Hero hero) {
        return heroDao.updateHero(hero);
    }
    
    @Override
    public Sighting updateSighting(Sighting sighting){
        return sightingDao.updateSighting(sighting);
    }

    @Override
    public List<Sighting> getSightingsByLocation(int locationId){
        return sightingDao.getSightingsByLocation(locationId);
    }
    
    @Override
    public Members getHeroOrgRelation(int heroId, int orgId){
        return heroOrgBridgeDao.getHeroOrgRelation(heroId, orgId);
    }
    
    @Override
    public List<Members> getMembersByHero(int heroId){
        return heroOrgBridgeDao.getMembersByHero(heroId);
    }
    
    @Override
    public List<Sighting> getSightingsByHero(int heroId){
        return sightingDao.getSightingsByHero(heroId);
    }
    
    @Override
    public List<Sighting> getAllSightings(){
        return sightingDao.getAllSightings();
    }
    
    @Override
    public List<Sighting> getLastTenSightings(){
        return sightingDao.getLastTenSightings();
    }
    
    @Override
    public Address addAddress(Address address) {
        return addressDao.addAddress(address);
    }

    @Override
    public Address getAddress(int addressId) {
        return addressDao.getAddress(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public Address updateAddress(Address address) {
        return addressDao.updateAddress(address);
    }

    @Override
    public void delAddress(int addressId) {
        addressDao.delAddress(addressId);
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroDao.addHero(hero);
    }

    @Override
    public Hero getHero(int heroId) {
        return heroDao.getHero(heroId);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }


    @Override
    public Members addMembersBridge(Members member) {
        return heroOrgBridgeDao.addMembersBridge(member);
    }

    @Override
    public void delMembersBridge(int memberId) {
        heroOrgBridgeDao.delMembersBridge(memberId);
    }

    @Override
    public List<Hero> getHeroesByOrg(int orgId) {
        return heroOrgBridgeDao.getHeroesByOrg(orgId);
    }

    @Override
    public List<Organization> getOrgsByHero(int heroId) {
        return heroOrgBridgeDao.getOrgsByHero(heroId);
    }

    @Override
    public Members getHeroOrgRelation(int memberId) {
        return heroOrgBridgeDao.getHeroOrgRelation(memberId);
    }

    @Override
    public void delAllMemberBridges() {
        heroOrgBridgeDao.delAllMemberBridges();
    }

    @Override
    public void addHeroPowerBridge(int heroId, int powerId) {
        heroPowerBridgeDao.addHeroPowerBridge(heroId, powerId);
    }

    @Override
    public void delHeroPowerBridge(int heroId, int powerId) {
        heroPowerBridgeDao.delHeroPowerBridge(heroId, powerId);
    }

    @Override
    public List<Hero> getHeroesByPower(int powerId) {
        return heroPowerBridgeDao.getHeroesByPower(powerId);
    }

    @Override
    public List<Power> getPowersByHero(int heroId) {
        return heroPowerBridgeDao.getPowersByHero(heroId);
    }

    @Override
    public HeroPowerBridge getHeroPowerRelation(int heroId, int powerId) {
        return heroPowerBridgeDao.getHeroPowerRelation(heroId, powerId);
    }

    @Override
    public void delAllHPBridges() {
        heroPowerBridgeDao.delAllHPBridges();
    }

    @Override
    public Location addLocation(Location location) {
        return locationDao.addLocation(location);
    }

    @Override
    public Location getLocation(int locationId) {
        return locationDao.getLocation(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location updateLocation(Location location) {
        return locationDao.updateLocation(location);
    }


    @Override
    public Organization addOrg(Organization org) {
        return orgDao.addOrg(org);
    }

    @Override
    public Organization getOrg(int orgId) {
        return orgDao.getOrg(orgId);
    }

    @Override
    public List<Organization> getAllOrgs() {
        return orgDao.getAllOrgs();
    }

    @Override
    public Organization updateOrg(Organization org) {
        return orgDao.updateOrg(org);
    }


    @Override
    public Power addPower(Power power) {
        return powerDao.addPower(power);
    }

    @Override
    public Power getPower(int powerId) {
        return powerDao.getPower(powerId);
    }

    @Override
    public List<Power> getAllPowers() {
        return powerDao.getAllPowers();
    }

    @Override
    public Power updatePower(Power power) {
        return powerDao.updatePower(power);
    }


    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDao.addSighting(sighting);
    }

    @Override
    public void delSighting(Sighting sighting) {
        sightingDao.delSighting(sighting);
    }

    @Override
    public List<Hero> getHeroesByLocation(int locationId) {
        return sightingDao.getHeroesByLocation(locationId);
    }

    @Override
    public List<Location> getLocationsByHero(int heroId) {
        return sightingDao.getLocationsByHero(heroId);
    }

    @Override
    public Sighting getHeroLocationRelationship(int sightingId) {
        return sightingDao.getHeroLocationRelationship(sightingId);
    }

    @Override
    public void delAllSightingBridges() {
        sightingDao.delAllSightingBridges();
    }
    
}
