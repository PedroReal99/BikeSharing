/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Ride;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hugov
 */
public class LockBicycleControllerTest {

    public LockBicycleControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of lockBicycle method, of class LockBicycle.
     */
    @Test
    public void testLockBicycle() {
        System.out.println("lockBicycle");
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        Company.setInvoiceList(new ArrayList<>());
        Calendar now = Calendar.getInstance();
        Park p1 = new Park(5, 6, 40, "12.34,56.78", "1", 3, 4);
        Park p2 = new Park(5, 6, 40, "12.34,56.78", "2", 3, 4);
        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);
        Electric e = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(e, "1");
        p1.addBicycle(e);
        User u = new User(1, "gandapatrick", 1.7f, 70, 123456789, "testeemailg47@gmail.com", 30, 2.0, "Pedro");
        Ride r = new Ride("12/12/2018", "10:20", "12:41", "1", "0", 1, 345, "1");

        Ride r2 = new Ride("12/12/2018", "12:43", "", "1", "0", 2, 345, "1");

        Company.getUserRegistry().addNewUser(u);
        u.addRide(r);
        u.addRide(r2);
        int userId = 1;
        String parkFinalId = "2";
        LockBicycleController instance = new LockBicycleController();
        Ride expResult = new Ride("12/12/2018", "12:43", now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE), "1", "2", 2, 345, "1");

        Ride result = instance.lockBicycle(userId, parkFinalId);
        assertEquals(expResult, result);

    }

    /**
     * Test of sendFromGMail method, of class LockBicycle.
     */
    @Test
    public void testSendFromGMail() {
        System.out.println("sendFromGMail");
        String from = "gandapatrick47@gmail.com";
        String pass = "gandapatrick";
        String to = "pintainhohugo@gmail.com";
        String subject = "teste";
        String body = "Mensagem enviada!";
        LockBicycleController instance = new LockBicycleController();

        boolean expResult = true;
        boolean result = instance.sendFromGMail(from, pass, to, subject, body);
        assertEquals(expResult, result);

        String from2 = "testeemailg47@gmail.com";
        String pass2 = "gandapatrick2";
        boolean expResult2 = false;
        boolean result2 = instance.sendFromGMail(from2, pass2, to, subject, body);
        assertEquals(expResult2, result2);

        String to2 = "gdsljgbçsaogsa@gmail.com";
        boolean expResult3 = false;
        boolean result3 = instance.sendFromGMail(from, pass, to2, subject, body);
        assertEquals(expResult3, result3);
    }

    /**
     * Test of chargeUser method, of class LockBicycle.
     */
    @Test
    public void testChargeUser() {
        System.out.println("chargeUser");
        Company.setUserRegistry(new UserRegistry());
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Ride r = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 1, 100, "1");
        Company.getUserRegistry().addNewUser(u);
        u.getRideMap().put(1, r);
        int userId = u.getUserId();
        int rideId = r.getRideId();
        LockBicycleController instance = new LockBicycleController();

        int expResult = 3;
        int result = instance.chargeUser(userId, rideId);
        assertEquals(expResult, result);

        User u2 = new User(2, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Ride r2 = new Ride("20/12/2018", "18:22", "18:43", "1", "2", 2, 100, "2");
        Company.getUserRegistry().addNewUser(u2);
        u2.getRideMap().put(2, r2);
        int userId2 = u2.getUserId();
        int rideId2 = r2.getRideId();

        int expResult2 = 0;
        int result2 = instance.chargeUser(userId2, rideId2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of lockBicycle method, of class LockBicycleController.
     */
    @Test
    public void testLockBicycle_String_String() {
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        Company.setInvoiceList(new ArrayList<>());
        Calendar now = Calendar.getInstance();
        System.out.println("lockBicycle");
        Park p1 = new Park(5, 6, 40, "12.34, 56.78", "1", 3, 4);
        User u = new User();
        Ride r2 = new Ride("20/12/2018", "18:22", "18:43", "", "", 0, 100, "mount");
        Mountain m = new Mountain("mount", 0, 0, 0);
        u.addRide(r2);
        Company.getParkRegistry().addNewPark(p1);
        Company.getUserRegistry().addNewUser(u);
        Company.getBicycleRegistry().addNewbicycle(m, m.getBicycleDesc());
        String location = "12.34, 56.78";

        String bikedesc = "mount";
        LockBicycleController instance = new LockBicycleController();
        long expResult = now.get(Calendar.HOUR_OF_DAY);
        long result = instance.lockBicycle(location, bikedesc);
        assertEquals(expResult, result);

    }

}
