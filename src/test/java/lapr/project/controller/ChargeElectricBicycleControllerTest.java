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
public class ChargeElectricBicycleControllerTest {

    @Test
    public void chargeElectricBicycle() {

        System.out.println("chargeElectricalBicycle");

        ChargeElectricBicycleController instance = new ChargeElectricBicycleController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric(1, 30, 5, "1", true, 60, 30, 3, 4); //capacidade 1Kw   atual 30 percento de 1Kw
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - atualCapacity * maxCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double time = (double) energyNeeded / potencyPerSlot;
        instance.chargeElectricBicycle(bike.getBicycleDesc(), p.getDescription(), time);
        int capacityResult = (int) bike.getAtualBaterry();
        int expResult = 100;
        assertEquals(expResult, capacityResult);

    }

    @Test
    public void chargeElectricBicycle2() {

        System.out.println("chargeElectricalBicycle");

        ChargeElectricBicycleController instance = new ChargeElectricBicycleController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric(80, 80, 5, "1", true, 60, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - atualCapacity * maxCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double time = (double) energyNeeded / potencyPerSlot;

        instance.chargeElectricBicycle(bike.getBicycleDesc(), p.getDescription(), time);
        int capacityResult = (int) bike.getAtualBaterry();
        int expResult = 100;

        assertEquals(expResult, capacityResult);

    }

    @Test
    public void chargeElectricBicycle3() {

        System.out.println("chargeElectricalBicycle");

        ChargeElectricBicycleController instance = new ChargeElectricBicycleController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric(80, 0, 5, "1", true, 60, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - atualCapacity * maxCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double time = (double) energyNeeded / potencyPerSlot;

        instance.chargeElectricBicycle(bike.getBicycleDesc(), p.getDescription(), time);
        int capacityResult = (int) bike.getAtualBaterry();
        int expResult = 100;

        assertEquals(expResult, capacityResult);

    }

    @Test
    public void chargeElectricBicycle4() {

        System.out.println("chargeElectricalBicycle");

        ChargeElectricBicycleController instance = new ChargeElectricBicycleController();

        Company.setBicycleRegistry(new BicycleRegistry());
        Company.setParkRegistry(new ParkRegistry());

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);
        Company.getParkRegistry().addNewPark(p);

        Electric bike = new Electric(5, 0, 5, "1", true, 60, 30, 3, 4);
        Company.getBicycleRegistry().addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - atualCapacity * maxCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double time = (double) energyNeeded / potencyPerSlot;

        instance.chargeElectricBicycle(bike.getBicycleDesc(), p.getDescription(), time);
        int capacityResult = (int) bike.getAtualBaterry();
        int expResult = 100;

        assertEquals(expResult, capacityResult);

    }

}
