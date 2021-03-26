package lapr.project.model;

public class Electric extends Bicycle {

    private float baterrycapacity;
    private float atualBaterry;
    private float batteryWeight;

    public Electric(float baterrycapacity, float atualBaterry, float batteryWeight, String bicycleDesc, boolean isAvailable, float costPerHour, float weight, float aerodynamicCoeficient, float frontalArea) {
        super(bicycleDesc, isAvailable, costPerHour, weight, aerodynamicCoeficient, frontalArea);
        setAtualBaterry(atualBaterry);
        this.baterrycapacity = baterrycapacity;
        this.batteryWeight = batteryWeight;
        this.weight += batteryWeight;
    }

    public Electric(String bicycleDesc, boolean isAvailable, float costPerHour, float weight, float aerodynamicCoeficient, float frontalArea) {
        super(bicycleDesc, isAvailable, costPerHour, weight, aerodynamicCoeficient, frontalArea);
    }

    public Electric(float baterryCapacity, float atualBaterry, String bicycleDesc, float weight, float aerodynamicCoeficient, float frontalArea) {
        super(bicycleDesc, weight, aerodynamicCoeficient, frontalArea);
        this.baterrycapacity = baterryCapacity;
        this.atualBaterry = atualBaterry;
    }

    public Electric(float baterrycapacity, float atualBaterry, float batteryWeight) {
        setAtualBaterry(atualBaterry);
        this.baterrycapacity = baterrycapacity;
        this.batteryWeight = batteryWeight;
    }

    public void setAtualBaterry(float atualBaterry) {
        if (atualBaterry <= 100) {
            this.atualBaterry = atualBaterry;
        }
    }


    public float getBatteryWeight() {
        return batteryWeight;
    }

    public float getBaterrycapacity() {
        return baterrycapacity;
    }

    public float getAtualBaterry() {
        return atualBaterry;
    }

    public Electric() {
        super();
    }

    public void setBaterrycapacity(float baterrycapacity) {
        this.baterrycapacity = baterrycapacity;
    }

    public void setBatteryWeight(float batteryWeight) {
        this.batteryWeight = batteryWeight;
    }

    public void changeBattery(float baterrycapacity, float atualBaterry, float batteryWeight) {
        float oldBatteryWeight = this.getBatteryWeight();
        setBaterrycapacity(baterrycapacity);
        setAtualBaterry(atualBaterry);
        setBatteryWeight(batteryWeight);

        this.setWeight(this.getWeight() + batteryWeight - oldBatteryWeight);
    }

}
