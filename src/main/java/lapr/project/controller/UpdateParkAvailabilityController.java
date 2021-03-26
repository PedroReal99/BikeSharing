/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.Road;

/**
 *
 * @author USER
 */
public class UpdateParkAvailabilityController {

    public String updateAvailability(String p, Bicycle b) {
        if (!Company.getParkRegistry().checkPark(p)) {
            Park Park = Company.getParkRegistry().getParkByDescription(p);
            if (Park.addBicycle(b)) {
                if (b.getClass() == Electric.class) {
                    return "Now there are only " + Park.availableElectricalSpots() + " eletric bicycle spots!";
                } else if (b.getClass() == Mountain.class || b.getClass() == Road.class) {
                    return "Now there are only " + Park.availableMountainRoadBikes() + " mountain and road bicycle spots!";
                }
            }
            return "No available spots for the bicycle!";
        }
        return "The park doesn't exist in the company!";
    }

}
