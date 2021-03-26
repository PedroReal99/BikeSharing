/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.assessment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import lapr.project.controller.AwardPointsController;
import lapr.project.controller.BicycleController;
import lapr.project.controller.FindAvailableBikesOnAParkController;
import lapr.project.controller.GraphController;
import lapr.project.controller.LockBicycleController;
import lapr.project.controller.ParkController;
import lapr.project.controller.UnlockBicycleController;
import lapr.project.controller.UserRegistryController;
import lapr.project.graph.Graph;
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Location;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.Ride;
import lapr.project.model.Road;
import lapr.project.model.User;
import lapr.project.utils.Calculator;

/**
 *
 * @author hugov
 */
public class InputOutput {

    Graph completeMap = new Graph(true);
    BufferedWriter writer = null;

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
    public int addBicycles(String inputBicycleFile) {
        File file = new File(inputBicycleFile);
        int cont = 0;
        BicycleController instance = new BicycleController();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {

                String[] line = s.split(";");
                String[] interrogacao = line[0].split("");
                String bikeDesc = "";
                if (cont == 0) {
                    for (int i = 1; i < interrogacao.length; i++) {
                        bikeDesc = bikeDesc + interrogacao[i];
                    }
                } else {
                    bikeDesc = line[0];
                }
                String type = line[2];
                String parkLocation = line[3] + ", " + line[4];
                float bikeWeight = Float.parseFloat(line[1]);
                float aeroCo = Float.parseFloat(line[7]);
                float frontArea = Float.parseFloat(line[8]);

                if (type.equalsIgnoreCase("electric")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    float batterryCap = Float.parseFloat(line[5]);
                    float atualBat = Float.parseFloat(line[6]);
                    Electric e = new Electric(batterryCap, atualBat, bikeDesc, bikeWeight, aeroCo, frontArea);
                    e.setIsAvailable(true);
                    instance.addBicycle(e, p.getDescription());
                    cont++;
                } else if (type.equalsIgnoreCase("mtb")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    Mountain m = new Mountain(bikeDesc, bikeWeight, aeroCo, frontArea);
                    m.setIsAvailable(true);
                    instance.addBicycle(m, p.getDescription());
                    cont++;
                } else if (type.equalsIgnoreCase("road")) {
                    Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
                    Road r = new Road(bikeDesc, bikeWeight, aeroCo, frontArea);
                    r.setIsAvailable(true);
                    instance.addBicycle(r, p.getDescription());
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
    public int addParks(String inputParksFile) {
        File file = new File(inputParksFile);
        int cont = 0;
        ParkController instance = new ParkController();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] line = s.split(";");
                String[] interrogacao = line[0].split("");
                String location = "";
                if (cont == 0) {
                    for (int i = 1; i < interrogacao.length; i++) {
                        location = location + interrogacao[i];
                    }
                    location = location + ", " + line[1];
                } else {
                    location = line[0] + ", " + line[1];
                }
                int elevation = Integer.parseInt(line[2]);
                String description = line[3];
                int maxEletricBikes = Integer.parseInt(line[4]);
                int maxMountainRoadBikes = Integer.parseInt(line[5]);
                int voltage = Integer.parseInt(line[6]);
                int current = Integer.parseInt(line[7]);

                Park p = new Park(maxMountainRoadBikes, maxEletricBikes, elevation, location, description, voltage, current);
                instance.addPark(p);
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
    public int addPOIs(String inputPOIsFile) {
        File file = new File(inputPOIsFile);
        int cont = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] line = s.split(";");
                String[] interrogacao = line[0].split("");
                String location = "";
                if (cont == 0) {
                    for (int i = 1; i < interrogacao.length; i++) {
                        location = location + interrogacao[i];
                    }
                    location = location + ", " + line[1];
                } else {
                    location = line[0] + ", " + line[1];
                }
                int elevation = Integer.parseInt(line[2]);
                String description = line[3];

                GraphController.loadVertex(description);
                cont++;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return cont;
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
    public int addUsers(String inputUsersFile) {
        File file = new File(inputUsersFile);
        int cont = 0;
        UserRegistryController instance = new UserRegistryController();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] line = s.split(";");
                String[] interrogacao = line[0].split("");
                String userName = "";
                if (cont == 0) {
                    for (int i = 1; i < interrogacao.length; i++) {
                        userName = userName + interrogacao[i];
                    }
                } else {
                    userName = line[0];
                }
                String mail = line[1];
                float height = Float.parseFloat(line[2]);
                float weight = Float.parseFloat(line[3]);
                double averageSpeed = Double.parseDouble(line[4]);
                int visa = Integer.parseInt(line[5]);
                User u = new User(userName, mail, height, weight, averageSpeed, visa);
                u.setUserId(cont);
                instance.addUser(u);

                cont++;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return cont;

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
    public int getNumberOfBicyclesAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String outputFileName) throws IOException {
        String loc = locationToInt(parkLatitudeInDegrees, parkLongitudeInDegrees);
        FindAvailableBikesOnAParkController instance = new FindAvailableBikesOnAParkController();
        List<Bicycle> list = instance.availableBikes(loc);

        try (BufferedWriter w = new BufferedWriter(new FileWriter(outputFileName));) {
            for (Bicycle b : list) {
                if (b.getClass() == Electric.class) {
                    Electric c = (Electric) b;
                    w.write(c.getBicycleDesc() + ";electric;" + c.getAtualBaterry());
                    w.newLine();
                } else if (b.getClass() == Mountain.class) {
                    w.write(b.getBicycleDesc() + ";mtb;0");
                    w.newLine();
                } else {
                    w.write(b.getBicycleDesc() + ";road;0");
                    w.newLine();
                }
            }
        }

        return instance.availableBikes(loc).size();
    }

    public String locationToInt(double parkLatitudeInDegrees, double parkLongitudeInDegrees) {
        int lat = (int) parkLatitudeInDegrees;
        int lon = (int) parkLongitudeInDegrees;
        return lat + ", " + lon;
    }

    /**
     * Unlocks a specific bicycle.
     *
     * @param bicycleDescription Bicycle description to unlock.
     * @return The time in miliseconds at which the bicycle was unlocked.
     */
    public long unlockBicycle(String bicycleDescription, String username) {
        UnlockBicycleController instance = new UnlockBicycleController();
        return instance.unlockSpecificBike(bicycleDescription, username);
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
     * @throws java.io.IOException
     */
    public long unlockAnyBicycleAtPark(double parkLatitudeInDegrees, double parkLongitudeInDegrees, String username, String outputFileName) throws IOException {
        String loc = locationToInt(parkLatitudeInDegrees, parkLongitudeInDegrees);
        UnlockBicycleController instance = new UnlockBicycleController();
        long hour = instance.unlockAnyBicycleAtPark(loc, username, outputFileName);

        return hour;
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
    public long unlockAnyElectricBicycleAtPark(double parkLatitudeInDegrees, double parkLongitudeInDegrees, String username, String outputFileName) throws IOException {
        String loc = locationToInt(parkLatitudeInDegrees, parkLongitudeInDegrees);
        UnlockBicycleController instance = new UnlockBicycleController();
        long hour = instance.unlockAnyElectricBicycleAtPark(loc, username, outputFileName);

        return hour;
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
    public int getFreeSlotsAtPArk(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees, String username) {
        String loc = locationToInt(parkLatitudeInDegrees, parkLongitudeInDegrees);
        ParkController instance = new ParkController();
        return instance.getFreeSlotsAtPArk(loc, username);

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
    public long lockBicycle(String bicycleDescription, double parkLatitudeInDegrees, double parkLongitudeInDegrees) {
        String loc = locationToInt(parkLatitudeInDegrees, parkLongitudeInDegrees);
        LockBicycleController instance = new LockBicycleController();
        return instance.lockBicycle(loc, bicycleDescription);
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
    public double getUserCurrentDebt(String username, String outputFileName) {
        User u = Company.getUserRegistry().getUserByUsername(username);
        return u.getUserCurrentDebt();
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
    public int distanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees) {
        String locOrig = locationToInt(originLatitudeInDegrees, originLongitudeInDegrees);
        String locDest = locationToInt(destinyLatitudeInDegrees, destinyLongitudeInDegrees);
        return (int) Calculator.getDistance(locOrig, locDest);
    }

    /**
     * Return the current points for the user.
     *
     * @param username The user to get the points report from.
     * @param outputFileName The path for the file to output the points,
     * according to file output/points.csv. Sort the information by unlock time
     * in ascenind order (oldest to newest).
     * @return The User's current points.
     * @throws java.io.IOException
     */
    public double getUserCurrentPoints(String username, String outputFileName) throws IOException {
        AwardPointsController instance = new AwardPointsController();
        User u = Company.getUserRegistry().getUserByUsername(username);
        int total = 0;

        try (BufferedWriter w = new BufferedWriter(new FileWriter(outputFileName));) {
            for (Ride r : u.getRideMap().values()) {
                String bikeDesc = r.getBicycleDesc();
                String bikeUnlockTime = r.getStartTime();
                String bikeLockTime = r.getEndTime();
                if (!r.getStartParkDesc().equals("")) {

                    Park origPark = Company.getParkRegistry().getParkByDescription(r.getStartParkDesc());
                    Park endPark = Company.getParkRegistry().getParkByDescription(r.getEndParkDesc());
                    String locationO = origPark.getLocation();
                    int altitudeO = origPark.getAltitude();
                    String locationF = endPark.getLocation();
                    int altitudeF = endPark.getAltitude();
                    int difference = altitudeF - altitudeO;
                    int points = instance.getAwardPointsBetweenParks(r.getStartParkDesc(), r.getEndParkDesc());
                    total = total + points;

                    w.write(bikeDesc + ";" + bikeUnlockTime + ";" + bikeLockTime + ";" + locationO + ";" + altitudeO + ";" + locationF + ";" + altitudeF + ";" + difference + ";" + points);
                }

            }
        } catch (IOException e) {
        } finally {
            writer.close();
        }
        return total;
    }

}
