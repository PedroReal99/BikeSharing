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
public class Connection {

    private int windDirection;
    private double windSpeed;
    private int id;


    public Connection(int id, int windDirection, double windSpeed) {
        this.id = id;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public Connection() {
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Connection other = (Connection) obj;
        if (this.windDirection != other.windDirection) {
            return false;
        }
        if (Double.doubleToLongBits(this.windSpeed) != Double.doubleToLongBits(other.windSpeed)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

 

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

 
    
    
}
