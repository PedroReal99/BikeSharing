package lapr.project.ui;

import java.io.IOException;
import java.sql.SQLException;
import lapr.project.assessment.InputOutput;
import lapr.project.controller.UserRegistryController;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    private static InputOutput io;

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        /**
         * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
         */
        io = new InputOutput();

        int numParks = io.addParks("parks.csv");
        System.out.println("Number of added parks: " + numParks);

        int numBicycles = io.addBicycles("bicycles.csv");
        System.out.println("Number of added bicycles: " + numBicycles);

        int numPOIs = io.addPOIs("pois.csv");
        System.out.println("Number of added POIs: " + numPOIs);

        int numUsers = io.addUsers("users.csv");
        System.out.println("Number of added users: " + numUsers);

        int availableBikes = io.getNumberOfBicyclesAtPark(1234, 3121, "bicyclesO.csv");
        System.out.println("Number of available bicycles: " + availableBikes);

        long unlockedHour = io.unlockBicycle("PT050", "Hugo");
        System.out.println("Unlocked at: " + unlockedHour);

        long unlockAnyHour = io.unlockAnyBicycleAtPark(1234, 3121, "hugo", "bicyclesO.csv");
        System.out.println("Unlocked random bike at: " + unlockAnyHour);

        long unlockAnyElectricalHour = io.unlockAnyElectricBicycleAtPark(4567, 5345, "pedro", "bicyclesO.csv");
        System.out.println("Unlocked random bike at: " + unlockAnyElectricalHour);

        int availableSpots = io.getFreeSlotsAtPArk(1234, 3121, "Hugo");
        System.out.println("Available spots: " + availableSpots);

        long parkedBikeHours = io.lockBicycle("PT070", 4567, 5345);
        System.out.println("Hour the bicycle was locked: " + parkedBikeHours);

        int distanceBetweenParks = io.distanceTo(1234, 3121, 4567, 5345);
        System.out.println("Distance between the parks: " + distanceBetweenParks);

        double totalPoints = io.getUserCurrentPoints("Hugo", "points.csv");
        System.out.println("User current points: " + totalPoints);      
    }
}
