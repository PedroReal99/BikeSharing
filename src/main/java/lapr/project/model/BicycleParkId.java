/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author User
 */
public class BicycleParkId {

    private String bicycleDescription;
    private String parkDescription;

    public BicycleParkId(String bicycleId, String parkDescription) {
        this.bicycleDescription = bicycleId;
        this.parkDescription = parkDescription;
    }

    public String getParkDescription() {
        return parkDescription;
    }

    public void setParkId(String parkDescription) {
        this.parkDescription = parkDescription;
    }

    public String getBicycleDesc() {
        return bicycleDescription;
    }

    public void setBicycleId(String bicycleId) {
        this.bicycleDescription = bicycleId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.bicycleDescription);
        hash = 29 * hash + Objects.hashCode(this.parkDescription);
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
        final BicycleParkId other = (BicycleParkId) obj;
        if (!Objects.equals(this.bicycleDescription, other.bicycleDescription)) {
            return false;
        }
        if (!Objects.equals(this.parkDescription, other.parkDescription)) {
            return false;
        }
        return true;
    }


    
    
}
