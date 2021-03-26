/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



/**
 *
 * @author USER
 */
public class RideBillingTest {
    
    public RideBillingTest() {
    }
    
    /**
     * Test of getCost method, of class RideBilling.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        RideBilling instance = new RideBilling(23f,4,"jan");
        float expResult = 23F;
        float result = instance.getCost();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCost method, of class RideBilling.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        float cost = 2F;
        RideBilling instance = new RideBilling(23f,4,"jan");
        instance.setCost(cost);
        assertEquals(cost, instance.getCost());
        
    }

    /**
     * Test of getRideId method, of class RideBilling.
     */
    @Test
    public void testGetRideId() {
        System.out.println("getRideId");
        RideBilling instance = new RideBilling(23f,4,"jan");
        int expResult = 4;
        int result = instance.getRideId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setRideId method, of class RideBilling.
     */
    @Test
    public void testSetRideId() {
        System.out.println("setRideId");
        int rideId = 7;
        RideBilling instance = new RideBilling();
        instance.setRideId(rideId);
        assertEquals(rideId, instance.getRideId());
    }

    /**
     * Test of getMonth method, of class RideBilling.
     */
    @org.junit.Test
    public void testGetMonth() {
        System.out.println("getMonth");
        RideBilling instance = new RideBilling(23f,4,"jan");
        String expResult = "jan";
        String result = instance.getMonth();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMonth method, of class RideBilling.
     */
    @org.junit.Test
    public void testSetMonth() {
        System.out.println("setMonth");
        String month = "lol";
        RideBilling instance = new RideBilling(23f,4,"jan");
        instance.setMonth(month);
        
    }
    
}
