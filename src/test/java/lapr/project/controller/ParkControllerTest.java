/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lapr.project.model.Bicycle;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Electric;
import lapr.project.model.Location;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Ride;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author titi1
 */
public class ParkControllerTest {

    public ParkControllerTest() {
    }

    /**
     * Test of addPark method, of class ParkController.
     */
    @Test
    public void testAddPark() {
        System.out.println("addPark");
        Park park = new Park(50, 30, 2566, "10.324, -5.324", "location", 3, 4);
        Map<String, Location> parkMap = new TreeMap<>();
        parkMap.put(park.getDescription(), park);
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        Company.setInvoiceList(new ArrayList<>());
        ParkController instance = new ParkController();
        instance.addPark(park);
        Map<String, Location> result = Company.getParkRegistry().getParkMap();
        assertEquals(result, parkMap);
    }

    @Test
    public void testUpdatePark() {
        System.out.println("updatePark");
        Park park = new Park(50, 30, 2566, "10.324, -5.324", "location", 3, 4);
        ParkController instance = new ParkController();
        Company.getParkRegistry().addNewPark(park);
        park.setAltitude(52);
        boolean result = instance.updatepark(new Park(50, 30, 52, "10.324, -5.324", "location", 3, 4));
        assertTrue(result);
        assertEquals(park, Company.getParkRegistry().getParkMap().get(park.getDescription()));
        boolean result1 = instance.updatepark(new Park(50, 30, 52, "10.324, -5.324", "loction", 3, 4));
        assertTrue(!result1);

    }

    @Test
    public void testDeletePark() {
        System.out.println("deletePark");
        ParkController instance = new ParkController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park Park1 = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park Park2 = new Park(12, 3, 50, "10.324, -5.324", "Csa da Musica", 3, 4);
        Park Park3 = new Park(0, 0, 50, "10.324, -5.324", "Casa da usica", 3, 4);
        Park Park4 = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musca", 3, 4);

        Park Park5 = new Park(12, 3, 50, "10.324, -5.324", "Casa da Music", 3, 4);

        Bicycle bike1 = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Bicycle bike2 = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Bicycle bike3 = new Mountain("3", true, 5, 30, 3, 4);
        Bicycle bike4 = new Mountain("4", true, 5, 30, 3, 4);
        Park1.addBicycle(bike1);
        Park1.addBicycle(bike2);
        Park1.addBicycle(bike3);
        Park1.addBicycle(bike4);
        Company.getBicycleRegistry().getBicycleMap().put(bike1.getBicycleDesc(), bike1);
        Company.getBicycleRegistry().getBicycleMap().put(bike2.getBicycleDesc(), bike2);
        Company.getBicycleRegistry().getBicycleMap().put(bike3.getBicycleDesc(), bike3);
        Company.getBicycleRegistry().getBicycleMap().put(bike4.getBicycleDesc(), bike4);

        Company.getParkRegistry().addNewPark(Park1);
        Company.getParkRegistry().addNewPark(Park2);
        Company.getParkRegistry().addNewPark(Park3);
        Company.getParkRegistry().addNewPark(Park4);

        boolean res = instance.deletePark(Park1.getDescription());
        assertTrue(res);
        boolean res2 = instance.deletePark(Park5.getDescription());  // Park doesnt exist
        assertTrue(!res2);

    }

    /**
     * Test of availableElectricalSpots method, of class
     * ParkingLoanedBicycleController.
     */
    @Test
    public void testAvailableElectricalSpots() {
        System.out.println("availableElectricalSpots");
        Bicycle bike = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);

