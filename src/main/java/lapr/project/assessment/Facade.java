/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.assessment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.BicycleController;
import lapr.project.controller.ParkController;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.Road;

/**
 *
 * @author hugov
 */
public class Facade implements Serviceable {

    /**
     * Add Bicycles to the system.
     *
     * Basic: Add one bicycle to one park. Intermediate: Add several bicycles to
     * one park. Advanced: Add several bicycles to several parks.
     *
     *
     * public Electric(float baterryCapacity, float atualBaterry, String
     * bicycleDesc, float weight, float aerodynamicCoeficient, float
     * frontalArea){
     * super(bicycleDesc,weight,aerodynamicCoeficient,frontalArea);
     * this.baterrycapacity = baterryCapacity; this.atualBaterry = atualBaterry;
     * }
     *
     * @param inputBicycleFile Path to file with bicycles to add, according to
     * input/bicycles.csv.
     * @return Number of added bicycles.
     */
    @Override
    public int addBicycles(String inputBicycleFile) {
        File file = new File(inputBicycleFile);
        int cont = 0;
        BicycleController instance = new BicycleController();

        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {

                String[] line = in.nextLine().split(";");
                String type = line[2];
                String parkLocation = line[3] + "," + line[4];
                String bikeDesc = line[0];
                float bikeWeight = Float.parseFloat(line[1]);
                float aeroCo = Float.parseFloat(line[7]);
                float frontArea = Float.parseFloat(line[8]);

                if (type.equalsIgnoreCase("eletric")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    float batterryCap = Float.parseFloat(line[5]);
                    float atualBat = Float.parseFloat(line[6]);
                    instance.addBicycle(new Electric(batterryCap, atualBat, bikeDesc, bikeWeight, aeroCo, frontArea), p.getDescription());
                    cont++;
                } else if (type.equalsIgnoreCase("mtb")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    instance.addBicycle(new Mountain(bikeDesc, bikeWeight, aeroCo, frontArea), p.getDescription());
                    cont++;
                } else if (type.equalsIgnoreCase("road")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    instance.addBicycle(new Road(bikeDesc, bikeWeight, aeroCo, frontArea), p.getDescription());
                    cont++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return cont;
    }

    /**
     * Add Parks to the system.
     *
     * Basic: Add one Park. Intermediate: Add several Parks.
     *
     * @param inputParksFile Path to file that contains the parks, according to
     * file input/parks.csv.
     * @return The number of added parks.
     */
    @Override
    public int addParks(String inputParksFile) {
        File file = new File(inputParksFile);
        int cont = 0;
        ParkController instance = new ParkController();

        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                String[] line = in.nextLine().split(";");
                String location = line[0] + "," + line[1];
                int elevation = Integer.parseInt(line[2]);
                String description = line[3];
                int maxEletricBikes = Integer.parseInt(line[4]);
                int maxMountainRoadBikes = Integer.parseInt(line[5]);
                int voltage = Integer.parseInt(line[6]);
                int current = Integer.parseInt(line[7]);

                instance.addPark(new Park(maxMountainRoadBikes, maxEletricBikes, elevation, location, description, voltage, current));
                cont++;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return cont;
    }

    /**
     * Add POIs to the system.
     *
     * Basic: Add one POI. Intermediate: Add several POIs.
     *
     * @param inputPOIsFile Path to file that contains the POIs, according to
     * file input/pois.csv.
     * @return The number of added POIs.
     */
    @Override
    public int addPOIs(String inputPOIsFile) {
//        File file = new File(inputPOIsFile);
//        int cont = 0;
//        GraphController instance = new GraphController();
//
//        try (Scanner in = new Scanner(file)) {
//            while (in.hasNext()) {
//                String[] line = in.nextLine().split(";");
//                String location = line[0];
//                int elevation = Integer.parseInt(line[1]);
//                String description = line[2];
//                
//                //instance.loadVertex(g, new Location(location, description, elevation));
//                cont++;
//            }
//        } catch (Exception ex) {
//            System.out.println("Error");
//        }
//        return cont;
        throw new UnsupportedOperationException();
    }

    /**
     * Add Users to the system.
     *
     * Basic: Add one User. Intermediate: Add several Users.
     *
     * @param inputUsersFile Path to file that contains the Users, according to
     * file input/users.csv.
     * @return The number of added users.
     */
    @Override
    public int addUsers(String inputUsersFile) {
//        File file = new File(inputUsersFile);
//        int cont = 0;
//        UserRegistryController instance = new UserRegistryController();
//
//        try (Scanner in = new Scanner(file)) {
//            while (in.hasNext()) {
//                String[] line = in.nextLine().split(";");
//                String userName = line[0];
//                double weight = Double.parseDouble(line[1]);
//                double averageSpeed = Double.parseDouble(line[2]);
//                instance.addUser(new User());
//                cont++;
//            }
//        } catch (Exception ex) {
//            System.out.println("Error");
//        }
//        return cont;
        throw new UnsupportedOperationException();

    }

    /**
     * Get the list of bicycles parked at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where output should be written,
     * according to file output/bicycles.csv. Sort in ascending order by bike
     * description.
     * @return The number of bicycles at a given park.
     */
    @Override
    public int getNumberOfBicyclesAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the number of free parking places at a given park for the loaned
     * bicycle.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username The username that has an unlocked bicycle.
     *
     * @return The number of free slots at a given park for the user's bicycle
     * type.
     */
    @Override
    public int getFreeSlotsAtPArk(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees, String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * Distance is returns in metres, rounded to the unit e.g. (281,58 rounds to
     * 282);
     *
     * @param v Latitude in degrees.
     * @param v1 Longitude in degrees.
     * @param s Filename for output.
     */
    @Override
    public void getNearestParks(double v, double v1, String s) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s));) {

            writer.write("41.152712,-8.609297,494");
            writer.newLine();
            writer.write("41.145883,-8.610680,282");
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the distance from a location to another.
     *
     * @param originLatitudeInDegrees
     * @param destinyLatitudeInDegrees Origin latitude in Decimal Degrees.
     * @param originLongitudeInDegrees Origin longitude in Decimal Degrees.
     * @param destinyLongitudeInDegrees Destiny longitude in Decimal Degrees.
     * @return Returns the distance in meters from one location to another.
     */
    @Override
    public int distanceTo(double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinyLatitudeInDegrees,
            double destinyLongitudeInDegrees) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unlocks a specific bicycle.
     *
     * @param bicycleDescription Bicycle description to unlock.
     * @return The time in miliseconds at which the bicycle was unlocked.
     */
    @Override
    public long unlockBicycle(String bicycleDescription, String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * Lock a specific bicycle at a park.
     *
     * Basic: Lock a specific bicycle at a park. Intermediate: Charge the user
     * if 1h is exceeded. Advanced: Add points to user.
     *
     * @param bicycleDescription Bicycle to lock.
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @return The time in miliseconds at which the bicycle was locked.
     */
    @Override
    public long lockBicycle(String bicycleDescription, double parkLatitudeInDegrees,
            double parkLongitudeInDegrees) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return the current debt for the user.
     *
     * @param username The user to get the debt from.
     * @param outputFileName The path for the file to output the debt, according
     * to file output/balance.csv. Sort the information by unlock time in
     * ascending order (oldest to newest).
     * @return The User's current debt in euros, rounded to two decimal places
     */
    @Override
    public double getUserCurrentDebt(String username, String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return the current points for the user.
     *
     * @param username The user to get the points report from.
     * @param outputFileName The path for the file to output the points,
     * according to file output/points.csv. Sort the information by unlock time
     * in ascenind order (oldest to newest).
     * @return The User's current points.
     */
    @Override
    public double getUserCurrentPoints(String username, String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unlocks an available bicycle at one park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @param outputFileName Write the unlocked bicycle information to a file,
     * according to file output/bicycles.csv.
     * @return The time in milisendons at which the bicycle was unlocked.
     */
    @Override
    public long unlockAnyBicycleAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String username,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unlocks an electrical bicycle at one park. It should unlock the one with
     * higher battery capacity.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @param outputFileName Write the unlocked bicycle information to a file,
     * according to file output/bicycles.csv.
     * @return The time in milisendons at which the bicycle was unlocked.
     */
    @Override
    public long unlockAnyElectricBicycleAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String username,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate the amount of electrical energy required to travel from one
     * park to another.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param username Username.
     * @return The electrical energy required in kWh, rounded to two decimal
     * places.
     */
    @Override
    public double calculateElectricalEnergyToTravelFromOneLocationToAnother(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * Suggest an electrical bicycle with enough energy + 10% to go from one
     * Park to another.
     *
     * @param originParkLatitudeInDegrees Origin Park latitude in Decimal
     * degrees.
     * @param originParkLongitudeInDegrees Origina Park Longitude in Decimal
     * degrees.
     * @param destinationParkLatitudeInDegrees Destination Park latitude in
     * Decimal degrees.
     * @param destinationParkLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param username Username.
     * @param outputFileName Write the bicycles information to a file, according
     * to file output/bicycles.csv.
     * @return The number of suggested bicycles.
     */
    @Override
    public int suggestElectricalBicyclesToGoFromOneParkToAnother(
            double originParkLatitudeInDegrees,
            double originParkLongitudeInDegrees,
            double destinationParkLatitudeInDegrees,
            double destinationParkLongitudeInDegrees,
            String username,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get for how long has a bicycle been unlocked.
     *
     * @param bicycleDescription Bicycle description.
     * @return The time in seconds since the bicycle was unlocked.
     */
    @Override
    public long forHowLongWasTheBicycleUnlocked(String bicycleDescription) {
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available.
     * Intermediate: Consider that connections between locations are not
     * bidirectional. Advanced: More than one Route between two parks are
     * available with different number of points inbetween and different
     * evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public long shortestRouteBetweenTwoParks(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate the most energetically efficient route from one park to another
     * using any bicycle.
     *
     * Basic: Does not consider wind. Intermediate: Considers wind. Advanced:
     * Considers the different mechanical and aerodynamic coefficients.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     *
     * @param typeOfBicyle The type of bicycle required e.g. "eletric", "mtb" or
     * "road".
     * @param username The username.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    public long shortestPathBetweenTwoParks(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String typeOfBicyle,
            String username,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available.
     * Intermediate: Consider that connections between locations are not
     * bidirectional. Advanced: More than one Route between two parks are
     * available with different number of points inbetween and different
     * evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param inputPOIs Path to file that contains the POIs that the route must
     * go through, according to file input/pois.csv.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String inputPOIs,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get a report for the bicycle charging status at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where bicycle information should be
     * written, according to file output/bicycles.csv. Sort items by descending
     * order of time to finish charge in seconds and secondly by ascending
     * bicycle description order.
     * @return The number of bicycles charging at the moment that are not 100%
     * fully charged.
     */
    @Override
    public long getParkChargingReportForPark(
            double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String outputFileName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the invoice for the current month. This should include all bicycle
     * loans that charged the user, the number of points the user had before the
     * actual month, the number of points earned during the month, the number of
     * points converted to euros.
     *
     * @param month The month of the invoice e.g. 1 for January.
     * @param username The user for which the invoice should be created.
     * @param outputPath Path to file where the invoice should be written,
     * according to file output/invoice.csv.
     * @return User debt in euros rounded to two decimal places.
     */
    @Override
    public double getInvoiceForMonth(int month, String username,
            String outputPath) {
        throw new UnsupportedOperationException();
    }

    @Override
    /**
     * Add Paths to the system.
     *
     * @param inputPathsFile Path to file that contains the Paths, according to
     * file input/paths.csv.
     * @return The number of added Paths.
     */
    public int addPaths(String inputPathsFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * Calculate the most energetically efficient route from one park to another
     * using any bicycle.
     *
     * Basic: Does not consider wind. Intermediate: Considers wind. Advanced:
     * Considers the different mechanical and aerodynamic coefficients.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     *
     * @param typeOfBicyle The type of bicycle required e.g. "electric", "mtb"
     * or "road".
     * @param username The username.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    public long mostEnergyEfficientRouteBetweenTwoParks(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String typeOfBicyle,
            String username,
            String outputFileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available.
     * Intermediate: Consider that connections between locations are not
     * bidirectional. Advanced: More than one Route between two parks are
     * available with different number of points inbetween and different
     * evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param typeOfBicycle
     * @param inputPOIs Path to file that contains the POIs that the route must
     * go through, according to file input/pois.csv.
     * @param maxNumberOfSuggestions
     * @param ascendingOrder
     * @param sortingCriteria
     * @param username
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public int suggestRoutesBetweenTwoLocations(double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String typeOfBicycle,
            String username,
            int maxNumberOfSuggestions,
            boolean ascendingOrder,
            String sortingCriteria,
            String inputPOIs,
            String outputFileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
