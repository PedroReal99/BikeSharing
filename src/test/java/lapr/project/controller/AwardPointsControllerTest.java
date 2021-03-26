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
 * @author Leandro
 */
public class AwardPointsControllerTest {

    @Test
    public void getAwardPointsBEtweenParksTest() {
        System.out.println("award points betwwen 2 parks test");

        AwardPointsController instance = new AwardPointsController();
        Company.setParkRegistry(new ParkRegistry());

        Park Park = new Park(12, 3, 1, "10.324, -5.324", "Casa da Musica",3,4);
        Park Park2 = new Park(12, 3, 51, "10.324, -5.324", "Casa",3,4);

        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);

        String id1= Park.getDescription();
        String id2 = Park2.getDescription();
        int expected = 15; //altitude difference =50
        int result = instance.getAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);
    }
    
        @Test
    public void getAwardPointsBEtweenParksTest2() {
        System.out.println("award points betwwen 2 parks test");

        AwardPointsController instance = new AwardPointsController();
        Company.setParkRegistry(new ParkRegistry());

        Park Park = new Park(12, 3, 1, "10.324, -5.324", "Casa da Musica",3,4);
        Park Park2 = new Park(12, 3, 26, "10.324, -5.324", "Casa",3,4);

        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);

        String id1= Park.getDescription();
        String id2 = Park2.getDescription();
        int expected = 5; //altitude difference =25
        int result = instance.getAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);
    }
    
           @Test
    public void getAwardPointsBEtweenParksTest3() {
        System.out.println("award points betwwen 2 parks test");

        AwardPointsController instance = new AwardPointsController();
        Company.setParkRegistry(new ParkRegistry());

        Park Park = new Park(12, 3, 20, "10.324, -5.324", "Casa da Musica",3,4);
        Park Park2 = new Park(12, 3, 20, "10.324, -5.324", "Casa dica",3,4);

        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);

        String id1= Park.getDescription();
        String id2 = Park2.getDescription();
        int expected = 0; //altitude difference =0
        int result = instance.getAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);
    }
    
           @Test
    public void getAwardPointsBEtweenParksTest4() {
        System.out.println("award points betwwen 2 parks test");

        AwardPointsController instance = new AwardPointsController();
        Company.setParkRegistry(new ParkRegistry());

        Park Park = new Park(12, 3, 12, "10.324, -5.324", "Casa da Musica",3,4);
        Park Park2 = new Park(12, 3, 2, "10.324, -5.324", "Casa da Mua",3,4);

        Company.getParkRegistry().addNewPark(Park);
        Company.getParkRegistry().addNewPark(Park2);

        String id1= Park.getDescription();
        String id2 = Park2.getDescription();
        int expected = 0; //altitude difference =-10
        int result = instance.getAwardPointsBetweenParks(id1, id2);
        assertEquals(expected, result);
    }
}
