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
public class LocationTest {
    
    public LocationTest() {
    }


    /**
     * Test of getLocation method, of class Location.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Location instance = new Location("12,3 14,1", "Vila do Conde");
        String expResult = "12,3 14,1";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocation method, of class Location.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "13,5 55,6";
        Location instance = new Location("12,3 14,1", "Vila do Conde");;
        instance.setLocation(location);
        assertEquals(location,instance.getLocation());
    }

    /**
     * Test of getName method, of class Location.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Location instance = new Location("12,3 14,1", "Vila do Conde");
        String expResult = "Vila do Conde";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Location.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Porto";
        Location instance = new Location("12,3 14,1", "Vila do Conde");
        instance.setName(name);
        assertEquals(name,instance.getName());
    }

    

    /**
     * Test of getAltitude method, of class Location.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Location instance = new Location("12,3 14,1", "Vila do Conde", 2);
        int expResult = 2;
        int result = instance.getAltitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAltitude method, of class Location.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        int altitude = 0;
        Location instance = new Location("12,3 14,1", "Vila do Conde", 2);
        instance.setAltitude(altitude);
        int result=instance.getAltitude();
        assertEquals(altitude, result);
    }

    /**
     * Test of hashCode method, of class Location.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Location instance = new Location();
        int expResult = 6388711;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Location.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Location instance = new Location("12,3 14,1", "Vila do Conde", 2);
        Location instance2 = new Location("12,3 14,1", "Vila do Conde", 2);
        Location instance3 = new Location("12,3 14,1", "Vila do Conde", 2);
        
        boolean result = instance.equals(instance2);  //true
        assertTrue(result);
        
        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345,"1");
        boolean result3 = instance.equals(r);   // false class
        assertTrue(!result3);
        
        boolean result4 = instance.equals(null);
        assertTrue(!result4);
        
        Location instance4 = new Location("11,3 14,1", "Vila do Conde", 2);
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);
        
        Location instance5 = new Location("12,3 14,1", "Porto", 2);
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);
        
        Location instance6 = new Location("12,3 14,1", "Porto", 3);
        boolean result7 = instance.equals(instance6);
        assertTrue(!result7);
    }
    
}
