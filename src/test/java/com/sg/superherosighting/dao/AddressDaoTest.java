/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
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
public class AddressDaoTest {
    
    AddressDao dao;
    
    public AddressDaoTest() {
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
        dao = ac.getBean("addressDao", AddressDao.class);
        List<Address> addresses = dao.getAllAddresses();
        for(Address ad : addresses){
            dao.delAddress(ad.getAddressId());
        }
    }
    
    @After
    public void tearDown() {
        List<Address> addresses = dao.getAllAddresses();
        for(Address currentAddress : addresses){
            dao.delAddress(currentAddress.getAddressId());
        }
    }

    /**
     * Test of addAddress method, of class AddressDao.
     */
    @Test
    public void testAddGetAddress() {
        Address ad2 = new Address();
        ad2.setAddressId(2);
        ad2.setAddress("401 S Main street");
        ad2.setCity("Akron");
        ad2.setState("OH");
        ad2.setCountry("USA");
        ad2.setZip("44311");
        ad2.setWorld("Earth");
        dao.addAddress(ad2);
        Address fromDao = dao.getAddress(ad2.getAddressId());
        assertEquals(fromDao, ad2);
    }


    /**
     * Test of getAllAddresses method, of class AddressDao.
     */
    @Test
    public void testGetAllDelAddresses() {
        Address ad2 = new Address();
        ad2.setAddressId(2);
        ad2.setAddress("401 S Main street");
        ad2.setCity("Akron");
        ad2.setState("OH");
        ad2.setCountry("USA");
        ad2.setZip("44311");
        ad2.setWorld("Earth");
        dao.addAddress(ad2);
        List<Address> addList = dao.getAllAddresses();
        assertEquals(1, addList.size());
        dao.delAddress(ad2.getAddressId());
        addList = dao.getAllAddresses();
        assertEquals(0, addList.size());
    }


    @Test
    public void testUpdateAddress() {
        Address ad2 = new Address();
        ad2.setAddressId(2);
        ad2.setAddress("401 S Main street");
        ad2.setCity("Akron");
        ad2.setState("OH");
        ad2.setCountry("USA");
        ad2.setZip("44311");
        ad2.setWorld("Earth");
        dao.addAddress(ad2);
        ad2.setCity("Cleveland");
        assertNotEquals(ad2, dao.getAddress(ad2.getAddressId()));
        dao.updateAddress(ad2);
        assertEquals(ad2, dao.getAddress(ad2.getAddressId()));
    }

    
}
