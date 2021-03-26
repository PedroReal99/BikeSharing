package lapr.project.model;

public class Road extends Bicycle {

    public Road(String bicycleDesc, boolean isAvailable, float costPerHour, float weight, float aerodynamicCoeficient, float frontalArea) {
        super(bicycleDesc, isAvailable, costPerHour, weight, aerodynamicCoeficient, frontalArea);
    }

    public Road(String bicycleDesc, float weight, float aeroCo, float frontalArea){
        super(bicycleDesc,weight,aeroCo,frontalArea);
    }
    
    public Road() {
        super();
    }
}
