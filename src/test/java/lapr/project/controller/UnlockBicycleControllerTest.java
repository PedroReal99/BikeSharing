/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import lapr.project.model.Bicycle;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Road;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hugov
 */
public class UnlockBicycleControllerTest {

    public UnlockBicycleControllerTest() {
    }

    /**
     * Test of unlockBike method, of class UnlockBicycleController.
     */
    @Test
    public void testUnlockBike() {
        System.out.println("unlockBike");
        UnlockBicycleController instance = new UnlockBicycleController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        Company.setInvoiceList(new ArrayList<>());

        Park p1 = new Park(5, 6, 40, "12.34,56.78", "1", 3, 4);
        Park p2 = new Park(5, 6, 40, "12.34,56.78", "2", 3, 4);
        Park p3 = new Park(5, 6, 40, "12.34,56.78", "3", 3, 4);

        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Electric e = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Bicycle m = new Mountain("2", true, 5, 30, 3, 4);
        Road r = new Road("3", true, 12.5f, 30, 3, 4);

        p1.addBicycle(e);
        p1.addBicycle(m);
        p1.addBicycle(r);

        p2.addBicycle(r);
        p3.addBicycle(m);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);
        Company.getParkRegistry().addNewPark(p3);
        Company.getBicycleRegistry().addNewbicycle(e, "1");
        Company.getBicycleRegistry().addNewbicycle(m, "2");
        Company.getBicycleRegistry().addNewbicycle(r, "3");
        Company.getUserRegistry().addNewUser(u);

        int userID = 1;
        String pId = "1";
        String type = "Electric";
        Bicycle expResult = new Electric(80, 30, 5, "1", false, 60, 30, 3, 4);
        Bicycle result = instance.unlockBike(userID, pId, type);
        assertEquals(expResult, result);

        String type1 = "Mountain";
        Bicycle expResult1 = new Mountain("2", false, 5, 30, 3, 4);
        Bicycle result1 = instance.unlockBike(userID, pId, type1);
        assertEquals(expResult1, result1);

        String type2 = "Road";
        Road expResult2 = new Road("3", false, 12.5f, 30, 3, 4);
        Bicycle result2 = instance.unlockBike(userID, pId, type2);
        assertEquals(expResult2, result2);

        String typee = "abc";
        Bicycle expResultt = null;
        Bicycle resultt = instance.unlockBike(userID, pId, typee);
        assertEquals(expResultt, resultt);

        String type3 = "Electric";
        Bicycle expResult3 = null;
        Bicycle result3 = instance.unlockBike(userID, pId, type3);
        assertEquals(expResult3, result3);

        String type4 = "Mountain";
        Bicycle expResult4 = null;
        Bicycle result4 = instance.unlockBike(userID, pId, type4);
        assertEquals(expResult4, result4);

        String type5 = "Road";
        Road expResult5 = null;
        Bicycle result5 = instance.unlockBike(userID, pId, type5);
        assertEquals(expResult5, result5);

        String pId2 = "2";
        String type6 = "Mountain";
        Bicycle expResult6 = null;
        Bicycle result6 = instance.unlockBike(userID, pId2, type6);
        assertEquals(expResult6, result6);

