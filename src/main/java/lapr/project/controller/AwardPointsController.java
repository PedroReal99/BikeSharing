/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.ParkRegistry;

import lapr.project.model.Company;

/**
 *
 * @author Leandro
 */
public class AwardPointsController {
    
    public int getAwardPointsBetweenParks( String parkId1, String parkId2){
        ParkRegistry pr = Company.getParkRegistry();
        
        return pr.calculateAwardPointsBetweenParks(parkId1, parkId2);
    }
}

