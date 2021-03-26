/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.Calculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class ParkTest {

    public ParkTest() {
    }

    /**
     * Test of availableElectricalSpots method, of class Park.
     */
    @Test
    public void testAvailableElectricalSpots() {
        System.out.println("availableElectricalSpots");
        Park instance = new Park();
        instance.setCapacityElectric(12);
        List<String> list = new ArrayList<>();
        list.add("12");
        list.add("14");
        instance.setElectricList(list);
        int expResult = 10;
        int result = instance.availableElectricalSpots();
        assertEquals(expResult, result);
    }

    /**
     * Test of availableMountainRoadBikes method, of class Park.
     */
    @Test
    public void testAvailableMountainRoadBikes() {
        System.out.println("availableMountainRoadBikes");
        Park instance = new Park();
        instance.setCapacityMountainRoad(15);
        List<String> list = new ArrayList<>();
        list.add("13");
        list.add("15");
        list.add("16");
        instance.setMountainRoadList(list);
        int expResult = 12;
        int result = instance.availableMountainRoadBikes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Park.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        String location = "42.152699, -9.609267";
        Park instance = new Park();
        instance.setLocation("41.152699, -8.609267");
        double expResult = 138803;
        double result = Calculator.getDistance(instance.getLocation(), location);
        assertEquals(expResult, result, 1.0);
    }

    /**
     * Test of getCapacityMountainRoad method, of class Park.
     */
    @Test
    public void testGetCapacityMountainRoad() {
        System.out.println("getCapacityMountainRoad");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        int expResult = 5;
        int result = instance.getCapacityMountainRoad();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCapacityMountainRoad method, of class Park.
     */
    @Test
    public void testSetCapacityMountainRoad() {
        System.out.println("setCapacityMountainRoad");
        int capacityMountainRoad = 9;
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        instance.setCapacityMountainRoad(capacityMountainRoad);
        assertEquals(capacityMountainRoad, instance.getCapacityMountainRoad());
    }

    /**
     * Test of getCapacityElectric method, of class Park.
     */
    @Test
    public void testGetCapacityElectric() {
        System.out.println("getCapacityElectric");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        int expResult = 6;
        int result = instance.getCapacityElectric();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCapacityElectric method, of class Park.
     */
    @Test
    public void testSetCapacityElectric() {
        System.out.println("setCapacityElectric");
        int capacityElectric = 10;
        Park instance = new Park();
        instance.setCapacityElectric(capacityElectric);
        assertEquals(capacityElectric, instance.getCapacityElectric());
    }

    /**
     * Test of getParkDesc method, of class Park.
     */
    @Test
    public void testGetParkDesc() {
        System.out.println("getParkDesc");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        String expResult = "Trindade";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of setParkId method, of class Park.
     */
    @Test
    public void testSetParkDesc() {
        System.out.println("setParkId");
        String parkId = "2";
        Park instance = new Park("5", "12.34,56.78");
        instance.setDescription(parkId);

    }

    /**
     * Test of getAltitude method, of class Park.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        int expResult = 40;
        int result = instance.getAltitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAltitude method, of class Park.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        int altitude = 30;
        Park instance = new Park();
        instance.setAltitude(altitude);
        assertEquals(altitude, instance.getAltitude());
    }

    /**
     * Test of getLocation method, of class Park.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        String expResult = "12.34,56.78";
        String result = instance.getLocation();
        assertEquals(expResult, result);

    }

    /**
     * Test of setLocation method, of class Park.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "5678:1234";
        Park instance = new Park();
        instance.setLocation(location);
        assertEquals(location, instance.getLocation());
    }

    /**
     * Test of getMountainRoadList method, of class Park.
     */
    @Test
    public void testGetMountainRoadList() {
        System.out.println("getMountainRoadList");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.getMountainRoadList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMountainRoadList method, of class Park.
     */
    @Test
    public void testSetMountainRoadList() {
        System.out.println("setMountainRoadList");
        List<String> mountainRoadList = new ArrayList<>();
        Bicycle b = new Bicycle();
        mountainRoadList.add(b.getBicycleDesc());
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        instance.setMountainRoadList(mountainRoadList);
        assertEquals(mountainRoadList, instance.getMountainRoadList());
    }

    /**
     * Test of getElectricList method, of class Park.
     */
    @Test
    public void testGetElectricList() {
        System.out.println("getElectricList");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        List<Integer> expResult = new ArrayList<>();
        List<String> result = instance.getElectricList();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElectricList method, of class Park.
     */
    @Test
    public void testSetElectricList() {
        System.out.println("setElectricList");
        List<String> electricList = new ArrayList<>();
        Bicycle b = new Bicycle();
        electricList.add(b.getBicycleDesc());
        Park instance = new Park();
        instance.setElectricList(electricList);
        assertEquals(electricList, instance.getElectricList());
    }

    /**
     * Test of hashCode method, of class Park.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Park instance = new Park();
        int expResult = -1761553367;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Park instance = new Park(5, 6, 40, "12.34,56.78", "1", 3, 4);

        boolean result = instance.equals(null);
        assertTrue(!result);

        Bicycle b = new Bicycle();
        boolean result1 = instance.equals(b);
        assertTrue(!result1);

        Park instance1 = new Park(6, 6, 40, "12.34,56.78", "2", 3, 4);
        boolean result2 = instance.equals(instance1);
        assertTrue(!result2);

        Park instance2 = new Park(5, 7, 40, "12.34,56.78", "3", 3, 4);
        boolean result3 = instance.equals(instance2);
        assertTrue(!result3);

        Park instance4 = new Park(5, 6, 45, "12.34,56.78", "5", 3, 4);
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);

        Park instance5 = new Park(5, 6, 40, "12.4,56.78", "6", 3, 4);
        boolean result6 = instance.equals(instance5);
        assertTrue(result6);

    }

    /**
     * Test of addBicycle method, of class Park.
     */
    @Test
    public void testAddBicycle() {
        System.out.println("addBicycle");
        Bicycle b = new Bicycle();
        Mountain m = new Mountain();
        Electric e = new Electric();
        Road r = new Road();
        Park instance = new Park(5, 6, 40, "12.34,56.78", "Trindade", 3, 4);
        Park instance2 = new Park(0, 0, 40, "12.34,56.78", "Trindade", 3, 4);

        boolean result = instance.addBicycle(b);
        assertTrue(!result);

        boolean result1 = instance.addBicycle(m);
        assertTrue(result1);

        boolean result2 = instance.addBicycle(e);
        assertTrue(result2);

        boolean result3 = instance.addBicycle(r);
        assertTrue(result3);

        boolean result4 = instance2.addBicycle(m);
        assertTrue(!result4);

        boolean result5 = instance2.addBicycle(e);
        assertTrue(!result5);
    }

    /**
     * Test of deleteBicycle method, of class Park.
     */
    @Test
    public void testDeleteBicycle() {
        System.out.println("deleteBicycle");
        Bicycle b = new Bicycle("1", true, 4, 10, 3, 4);
        Mountain m = new Mountain("2", true, 3, 12, 3, 4);
        Electric e = new Electric(30, 15, 2, "3", true, 9, 15, 3, 4);
        Road r = new Road("4", true, 3, 6, 3, 4);
        Park Park = new Park(5, 6, 40, "12.34,56.78", "1", 3, 4);
        Park Park2 = new Park(0, 0, 40, "12.34,56.78", "2", 3, 4);
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.getBicycleRegistry().addNewbicycle(m, Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(e, Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(r, Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(b, Park2.getDescription());
        Park.addBicycle(m);
        Park.addBicycle(e);

        Park.addBicycle(r);
        Park2.addBicycle(r);

        boolean result = Park.deleteBicycle(b.getBicycleDesc());
        assertTrue(!result);
        boolean result1 = Park.deleteBicycle(m.getBicycleDesc());
        assertTrue(result1);
        boolean result2 = Park.deleteBicycle(e.getBicycleDesc());
        assertTrue(result2);
        boolean result3 = Park.deleteBicycle(r.getBicycleDesc());
        assertTrue(result3);
        boolean result4 = Park2.deleteBicycle(m.getBicycleDesc());
        assertTrue(!result4);
        boolean result5 = Park2.deleteBicycle(e.getBicycleDesc());
        assertTrue(!result5);

    }

    /**
     * Test of CalculateBikeEnergyBetweenPark method, of class Park.
     */
    @Test
    public void testCalculateBikeEnergyBetweenPark() {
        System.out.println("calculateBikeEnergyBetweenPark");
        Park Park = new Park(5, 6, 40, "42.152699, -9.609267", "1", 3, 4);
        Park Park2 = new Park(0, 0, 40, "42.152699, -9.409267", "2", 3, 4);
        Park Park3 = new Park(0, 0, 40, "42.152699, -9.209267", "3", 3, 4);
        Electric bike1 = new Electric(8, 5, 12, "1", true, 0, 0, 3, 4);
        Electric bike2 = new Electric(20, 20, 12, "2", true, 0, 0, 3, 4);
        Company.setParkRegistry(new ParkRegistry());
        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);
        Company.getParkRegistry().addNewPark(Park3);
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.getBicycleRegistry().addNewbicycle(bike1, "1");
        Company.getBicycleRegistry().addNewbicycle(bike2, "1");
        Park.addBicycle(bike1);
        Park.addBicycle(bike2);

        float expResult1 = bike1.getAtualBaterry() * bike1.getBaterrycapacity() / 100;
        float result1 = (float) Park.calculateBikeEnergy(bike1);
        assertEquals(expResult1, result1);

        float expResult2 = bike2.getAtualBaterry() * bike2.getBaterrycapacity() / 100;
        float result2 = (float) Park.calculateBikeEnergy(bike2);
        assertEquals(expResult2, result2);

        float expResult3 = bike1.getAtualBaterry() * bike1.getBaterrycapacity() / 100;
        float result3 = (float) Park.calculateBikeEnergy(bike1);
        assertEquals(expResult3, result3);

        float expResult4 = bike2.getAtualBaterry() * bike2.getBaterrycapacity() / 100;
        float result4 = (float) Park.calculateBikeEnergy(bike2);
        assertEquals(expResult4, result4);
    }

}
