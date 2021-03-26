package lapr.project.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lapr.project.graph.Graph;
import lapr.project.utils.Calculator;
import static lapr.project.utils.Calculator.calculateAwardPoints;

public class ParkRegistry {

    private Map<String, Location> parkMap;
    private List<String> newparks;
    private List<String> updatedparks;
    private List<String> deletedparks;
    private Graph g;

    public ParkRegistry(Map<String, Location> parkMap) {
        this.parkMap = parkMap;
        g = new Graph(true);
        parkMap = new TreeMap<>();
        newparks = new ArrayList<>();
        updatedparks = new ArrayList<>();
        deletedparks = new ArrayList<>();
    }

    public ParkRegistry() {
        g = new Graph(true);
        parkMap = new TreeMap<>();
        newparks = new ArrayList<>();
        updatedparks = new ArrayList<>();
        deletedparks = new ArrayList<>();
    }

    public Map<String, Location> getParkMap() {
        return parkMap;
    }

    public Location getLocByLocation(String coord) {
        Location aux = null;
        for (Location l : parkMap.values()) {
            if (l.getLocation().equals(coord)) {
                aux = l;
                break;
            }

        }
        return aux;
    }

    public void addNewPark(Park p) {
        if (checkPark(p.getDescription())) {
            parkMap.put(p.getDescription(), p);
            newparks.add(p.getDescription());
        }
    }

    public void addUpdatedPark(Park p) {
        if (!checkPark(p.getDescription())) {
            parkMap.remove(p.getDescription());
            parkMap.put(p.getDescription(), p);
            updatedparks.add(p.getDescription());
        }
    }

    public void addDeletedPark(String description) {
        if (!checkPark(description)) {
            deletedparks.add(description);
            parkMap.remove(description);
        }
    }

    public boolean checkPark(String description) {
        return parkMap.get(description) == null;
    }

    public List<String> getDeletedparks() {
        return deletedparks;
    }

    public List<String> getNewparks() {
        return newparks;
    }

    public List<String> getUpdatedparks() {
        return updatedparks;
    }

    public Collection<Park> getNearestParks(String userLocation, int maxNumberParks) {
        Map<Double, Park> mapNearestParks = new TreeMap<>();
        parkMap.values().forEach((p) -> {
            double distance = Calculator.getDistance(p.getLocation(), userLocation);
            int n = mapNearestParks.keySet().size();
            if (n < maxNumberParks) {
                mapNearestParks.put(distance, (Park) p);
            } else {
                Double d = (Double) mapNearestParks.keySet().toArray()[n - 1];
                if (d > distance) {
                    mapNearestParks.remove(d);
                    mapNearestParks.put(distance, (Park) p);
                }
            }
        });
        return mapNearestParks.values();
    }

    public Park getParkByDescription(String description) {
        return (Park) parkMap.get(description);
    }

    public int calculateAwardPointsBetweenParks(String desc1, String desc2) {
        Park p1 = getParkByDescription(desc1);
        int a1 = p1.getAltitude();
        Park p2 = getParkByDescription(desc2);
        int a2 = p2.getAltitude();

        return calculateAwardPoints(a1, a2); //method from class Calculator
    }

    public Graph getGraph() {
        return g;
    }
    
    public void setGraph(Graph g){
        this.g = g;
    }

}
