package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Park extends Location {

    private int capacityMountainRoad;
    private int capacityElectric;
    private int tension;
    private int current;
    private List<String> mountainRoadList;
    private List<String> electricList;

    public Park(int capacityMountainRoad, int capacityElectric, int altitude, String location, String description, int tension, int current) {

        this.capacityMountainRoad = capacityMountainRoad;
        this.capacityElectric = capacityElectric;
        mountainRoadList = new ArrayList<>();
        electricList = new ArrayList<>();
        this.location = location;
        this.description = description;
        this.altitude = altitude;
        this.description = description;
        this.tension = tension;
        this.current = current;
    }

    public Park() {
        mountainRoadList = new ArrayList<>();
        electricList = new ArrayList<>();
    }

    public Park(String description, String location) {
        this.location = location;
        this.description = description;
    }

    public int getCapacityMountainRoad() {
        return capacityMountainRoad;
    }

    public void setCapacityMountainRoad(int capacityMountainRoad) {
        this.capacityMountainRoad = capacityMountainRoad;
    }

    public int getCapacityElectric() {
        return capacityElectric;
    }

    public void setCapacityElectric(int capacityElectric) {
        this.capacityElectric = capacityElectric;
    }

    @Override
    public int getAltitude() {
        return altitude;
    }

    @Override
    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public List<String> getMountainRoadList() {
        return mountainRoadList;
    }

    public void setMountainRoadList(List<String> mountainRoadList) {
        this.mountainRoadList = mountainRoadList;
    }

    public List<String> getElectricList() {
        return electricList;
    }

    public void setElectricList(List<String> electricList) {
        this.electricList = electricList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.capacityMountainRoad;
        hash = 97 * hash + this.capacityElectric;
        hash = 97 * hash + this.altitude;
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.mountainRoadList);
        hash = 97 * hash + Objects.hashCode(this.electricList);
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
        final Park other = (Park) obj;
        if (this.capacityMountainRoad != other.capacityMountainRoad) {
            return false;
        }
        if (this.capacityElectric != other.capacityElectric) {
            return false;
        }
        if (this.altitude != other.altitude) {
            return false;
        }
        if (!Objects.equals(this.mountainRoadList, other.mountainRoadList)) {
            return false;
        }
        return Objects.equals(this.electricList, other.electricList);
    }

    public boolean addBicycle(Bicycle b) {
        if (b.getClass() == Electric.class) {
            if (availableElectricalSpots() > 0) {
                electricList.add(b.getBicycleDesc());
                return true;
            }
            return false;
        } else if (b.getClass() == Mountain.class || b.getClass() == Road.class) {
            if (availableMountainRoadBikes() > 0) {
                mountainRoadList.add(b.getBicycleDesc());
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean deleteBicycle(String b) {
        Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(b);
        if (bike.getClass() == Electric.class) {
            if (availableElectricalSpots() > 0) {
                int index = electricList.indexOf(b);
                electricList.remove(index);
                return true;
            }
            return false;
        } else if (bike.getClass() == Mountain.class || bike.getClass() == Road.class) {
            if (availableMountainRoadBikes() > 0) {
                int index = mountainRoadList.indexOf(b);
                mountainRoadList.remove(index);
                return true;
            }
            return false;
        }
        return false;
    }

    public int availableElectricalSpots() {
        return capacityElectric - electricList.size();
    }

    public int availableMountainRoadBikes() {
        return capacityMountainRoad - mountainRoadList.size();
    }

    public int getCurrent() {
        return current;
    }

    public int getTension() {
        return tension;
    }

    public double splitPotency() {
        double totalPotency = calculatePotency();
        totalPotency /= 1000;
        int numberOfEletricalBicycle = this.getCapacityElectric();
        if (numberOfEletricalBicycle == 0) {
            return 0;
        } else if (numberOfEletricalBicycle == 1) {
            return 3;
        }
        return totalPotency / numberOfEletricalBicycle;
    }

    private double calculatePotency() {
        return (double) this.getTension() * this.getCurrent();
    }

    public double calculateBikeEnergy(Bicycle bike) {
        return calculateBikeEnergy((Electric) bike);
    }

    private static double calculateBikeEnergy(Electric bike) {
        //E=P=V*A
        return bike.getAtualBaterry() * bike.getBaterrycapacity() / 100;
    }

}
