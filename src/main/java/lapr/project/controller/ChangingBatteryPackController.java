/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Electric;

/**
 *
 * @author USER
 */
public class ChangingBatteryPackController {

    public float changeBattery(String e, float tension, float baterrycapacity, float atualBaterry, float batteryWeight) {
        if (!Company.getBicycleRegistry().checkBicycle(e)) {
            Electric bike = (Electric) Company.getBicycleRegistry().getBicycleByDescription(e);
            bike.changeBattery( baterrycapacity, atualBaterry, batteryWeight);
            Company.getBicycleRegistry().addUpdatedbicycle(bike);
            return bike.getWeight();
        }
        return 0;
    }

}
