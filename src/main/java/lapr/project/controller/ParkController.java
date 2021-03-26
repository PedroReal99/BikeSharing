/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Ride;
import lapr.project.model.Road;
import lapr.project.model.User;
import lapr.project.utils.Calculator;

/**
 *
 * @author Leandro
 */
public class ParkController {

    public void addPark(Park park) {
        Company.getParkRegistry().addNewPark(park);
    }

    public boolean updatepark(Park Park) {

        if (!Company.getParkRegistry().checkPark(Park.getDescription())) {
            Company.getParkRegistry().addUpdatedPark(Park);
            return true;
        }
        return false;
    }

    public boolean deletePark(String p) {
        if (!Company.getParkRegistry().checkPark(p)) {
            Park Park = Company.getParkRegistry().getParkByDescription(p);
            List<String> eletricBikes = Park.getElectricList();
            List<String> mountainRoasBikes = Park.getMountainRoadList();
            Map<String, Bicycle> bicycleMap = Company.getBicycleRegistry().getBicycleMap();
            Company.getParkRegistry().addDeletedPark(p);
            int i = 0;
            while (i != eletricBikes.size()) {
                for (String desc : Company.getParkRegistry().getParkMap().keySet()) {

                    Park p1 = (Park) Company.getParkRegistry().getParkMap().get(desc);
                    if (p1.availableElectricalSpots() != 0) {

                        p1.addBicycle(bicycleMap.get(eletricBikes.get(i)));
                        i++;
                    }
                }
            }
            int j = 0;
            while (j != mountainRoasBikes.size()) {
                for (String desc : Company.getParkRegistry().getParkMap().keySet()) {
                    Park p2 = (Park) Company.getParkRegistry().getParkMap().get(desc);
                    if (p2.availableMountainRoadBikes() != 0) {
                        p2.addBicycle(bicycleMap.get(mountainRoasBikes.get(j)));
                        j++;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public int availableElectricalSpots(String p) {
        if (!Company.getParkRegistry().checkPark(p)) {
            Park Park = Company.getParkRegistry().getParkByDescription(p);
            return Park.availableElectricalSpots();
        }

        return -1;
    }

    public int availableMountainRoadSpots(String p) {
        if (!Company.getParkRegistry().checkPark(p)) {
            Park Park = Company.getParkRegistry().getParkByDescription(p);
            return Park.availableMountainRoadBikes();
        }

        return -1;
    }
    
    public int getFreeSlotsAtPArk(String loc, String username){
        User u = Company.getUserRegistry().getUserByUsername(username);
        Park p = (Park) Company.getParkRegistry().getLocByLocation(loc);
        for(Ride r : u.getRideMap().values()){
            Bicycle b = Company.getBicycleRegistry().getBicycleByDescription(r.getBicycleDesc());
            if(!b.isIsAvailable()){
                if(b.getClass() == Electric.class){
                    return availableElectricalSpots(p.getDescription());
                }else if(b.getClass() == Mountain.class || b.getClass() == Road.class){
                    return availableMountainRoadSpots(p.getDescription());
                }
            }
        }
        return -1;
        
    }

    public double splitPotency(String parkDesc) {
        Park Park = (Park) Company.getParkRegistry().getParkMap().get(parkDesc);
        return Park.splitPotency();
    }

    public List<Bicycle> bicycleWithEnergyNeeded(String oParkId, String dParkId, User user) {
        Park oPark = Company.getParkRegistry().getParkByDescription(oParkId);
        Park dPark = Company.getParkRegistry().getParkByDescription(dParkId);
        double energy = Calculator.energyBetweenParks(oPark.getLatitude(), oPark.getLongitude(), dPark.getLatitude(), dPark.getLongitude(), user.getUsername());
        double energyNeeded = energy + energy * 0.1;
        double atualBikeEnergy;
        List<Bicycle> bicycle = new ArrayList<>();
        for (String idBike : oPark.getElectricList()) {
            Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(idBike);
            atualBikeEnergy = oPark.calculateBikeEnergy(bike);
            if (atualBikeEnergy >= energyNeeded) {
                bicycle.add(bike);

            }
        }
        return bicycle;
    }

    public String[][] chargeReport(String parkId) {
        String bikeList[][] = null;
        if (!Company.getParkRegistry().checkPark(parkId)) {
            Park Park = Company.getParkRegistry().getParkByDescription(parkId);
            List<String> bicyclelist = Park.getElectricList();
            bikeList = new String[bicyclelist.size()][2];
            int i = 0;
            for (String b : bicyclelist) {
                Electric bike = (Electric) Company.getBicycleRegistry().getBicycleByDescription(b);
                float baterryCapacity = bike.getBaterrycapacity();
                float atualBaterry = bike.getAtualBaterry();
                float percetagem = atualBaterry * 100 / baterryCapacity;
                bikeList[i][0] = bike.getBicycleDesc();
                bikeList[i][1] = String.valueOf(percetagem);
                i++;
            }
        }
        return bikeList;
    }
}
