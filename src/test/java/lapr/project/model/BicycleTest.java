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
 * @author USER
 */
public class BicycleTest {

    public BicycleTest() {
    }

    /**
     * Test of getBicycleId method, of class bicycle.
     */
    @Test
    public void testGetBicycleId() {
        System.out.println("getBicycleId");
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        String expResult = "3";
        String result = instance.getBicycleDesc();
        assertEquals(expResult, result);

    }

    /**
     * Test of setBicycleId method, of class bicycle.
     */
    @Test
    public void testSetBicycleId() {
        System.out.println("setBicycleId");
        String bicycleDesc = "1";
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        instance.setBicycleDesc(bicycleDesc);
        assertEquals(bicycleDesc, instance.getBicycleDesc());
    }

    /**
     * Test of isIsAvailable method, of class bicycle.
     */
    @Test
    public void testIsIsAvailable() {
        System.out.println("isIsAvailable");
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);

        boolean result = instance.isIsAvailable();
        assertTrue(result);

        Bicycle instance2 = new Bicycle("3", false, 23F, 30, 3, 4);
        boolean result2 = instance2.isIsAvailable();
        assertTrue(!result2);
    }

    /**
     * Test of setIsAvailable method, of class bicycle.
     */
    @Test
    public void testSetIsAvailable() {
        System.out.println("setIsAvailable");
        boolean isAvailable = false;
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        instance.setIsAvailable(isAvailable);
        assertEquals(isAvailable, instance.isIsAvailable());
    }

    /**
     * Test of getCostPerHour method, of class bicycle.
     */
    @Test
    public void testGetCostPerHour() {
        System.out.println("getCostPerHour");
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        float expResult = 23F;
        float result = instance.getCostPerHour();
        assertEquals(expResult, result, 23);

    }

    /**
     * Test of setCostPerHour method, of class bicycle.
     */
    @Test
    public void testSetCostPerHour() {
        System.out.println("setCostPerHour");
        float costPerHour = 4F;
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        instance.setCostPerHour(costPerHour);
        assertEquals(costPerHour, instance.getCostPerHour());

    }

    @Test
    public void testSetfrontalArea() {
        System.out.println("testSetfrontalArea");
        float area = 2;
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        instance.setFrontalArea(area);
        assertEquals(area, instance.getFrontalArea());

    }

    /**
     * Test of hashCode method, of class bicycle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Bicycle instance = new Bicycle();
        int expResult = 531705247;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class bicycle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Bicycle instance = new Bicycle("3", true, 23F, 30, 3, 4);
        Bicycle instance1 = new Bicycle("3", true, 23F, 30, 3, 4);

        boolean result = instance.equals(instance1);
        assertTrue(result);

        Bicycle instance2 = new Bicycle("2", true, 23F, 30, 3, 4);
        boolean result2 = instance.equals(instance2);
        assertTrue(!result2);

        boolean result3 = instance.equals(null);
        assertTrue(!result3);

        Ride r = new Ride();
        boolean result4 = instance.equals(r);
        assertTrue(!result4);

        Bicycle instance4 = new Bicycle("3", false, 23F, 30, 3, 4);
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);

        Bicycle instance5 = new Bicycle("3", true, 10F, 30, 3, 4);
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);

        Bicycle b = new Bicycle("1", 0, 0, 0);

        Bicycle b1 = new Bicycle("1", 1, 0, 0);
        boolean result7 = b.equals(b1);
        assertTrue(!result7);

        Bicycle b2 = new Bicycle("1", 0, 1, 0);
        boolean result8 = b.equals(b2);
        assertTrue(!result8);

        Bicycle b3 = new Bicycle("1", 0, 0, 11);
        boolean result9 = b.equals(b3);
        assertTrue(!result9);

    }

}
