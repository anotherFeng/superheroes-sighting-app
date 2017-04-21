/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Location;
import java.math.BigDecimal;
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
public class LocationDaoTest {
    
    LocationDao dao;
    AddressDao aDao;
    Address ad = new Address();
    BigDecimal lat = new BigDecimal("40.758895");
    BigDecimal lon = new BigDecimal("73.985131").negate();
    
    public LocationDaoTest() {
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
        dao = ac.getBean("locationDao", LocationDao.class);
        aDao = ac.getBean("addressDao", AddressDao.class);
        List<Location> locations = dao.getAllLocations();
        for(Location location: locations){
            dao.deleteLocation(location.getLocationId());
        }
        List<Address> addressList = aDao.getAllAddresses();
        for(Address address : addressList){
            aDao.delAddress(address.getAddressId());
        }
        ad.setAddressId(1);
        ad.setAddress("401 S Main street");
        ad.setCity("Akron");
        ad.setState("OH");
        ad.setCountry("USA");
        ad.setZip("44311");
        ad.setWorld("Earth");
        aDao.addAddress(ad);
    }
    
    @After
    public void tearDown() {
        List<Location> locations = dao.getAllLocations();
        for(Location location: locations){
            dao.deleteLocation(location.getLocationId());
        }
        List<Address> addresses = aDao.getAllAddresses();
        for(Address address : addresses){
            aDao.delAddress(address.getAddressId());
        }

    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetLocation() {
        Location l = new Location();
        l.setLocationId(1);
        l.setLocationName("Stark Tower");
        l.setDescription("Tony Stark's Home");
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAddress(ad);
        dao.addLocation(l);
        Location fromDao = dao.getLocation(l.getLocationId());
        assertEquals(fromDao, l);
    }


    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {
        Location l = new Location();
        l.setLocationId(1);
        l.setLocationName("Stark Tower");
        l.setDescription("Tony Stark's Home");
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAddress(ad);
        dao.addLocation(l);
        List<Location> locations = dao.getAllLocations();
        assertEquals(1, locations.size());
        dao.deleteLocation(l.getLocationId());
        locations = dao.getAllLocations();
        assertEquals(0, locations.size());
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location l = new Location();
        l.setLocationId(1);
        l.setLocationName("Stark Tower");
        l.setDescription("Tony Stark's Home");
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAddress(ad);
        dao.addLocation(l);
        l.setLocationName("Avenger HQ");
        assertNotEquals(l, dao.getLocation(l.getLocationId()));
        dao.updateLocation(l);
        assertEquals(l, dao.getLocation(l.getLocationId()));
    }

}
