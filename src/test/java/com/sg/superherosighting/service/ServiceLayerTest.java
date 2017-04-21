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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author feng
 */
public class ServiceLayerTest {
    
    ServiceLayer service;
    Address address = new Address();
    Power power = new Power();
    Hero hero = new Hero();
    Organization org = new Organization();
    BigDecimal lat = new BigDecimal("40.758895");
    BigDecimal lon = new BigDecimal("73.985131").negate();
    Location location = new Location();
    Members member = new Members();
    Sighting sighting = new Sighting();
    HeroPowerBridge hpb = new HeroPowerBridge();

    
    public ServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ac.getBean("service", ServiceLayer.class);
        
        service.delAllSightingBridges();
        service.delAllMemberBridges();
        service.delAllHPBridges();

        List<Organization> orgs = service.getAllOrgs();
        orgs.forEach((o) -> {
            service.delOrg(o.getOrgId());
        });
        List<Hero> heroList = service.getAllHeroes();
        for(Hero h : heroList){
            service.deleteHero(hero.getHeroId());
        }
        List<Location> l = service.getAllLocations();
        for(Location loc: l){
            service.deleteLocation(loc.getLocationId());
        }
        List<Address> addresses = service.getAllAddresses();
        for(Address ad : addresses){
            service.delAddress(ad.getAddressId());
        }
        List<Power> powerList = service.getAllPowers();
        for(Power currentPower: powerList){
            service.delPower(currentPower.getPowerId());
        }
        
        address.setAddress("401 S Main street");
        address.setCity("Akron");
        address.setState("OH");
        address.setCountry("USA");
        address.setZip("44311");
        address.setWorld("Earth");
        service.addAddress(address);
        
        power.setPower("Water!");
        power.setDescription("Breath Water");
        service.addPower(power);
        
        hero.setAlias("Thualk");
        hero.setFirstName("Thuan");
        hero.setLastName("Huynh");
        hero.setDescription("Strong!");
        service.addHero(hero);
        
        
        org.setOrgName("Avenger");
        org.setDescription("blah blah");
        org.setAddress(address);
        service.addOrg(org);
        
        location.setLocationName("Stark Tower");
        location.setDescription("Tony Stark's Home");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAddress(address);
        service.addLocation(location);

        hpb.setHero(hero);
        hpb.setPower(power);
        service.addHeroPowerBridge(hero.getHeroId(), power.getPowerId());
        
        member.setOrg(org);
        member.setHero(hero);
        member.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        service.addMembersBridge(member);
        
