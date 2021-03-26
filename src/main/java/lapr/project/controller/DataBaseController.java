/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.DataBase;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.ParkRegistry;
import lapr.project.model.UserRegistry;
import lapr.project.model.Company;

/**
 *
 * @author Pedro
 */
public class DataBaseController {
    
    public void importDataBase(){
    
        new DataBase().loadWhenApplicationOpen();
        
}
    
    public void exportDataBase(){
        UserRegistry ur = Company.getUserRegistry();
        ParkRegistry pr = Company.getParkRegistry();
        BicycleRegistry br = Company.getBicycleRegistry();
        new DataBase().SaveWhenApplicationClose(ur, pr, br);
    }
    
}