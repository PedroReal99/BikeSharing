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
public class RideTest {

    public RideTest() {
    }

    /**
     * Test of getRideDate method, of class Ride.
     */
    @Test
    public void testGetRideDate() {
        System.out.println("getRideDate");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "12/12/2018";
        String result = instance.getRideDate();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRideDate method, of class Ride.
     */
    @Test
    public void testSetRideDate() {
        System.out.println("setRideDate");
        String rideDate = "20/07/2018";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setRideDate(rideDate);
        assertEquals(rideDate, instance.getRideDate());
    }

    /**
     * Test of getStartTime method, of class Ride.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "10:20";
        String result = instance.getStartTime();
        assertEquals(expResult, result);

    }

    /**
     * Test of setStartTime method, of class Ride.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        String startTime = "10:12";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setStartTime(startTime);
        assertEquals(startTime, instance.getStartTime());
    }

    /**
     * Test of getEndTime method, of class Ride.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "10:53";
        String result = instance.getEndTime();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEndTime method, of class Ride.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        String endTime = "10:57";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setEndTime(endTime);
        assertEquals(endTime, instance.getEndTime());
    }

    /**
     * Test of getRideId method, of class Ride.
     */
    @Test
    public void testGetRideId() {
        System.out.println("getRideId");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        int expResult = 1;
        int result = instance.getRideId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRideId method, of class Ride.
     */
    @Test
    public void testSetRideId() {
        System.out.println("setRideId");
        int rideId = 2;
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setRideId(rideId);
        assertEquals(rideId, instance.getRideId());
    }

    /**
     * Test of getCaloriesBurned method, of class Ride.
     */
    @Test
    public void testGetCaloriesBurned() {
        System.out.println("getCaloriesBurned");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        int expResult = 345;
        double result = instance.getCaloriesBurned();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCaloriesBurned method, of class Ride.
     */
    @Test
    public void testSetCaloriesBurned() {
        System.out.println("setCaloriesBurned");
        int caloriesBurned = 234;
        Ride instance = new Ride();
        instance.setCaloriesBurned(caloriesBurned);
        assertEquals(caloriesBurned, instance.getCaloriesBurned());
    }

    /**
     * Test of getBicycleDesc method, of class Ride.
     */
    @Test
    public void testGetBicycleDesc() {
        System.out.println("getBicycleDesc");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "1";
        String result = instance.getBicycleDesc();
        assertEquals(expResult, result);

    }

    /**
     * Test of setBicycleDesc method, of class Ride.
     */
    @Test
    public void testBicycleDesc() {
        System.out.println("setBicycleDesc");
        String bicycleDesc = "2";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setBicycleDesc(bicycleDesc);
        assertEquals(bicycleDesc, instance.getBicycleDesc());
    }

    /**
     * Test of setBicycleDesc method, of class Ride.
     */
    @Test
    public void testSetBicycleDesc() {
        System.out.println("setBicycleDesc");
        String bicycleId = "4";
        Ride instance = new Ride();
        instance.setBicycleDesc(bicycleId);
        assertEquals(bicycleId, instance.getBicycleDesc());
    }

    /**
     * Test of getHoursUsed method, of class Ride.
     */
    @Test
    public void testGetHoursUsed() {
        System.out.println("getHoursUsed");
        Ride instance = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 1, 100, "1");
        int expResult = 2;
        int result = instance.getHoursUsed();
        assertEquals(expResult, result);

        Ride instance2 = new Ride("20/12/2018", "23:22", "02:12", "1", "2", 1, 100, "1");
        int expResult2 = 2;
        int result2 = instance2.getHoursUsed();
        assertEquals(expResult2, result2);

        Ride instance3 = new Ride("20/12/2018", "23:22", "23:12", "1", "2", 1, 100, "1");
        int expResult3 = 0;
        int result3 = instance3.getHoursUsed();
        assertEquals(expResult3, result3);

    }

    /**
     * Test of getStartParkId method, of class Ride.
     */
    @Test
    public void testGetStartParkDesc() {
        System.out.println("getStartParkId");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "1";
        String result = instance.getStartParkDesc();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEndParkId method, of class Ride.
     */
    @Test
    public void testGetEndParkDesc() {
        System.out.println("getEndParkId");
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        String expResult = "2";
        String result = instance.getEndParkDesc();
        assertEquals(expResult, result);

    }

    /**
     * Test of setStartParkId method, of class Ride.
     */
    @Test
    public void testSetStartParkDesc() {
        System.out.println("setStartParkId");
        String startParkId = "5";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setStartParkDesc(startParkId);
        assertEquals(startParkId, instance.getStartParkDesc());

    }

    /**
     * Test of setEndParkId method, of class Ride.
     */
    @Test
    public void testSetEndParkDesc() {
        System.out.println("setEndParkId");
        String endParkId = "6";
        Ride instance = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        instance.setEndParkDesc(endParkId);
        assertEquals(endParkId, instance.getEndParkDesc());
    }

    /**
     * Test of hashCode method, of class Ride.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Ride instance = new Ride();
        int expResult = -1984164793;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Ride.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Ride instance = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 1, 100, "1");
        Ride instance2 = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 1, 100, "1");

        boolean result = instance.equals(instance2);
        assertTrue(result);

        boolean result2 = instance.equals(null);
        assertTrue(!result2);

        boolean result3 = instance.equals(new Bicycle());
        assertTrue(!result3);

        Ride instance3 = new Ride("20/12/201", "16:22", "18:43", "1", "2", 1, 100, "1");
        boolean result4 = instance.equals(instance3);
        assertTrue(!result4);

        Ride instance4 = new Ride("20/12/2018", "16:2", "18:43", "1", "2", 1, 100, "1");
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);

        Ride instance5 = new Ride("20/12/2018", "16:22", "18:4", "1", "2", 1, 100, "1");
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);

        Ride instance6 = new Ride("20/12/2018", "16:22", "18:43", "0", "2", 1, 100, "1");
        boolean result7 = instance.equals(instance6);
        assertTrue(!result7);

        Ride instance7 = new Ride("20/12/2018", "16:22", "18:43", "1", "1", 1, 100, "1");
        boolean result8 = instance.equals(instance7);
        assertTrue(!result8);

        Ride instance8 = new Ride("20/12/2018", "16:22", "18:43", "1", "1", 4, 100, "1");
        boolean result9 = instance.equals(instance8);
        assertTrue(!result9);

        Ride instance9 = new Ride("20/12/2018", "16:22", "18:43", "1", "1", 1, 2, "1");
        boolean result10 = instance.equals(instance9);
        assertTrue(!result10);

        Ride instance10 = new Ride("20/12/2018", "16:22", "18:43", "1", "1", 1, 100, "0");
        boolean result11 = instance.equals(instance10);
        assertTrue(!result11);

        Ride instance11 = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 6, 100, "1");
        boolean result12 = instance.equals(instance11);
        assertTrue(!result12);
    }

    /**
     * Test of toString method, of class Ride.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Ride instance = new Ride("20/12/2018", "16:22", "18:43", "1", "2", 6, 100, "1");
        String expResult = "Ride{rideDate=20/12/2018, startTime=16:22, endTime=18:43, startParkDesc=1, endParkDesc=2, bicycleId=1, rideId=6, caloriesBurned=100.0}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}
