/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author titi1
 */
public class BicycleRegistryTest {

    public BicycleRegistryTest() {
    }

//    /**
//     * Test of getUserMap method, of class BicycleRegistry.
//     */
    @Test
    public void testGetUserMap() {
        System.out.println("getUserMap");
        BicycleRegistry instance = new BicycleRegistry();
        Map<String, Bicycle> expResult = new TreeMap<>();
        Bicycle bike = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        expResult.put(bike.getBicycleDesc(), bike);
        instance.addNewbicycle(bike, "10");
        Map<String, Bicycle> result = instance.getBicycleMap();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of addNewbicycle method, of class BicycleRegistry.
//     */
    @Test
    public void testAddNewbicycle() {
        System.out.println("addNewbicycle");
        Bicycle bike = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        BicycleRegistry instance = new BicycleRegistry();
        instance.addNewbicycle(bike, "10");
        assertEquals(bike, instance.getBicycleMap().get(bike.getBicycleDesc()));
        List<BicycleParkId> listResult = new ArrayList<>();
        listResult.add(new BicycleParkId(bike.getBicycleDesc(), "10"));
        assertEquals(listResult, instance.getNewbicycles());
    }

    /**
     * Test of addUpdatedbicycle method, of class BicycleRegistry.
     */
    @Test
    public void testAddUpdatedbicycle() {
        System.out.println("addUpdatedbicycle");
        Bicycle bike = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        BicycleRegistry instance = new BicycleRegistry();
        instance.addNewbicycle(bike, "10");
        bike.setCostPerHour(3);
        instance.addUpdatedbicycle(bike);
        Map<String, Boolean> listResult = new HashMap<>();
        listResult.put(bike.getBicycleDesc(), true);
        assertEquals(listResult, instance.getUpdatedbicycles());
    }

    /**
     * Test of addDeletedbicycle method, of class BicycleRegistry.
     */
    @Test
    public void testAddDeletedbicycle() {
        System.out.println("addDeletedbicycle");
        Bicycle bike = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        BicycleRegistry instance = new BicycleRegistry();
        instance.addNewbicycle(bike, "10");
        instance.addDeletedbicycle(bike.getBicycleDesc());
        assertEquals(null, instance.getBicycleMap().get(bike.getBicycleDesc()));
        List<String> listResult = new ArrayList<>();
        listResult.add(bike.getBicycleDesc());
        assertEquals(listResult, instance.getDeletedbicycles());
    }

    /**
     * Test of checkBicycle method, of class BicycleRegistry.
     */
    @Test
    public void testCheckBicycle() {
        System.out.println("checkBicycle");
        Bicycle bike = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        Bicycle bike1 = new Electric(80, 45, 5, "1", true, 20, 30, 3, 4);
        BicycleRegistry instance = new BicycleRegistry();
        boolean result = instance.checkBicycle(bike.getBicycleDesc());
        assertTrue(result);
        instance.addNewbicycle(bike, "10");
        boolean result1 = instance.checkBicycle(bike1.getBicycleDesc());
        assertTrue(!result1);

    }
}
