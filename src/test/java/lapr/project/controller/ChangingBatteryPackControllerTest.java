/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author USER
 */
public class ChangingBatteryPackControllerTest {
    
    public ChangingBatteryPackControllerTest() {
    }

    /**
     * Test of changeBattery method, of class changingBatteryPackController.
     */
    @Test
    public void testChangeBattery() {
        System.out.println("changeBattery");
        Company.setBicycleRegistry(new BicycleRegistry());
        Electric e = new Electric( 20f, 13f,30f, "1", true, 10f, 30f,3,4);
        float tension = 10F;
        float baterrycapacity = 12F;
        float atualBaterry = 14F;
        float batteryWeight = 10F;
        ChangingBatteryPackController instance = new ChangingBatteryPackController();
        float expResult = 0F;
        float result = instance.changeBattery(e.getBicycleDesc(), tension, baterrycapacity, atualBaterry, batteryWeight);
        assertEquals(expResult, result);
        
        Company.getBicycleRegistry().addNewbicycle(e,"10");
        float expResult1 = 40f;
        float result1 = instance.changeBattery(e.getBicycleDesc(), tension, baterrycapacity, atualBaterry, batteryWeight);
        assertEquals(expResult1, result1);
        
    }
    
}