        String pId3 = "3";
        String type7 = "Road";
        Road expResult7 = null;
        Bicycle result7 = instance.unlockBike(userID, pId3, type7);
        assertEquals(expResult7, result7);

    }

    /**
     * Test of unlockSpecificBike method, of class UnlockBicycleController.
     */
    @Test
    public void testUnlockSpecificBike() {
        System.out.println("unlockSpecificBike");
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Company.getUserRegistry().addNewUser(u);
        Park p1 = new Park(5, 6, 40, "12.34,56.78", "1", 3, 4);
        Electric e = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Company.getParkRegistry().addNewPark(p1);
        Company.getBicycleRegistry().addNewbicycle(e, p1.getDescription());
        UnlockBicycleController instance = new UnlockBicycleController();
        Calendar now = Calendar.getInstance();
        long expResult = now.get(Calendar.HOUR_OF_DAY);
        long result = instance.unlockSpecificBike(e.getBicycleDesc(), u.getUsername());
        assertEquals(expResult, result);
    }

    /**
     * Test of unlockApropriateElectrical method, of class
     * UnlockBicycleController.
     */
    @Test
    public void testUnlockApropriateElectrical() {
        System.out.println("unlockApropriateElectrical");
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());

        int userID = 1;
        Park p1 = new Park(5, 6, 40, "12.34, 5.88", "1", 220, 16);
        Park p2 = new Park(5, 6, 40, "12.34, 5.78", "2", 220, 16);
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");

        String inicialParkDescription = "1";
        String endParkDescription = "2";
        Electric e = new Electric(400, 100, 5, "bike", true, 5, 12, 1.10f, 1);
        Electric e1 = new Electric(50, 0, 5, "bike2", true, 5, 12, 1.10f, 1);
        Connection c = new Connection(1, 45, 3);
        Connection c2 = new Connection(2, 315, 5);
        GraphController.loadVertex(p1.getDescription());
        GraphController.loadVertex(p2.getDescription());
        GraphController.addConnection(p1.getDescription(), p2.getDescription(), c, 300);
        GraphController.addConnection(p2.getDescription(), p1.getDescription(), c2, 300);
        p1.addBicycle(e);
        p2.addBicycle(e1);
        
        Company.getBicycleRegistry().addNewbicycle(e, p1.getDescription());
        Company.getBicycleRegistry().addNewbicycle(e1, p2.getDescription());
        Company.getUserRegistry().addNewUser(u);
        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);
        
        UnlockBicycleController instance = new UnlockBicycleController();
        Bicycle expResult = e;
        Bicycle result = instance.unlockApropriateElectrical(userID, inicialParkDescription, endParkDescription);
        assertEquals(expResult, result);
        
        Bicycle result1 = instance.unlockApropriateElectrical(userID, endParkDescription, inicialParkDescription);
        assertEquals(null,result1);
    }

    /**
     * Test of unlockAnyBicycleAtPark method, of class UnlockBicycleController.
     */
    @Test
    public void testUnlockAnyBicycleAtPark() throws Exception {
        Calendar now = Calendar.getInstance();
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        User u = new User("user", "email", 0, 0, 0, 0);
        Park p1 = new Park(5, 6, 40, "12.34, 5.88", "1", 220, 16);
        Park p2 = new Park(5, 6, 40, "12.3, 53.88", "2", 220, 16);
        Mountain m = new Mountain("bike", 0, 0, 0);
        Mountain m2 = new Mountain("bike", 0, 0, 0);
        m.setIsAvailable(true);
        p1.addBicycle(m);
        p2.addBicycle(m2);
        System.out.println("unlockAnyBicycleAtPark");
        String parkLocation = "12.34, 5.88";
        String username = "user";
        String outputFileName = "bicycleO.csv";
        Company.getUserRegistry().addNewUser(u);
        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);
        Company.getBicycleRegistry().addNewbicycle(m, m2.getBicycleDesc());
        Company.getBicycleRegistry().addNewbicycle(m2, m2.getBicycleDesc());
        
        UnlockBicycleController instance = new UnlockBicycleController();
        long expResult = now.get(Calendar.HOUR_OF_DAY);
        long result = instance.unlockAnyBicycleAtPark(parkLocation, username, outputFileName);
        assertEquals(expResult, result);
        
        long result2 = instance.unlockAnyBicycleAtPark("12.3, 53.88", username, outputFileName);
        long expResult2 = 100;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of unlockAnyElectricBicycleAtPark method, of class
     * UnlockBicycleController.
     */
    @Test
    public void testUnlockAnyElectricBicycleAtPark() throws Exception {
        System.out.println("unlockAnyElectricBicycleAtPark");
        Calendar now = Calendar.getInstance();
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        User u = new User("user", "email", 0, 0, 0, 0);
        Park p1 = new Park(5, 6, 40, "12.34, 5.88", "1", 220, 16);
        Electric e = new Electric("elec", false, 0, 0, 0, 0);
        Electric e2 = new Electric("elec2", true, 0, 0, 0, 0);
        p1.addBicycle(e);
        p1.addBicycle(e2);
        String parkLocation = "12.34, 5.88";
        String username = "user";
        String outputFileName = "bicycle=.csv";
        Company.getUserRegistry().addNewUser(u);
        Company.getParkRegistry().addNewPark(p1);
        Company.getBicycleRegistry().addNewbicycle(e, e.getBicycleDesc());
        Company.getBicycleRegistry().addNewbicycle(e2, e2.getBicycleDesc());
        UnlockBicycleController instance = new UnlockBicycleController();
        
        long expResult = now.get(Calendar.HOUR_OF_DAY);
        long result = instance.unlockAnyElectricBicycleAtPark(parkLocation, username, outputFileName);
        assertEquals(expResult, result);
        
        p1.deleteBicycle(e2.getBicycleDesc());
        
        long expResult2 = 100;
        long result2 = instance.unlockAnyElectricBicycleAtPark(parkLocation, username, outputFileName);
        assertEquals(expResult2, result2);
    }

}
