/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;

/**
 *
 * @author Leandro
 */
public class EstimateProjectionForChargingController {
    public double getProjectionForChargingBicycle(String bicycleId, String parkId){
        
        BicycleRegistry br = Company.getBicycleRegistry();
        ParkRegistry pr = Company.getParkRegistry();
        
        Park p = pr.getParkByDescription(parkId);
        
        return br.calculateProjectionForChargingBicycle(bicycleId, p);
    }
}
