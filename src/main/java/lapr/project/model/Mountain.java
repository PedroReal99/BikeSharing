package lapr.project.model;

public class Mountain extends Bicycle {

    public Mountain(String bicycleDesc, boolean isAvailable, float costPerHour, float weight, float aerodynamicCoeficient, float frontalArea) {
        super(bicycleDesc, isAvailable, costPerHour, weight, aerodynamicCoeficient, frontalArea);
    }
    
    public Mountain(String bicycleDesc, float weight, float aeroCo, float frontalArea){
        super(bicycleDesc,weight,aeroCo,frontalArea);
    }
    public Mountain() {
        super();
    }

}
