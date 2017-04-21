/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Location;
import com.sg.superherosighting.model.Sighting;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author feng
 */
public class SightingDaoTest {
    
    SightingDao dao;
    LocationDao lDao;
    HeroDao hDao;
    AddressDao aDao;
    
    Location l = new Location();
    Hero h = new Hero();
    Address ad2 = new Address();
    BigDecimal lat = new BigDecimal("40.758895");
    BigDecimal lon = new BigDecimal("73.985131").negate();

    
    public SightingDaoTest() {
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
        dao = ac.getBean("sightingDao", SightingDao.class);
        hDao = ac.getBean("heroDao", HeroDao.class); 
        lDao = ac.getBean("locationDao", LocationDao.class); 
        aDao = ac.getBean("addressDao", AddressDao.class);
        dao.delAllSightingBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Location> locs = lDao.getAllLocations();
        for(Location loc: locs){
            lDao.deleteLocation(loc.getLocationId());
        }
        List<Address> addresses = aDao.getAllAddresses();
        for(Address ad : addresses){
            aDao.delAddress(ad.getAddressId());
        }
        ad2.setAddressId(2);
        ad2.setAddress("401 S Main street");
        ad2.setCity("Akron");
        ad2.setState("OH");
        ad2.setCountry("USA");
        ad2.setZip("44311");
        ad2.setWorld("Earth");
        aDao.addAddress(ad2);
        
        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        hDao.addHero(h);
        
        l.setLocationId(1);
        l.setLocationName("Stark Tower");
        l.setDescription("Tony Stark's Home");
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAddress(ad2);
        lDao.addLocation(l);
    }
    
    @After
    public void tearDown() {
        dao.delAllSightingBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Location> locs = lDao.getAllLocations();
        for(Location loc: locs){
            lDao.deleteLocation(loc.getLocationId());
        }
        List<Address> addresses = aDao.getAllAddresses();
        for(Address ad : addresses){
            aDao.delAddress(ad.getAddressId());
        }
    }

    /**
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddGetSighting() {
        Sighting s = new Sighting();
        s.setSightingId(1);
        s.setLocation(l);
        s.setHero(h);
        s.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        s.setDescription("blah blah");
        dao.addSighting(s);
        Sighting fromDao = dao.getHeroLocationRelationship(s.getSightingId());
        assertEquals(fromDao, s);
        dao.delSighting(s);
        fromDao = dao.getHeroLocationRelationship(s.getSightingId());
        assertNotEquals(fromDao, s);
    }

    @Test
    public void testGetHeroesByLocation(){
        Sighting s = new Sighting();
        s.setSightingId(1);
        s.setLocation(l);
        s.setHero(h);
        s.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        s.setDescription("blah blah");
        dao.addSighting(s);
        List<Hero> heroes = dao.getHeroesByLocation(l.getLocationId());
        assertEquals(1, heroes.size());
        Hero h2 = new Hero();
        h2.setHeroId(2);
        h2.setAlias("MaxiMcMillien");
        h2.setFirstName("Cory");
        h2.setLastName("McMillien");
        h2.setDescription("Fly!");
        hDao.addHero(h2);
        s.setSightingId(2);
        s.setLocation(l);
        s.setHero(h2);
        s.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        s.setDescription("blah blah");
        dao.addSighting(s);
        heroes = dao.getHeroesByLocation(l.getLocationId());
        assertEquals(2, heroes.size());
    }
    
    @Test
    public void testGetLocationsByHeroes(){
        Sighting s = new Sighting();
        s.setSightingId(1);
        s.setLocation(l);
        s.setHero(h);
        s.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        s.setDescription("blah blah");
        dao.addSighting(s);
        List<Location> locations = dao.getLocationsByHero(h.getHeroId());
        assertEquals(1, locations.size());
        Location l2 = new Location();
        l2.setLocationId(2);
        l2.setLocationName("Bat Tower");
        l2.setDescription("Bruce's Home");
        l2.setLatitude(lat);
        l2.setLongitude(lon);
        l2.setAddress(ad2);
        lDao.addLocation(l2);
        s.setSightingId(2);
        s.setLocation(l2);
        s.setHero(h);
        s.setSightingDate(LocalDateTime.parse("2014-05-20T12:00"));
        s.setDescription("blah blah");
        dao.addSighting(s);
        locations = dao.getLocationsByHero(h.getHeroId());
        assertEquals(2, locations.size());
    }
    
}
