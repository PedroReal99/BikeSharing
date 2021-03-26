/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author USER
 */
public class ElectricTest {

    public ElectricTest() {
    }

    @Test
    public void ElectricTest() {
        System.out.println("ElectricTest");
        Electric bike = new Electric(250, 20, "1", 2, 2, 5);
        assertEquals(250, bike.getBaterrycapacity());
        assertEquals(20, bike.getAtualBaterry());
        assertEquals("1", bike.getBicycleDesc());
        assertEquals(2, bike.getWeight());
        assertEquals(2, bike.getAerodynamicCoeficient());
        assertEquals(5, bike.getFrontalArea());
    }

    /**
     * Test of setAtualBaterry method, of class Electric.
     */
    @Test
    public void testSetAtualBaterry() {
        System.out.println("setAtualBaterry");
        float atualBaterry = 20F;
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        instance.setAtualBaterry(atualBaterry);
        assertEquals(atualBaterry, instance.getAtualBaterry());

        float atualBaterry2 = 30F;
        Electric instance2 = new Electric(80, 20, 5, "1", true, 60, 30, 3, 4);
        instance2.setAtualBaterry(atualBaterry2);
        assertEquals(atualBaterry2, instance2.getAtualBaterry());
    }
    
    /**
     * Test of setAtualBaterry method, of class Electric.
     */
    @Test
    public void testSetAtualBaterryF() {
        System.out.println("setAtualBaterry");
        float atualBaterry = 110F;
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        instance.setAtualBaterry(atualBaterry);
        assertEquals(30, instance.getAtualBaterry());
    }

    /**
     * Test of getBaterrycapacity method, of class Electric.
     */
    @Test
    public void testGetBaterrycapacity() {
        System.out.println("getBaterrycapacity");
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        float expResult = 80F;
        float result = instance.getBaterrycapacity();
        assertEquals(expResult, result, 80);

    }

    /**
     * Test of getAtualBaterry method, of class Electric.
     */
    @Test
    public void testGetAtualBaterry() {
        System.out.println("getAtualBaterry");
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        float expResult = 30F;
        float result = instance.getAtualBaterry();
        assertEquals(expResult, result, 30);

    }

    /**
     * Test of getBatteryWeight method, of class Electric.
     */
    @org.junit.Test
    public void testGetBatteryWeight() {
        System.out.println("getBatteryWeight");
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        float expResult = 30F;
        float result = instance.getBatteryWeight();
        assertEquals(expResult, result);

    }

    /**
     * Test of setBaterrycapacity method, of class Electric.
     */
    @org.junit.Test
    public void testSetBaterrycapacity() {
        System.out.println("setBaterrycapacity");
        float baterrycapacity = 80F;
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        instance.setBaterrycapacity(baterrycapacity);
        assertEquals(baterrycapacity, instance.getBaterrycapacity());
    }

    /**
     * Test of setBatteryWeight method, of class Electric.
     */
    @org.junit.Test
    public void testSetBatteryWeight() {
        System.out.println("setBatteryWeight");
        float batteryWeight = 5F;
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        instance.setBatteryWeight(batteryWeight);
        assertEquals(batteryWeight, instance.getBatteryWeight());
    }

    /**
     * Test of changeBattery method, of class Electric.
     */
    @org.junit.Test
    public void testChangeBattery() {
        System.out.println("changeBattery");
        float baterrycapacity = 12F;
        float atualBaterry = 14F;
        float batteryWeight = 10F;
        Electric instance = new Electric(80, 30, 5, "1", true, 60, 30, 3, 4);
        instance.changeBattery(baterrycapacity, atualBaterry, batteryWeight);
        float expectResult = 40f;
        assertEquals(expectResult, instance.getWeight());
    }

}
