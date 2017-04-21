/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Hero;
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
public class HeroDaoTest {
    
    HeroDao dao;
    
    public HeroDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ac= new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ac.getBean("heroDao", HeroDao.class);
        
        List<Hero> heroList = dao.getAllHeroes();
        for(Hero hero : heroList){
            dao.deleteHero(hero.getHeroId());
        }
        
    }
    
    @After
    public void tearDown() {
        List<Hero> heroList = dao.getAllHeroes();
        for(Hero hero : heroList){
            dao.deleteHero(hero.getHeroId());
        }
    }

    /**
     * Test of addHero method, of class HeroDao.
     */
    @Test
    public void testAddGetHero() {
        Hero h = new Hero();
        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        dao.addHero(h);
        Hero fromDao = dao.getHero(h.getHeroId());
        assertEquals(fromDao, h);
    }

    /**
     * Test of getAllHeroes method, of class HeroDao.
     */
    @Test
    public void testGetAllDelHeroes() {
        Hero h = new Hero();
        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        dao.addHero(h);
        List<Hero> heroes = dao.getAllHeroes();
        assertEquals(1, heroes.size());
        dao.deleteHero(h.getHeroId());
        heroes = dao.getAllHeroes();
        assertEquals(0, heroes.size());
    }

    /**
     * Test of updateHero method, of class HeroDao.
     */
    @Test
    public void testUpdateHero() {
        Hero h = new Hero();
        h.setHeroId(1);
        h.setAlias("Thuanination");
        h.setFirstName("Thuan");
        h.setLastName("Huynh");
        h.setDescription("Strong!");
        dao.addHero(h);
        h.setAlias("Thulk");
        assertNotEquals(h, dao.getHero(h.getHeroId()));
        dao.updateHero(h);
        assertEquals(h, dao.getHero(h.getHeroId()));
    }


    
}
