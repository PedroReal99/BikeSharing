/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Road;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



/**
 *
 * @author USER
 */
public class UpdateParkAvailabilityControllerTest {
    
    public UpdateParkAvailabilityControllerTest() {
    }
    
  

    /**
     * Test of updateAvailability method, of class UpdateParkAvailabilityController.
     */
    @Test
    public void testUpdateAvailability() {
        System.out.println("updateAvailability");
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Park p = new Park(12, 1, 50,  "10.324, -5.324","1",3,4);       
        Road r = new Road();
        Mountain m = new Mountain();
        Electric e = new Electric();
        UpdateParkAvailabilityController instance = new UpdateParkAvailabilityController();
        
        
        String expResult = "The park doesn't exist in the company!";
        String result = instance.updateAvailability(p.getDescription(), r);
        assertEquals(expResult, result);
        
        Company.getParkRegistry().addNewPark(p);
        String expResult1 = "Now there are only " + 11 + " mountain and road bicycle spots!";
        String result1 = instance.updateAvailability(p.getDescription(), r);
        assertEquals(expResult1, result1);
        
        String expResult2 = "Now there are only " + 10 + " mountain and road bicycle spots!";
        String result2 = instance.updateAvailability(p.getDescription(), m);
        assertEquals(expResult2, result2);
        
        String expResult3 = "Now there are only " + 0 + " eletric bicycle spots!";
        String result3 = instance.updateAvailability(p.getDescription(), e);
        assertEquals(expResult3, result3);
        
        String expResult4 = "No available spots for the bicycle!";
        String result4 = instance.updateAvailability(p.getDescription(), e);
        assertEquals(expResult4, result4);
        
        
    }
    
}
