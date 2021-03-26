/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import lapr.project.controller.GraphController;
import lapr.project.graph.Edge;
import lapr.project.model.BicycleParkId;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.ParkRegistry;
import lapr.project.model.UserRegistry;
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Electric;
import lapr.project.model.Invoice;
import lapr.project.model.Location;
import lapr.project.model.Mountain;
import lapr.project.model.Park;
import lapr.project.model.Receipt;
import lapr.project.model.Ride;
import lapr.project.model.RideBilling;
import lapr.project.model.Road;
import lapr.project.model.User;

/**
 *
 * @author Pedro
 */
public class DataBase {

    public void loadWhenApplicationOpen() {

        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        importUsers();
        importParks();
        importBikes();
        importRides();
        importInvoice();
        importReceipt();

        importLocations();
        importConnections();
    }

    private void importUsers() {

        UserRegistry ur = Company.getUserRegistry();
        Map<Integer, User> userMap = ur.getUserMap();
        List<User> userList = getAllUser();
        for (User u : userList) {
            userMap.put(u.getUserId(), u);
        }
    }

    private static List<User> getAllUser() {
        return new UserDB().getAllUser();
    }

    private void importParks() {
        ParkRegistry pr = Company.getParkRegistry();
        Map<String, Location> parkMap = pr.getParkMap();
        List<Park> parkList = getAllParks();
        for (Park p : parkList) {
            parkMap.put(p.getDescription(), p);
            GraphController.loadVertex(p.getName());
        }
    }

    private static List<Park> getAllParks() {
        return new ParkDB().getAllPark();
    }

    private void importBikes() {
        BicycleRegistry br = Company.getBicycleRegistry();
        Map<String, Bicycle> bikeMap = br.getBicycleMap();
        List<Bicycle> bikeList = getAllBicycle();
        for (Bicycle b : bikeList) {
            bikeMap.put(b.getBicycleDesc(), b);
        }
    }

    private static List<Bicycle> getAllBicycle() {
        return new BicycleDB().getAllBikes();
    }

    private void importRides() {
        new RideDB().getAllRides();
        User u = Company.getUserRegistry().getUserById(1);
    }

    private void importInvoice() {
        List<Invoice> listaInv = new InvoiceDB().getAllInvoices();
        for (Invoice inv : listaInv) {
            List<RideBilling> lista = new RideBillingDB().getAllRideBillings(inv.getInvoiceId());
            for (RideBilling rb : lista) {

                inv.addBill(rb);
            }
        }
    }

    private void importReceipt() {
        new ReceiptDB().getAllReceipts();
    }

    private void importLocations() {
        List<Location> list = new LocationDB().getAllLocation();
        Map<String,Location> map = Company.getParkRegistry().getParkMap();
        for(Location l:list){
            map.put(l.getName(), l);
            GraphController.loadVertex(l.getName());
        }
    }

    private void importConnections() {
        new ConnectionDB().getAllConnection();
    }

    public void SaveWhenApplicationClose(UserRegistry ur, ParkRegistry pr, BicycleRegistry br) {
        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        exportUsers(ur);
        exportParks(pr);
        exportBicycles(br);
        exportLocations();
        exportConnections();
    }

    private void exportUsers(UserRegistry ur) {
        for (int id : ur.getNewUsers()) {
            User u = ur.getUserById(id);
            new UserDB().addUser(u);

            Collection<Ride> listaRides = u.getRideMap().values();
            for (Ride r : listaRides) {
                new RideDB().addRide(r, id);
            }
            List<Invoice> listaInvoices = u.getInvoiceList();
            for (Invoice in : listaInvoices) {
                new InvoiceDB().addInvoice(in, id);
                List<RideBilling> listaRideBilling = in.getRideBillingList();
                for (RideBilling rb : listaRideBilling) {
                    new RideBillingDB().addRideBilling(rb, in.getInvoiceId());
                }
            }
            List<Receipt> listaReceipts = u.getReceiptList();
            for (Receipt re : listaReceipts) {
                new ReceiptDB().addReceipt(re);

            }
        }

        for (int id : ur.getDeletedUsers()) {
            new UserDB().removeUser(id);
        }

        for (int id : ur.getUpdatedUsers()) {
            User u = ur.getUserById(id);
            new UserDB().updateUser(u);
        }
    }

    private void exportParks(ParkRegistry pr) {

        for (String id : pr.getDeletedparks()) {

            new ParkDB().removePark(id);
        }

        for (String id : pr.getNewparks()) {
            Park p = pr.getParkByDescription(id);
            new ParkDB().addPark(p);
        }
        for (String id : pr.getUpdatedparks()) {
            Park p = pr.getParkByDescription(id);
            new ParkDB().updatePark(p);
        }
    }

    private void exportBicycles(BicycleRegistry br) {
        for (BicycleParkId b : br.getNewbicycles()) {
            String bikeId = b.getBicycleDesc();
            String parkId = b.getParkDescription();
            Bicycle bike = br.getBicycleMap().get(bikeId);
            if (bike instanceof Road) {
                new BicycleDB().addBike(bike, "road", parkId);
            } else if (bike instanceof Mountain) {
                new BicycleDB().addBike(bike, "mountain", parkId);
            } else if (bike instanceof Electric) {
                new BicycleDB().addBike(bike, "Eletrical", parkId);
                new ElectricDB().addElectric((Electric) bike);
            }
        }

        for (String id : br.getDeletedbicycles()) {
            Bicycle bike = br.getBicycleMap().get(id);
            if (bike instanceof Road) {
                new BicycleDB().removeBike(id);
            } else if (bike instanceof Mountain) {
                new BicycleDB().removeBike(id);
            } else if (bike instanceof Electric) {
                new ElectricDB().removeElectric(id);
                new BicycleDB().removeBike(id);
            }
        }

    }

    private void exportLocations() {
        Iterable<String> itLoc = Company.getParkRegistry().getGraph().vertices();
        Map<String, Location> map = Company.getParkRegistry().getParkMap();
        for (String loc : itLoc) {
            new LocationDB().addLocation(map.get(loc));
        }
    }

    private void exportConnections() {
        Iterable<Edge> itEdg = Company.getParkRegistry().getGraph().edges();
        Map<String, Location> map = Company.getParkRegistry().getParkMap();
        for (Edge edg : itEdg) {
            Connection c = edg.getElement();
            new ConnectionDB().addConnection(c, map.get(edg.getVOrig()).getName(), map.get(edg.getVDest()).getName(), edg.getWeight());
        }
    }
}
