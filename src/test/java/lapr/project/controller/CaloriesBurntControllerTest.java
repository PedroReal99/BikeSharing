/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ride;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class CaloriesBurntControllerTest {

    public CaloriesBurntControllerTest() {
    }

    /**
     * Test of calculateCalories method, of class caloriesBurntController.
     */
    @Test
    public void testCalculateCalories() {
        System.out.println("calculateCalories");
        Company.setUserRegistry(new UserRegistry());
        int userId = 10;
        int rideId = 10;
       //userRegistry uR = Company.getUserRegistry();
        User u = new User(10, "abc");
        Ride r = new Ride();
        r.setRideId(10);
        r.setStartTime("12:32:20");
        r.setEndTime("12:57:10");
        u.addRide(r);
        u.setWeight(80);
        Company.getUserRegistry().addNewUser(u);
        //uR.addNewUser(u);

        CaloriesBurntController instance = new CaloriesBurntController();
        double expResult = 132.0;
        double result = instance.calculateCalories(userId, rideId);

        assertEquals(expResult, result, 1);
    }

}
