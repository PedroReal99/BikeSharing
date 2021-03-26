/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Bicycle;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author USER
 */
public class FindAvailableBikesOnAParkControllerTest {

    /**
     * Test of availableBikes method, of class
     * FindAvailableBikesOnAParkController.
     */
    
    @Test
    public void testAvailableBikes(){
        System.out.println("availableBikes");
        FindAvailableBikesOnAParkController instance = new FindAvailableBikesOnAParkController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        
        Park Park = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica",3,4);
        Park Park2 = new Park(12, 3, 50, "11.324, -5.324", "Casa da Music",3,4);
        
        
        
        Bicycle bike2 = new Mountain("4", true, 5, 30,3,4);
        Bicycle bike22 = new Mountain("3", false, 5, 30,3,4);
        Bicycle bike = new Electric( 80, 30, 5, "1", true, 60, 30,3,4);
        Bicycle bikee = new Electric( 80, 30, 5, "2", false, 60, 30,3,4);
        
        Park.addBicycle(bike);
        Park.addBicycle(bike2);
        Park.addBicycle(bike22);
        Park.addBicycle(bikee);
        
        Company.getBicycleRegistry().addNewbicycle(bike,Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(bike2,Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(bike22,Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(bikee,Park.getDescription());
        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);
        
        List<Bicycle> expResult = new ArrayList<>();
        expResult.add(bike);
        expResult.add(bike2);
        List<Bicycle> result = instance.availableBikes(Park.getLocation());
        assertEquals(expResult, result);
        
        List<Bicycle> expResult2 = new ArrayList<>();
        List<Bicycle> result2 = instance.availableBikes(Park2.getLocation());
        assertEquals(expResult2, result2);
    }

}
