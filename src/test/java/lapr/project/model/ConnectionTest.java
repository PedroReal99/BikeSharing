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
public class ConnectionTest {

    public ConnectionTest() {
    }

    /**
     * Test of getWindDirection method, of class Connection.
     */
    @Test
    public void testGetWindDirection() {
        System.out.println("getWindDirection");
        Connection instance = new Connection(1,45, 22.0);
        int expResult = 45;
        int result = instance.getWindDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWindDirection method, of class Connection.
     */
    @Test
    public void testSetWindDirection() {
        System.out.println("setWindDirection");
        int windDirection = 50;
        Connection instance = new Connection(1,45, 22.0);
        instance.setWindDirection(windDirection);
        int result = instance.getWindDirection();
        assertEquals(windDirection, result);
    }

    /**
     * Test of getWindSpeed method, of class Connection.
     */
    @Test
    public void testGetWindSpeed() {
        System.out.println("getWindSpeed");
        Connection instance = new Connection(1,45, 22.0);
        double expResult = 22.0;
        double result = instance.getWindSpeed();
        assertEquals(expResult, result, 22.0);
    }

    /**
     * Test of setWindSpeed method, of class Connection.
     */
    @Test
    public void testSetWindSpeed() {
        System.out.println("setWindSpeed");
        double windSpeed = 3.0;
        Connection instance = new Connection(1,45, 22.0);
        instance.setWindSpeed(windSpeed);
        double result = instance.getWindSpeed();
        assertEquals(windSpeed, result, 3.0);
    }

    /**
     * Test of hashCode method, of class Connection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Connection instance = new Connection();
        int expResult = 3;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Connection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
         Connection instance = new Connection(1,45, 22.0);
         Connection instance2 = new Connection(1,45, 22.0);
         Connection instance3 = new Connection(2,45, 22.0);
           
           
        boolean result = instance.equals(instance2);
        assertTrue(result);

        boolean result2 = instance.equals(instance3);
        assertTrue(!result2);
        
        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        boolean result3 = instance.equals(r);   // false class
        assertTrue(!result3);
        
        boolean result4 = instance.equals(null);
        assertTrue(!result4);
        
        Connection instance4 = new Connection(1,44, 22.0);
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);
        
        Connection instance5 = new Connection(1,45, 23.0);
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);
    }

    /**
     * Test of getId method, of class Connection.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        Connection instance = new Connection(1,45, 23.0);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Connection.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;
        Connection instance = new Connection(1,45, 23.0);
        instance.setId(id);
        assertEquals(id,instance.getId());
    }

}
