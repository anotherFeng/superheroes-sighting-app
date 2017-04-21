/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Power;
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
public class PowerDaoTest {
    
    PowerDao dao;
    
    public PowerDaoTest() {
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
        dao = ac.getBean("powerDao", PowerDao.class);
        
        List<Power> powerList = dao.getAllPowers();
        for(Power currentPower: powerList){
            dao.delPower(currentPower.getPowerId());
        }

    }
    
    @After
    public void tearDown() {
        List<Power> powerList = dao.getAllPowers();
        for(Power currentPower: powerList){
            dao.delPower(currentPower.getPowerId());
        }
    }

    /**
     * Test of addPower method, of class PowerDao.
     */
    @Test
    public void testAddGetPower() {
        Power p2 = new Power();
        p2.setPowerId(2);
        p2.setPower("Water!");
        p2.setDescription("Breath Water");
        dao.addPower(p2);
        Power fromDao = dao.getPower(p2.getPowerId());
        assertEquals(fromDao, p2);
    }

    /**
     * Test of getAllPowers method, of class PowerDao.
     */
    @Test
    public void testGetAllDelPowers() {
        Power p2 = new Power();
        p2.setPowerId(2);
        p2.setPower("Water!");
        p2.setDescription("Breath Water");
        dao.addPower(p2);
        List<Power> powerList = dao.getAllPowers();
        assertEquals(1, powerList.size());
        dao.delPower(p2.getPowerId());
        powerList = dao.getAllPowers();
        assertEquals(0, powerList.size());
    }

    /**
     * Test of updatePower method, of class PowerDao.
     */
    @Test
    public void testUpdatePower() {
        Power p2 = new Power();
        p2.setPowerId(2);
        p2.setPower("Water!");
        p2.setDescription("Breath Water");
        dao.addPower(p2);
        p2.setPower("Lightning");
        assertNotEquals(p2, dao.getPower(p2.getPowerId()));
        Power fromDao = dao.updatePower(p2);
        assertEquals(p2, fromDao);
    }

    
}