        ParkController instance = new ParkController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park Park = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);

        Company.getParkRegistry().addNewPark(Park);
        Park Park2 = new Park(12, 3, 50, "10.324, -5.324", "Casa da Msica", 3, 4);

        Park.addBicycle(bike);

        int expResult = 2;

        int result = instance.availableElectricalSpots(Park.getDescription());
        assertEquals(expResult, result);

        int expResult2 = -1;
        int result2 = instance.availableElectricalSpots(Park2.getDescription());
        assertEquals(expResult2, result2);

    }

    /**
     * Test of availableMountainRoadSpots method, of class
     * ParkingLoanedBicycleController.
     */
    @Test
    public void testAvailableMountainRoadSpots() {
        System.out.println("availableMountainRoadSpots");
        ParkController instance = new ParkController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Bicycle bike = new Mountain("2", true, 5, 30, 3, 4);

        Park Park = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park Park2 = new Park(12, 3, 50, "10.324, -5.324", "Cas da Musica", 3, 4);

        Park.addBicycle(bike);
        Company.getParkRegistry().addNewPark(Park);

        int expResult = 11;
        int result = instance.availableMountainRoadSpots(Park.getDescription());
        assertEquals(expResult, result);

        int expResult2 = -1;
        int result2 = instance.availableMountainRoadSpots(Park2.getDescription());
        assertEquals(expResult2, result2);

    }

    @Test
    public void testsplitPotency() {
        System.out.println("splitPotency");
        ParkController instance = new ParkController();

        Park Park = new Park(12, 4, 50, "10.324, -5.324", "Casa da Musica", 220, 16);
        Park Park2 = new Park(12, 0, 50, "10.324, -5.324", "Casa a Musica", 3, 4);
        Park Park3 = new Park(12, 1, 50, "10.324, -5.324", "Casa dMusica", 3, 4);

        Company.setParkRegistry(new ParkRegistry());
        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);
        Company.getParkRegistry().addNewPark(Park3);
        double expResult = 0.88;
        double result = instance.splitPotency(Park.getDescription());
        assertEquals(expResult, result);

        double expResult2 = 0;
        double result2 = instance.splitPotency(Park2.getDescription());
        assertEquals(expResult2, result2);

        double expResult3 = 3;
        double result3 = instance.splitPotency(Park3.getDescription());
        assertEquals(expResult3, result3);

    }

    @Test
    public void testbicycleWithEnergyNeeded() {
        System.out.println("bicycleWithEenergyNeeded");
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        Park park = new Park(30, 7, 200, "42.152699, -9.209267", "Trindade", 220, 16);
        Park park2 = new Park(0, 0, 40, "42.152699, -9.409267", "Casa da Musica", 220, 16);
        Bicycle bike1 = new Electric(0.5f, 5, 12, "1", true, 0, 0, 0, 0);
        Bicycle bike2 = new Electric(20, 45, 12, "2", true, 0, 0, 0, 0);
        Bicycle bike3 = new Electric(0.75f, 14, 12, "3", true, 0, 0, 0, 0);
        Bicycle bike4 = new Electric(12, 100, 12, "4", true, 0, 0, 0, 0);
        Bicycle bike5 = new Electric(10, 95, 12, "5", true, 0, 0, 0, 0);
        User user = new User(1, "123456", 180, 80, 12368521, "email", 30, 12, "Patrick");
        ParkController instance = new ParkController();
        Connection c = new Connection(1, 45, 3);
        Connection c2 = new Connection(2, 315, 5);
        GraphController.loadVertex(park.getDescription());
        GraphController.loadVertex(park2.getDescription());
        GraphController.addConnection(park.getDescription(), park2.getDescription(), c, 300);
        GraphController.addConnection(park2.getDescription(), park.getDescription(), c2, 300);
        Company.getBicycleRegistry().addNewbicycle(bike1, "Casa da Musica");
        Company.getBicycleRegistry().addNewbicycle(bike2, "Casa da Musica");
        Company.getBicycleRegistry().addNewbicycle(bike3, "Casa da Musica");
        Company.getBicycleRegistry().addNewbicycle(bike4, "Casa da Musica");
        Company.getBicycleRegistry().addNewbicycle(bike5, "Casa da Musica");
        park.addBicycle(bike1);
        park.addBicycle(bike2);
        park.addBicycle(bike3);
        park.addBicycle(bike4);
        park.addBicycle(bike5);
        Company.getParkRegistry().addNewPark(park);
        Company.getParkRegistry().addNewPark(park2);
        Company.getUserRegistry().addNewUser(user);

        List<Bicycle> expResult = new ArrayList<>();
        expResult.add(bike4);

        List<Bicycle> result = instance.bicycleWithEnergyNeeded("Trindade", "Casa da Musica", user);

        assertEquals(expResult, result);
    }

    @Test
    public void testchargeReport() {
        System.out.println("chargeReport");
        ParkController instance = new ParkController();
        Park Park = new Park(12, 4, 50, "10.324, -5.324", "1", 3, 4);
        Company.setParkRegistry(new ParkRegistry());
        Company.getParkRegistry().addNewPark(Park);
        Electric bike = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Electric bike1 = new Electric(50, 30, 4, "2", true, 10000, 30, 3, 4);
        Mountain bike2 = new Mountain("2", true, 5, 30, 3, 4);
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.getBicycleRegistry().addNewbicycle(bike, "1");
        Company.getBicycleRegistry().addNewbicycle(bike1, "1");
        Company.getBicycleRegistry().addNewbicycle(bike2, "1");
        Park.addBicycle(bike);
        Park.addBicycle(bike1);
        Park.addBicycle(bike2);
        String[][] result = instance.chargeReport("1");
        String[][] expResult = new String[2][2];
        expResult[0][0] = bike.getBicycleDesc();
        expResult[0][1] = String.valueOf(bike.getAtualBaterry() * 100 / bike.getBaterrycapacity());
        expResult[1][0] = bike1.getBicycleDesc();
        expResult[1][1] = String.valueOf(bike1.getAtualBaterry() * 100 / bike1.getBaterrycapacity());
        assertArrayEquals(expResult, result);

        String[][] result1 = instance.chargeReport("2");
        String[][] expResult1 = null;
        assertArrayEquals(expResult1, result1);
    }

    /**
     * Test of getFreeSlotsAtPArk method, of class ParkController.
     */
    @Test
    public void testGetFreeSlotsAtPArk() {
        ParkController instance = new ParkController();
        Company.setUserRegistry(new UserRegistry());
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.getUserRegistry().addNewUser(u);
        Park Park = new Park(12, 4, 50, "10.324, -5.324", "1", 3, 4);
        Company.getParkRegistry().addNewPark(Park);
        Ride ride = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "2");
        u.addRide(ride);
        Electric bike1 = new Electric(50, 30, 4, "2", false, 10000, 30, 3, 4);
        Mountain bike2 = new Mountain("2", false, 5, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(bike1, Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(bike2, Park.getDescription());

        int expResult = 4;
        int result = instance.getFreeSlotsAtPArk(Park.getLocation(), u.getUsername());

        assertEquals(expResult, result);
    }

}
