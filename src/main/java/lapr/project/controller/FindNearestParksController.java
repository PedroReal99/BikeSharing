/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Collection;
import java.util.List;
import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;

/**
 *
 * @author User
 */
public class FindNearestParksController {
    public Collection<Park> findNearestParks(String userLocation, int maxNumberParks){
        ParkRegistry pR = Company.getParkRegistry();
        return pR.getNearestParks(userLocation,maxNumberParks);
    }
}
