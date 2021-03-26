package lapr.project.model;

import java.util.Objects;

public class Bicycle {

    private String bicycleDesc;
    private boolean isAvailable;
    private float costPerHour;
    protected float weight;
    private float aerodynamicCoeficient;
    private float frontalArea;

    public Bicycle(String bicycleDesc, boolean isAvailable, float costPerHour, float weight, float aerodynamicCoeficient, float frontalArea) {
        this.bicycleDesc = bicycleDesc;
        this.isAvailable = isAvailable;
        this.costPerHour = costPerHour;
        this.weight = weight;
        this.aerodynamicCoeficient = aerodynamicCoeficient;
        this.frontalArea = frontalArea;
    }
    
    public Bicycle(String bicycleDesc, float weight, float aerodynamicCoeficient, float frontalArea){
        this.bicycleDesc = bicycleDesc;
        this.weight = weight;
        this.aerodynamicCoeficient = aerodynamicCoeficient;
        this.frontalArea = frontalArea;
    }
    
    public Bicycle() {
    }

    
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setBicycleDesc(String bicycicleDesc) {
        this.bicycleDesc = bicycicleDesc;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public float getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(float costPerHour) {
        this.costPerHour = costPerHour;
    }

    public float getAerodynamicCoeficient() {
        return aerodynamicCoeficient;
    }

    public void setAerodynamicCoeficient(float aerodynamicCoeficient) {
        this.aerodynamicCoeficient = aerodynamicCoeficient;
    }

    public float getFrontalArea() {
        return frontalArea;
    }

    public void setFrontalArea(float frontalArea) {
        this.frontalArea = frontalArea;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.bicycleDesc);
        hash = 53 * hash + (this.isAvailable ? 1 : 0);
        hash = 53 * hash + Float.floatToIntBits(this.costPerHour);
        hash = 53 * hash + Float.floatToIntBits(this.weight);
        hash = 53 * hash + Float.floatToIntBits(this.aerodynamicCoeficient);
        hash = 53 * hash + Float.floatToIntBits(this.frontalArea);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bicycle other = (Bicycle) obj;
        if (this.isAvailable != other.isAvailable) {
            return false;
        }
        if (Float.floatToIntBits(this.costPerHour) != Float.floatToIntBits(other.costPerHour)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        if (Float.floatToIntBits(this.aerodynamicCoeficient) != Float.floatToIntBits(other.aerodynamicCoeficient)) {
            return false;
        }
        if (Float.floatToIntBits(this.frontalArea) != Float.floatToIntBits(other.frontalArea)) {
            return false;
        }
        if (!Objects.equals(this.bicycleDesc, other.bicycleDesc)) {
            return false;
        }
        return true;
    }

    /**
     * @return the bicycleDesc
     */
    public String getBicycleDesc() {
        return bicycleDesc;
    }

}
