/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Bicycle;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.UserRegistry;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author titi1
 */
public class BicycleControllerTest {

    public BicycleControllerTest() {
    }

    /**
     * Test of addElectricalBicycle method, of class BicycleController.
     */
    @Test
    public void testAddBicycle() {
        System.out.println("addElectricalBicycle");
        Bicycle bike = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        Bicycle bike1 = new Mountain("2", true, 5, 30, 3, 4);
        Park p = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);

        BicycleController instance = new BicycleController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setUserRegistry(new UserRegistry());
        Company.setInvoiceList(new ArrayList<>());
        Company.getParkRegistry().addNewPark(p);

        boolean res = instance.addBicycle(bike, p.getDescription());
        assertTrue(res);
        boolean resF = instance.addBicycle(bike, p.getDescription());
        assertTrue(!resF);
        boolean res1 = instance.addBicycle(bike1, p.getDescription());
        assertTrue(res1);
    }

    /**
     * Test of deleteBicycle method, of class DeleteBicycleController.
     */
    @Test
    public void testDeleteBicycle() {
        System.out.println("deleteBicycle");
        BicycleController instance = new BicycleController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Bicycle bike = new Electric(50, 30, 5, "1", true, 60, 30, 3, 4);
        Bicycle bike2 = new Mountain("2", true, 5, 30, 3, 4);
        Park Park = new Park(20, 30, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park Park2 = new Park(20, 30, 50, "10.324, -5.324", "Casa", 3, 4);

        Park.addBicycle(bike);
        Park.addBicycle(bike2);

        Company.getParkRegistry().addNewPark(Park);

        Company.getBicycleRegistry().addNewbicycle(bike, Park.getDescription());
        Company.getBicycleRegistry().addNewbicycle(bike2, Park.getDescription());

        boolean result = instance.deleteBicycle(bike.getBicycleDesc(), Park.getDescription());
        assertTrue(result);
        boolean result2 = instance.deleteBicycle(bike2.getBicycleDesc(), Park.getDescription());
        assertTrue(result2);
        boolean result3 = instance.deleteBicycle(bike.getBicycleDesc(), Park2.getDescription());
        assertTrue(!result3);
    }

    /**
     * Test of deleteBicycle method, of class DeleteBicycleController.
     */
    @Test
    public void testUpdateBicycle() {
        System.out.println("updateBicycle");
        BicycleController instance = new BicycleController();
        Company.setBicycleRegistry(new BicycleRegistry());
        Bicycle bike = new Electric(50, 30, 5, "1", true, 60, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(bike, "10");
        boolean result = instance.updateBicycle(new Electric(50, 30, 4, "1", true, 10000, 30, 3, 4));
        assertTrue(result);
    }

}
