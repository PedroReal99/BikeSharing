/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Leandro
 */
public class EstimateProjectionForChargingControllerTest {

    @Test
    public void getProjectionForChargingBicycle() {

        System.out.println("chargeElectricalBicycle");

        EstimateProjectionForChargingController instance = new EstimateProjectionForChargingController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica",3,4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric( 80, 30, 5, "1", true, 60, 30,3,4); //capacidade 80   atual 30
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;

 

        double timeResult = instance.getProjectionForChargingBicycle(bike.getBicycleDesc(), p.getDescription());

        assertEquals(timeExpected, timeResult);

    }

    @Test
    public void getProjectionForChargingBicycle2() {

        System.out.println("chargeElectricalBicycle");

        EstimateProjectionForChargingController instance = new EstimateProjectionForChargingController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica",3,4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric( 80, 80, 5, "1", true, 60, 30,3,4); //capacidade 80   atual 30
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        
        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;


        double timeResult = instance.getProjectionForChargingBicycle(bike.getBicycleDesc(), p.getDescription());

        assertEquals(timeExpected, timeResult);

    }

    @Test
    public void getProjectionForChargingBicycle3() {

        System.out.println("chargeElectricalBicycle");

        EstimateProjectionForChargingController instance = new EstimateProjectionForChargingController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica",3,4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric( 0, 0, 5, "1", true, 60, 30,3,4); //capacidade 80   atual 30
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        
        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;


        double timeResult = instance.getProjectionForChargingBicycle(bike.getBicycleDesc(), p.getDescription());

        assertEquals(timeExpected, timeResult);

    }

}
