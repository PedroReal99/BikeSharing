/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Park;
import lapr.project.utils.Calculator;

/**
 *
 * @author Leandro
 */
public class ChargeElectricBicycleController {

    public void chargeElectricBicycle(String bicycleDesc, String parkId, double time) {

        Park p = Company.getParkRegistry().getParkByDescription(parkId);
        Electric eb = (Electric) Company.getBicycleRegistry().getBicycleByDescription(bicycleDesc);
        Calculator.chargeElectricBicycleBaterry(p, eb, eb.getBaterrycapacity(), eb.getAtualBaterry(), time);

    }
}
