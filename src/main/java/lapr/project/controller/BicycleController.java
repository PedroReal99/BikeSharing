/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Park;

/**
 *
 * @author titi1
 */
public class BicycleController {

    public boolean addBicycle(Bicycle b, String p) {

        if (!Company.getParkRegistry().checkPark(p)) {
            Park Park = Company.getParkRegistry().getParkByDescription(p);
            if (Company.getBicycleRegistry().checkBicycle(b.getBicycleDesc())) {
                if (Park.addBicycle(b)) {
                    Company.getBicycleRegistry().addNewbicycle(b, p);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateBicycle(Bicycle bike) {
        return Company.getBicycleRegistry().addUpdatedbicycle(bike);
    }

    public boolean deleteBicycle(String b, String parkDesc) {
        if (!Company.getParkRegistry().checkPark(parkDesc)) {
            if (!Company.getBicycleRegistry().checkBicycle(b)) {
                Park Park = Company.getParkRegistry().getParkByDescription(parkDesc);
                if (Park.deleteBicycle(b)) {
                    Company.getBicycleRegistry().addDeletedbicycle(b);
                    return true;
                }
            }
        }
        return false;
    }
}
