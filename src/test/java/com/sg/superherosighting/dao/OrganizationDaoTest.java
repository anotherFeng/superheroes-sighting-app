/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Organization;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author feng
 */
public class OrganizationDaoTest {
    
    OrganizationDao dao;
    AddressDao aDao;
    
    Address ad = new Address();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ac.getBean("orgDao", OrganizationDao.class);
        aDao = ac.getBean("addressDao", AddressDao.class);
        List<Organization> orgs = dao.getAllOrgs();
        for(Organization org: orgs){
            dao.delOrg(org.getOrgId());
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
        List<Organization> orgs = dao.getAllOrgs();
        for(Organization org: orgs){
            dao.delOrg(org.getOrgId());
        }
        List<Address> addressList = aDao.getAllAddresses();
        for(Address address : addressList){
            aDao.delAddress(address.getAddressId());
        }
    }

    /**
     * Test of addOrg method, of class OrganizationDao.
     */
    @Test
    public void testAddGetOrg() {
        Organization org = new Organization();
        org.setOrgId(1);
        org.setOrgName("Avenger");
        org.setDescription("blah blah");
        org.setAddress(ad);
        dao.addOrg(org);
        Organization fromDao = dao.getOrg(org.getOrgId());
        assertEquals(org, fromDao);
    }

    /**
     * Test of getAllOrgs method, of class OrganizationDao.
     */
    @Test
    public void testGetAllDelOrgs() {
        Organization org = new Organization();
        org.setOrgId(1);
        org.setOrgName("Avenger");
        org.setDescription("blah blah");
        org.setAddress(ad);
        dao.addOrg(org);
        List<Organization> orgs = dao.getAllOrgs();
        assertEquals(1, orgs.size());
        dao.delOrg(org.getOrgId());
        orgs = dao.getAllOrgs();
        assertEquals(0, orgs.size());
    }

    /**
     * Test of updateOrg method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrg() {
        Organization org = new Organization();
        org.setOrgId(1);
        org.setOrgName("Avenger");
        org.setDescription("blah blah");
        org.setAddress(ad);
        dao.addOrg(org);
        org.setOrgName("X-Men");
        assertNotEquals(org, dao.getOrg(org.getOrgId()));
        Organization fromDao = dao.updateOrg(org);
        assertEquals(org, fromDao);
    }

    
}
