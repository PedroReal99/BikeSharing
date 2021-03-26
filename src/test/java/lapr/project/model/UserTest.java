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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author USER
 */
public class UserTest {

    public UserTest() {
    }

    @Test
    public void testUserTest() {
        User user = new User("Patrick", "email", 1, 1, 1, 1);
        assertEquals("Patrick", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals(1, user.getHeight());
        assertEquals(1, user.getWeight());
        assertEquals(1, user.getAverageSpeed());
        assertEquals(1, user.getCreditCard());
    }

    /**
     * Test of getUserId method, of class User.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");

        int expResult = 1;
        int result = instance.getUserId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUserId method, of class User.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 5;
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }

    /**
     * Test of getHeight method, of class User.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        float expResult = 1.7F;
        float result = instance.getHeight();
        assertEquals(expResult, result, 1.7);

    }

    /**
     * Test of setHeight method, of class User.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        float height = 1.8F;
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setHeight(height);
        assertEquals(height, instance.getHeight());
    }

    /**
     * Test of getWeight method, of class User.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        float expResult = 70;
        float result = instance.getWeight();
        assertEquals(expResult, result, 70);

    }

    /**
     * Test of setWeight method, of class User.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        float weight = 55;
        User instance = new User();
        instance.setWeight(weight);
        assertEquals(weight, instance.getWeight());
    }

    /**
     * Test of getCreditCard method, of class User.
     */
    @Test
    public void testGetCreditCard() {
        System.out.println("getCreditCard");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        int expResult = 123456789;
        int result = instance.getCreditCard();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCreditCard method, of class User.
     */
    @Test
    public void testSetCreditCard() {
        System.out.println("setCreditCard");
        int creditCard = 123;
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setCreditCard(creditCard);
        assertEquals(creditCard, instance.getCreditCard());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        String expResult = "hugovinhal98@gmail.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "hugo@gmail.com";
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());

    }

    /**
     * Test of getRideList method, of class User.
     */
    @Test
    public void testGetRideList() {
        System.out.println("getRideList");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        Ride r = new Ride("qwe", "312", "312", "1", "2", 0, 0, "1");
        Ride r2 = new Ride("qe", "32", "32", "1", "2", 3, 4, "1");
        instance.addRide(r);
        instance.addRide(r2);
        Map<Integer, Ride> expResult = new HashMap<>();
        expResult.put(r.getRideId(), r);
        expResult.put(r2.getRideId(), r2);
        Map<Integer, Ride> result = instance.getRideMap();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRideList method, of class User.
     */
    @Test
    public void testSetRideList() {
        System.out.println("setRideList");
        Map<Integer, Ride> rideMap = new HashMap<>();
        Ride r = new Ride("qwe", "312", "312", "1", "2", 0, 0, "1");
        rideMap.put(r.getRideId(), r);
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setRideMap(rideMap);
        assertEquals(rideMap, instance.getRideMap());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        String expResult = "amo_lapr3";
        String result = instance.getPassword();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "nao_amo";
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        int expResult = 7;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        User instance2 = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        User instance3 = new User(2, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");

        boolean result = instance.equals(instance2);  //true
        assertTrue(result);

        boolean result2 = instance.equals(instance3); // false id
        assertTrue(!result2);

        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        boolean result3 = instance.equals(r);   // false class
        assertTrue(!result3);

        boolean result4 = instance.equals(null);
        assertTrue(!result4);

        User instance4 = new User(1, "lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);

        User instance5 = new User(1, "amo_lapr3", 1.9f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);

        User instance6 = new User(1, "amo_lapr3", 1.7f, 76, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        boolean result7 = instance.equals(instance6);
        assertTrue(!result7);

        User instance7 = new User(1, "amo_lapr3", 1.7f, 70, 123, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        boolean result8 = instance.equals(instance7);
        assertTrue(!result8);

        User instance8 = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal@gmail.com", 30, 2.0, "Pedro");
        boolean result9 = instance.equals(instance8);
        assertTrue(!result9);

        User instance9 = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 10, 2.0, "Pedro");
        boolean result10 = instance.equals(instance9);
        assertTrue(!result10);
    }

    /**
     * Test of getRideMap method, of class User.
     */
    @Test
    public void testGetRideMap() {
        System.out.println("getRideMap");
        User instance = new User();
        Map<Integer, Ride> expResult = new HashMap<>();
        Map<Integer, Ride> result = instance.getRideMap();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRideMap method, of class User.
     */
    @Test
    public void testSetRideMap() {
        System.out.println("setRideMap");
        Map<Integer, Ride> rideMap = new HashMap<>();
        User instance = new User();
        instance.setRideMap(rideMap);
        assertEquals(rideMap, instance.getRideMap());

    }

    /**
     * Test of getRideById method, of class User.
     */
    @Test
    public void testGetRideById() {
        System.out.println("getRideById");
        int rideId = 1;
        Map<Integer, Ride> rideMap = new HashMap<>();
        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        rideMap.put(rideId, r);
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setRideMap(rideMap);
        Ride expResult = r;
        Ride result = instance.getRideById(rideId);
        assertEquals(expResult, result);

    }

    /**
     * Test of addRide method, of class User.
     */
    @Test
    public void testAddRide() {
        System.out.println("addRide");
        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        User instance = new User();
        instance.addRide(r);
        instance.addRide(r);
    }

    /**
     * Test of getPoints method, of class User.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        int expResult = 30;
        int result = instance.getPoints();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPoints method, of class User.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        int points = 14;
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        instance.setPoints(points);
        assertEquals(instance.getPoints(), points);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30, 2.0, "Pedro");
        String expResult = "user{userId=1, password=amo_lapr3, height=1.7, weight=70.0, creditCard=123456789, email=hugovinhal98@gmail.com, rideMap={}, newRideList=[]}, points: 30";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of getNewRideList method, of class User.
     */
    @Test
    public void testGetNewRideList() {
        System.out.println("getNewRideList");
        User instance = new User();
        List<Ride> expResult = new ArrayList<>();
        List<Ride> result = instance.getNewRideList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getInvoiceList method, of class User.
     */
    @Test
    public void testGetInvoiceList() {
        System.out.println("getInvoiceList");
        User instance = new User();
        List<Invoice> expResult = new ArrayList<>();
        List<Invoice> result = instance.getInvoiceList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getReceiptList method, of class User.
     */
    @Test
    public void testGetReceiptList() {
        System.out.println("getReceiptList");
        User instance = new User();
        List<Receipt> expResult = new ArrayList<>();
        List<Receipt> result = instance.getReceiptList();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNewRideList method, of class User.
     */
    @Test
    public void testSetNewRideList() {
        System.out.println("setNewRideList");
        List<Ride> newRideList = new ArrayList<>();
        User instance = new User();
        instance.setNewRideList(newRideList);

    }

    /**
     * Test of setInvoiceList method, of class User.
     */
    @Test
    public void testSetInvoiceList() {
        System.out.println("setInvoiceList");
        List<Invoice> invoiceList = new ArrayList<>();
        User instance = new User();
        instance.setInvoiceList(invoiceList);

    }

    /**
     * Test of setReceiptList method, of class User.
     */
    @Test
    public void testSetReceiptList() {
        System.out.println("setReceiptList");
        List<Receipt> receiptList = new ArrayList<>();
        User instance = new User();
        instance.setReceiptList(receiptList);

    }

    /**
     * Test of invoicesTotalCost method, of class User.
     */
    @Test
    public void testInvoicesTotalCost() {
        System.out.println("invoicesTotalCost");
        User instance = new User();
        Invoice i1 = new Invoice(1, "23/04/2018", "April");
        Invoice i2 = new Invoice(1, "23/04/2018", "April");
        instance.getInvoiceList().add(i1);
        instance.getInvoiceList().add(i2);
        float expResult = 0F;
        float result = instance.invoicesTotalCost();
        assertEquals(expResult, result);

    }

    /**
     * Test of getInvoiceByMonth method, of class User.
     */
    @Test
    public void testGetInvoiceByMonth() {
        System.out.println("getInvoiceByMonth");
        String m = "jan";
        User u = new User();
        Invoice expResult = new Invoice(1, "23/04/2018", "jan");
        u.getInvoiceList().add(expResult);
        Invoice result = u.getInvoiceByMonth(m);
        assertEquals(expResult, result);

        Invoice expResult2 = null;
        Invoice result2 = u.getInvoiceByMonth("Apr");
        assertEquals(expResult2, result2);
    }

    /**
     * Test of addInvoice method, of class User.
     */
    @Test
    public void testAddInvoice() {
        System.out.println("addInvoice");
        Invoice e = new Invoice(1, "23/04/2018", "jan");
        User instance = new User();
        instance.addInvoice(e);

    }

    /**
     * Test of addReceipt method, of class User.
     */
    @Test
    public void testAddReceipt() {
        System.out.println("addReceipt");
        Receipt rec = new Receipt();
        User instance = new User();
        instance.addReceipt(rec);

        List<Receipt> expResult = new ArrayList<>();
        expResult.add(rec);

        assertEquals(expResult, instance.getReceiptList());

    }

    /**
     * Test of getAverageSpeed method, of class User.
     */
    @Test
    public void testGetAverageSpeed() {
        System.out.println("getAverageSpeed");
        User instance = new User(0, "ww", 0, 0, 0, "ww", 0, 2.3f, "mo");
        double expResult = 2.3f;
        double result = instance.getAverageSpeed();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAverageSpeed method, of class User.
     */
    @Test
    public void testSetAverageSpeed() {
        System.out.println("setAverageSpeed");
        double averageSpeed = 7;
        User instance = new User(0, "ww", 0, 0, 0, "ww", 0, 2.3f, "mo");
        instance.setAverageSpeed(averageSpeed);
        assertEquals(instance.getAverageSpeed(), averageSpeed);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User(0, "ww", 0, 0, 0, "ww", 0, 2.3f, "mo");
        String expResult = "mo";
        String result = instance.getUsername();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "eu";
        User instance = new User(0, "ww", 0, 0, 0, "ww", 0, 2.3f, "mo");
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

}
