/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.Members;
import com.sg.superherosighting.model.Organization;
import java.time.LocalDate;
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
public class HeroOrganizationBridgeDaoTest {
    
    HeroOrganizationBridgeDao dao;
    HeroDao hDao;
    OrganizationDao oDao;
    AddressDao aDao;
    
    Hero h = new Hero();
    Organization org = new Organization();
    Address ad2 = new Address();
    
    
    public HeroOrganizationBridgeDaoTest() {
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
        dao = ac.getBean("heroOrganizationBridgeDao", HeroOrganizationBridgeDao.class);
        hDao = ac.getBean("heroDao", HeroDao.class); 
        oDao = ac.getBean("orgDao", OrganizationDao.class); 
        aDao = ac.getBean("addressDao", AddressDao.class);
        dao.delAllMemberBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Organization> orgs = oDao.getAllOrgs();
        for(Organization org: orgs){
            oDao.delOrg(org.getOrgId());
        }
        List<Address> addresses = aDao.getAllAddresses();
        for(Address ad : addresses){
            aDao.delAddress(ad.getAddressId());
        }
        //create an address object to feed organization
        ad2.setAddressId(2);
        ad2.setAddress("401 S Main street");
        ad2.setCity("Akron");
        ad2.setState("OH");
        ad2.setCountry("USA");
        ad2.setZip("44311");
        ad2.setWorld("Earth");
        aDao.addAddress(ad2);
        
        //create hero and organization objects to feed member table
        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        hDao.addHero(h);
        
        org.setOrgId(1);
        org.setOrgName("Avenger");
        org.setDescription("blah blah");
        org.setAddress(ad2);
        oDao.addOrg(org);
    }
    
    @After
    public void tearDown() {
        dao.delAllMemberBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Organization> orgs = oDao.getAllOrgs();
        for(Organization org: orgs){
            oDao.delOrg(org.getOrgId());
        }
        List<Address> addresses = aDao.getAllAddresses();
        for(Address ad : addresses){
            aDao.delAddress(ad.getAddressId());
        }
    }

    /**
     * Test of addMembersBridge method, of class HeroOrganizationBridgeDao.
     */
    @Test
    public void testAddGetDelMembersBridge() {
        Members member = new Members();
        member.setMemberId(1);
        member.setOrg(org);
        member.setHero(h);
        member.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        dao.addMembersBridge(member);
        Members fromDao = dao.getHeroOrgRelation(member.getMemberId());
        assertEquals(fromDao, member);
        dao.delMembersBridge(member.getMemberId());
        fromDao = dao.getHeroOrgRelation(member.getMemberId());
        assertNotEquals(fromDao, member);
    }

    /**
     * Test of getHeroesByOrg method, of class HeroOrganizationBridgeDao.
     */
    @Test
    public void testGetHeroesByOrg() {
        Members member = new Members();
        member.setMemberId(1);
        member.setOrg(org);
        member.setHero(h);
        member.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        dao.addMembersBridge(member);
        List<Hero> heroes = dao.getHeroesByOrg(org.getOrgId());
        assertEquals(1, heroes.size());
        Hero h2 = new Hero();
        h2.setHeroId(2);
        h2.setAlias("MaxiMcMillien");
        h2.setFirstName("Cory");
        h2.setLastName("McMillien");
        h2.setDescription("Fly!");
        hDao.addHero(h2);
        member.setMemberId(2);
        member.setOrg(org);
        member.setHero(h2);
        member.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        dao.addMembersBridge(member);
        heroes = dao.getHeroesByOrg(org.getOrgId());
        assertEquals(2, heroes.size());
    }

    /**
     * Test of getOrgsByHero method, of class HeroOrganizationBridgeDao.
     */
    @Test
    public void testGetOrgsByHero() {
        Members member = new Members();
        member.setMemberId(1);
        member.setOrg(org);
        member.setHero(h);
        member.setStartDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        member.setEndDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        dao.addMembersBridge(member);
        List<Organization> orgs = dao.getOrgsByHero(h.getHeroId());
        assertEquals(1, orgs.size());
        Organization org2 = new Organization();
        org2.setOrgId(2);
        org2.setOrgName("Avenger");
        org2.setDescription("blah blah");
        org2.setAddress(ad2);
        oDao.addOrg(org2);
    }


    
}
