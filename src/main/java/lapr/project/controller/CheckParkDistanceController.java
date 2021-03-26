/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.utils.Calculator;

/**
 *
 * @author User
 */
public class CheckParkDistanceController {

    public double checkParkDistance(String location, String parkDesc) {
        Park park = Company.getParkRegistry().getParkByDescription(parkDesc);

        return Calculator.getDistance(location, park.getLocation());
    }
}
