/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Collection;
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
public class ParkRegistryTest {

    public ParkRegistryTest() {
    }

    /**
     * Test of getParkMap method, of class ParkRegistry.
     */
    @Test
    public void testGetParkMap() {
        System.out.println("getParkMap");
        ParkRegistry instance = new ParkRegistry();
        Park p = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Map<String, Park> parkMap = new TreeMap<>();
        parkMap.put(p.getDescription(), p);
        instance.getParkMap().put(p.getDescription(), p);
        Map<String, Location> result = instance.getParkMap();
        assertEquals(parkMap, result);

    }

    /**
     * Test of addNewPark method, of class ParkRegistry.
     */
    @Test
    public void testAddNewPark() {
        System.out.println("addNewPark");
        Park p = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        ParkRegistry instance = new ParkRegistry();
        instance.addNewPark(p);
        assertEquals(p, instance.getParkMap().get(p.getDescription()));
        List<String> listResult = new ArrayList<>();
        listResult.add(p.getDescription());
        assertEquals(listResult, instance.getNewparks());
    }

    /**
     * Test of addUpdatedPark method, of class ParkRegistry.
     */
    @Test
    public void testAddUpdatedPark() {
        System.out.println("addUpdatedPark");
        Park p = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        ParkRegistry instance = new ParkRegistry();
        instance.addNewPark(p);
        p.setCapacityElectric(80);
        instance.addUpdatedPark(p);
        assertEquals(p, instance.getParkMap().get(p.getDescription()));
        List<String> listResult = new ArrayList<>();
        listResult.add(p.getDescription());
        assertEquals(listResult, instance.getUpdatedparks());

    }

    /**
     * Test of addDeletedPark method, of class ParkRegistry.
     */
    @Test
    public void testAddDeletedPark() {
        System.out.println("addDeletedPark");
        Park p = new Park(20, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        ParkRegistry instance = new ParkRegistry();
        instance.addNewPark(p);
        instance.addDeletedPark(p.getDescription());
        assertEquals(null, instance.getParkMap().get(p.getDescription()));
        List<String> listResult = new ArrayList<>();
        listResult.add(p.getDescription());
        assertEquals(listResult, instance.getDeletedparks());

        HashMap<String, Location> hp = new HashMap<>();
        ParkRegistry pr = new ParkRegistry(hp);
    }

    /**
     * Test of checkPark method, of class ParkRegistry.
     */
    @Test
    public void testCheckPark() {
        System.out.println("checkPark");
        Park p = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park p1 = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        ParkRegistry instance = new ParkRegistry();
        boolean result = instance.checkPark(p.getDescription());
        assertTrue(result);
        instance.addNewPark(p);
        boolean result1 = instance.checkPark(p1.getDescription());
        assertTrue(!result1);
    }

    @Test
    public void testGetNearestParks() {
        System.out.println("getNearestParks");
        Company.setParkRegistry(new ParkRegistry());
        ParkRegistry pr = Company.getParkRegistry();
        pr.addNewPark(new Park(10, 10, 10, "42.152699, -8.609267", "10", 3, 4));
        pr.addNewPark(new Park(9, 9, 9, "43.152699, -8.609267", "9", 3, 4));
        pr.addNewPark(new Park(8, 8, 8, "41.152699, -9.309267", "8", 3, 4));
        pr.addNewPark(new Park(7, 7, 7, "61.152699, -8.609267", "7", 3, 4));
        pr.addNewPark(new Park(6, 6, 6, "41.652699, -8.609267", "6", 3, 4));
        pr.addNewPark(new Park(5, 5, 5, "41.152699, -30.609267", "5", 3, 4));

        String location = "41.152699, -8.609267";
        int maxNumberParks = 3;
        List<Park> expResult = new ArrayList<>();

        expResult.add(new Park(6, 6, 6, "41.652699, -8.609267", "6", 3, 4));
        expResult.add(new Park(8, 8, 8, "41.152699, -9.309267", "8", 3, 4));
        expResult.add(new Park(10, 10, 10, "42.152699, -8.609267", "10", 3, 4));

        Collection<Park> result = pr.getNearestParks(location, maxNumberParks);
        int i = 0;
        for (Location p : result) {
            assertEquals(p, expResult.get(i++));
        }
    }

    @Test
    public void testGetParkById() {
        System.out.println("getParkById");
        Park expected = new Park(20, 30, 50, "10.324, -5.324", "1", 3, 4);
        ParkRegistry instance = new ParkRegistry();
        instance.addNewPark(expected);
        Location result = instance.getParkByDescription("1");
        assertEquals(result, expected);
    }

    @Test
    public void calculateAwardPointsBetweenParks() {
        ParkRegistry instance = new ParkRegistry();
        Company.setParkRegistry(instance);

        Park p1 = new Park(12, 3, 1, "10.324, -5.324", "1", 3, 4);
        Park p2 = new Park(12, 3, 51, "10.324, -5.324", "2", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        String id1 = p1.getDescription();
        String id2 = p2.getDescription();
        int expected = 15;
        int result = instance.calculateAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);

    }

    @Test
    public void calculateAwardPointsBetweenParks2() {
        ParkRegistry instance = new ParkRegistry();
        Company.setParkRegistry(instance);

        Park p1 = new Park(12, 3, 1, "10.324, -5.324", "1", 3, 4);
        Park p2 = new Park(12, 3, 26, "10.324, -5.324", "2", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        String id1 = p1.getDescription();
        String id2 = p2.getDescription();
        int expected = 5;
        int result = instance.calculateAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);

    }

    @Test
    public void calculateAwardPointsBetweenParks3() {
        ParkRegistry instance = new ParkRegistry();
        Company.setParkRegistry(instance);

        Park p1 = new Park(12, 3, 20, "10.324, -5.324", "1", 3, 4);
        Park p2 = new Park(12, 3, 20, "10.324, -5.324", "2", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        String id1 = p1.getDescription();
        String id2 = p2.getDescription();
        int expected = 0;
        int result = instance.calculateAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);

    }

    @Test
    public void calculateAwardPointsBetweenParks4() {
        ParkRegistry instance = new ParkRegistry();
        Company.setParkRegistry(instance);

        Park p1 = new Park(12, 3, 12, "10.324, -5.324", "1", 3, 4);
        Park p2 = new Park(12, 3, 2, "10.324, -5.324", "2", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        String id1 = p1.getDescription();
        String id2 = p2.getDescription();
        int expected = 0;
        int result = instance.calculateAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);

    }

    /**
     * Test of getParkByLocation method, of class ParkRegistry.
     */
    @Test
    public void testGetParkByLocation() {
        System.out.println("getParkByLocation");
        String location = "123,456";
        Company.setParkRegistry(new ParkRegistry());
        Park p1 = new Park(12, 3, 12, "123,456", "1", 3, 4);
        Company.getParkRegistry().addNewPark(p1);

        Location expResult = p1;
        Location result = Company.getParkRegistry().getLocByLocation(location);
        assertEquals(expResult, result);

        Location result2 = Company.getParkRegistry().getLocByLocation("12312");
        Location expResult2 = null;
        assertEquals(result2, expResult2);
    }
}
