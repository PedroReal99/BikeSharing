/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Park;
import lapr.project.model.Electric;
import lapr.project.model.Ride;
import lapr.project.model.User;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import lapr.project.model.Mountain;
import lapr.project.model.Road;

/**
 *
 * @author vitor
 */
public class UnlockBicycleController {

    public Bicycle unlockBike(int userID, String pId, String type) {
        LocalDate localDate = LocalDate.now();
        Calendar now = Calendar.getInstance();
        User u = Company.getUserRegistry().getUserById(userID);
        Park p = Company.getParkRegistry().getParkByDescription(pId);
        if ("Electric".equalsIgnoreCase(type)) {
            for (String idBike : p.getElectricList()) {
                Electric elec = (Electric) Company.getBicycleRegistry().getBicycleByDescription(idBike);
                if (elec.isIsAvailable()) {
                    elec.setIsAvailable(false);
                    int idRide = u.countRides();

                    u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 200, elec.getBicycleDesc()));
                    return elec;
                }
            }
        } else if ("Mountain".equalsIgnoreCase(type)) {
            for (String idBike : p.getMountainRoadList()) {
                Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(idBike);
                if (bike.getClass() == Mountain.class) {

                    if (bike.isIsAvailable()) {
                        bike.setIsAvailable(false);
                        int idRide = u.countRides();

                        u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 200, bike.getBicycleDesc()));
                        return bike;
                    }
                }
            }
        } else if ("Road".equalsIgnoreCase(type)) {
            for (String idBike : p.getMountainRoadList()) {
                Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(idBike);
                if (bike.getClass() == Road.class) {

                    if (bike.isIsAvailable()) {
                        bike.setIsAvailable(false);
                        int idRide = u.countRides();

                        u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 200, bike.getBicycleDesc()));
                        return bike;
                    }
                }
            }

        }
        return null;
    }

    public Bicycle unlockApropriateElectrical(int userID, String inicialParkDescription, String endParkDescription) {
        LocalDate localDate = LocalDate.now();
        Calendar now = Calendar.getInstance();
        User u = Company.getUserRegistry().getUserById(userID);

        ParkController instance = new ParkController();

        List<Bicycle> list = instance.bicycleWithEnergyNeeded(inicialParkDescription, endParkDescription, u);
        for (Bicycle b : list) {
            if (b.isIsAvailable()) {
                b.setIsAvailable(false);
                int idRide = u.countRides();

                u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", inicialParkDescription, "", idRide, 200, b.getBicycleDesc()));
                return b;
            }
        }
        return null;
    }

    public long unlockSpecificBike(String bikeDesc, String username) {
        LocalDate localDate = LocalDate.now();
        Calendar now = Calendar.getInstance();

        User u = Company.getUserRegistry().getUserByUsername(username);
        Bicycle b = Company.getBicycleRegistry().getBicycleByDescription(bikeDesc);

        int rideID = u.countRides();

        Ride r = new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", "", "", rideID, 0, b.getBicycleDesc());
        u.addRide(r);
        b.setIsAvailable(false);
        return now.get(Calendar.HOUR_OF_DAY);
    }

    public long unlockAnyBicycleAtPark(String parkLocation, String username, String outputFileName) throws IOException {
        String[] file = new String[100];
        int i = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFileName), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {
                file[i] = s;
                i++;

            }
        } catch (Exception ex) {
            System.out.println("Error");
        }

        LocalDate localDate = LocalDate.now();
        Calendar now = Calendar.getInstance();

        Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
        User u = Company.getUserRegistry().getUserByUsername(username);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (int j = 0; j < i; j++) {
                writer.write(file[j]);
                writer.newLine();
            }
            for (String b : p.getMountainRoadList()) {
                Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(b);
                if (bike.isIsAvailable()) {
                    bike.setIsAvailable(false);
                    int idRide = u.countRides();
                    
                    u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 0, bike.getBicycleDesc()));
                    if (bike.getClass() == Mountain.class) {
                        writer.write(bike.getBicycleDesc() + ";mtb;0");
                        writer.newLine();
                    } else if (bike.getClass() == Road.class) {
                        writer.write(bike.getBicycleDesc() + ";road;0");
                        writer.newLine();
                    }
                    return now.get(Calendar.HOUR_OF_DAY);
                }
            }
            for (String b : p.getElectricList()) {
                Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(b);
                if (bike.isIsAvailable()) {
                    bike.setIsAvailable(false);
                    int idRide = u.countRides();
                    
                    u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 0, bike.getBicycleDesc()));
                    Electric e = (Electric) bike;
                    writer.write(e.getBicycleDesc() + ";electrical;" + e.getAtualBaterry());
                    
                    return now.get(Calendar.HOUR_OF_DAY);
                }
            }
        }
        return 100;
    }

    public long unlockAnyElectricBicycleAtPark(String parkLocation, String username, String outputFileName) throws IOException {
        String[] file = new String[100];
        int i = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFileName), "UTF8"))) {
            String s;
            while ((s = in.readLine()) != null) {
                file[i] = s;
                i++;

            }
        } catch (Exception ex) {
            System.out.println("Error");
        }

        LocalDate localDate = LocalDate.now();
        Calendar now = Calendar.getInstance();

        Park p = (Park) Company.getParkRegistry().getLocByLocation(parkLocation);
        User u = Company.getUserRegistry().getUserByUsername(username);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));) {

            for (int j = 0; j < i; j++) {
                writer.write(file[j]);
                writer.newLine();
            }
            for (String b : p.getElectricList()) {
                Bicycle bike = Company.getBicycleRegistry().getBicycleByDescription(b);
                System.out.print(bike.isIsAvailable());
                if (bike.isIsAvailable()) {
                    bike.setIsAvailable(false);
                    int idRide = u.countRides();

                    u.addRide(new Ride(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate), Calendar.HOUR_OF_DAY + ":" + now.get(Calendar.MINUTE), "", p.getDescription(), "", idRide, 0, bike.getBicycleDesc()));
                    Electric e = (Electric) bike;
                    writer.write(e.getBicycleDesc() + ";electrical;" + e.getAtualBaterry());

                    return now.get(Calendar.HOUR_OF_DAY);
                }
            }
        }
        return 100;
    }

}
