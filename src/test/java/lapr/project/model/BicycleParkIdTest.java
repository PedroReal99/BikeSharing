/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pedro
 */
public class BicycleParkIdTest {
    
    public BicycleParkIdTest() {
    }

    /**
     * Test of getParkId method, of class BicycleParkId.
     */
    @Test
    public void testGetParkId() {
        System.out.println("getParkId");
        BicycleParkId instance = new BicycleParkId("1", "1");
        String expResult = "1";
        String result = instance.getParkDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkId method, of class BicycleParkId.
     */
    @Test
    public void testSetParkId() {
        System.out.println("setParkId");
        String parkId = "2";
        BicycleParkId instance = new BicycleParkId("1", "1");
        instance.setParkId(parkId);
        assertEquals(parkId, instance.getParkDescription());
    }

    /**
     * Test of getBicycleId method, of class BicycleParkId.
     */
    @Test
    public void testGetBicycleId() {
        System.out.println("getBicycleId");
        BicycleParkId instance = new BicycleParkId("1", "1");
        String expResult = "1";
        String result = instance.getBicycleDesc();
        assertEquals(expResult, result);

    }

    /**
     * Test of setBicycleId method, of class BicycleParkId.
     */
    @Test
    public void testSetBicycleId() {
        System.out.println("setBicycleId");
        String bicycleId = "2";
        BicycleParkId instance = new BicycleParkId("1", "1");
        instance.setBicycleId(bicycleId);
        assertEquals(bicycleId, instance.getBicycleDesc());
    }

    /**
     * Test of hashCode method, of class BicycleParkId.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        BicycleParkId instance = new BicycleParkId("1", "1");
        int expResult = 5675;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class BicycleParkId.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        BicycleParkId instance = new BicycleParkId("1", "1");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        BicycleParkId instance1 = new BicycleParkId("1", "1");
        boolean result1 = instance.equals(instance1);
        assertTrue(result1);
        
        Ride r = new Ride();
        boolean resultt = instance.equals(r);
        assertTrue(!resultt);
        
        BicycleParkId instance2 = new BicycleParkId("11", "1");
        boolean result2 = instance.equals(instance2);
        assertTrue(!result2);
        
        BicycleParkId instance3 = new BicycleParkId("11", "11");
        boolean result3 = instance.equals(instance3);
        assertTrue(!result3);
    }


    
}
