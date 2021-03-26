package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lapr.project.utils.Calculator;

public class BicycleRegistry {

    private Map<String, Bicycle> bicycleMap;
    private List<BicycleParkId> newbicycles;
    private Map<String, Boolean> updatedbicycles;
    private List<String> deletedbicycles;

    public BicycleRegistry(Map<String, Bicycle> bicycleMap) {
        this.bicycleMap = bicycleMap;
    }

    public BicycleRegistry() {
        bicycleMap = new TreeMap<>();
        newbicycles = new ArrayList<>();
        updatedbicycles = new HashMap<>();
        deletedbicycles = new ArrayList<>();
    }

    public void addNewbicycle(Bicycle b, String p) {
        if (checkBicycle(b.getBicycleDesc())) {
            String id = b.getBicycleDesc();
            bicycleMap.put(id, b);
            newbicycles.add(new BicycleParkId(id, p));
        }
    }

    public boolean addUpdatedbicycle(Bicycle b) {
        if (!checkBicycle(b.getBicycleDesc())) {
            bicycleMap.remove(b.getBicycleDesc());
            bicycleMap.put(b.getBicycleDesc(), b);
            updatedbicycles.put(b.getBicycleDesc(), true);
            return true;
        }
        return false;
    }

    public void addDeletedbicycle(String b) {
        if (!checkBicycle(b)) {
            bicycleMap.remove(b);
            deletedbicycles.add(b);
        }
    }

    public boolean checkBicycle(String b) {
        return bicycleMap.get(b) == null;
    }

    public Map<String, Bicycle> getBicycleMap() {
        return bicycleMap;
    }

    public List<String> getDeletedbicycles() {
        return deletedbicycles;
    }

    public List<BicycleParkId> getNewbicycles() {
        return newbicycles;
    }

    public Map<String, Boolean> getUpdatedbicycles() {
        return updatedbicycles;
    }

    public Bicycle getBicycleByDescription(String id) {
        return bicycleMap.get(id);
    }

    public double calculateProjectionForChargingBicycle(String bicycleId, Park p) {
        Electric eb = (Electric) getBicycleByDescription(bicycleId);

        double potencyPerSlot = p.splitPotency();

        float actualCapacity = eb.getAtualBaterry();
        float maxCapacity = eb.getBaterrycapacity();

        return Calculator.calculateTimeForChargingBicycle(potencyPerSlot, actualCapacity, maxCapacity);
    }
}
