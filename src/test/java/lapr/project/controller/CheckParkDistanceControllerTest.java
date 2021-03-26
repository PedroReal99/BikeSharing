/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author User
 */
public class CheckParkDistanceControllerTest {
    
    public CheckParkDistanceControllerTest() {
    }
    
   

    /**
     * Test of checkParkDistance method, of class checkParkDistanceController.
     */
    @Test
    public void testCheckParkDistance() {
        System.out.println("checkParkDistance");
        String location = "45.152699, -9.609267";
        ParkRegistry pr = Company.getParkRegistry();
        pr.addNewPark(new Park(324,324,324,"41.152699, -8.609267", "Trindade",3,4));
        CheckParkDistanceController instance = new CheckParkDistanceController();
        double expResult = 452105;
        double result = instance.checkParkDistance(location, "Trindade");
        assertEquals(expResult, result,1);
      

    
    }
    
}
