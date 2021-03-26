/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Park;

/**
 *
 * @author USER
 */
public class FindAvailableBikesOnAParkController {

    public List<Bicycle> availableBikes(String p) {
        List<Bicycle> bikesAvailable = new ArrayList<>();
        Map<String, Bicycle> bicycleMap = Company.getBicycleRegistry().getBicycleMap();
        Park pa = (Park) Company.getParkRegistry().getLocByLocation(p);
        if (!Company.getParkRegistry().checkPark(pa.getDescription())) {
            for (String b : pa.getElectricList()) {
                Bicycle bike = bicycleMap.get(b);
                if (bike.isIsAvailable()) {
                    bikesAvailable.add(bike);
                }
            }
            for (String b : pa.getMountainRoadList()) {
                Bicycle bike = bicycleMap.get(b);
                if (bike.isIsAvailable()) {
                    bikesAvailable.add(bike);
                }
            }
            return bikesAvailable;
        }
        return new ArrayList<>();
    }
}
