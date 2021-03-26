/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class FindNearestParksControllerTest {

    public FindNearestParksControllerTest() {

    }

    /**
     * Test of findNearestParks method, of class FindNearestParksController.
     */
    @Test
    public void testFindNearestParks() {
        System.out.println("findNearestParks");
        Company.setParkRegistry(new ParkRegistry());
        ParkRegistry pr = Company.getParkRegistry();
        pr.addNewPark(new Park(10, 10, 10, "42.152699, -8.609267","Trindad",3,4));
        pr.addNewPark(new Park(9, 9, 9, "43.152699, -8.609267","Tridade",3,4));
        pr.addNewPark(new Park(8, 8, 8, "41.152699, -9.309267","Tindade",3,4));
        pr.addNewPark(new Park(7, 7, 7, "61.152699, -8.609267","Trindae",3,4));
        pr.addNewPark(new Park(6, 6, 6, "41.652699, -8.609267","rindade",3,4));
        pr.addNewPark(new Park(5, 5, 5, "41.152699, -30.609267","Trinade",3,4));

        String userLocation = "41.152699, -8.609267";
        int maxNumberParks = 3;
        FindNearestParksController instance = new FindNearestParksController();
        List<Park> expResult = new ArrayList<>();
        
        expResult.add(new Park(6, 6, 6, "41.652699, -8.609267","rindade",3,4));
        expResult.add(new Park(8, 8, 8, "41.152699, -9.309267","Tindade",3,4));
        expResult.add(new Park(10, 10, 10, "42.152699, -8.609267","Trindad",3,4));
        
        Collection<Park> result = instance.findNearestParks(userLocation, maxNumberParks);
        int i =0;
        for (Park p : result) {
           assertEquals(p,expResult.get(i++));
        }
    }

}
