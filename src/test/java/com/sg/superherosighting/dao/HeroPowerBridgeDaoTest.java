/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
import com.sg.superherosighting.model.HeroPowerBridge;
import com.sg.superherosighting.model.Power;
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
public class HeroPowerBridgeDaoTest {
    
    HeroPowerBridgeDao dao;
    HeroDao hDao;
    PowerDao pDao;
    
    Hero h = new Hero();

    Power p = new Power();

    public HeroPowerBridgeDaoTest() {
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
        dao = ac.getBean("heroPowerBridgeDao", HeroPowerBridgeDao.class);
        hDao = ac.getBean("heroDao", HeroDao.class);
        pDao = ac.getBean("powerDao", PowerDao.class);
        dao.delAllHPBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Power> powerList = pDao.getAllPowers();
        for(Power currentPower: powerList){
            pDao.delPower(currentPower.getPowerId());
        }
        

        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        p.setPowerId(2);
        p.setPower("Water!");
        p.setDescription("Breath Water");
        hDao.addHero(h);
        pDao.addPower(p);
    }
    
    @After
    public void tearDown() {
        dao.delAllHPBridges();
        List<Hero> heroList = hDao.getAllHeroes();
        for(Hero hero : heroList){
            hDao.deleteHero(hero.getHeroId());
        }
        List<Power> powerList = pDao.getAllPowers();
        for(Power currentPower: powerList){
            pDao.delPower(currentPower.getPowerId());
        }
    }

    /**
     * Test of addHeroPowerBridge method, of class HeroPowerBridgeDao.
     */
    @Test
    public void testAddHeroPowerGetBridge() {
        dao.addHeroPowerBridge(h.getHeroId(), p.getPowerId());
        HeroPowerBridge hpb = dao.getHeroPowerRelation(h.getHeroId(), p.getPowerId());
        assertEquals(h.getHeroId(), hpb.getHero().getHeroId());
    }

    /**
     * Test of delHeroPowerBridge method, of class HeroPowerBridgeDao.
     */
    @Test
    public void testDelHeroPowerBridge() {
        HeroPowerBridge hpb = new HeroPowerBridge();
        dao.addHeroPowerBridge(h.getHeroId(), p.getPowerId());
        hpb = dao.getHeroPowerRelation(h.getHeroId(), p.getPowerId());
        
        dao.delHeroPowerBridge(hpb.getHero().getHeroId(), hpb.getPower().getPowerId());
        List<Hero> heroes =dao.getHeroesByPower(hpb.getPower().getPowerId());
        assertEquals(0, heroes.size());
    }

    /**
     * Test of getHeroesByPower method, of class HeroPowerBridgeDao.
     */
    @Test
    public void testGetHeroesByPower() {
        HeroPowerBridge hpb = new HeroPowerBridge();
        dao.addHeroPowerBridge(h.getHeroId(), p.getPowerId());
        hpb = dao.getHeroPowerRelation(h.getHeroId(), p.getPowerId());
        List<Hero> heroes = dao.getHeroesByPower(hpb.getPower().getPowerId());
        assertEquals(1, heroes.size());
        Hero h2 = new Hero();
        h2.setHeroId(2);
        h2.setAlias("MaxiMcMillien");
        h2.setFirstName("Cory");
        h2.setLastName("McMillien");
        h2.setDescription("Fly!");
        hDao.addHero(h2);
        dao.addHeroPowerBridge(h2.getHeroId(), p.getPowerId());
        heroes = dao.getHeroesByPower(hpb.getPower().getPowerId());
        assertEquals(2, heroes.size());
    }

    /**
     * Test of getPowersByHero method, of class HeroPowerBridgeDao.
     */
    @Test
    public void testGetPowersByHero() {
        dao.addHeroPowerBridge(h.getHeroId(), p.getPowerId());
        List<Power> powers = dao.getPowersByHero(h.getHeroId());
        assertEquals(1, powers.size());
        
        Power p2 = new Power();
        p2.setPowerId(3);
        p2.setPower("Lightning!");
        p2.setDescription("Cast Lightning");
        pDao.addPower(p2);
        dao.addHeroPowerBridge(h.getHeroId(), p2.getPowerId());
        powers = dao.getPowersByHero(h.getHeroId());
        assertEquals(2, powers.size());
    }


    
}
