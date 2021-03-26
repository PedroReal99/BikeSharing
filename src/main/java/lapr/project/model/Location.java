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
public class Location {

    protected String location;
    protected String description;
    protected int altitude;

    public Location(String location, String name) {
        this.location = location;
        this.description = name;
    }

    public Location(String location, String name, int altitude) {
        this.location = location;
        this.description = name;
        this.altitude = altitude;
    }


    public Location() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + this.altitude;
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
        final Location other = (Location) obj;
        if (this.altitude != other.altitude) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

    public double getLongitude() {
        String[] a = location.split(",");
        return Double.parseDouble(a[1]);
    }

    public double getLatitude() {
        String[] a = location.split(",");
        return Double.parseDouble(a[0]);
    }

}
