/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ride;
import lapr.project.model.User;
import lapr.project.utils.Calculator;

/**
 *
 * @author User
 */
public class CaloriesBurntController {
    public double calculateCalories(int userId,int rideId){
        User u =Company.getUserRegistry().getUserMap().get(userId);
        Ride r = u.getRideMap().get(rideId);
        double time = Calculator.getTime(r.getStartTime(), r.getEndTime());
        return Calculator.getCaloriesBurned(time,u.getWeight());
    }
}