        sighting.setLocation(location);
        sighting.setHero(hero);
        sighting.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        sighting.setDescription("blah blah");
        service.addSighting(sighting);
    }
    
    @After
    public void tearDown() {
        service.delAllSightingBridges();
        service.delAllMemberBridges();
        service.delAllHPBridges();

        List<Organization> orgs = service.getAllOrgs();
        for(Organization org: orgs){
            service.delOrg(org.getOrgId());
        }
        List<Hero> heroList = service.getAllHeroes();
        for(Hero hero : heroList){
            service.deleteHero(hero.getHeroId());
        }
        List<Location> locs = service.getAllLocations();
        for(Location loc: locs){
            service.deleteLocation(loc.getLocationId());
        }
        List<Address> addresses = service.getAllAddresses();
        for(Address ad : addresses){
            service.delAddress(ad.getAddressId());
        }
        List<Power> powerList = service.getAllPowers();
        for(Power currentPower: powerList){
            service.delPower(currentPower.getPowerId());
        }
    }

    @Test
    public void testAssembleHero(){
        Hero hero2 = new Hero();
        hero2.setAlias("Thualk");
        hero2.setFirstName("Thuan");
        hero2.setLastName("Huynh");
        hero2.setDescription("Strong!");
        
        Power power2 = new Power();
        power2.setPower("Lightning!");
        power2.setDescription("Breath Water");
        service.addPower(power2);
        
        Power power3 = new Power();
        power3.setPower("Fire!");
        power3.setDescription("Breath Water");
        service.addPower(power3);

        Power power4 = new Power();
        power4.setPower("Earth!");
        power4.setDescription("Breath Water");
        service.addPower(power4);
        
        Organization org2 = new Organization();
        org2.setOrgName("Avenger2");
        org2.setDescription("blah blah");
        org2.setAddress(address);
        service.addOrg(org2);
        
        Members member2 = new Members();
        member2.setHero(hero2);
        member2.setOrg(org2);
        member2.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member2.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        
        String[] powerIds = {Integer.toString(power2.getPowerId()), 
            Integer.toString(power3.getPowerId()),
            Integer.toString(power4.getPowerId()),
            Integer.toString(power.getPowerId())};
        
        service.assembleHero(hero2, powerIds, member2);
        List<Power> powerList = service.getPowersByHero(hero2.getHeroId());
        assertEquals(4, powerList.size());
        
        List<Organization> organizationList = service.getOrgsByHero(hero2.getHeroId());
        assertEquals(1, organizationList.size());
        
        List<Hero> heroes = service.getHeroesByPower(power.getPowerId());
        assertEquals(2, heroes.size());
        
        Hero newHero = service.getHero(hero2.getHeroId());
        assertEquals(newHero, hero2);
    }

    @Test
    public void assembleOrganizatio(){
        Organization org2 = new Organization();
        org2.setOrgName("Avenger2");
        org2.setDescription("blah blah");
//        org2.setAddress(address);
//        service.addOrg(org2);
        
        service.assembleOrganization(org2, address);
        Organization fromDao = service.getOrg(org2.getOrgId());
        assertEquals(org2.getAddress().getAddressId(), fromDao.getAddress().getAddressId());
    }
    
    @Test
    public void assembleLocation(){
        Location loc2 = new Location();
        loc2.setLocationName("Base2");
        loc2.setDescription("Home");
        loc2.setLatitude(lat);
        loc2.setLongitude(lon);
        
        service.assembleLocation(loc2, address);
        Location fromDao = service.getLocation(loc2.getLocationId());
        assertEquals(loc2.getAddress().getCity(), fromDao.getAddress().getCity());
        
    }
    
    @Test
    public void deleteHero(){
        Hero hero2 = new Hero();
        hero2.setAlias("Thualk");
        hero2.setFirstName("Thuan");
        hero2.setLastName("Huynh");
        hero2.setDescription("Strong!");
        service.addHero(hero);
        
        List<Hero> heroes = service.getAllHeroes();
        assertEquals(2, heroes.size());
        service.deleteHero(hero.getHeroId());
        heroes = service.getAllHeroes();
        assertEquals(1, heroes.size());
    }
    
    @Test
    public void deleteLocation(){
        Location location2 = new Location();
        location2.setLocationName("Stark Tower");
        location2.setDescription("Tony Stark's Home");
        location2.setLatitude(lat);
        location2.setLongitude(lon);
        location2.setAddress(address);
        service.addLocation(location2);
        List<Location> locations = service.getAllLocations();
        assertEquals(2, locations.size());
        service.deleteLocation(location.getLocationId());
        locations = service.getAllLocations();
        assertEquals(1, locations.size());
    }
    
    @Test
    public void delOrg(){
        Organization org2 = new Organization();
        org2.setOrgName("Avenger");
        org2.setDescription("blah blah");
        org2.setAddress(address);
        service.addOrg(org2);
        
        List<Organization> orgs = service.getAllOrgs();
        assertEquals(2, orgs.size());
        service.delOrg(org.getOrgId());
        orgs = service.getAllOrgs();
        assertEquals(1, orgs.size());
    }
    
    
    @Test
    public void delPower(){
        Power power2= new Power();
        power2.setPower("Lightning!");
        power2.setDescription("Breath Water");
        service.addPower(power2);
        
        List<Power> powers = service.getAllPowers();
        assertEquals(2, powers.size());
        service.delPower(power.getPowerId());
        powers = service.getAllPowers();
        assertEquals(1, powers.size());
    }
}
