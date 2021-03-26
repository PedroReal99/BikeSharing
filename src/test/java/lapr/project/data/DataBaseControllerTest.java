/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import lapr.project.graph.Edge;
import lapr.project.graph.Graph;
import lapr.project.model.Bicycle;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Electric;
import lapr.project.model.Invoice;
import lapr.project.model.Location;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Receipt;
import lapr.project.model.Ride;
import lapr.project.model.RideBilling;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pedro
 */
public class DataBaseControllerTest {

    /**
     * Test of loadWhenApplicationOpen method, of class DataBase.
     */
    @Test
    public void testLoadWhenApplicationOpen() {
        System.out.println("loadWhenApplicationOpen");
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        DataBase instance = new DataBase();
        Map<Integer, User> mapa1 = new HashMap<>();
        User u1 = new User(1, "a", 12.0F, 10.0F, 123456, "email", 10, 2.0, "Pedro");
        User u2 = new User(2, "b", 10.0F, 35.0F, 123489, "email", 10, 2.0, "Pedro");
        Ride r1 = new Ride("2017-07-23,  13:10:11", "2017-07-23,  13:10:11", "2017-07-23,  13:10:11", "1", "2", 1, 123, "1");
        Ride r2 = new Ride("2017-07-23,  13:10:11", "2017-07-23,  13:10:11", "2017-07-23,  13:10:11", "1", "2", 2, 124, "1");
        u1.addRide(r1);
        u1.addRide(r2);
        
        Location loc = new Location("123", "Trindade", 30);
        Location loc2 = new Location("1234","Vila",20);
        Company.getParkRegistry().getParkMap().put("Trindade", loc);
        Company.getParkRegistry().getParkMap().put("Vila", loc2);

        mapa1.put(1, u1);
        mapa1.put(2, u2);
        instance.loadWhenApplicationOpen();
        
        
        Map<Integer, User> result1 = Company.getUserRegistry().getUserMap();
        assertEquals(result1, mapa1);

        Map<String, Location> mapa2 = new HashMap<>();
        Park p1 = new Park(1, 1, 1, "1", "1", 3, 4);
        Park p2 = new Park(2, 2, 2, "2", "2", 3, 4);
        Electric b = new Electric(3.0F, 2.0F, 33.0F, "1", true, 12.0F, 99.0F, 1.0F, 2.0F);
        mapa2.put("1", p1);
        mapa2.put("2", p2);
        mapa2.put("Trindade", loc);
        mapa2.put("Vila", loc2);
        
        p1.addBicycle(b);
        Map<String, Location> result2 = Company.getParkRegistry().getParkMap();
        assertEquals(mapa2, result2);

        Map<String, Bicycle> mapa3 = new TreeMap<>();
        
        Electric b1 = new Electric(3.0F, 2.0F, 33.0F, "1", true, 12.0F, 99.0F, 1.0F, 2.0F);
        
        mapa3.put("1", b1);
        Map<String, Bicycle> result3 = Company.getBicycleRegistry().getBicycleMap();
        assertEquals(result3, mapa3);

        Map<Integer, Ride> mapaRides = new HashMap<>();
        mapaRides.put(1, r1);
        mapaRides.put(2, r2);

        assertEquals(u1.getRideMap(), mapaRides);

        List<Invoice> listaInvoices = new ArrayList<>();
        Invoice inv = new Invoice(22.0F, 1, "2019-07-23,  13:10:11", "Janeiro");
        listaInvoices.add(inv);
        assertEquals(Company.getUserRegistry().getUserById(2).getInvoiceList(), listaInvoices);

        List<Receipt> listaReceipts = new ArrayList<>();
        Receipt rec = new Receipt(1, 2, "2019-07-23,  13:10:11", 200.0F);
        listaReceipts.add(rec);
        assertEquals(Company.getUserRegistry().getUserById(2).getReceiptList(), listaReceipts);
        
        
        List<RideBilling> listaRideBillings = new ArrayList<>();
        RideBilling rb = new RideBilling(22, 1, "Janeiro");
        listaRideBillings.add(rb);
        User u = Company.getUserRegistry().getUserById(2);

        Connection con = new Connection(1, 10, 22);
        Graph g1 = new Graph(true);
        g1.insertVertex(p1.getDescription());
        g1.insertVertex(p2.getDescription());
        g1.insertVertex(loc.getName());
        g1.insertVertex(loc2.getName());

        g1.insertEdge(loc.getName(), loc2.getName(), con, 44);

        assertEquals(Company.getParkRegistry().getGraph(),g1);
        

        assertEquals(Company.getParkRegistry().getGraph().getEdge(loc.getName(), loc2.getName()).getElement(),con);
        
        
    }

    /**
     * Test of loadWhenApplicationClose method, of class DataBase.
     */
    @Test
    public void testLoadWhenApplicationClose() {
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

        System.out.println("loadWhenApplicationClose");
        Company.setUserRegistry(new UserRegistry());
        Company.setParkRegistry(new ParkRegistry());
        Company.setBicycleRegistry(new BicycleRegistry());
        UserRegistry ur = Company.getUserRegistry();
        ParkRegistry pr = Company.getParkRegistry();
        BicycleRegistry br = Company.getBicycleRegistry();
        DataBase instance = new DataBase();
        User u = new User(3, "qw", 3.0F, 5.0F, 1, "saddvv", 30, 2.0, "Pedro");
        User u2 = new User(4, "aq", 7.0F, 7.0F, 123, "qwerty", 30, 2.0, "Pedro");
        Company.getUserRegistry().addNewUser(u);
        Company.getUserRegistry().addNewUser(u2);

        Company.getUserRegistry().addDeletedUser(u2.getUserId());
        Company.getUserRegistry().getNewUsers().remove(1);
        Map<Integer, User> mapa = Company.getUserRegistry().getUserMap();
        boolean flag = false;
        List<Integer> listaAdd = Company.getUserRegistry().getNewUsers();
        List<Integer> listaDelete = Company.getUserRegistry().getDeletedUsers();

        User u3 = new User(5, "abcde", 9.0F, 9.0F, 789, "qwe", 30, 2.0, "Pedro");
        User u33 = new User(5, "abcd", 8.0F, 8.0F, 689, "awe", 30, 2.0, "Pedro");
        Ride r99 = new Ride("2017-07-23 13:10:11", "2017-07-23 13:10:11", "2017-07-23 13:10:11", "1", "2", 99, 345, "1");
        Invoice in99 = new Invoice(22.0F, 5, "2019-07-23 13:10:11", "Janeiro");
        Receipt rec99 = new Receipt(5, 5, "2019-07-23 13:10:11", 10f);
        Company.getUserRegistry().addNewUser(u3);
        u33.addRide(r99);
        u33.addInvoice(in99);
        u33.addReceipt(rec99);

        Company.getUserRegistry().addUpdatedUser(u33);

        Park p = new Park(1, 1, 1, "1", "3", 3, 4);
        Park p2 = new Park(1, 1, 1, "1", "4", 3, 4);

        Company.getParkRegistry().addNewPark(p);
        Company.getParkRegistry().addNewPark(p2);
        Company.getParkRegistry().addDeletedPark(p2.getDescription());
        Company.getParkRegistry().getNewparks().remove(1);

        boolean flag2 = false;

        Park p3 = new Park(1, 1, 1, "1", "5", 3, 4);
        Park p33 = new Park(2, 1, 1, "1", "5", 3, 6);
        Company.getParkRegistry().addNewPark(p3);
        Company.getParkRegistry().addUpdatedPark(p33);

        Electric b = new Electric(3.0F, 2.0F, 33.0F, "2", true, 12.0F, 99.0F, 1.0F, 2.0F);
        Electric b2 = new Electric(3.0F, 2.0F, 33.0F, "3", true, 12.0F, 99.0F, 1.0F, 2.0F);
        Company.getBicycleRegistry().addNewbicycle(b, "1");
        Company.getBicycleRegistry().addNewbicycle(b2, "1");
        Company.getBicycleRegistry().addDeletedbicycle(b2.getBicycleDesc());
        Company.getBicycleRegistry().getNewbicycles().remove(1);

        boolean flag3 = false;

        Electric b3 = new Electric(3.0F, 2.0F, 33.0F, "4", true, 12.0F, 99.0F, 1.0F, 2.0F);
        Electric b33 = new Electric(3.0F, 2.0F, 33.0F, "4", false, 14.0F, 69.0F, 1.0F, 2.0F);
        Company.getBicycleRegistry().addNewbicycle(b3, "1");
        Company.getBicycleRegistry().addUpdatedbicycle(b33);

        Company.getParkRegistry().addNewPark(p3);
        Company.getParkRegistry().addUpdatedPark(p33);

        //////
        instance.SaveWhenApplicationClose(ur, pr, br);
        //////
        User result = Company.getUserRegistry().getUserById(3);
        User expResult = new UserDB().getUser(3);
        try {
            User result2 = new UserDB().getUser(4);
        } catch (IllegalArgumentException ex) {
            flag = true;
        }
        Park result3 = Company.getParkRegistry().getParkByDescription("3");
        Park expResult3 = new ParkDB().getPark("3");

        try {
            
            Park result4 = new ParkDB().getPark("4");
        } catch (IllegalArgumentException ex) {
            flag2 = true;
        }

        Bicycle result5 = Company.getBicycleRegistry().getBicycleByDescription("2");
        Bicycle expResult5 = new BicycleDB().getBike("2");

        try {

            Electric result6 = new ElectricDB().getEletric("3");
        } catch (IllegalArgumentException ex) {
            flag3 = true;
        }

        assertEquals(result, expResult);
        assertEquals(Company.getUserRegistry().getUserById(5).getRideMap().get(99), new RideDB().getRide(99));
        assertEquals(Company.getUserRegistry().getUserById(5).getInvoiceList().get(0), new InvoiceDB().getInvoice(5));
        assertEquals(Company.getUserRegistry().getUserById(5).getReceiptList().get(0), new ReceiptDB().getReceipt(5));
        assertEquals(flag, true);
        assertEquals(Company.getUserRegistry().getUserById(3), new UserDB().getUser(3));

        assertEquals(result3, expResult3);
        assertEquals(flag2, true);
        assertEquals(Company.getParkRegistry().getParkByDescription("5"), new ParkDB().getPark("5"));

        assertEquals(result5, expResult5);
        assertEquals(flag3, true);
        Ride temp = new RideDB().getRide(99);

        assertEquals(Company.getUserRegistry().getUserById(5).getRideMap().get(99), new RideDB().getRide(99));

        new RideDB().removeRide(99);
        new ReceiptDB().removeReceipt(5);
        new InvoiceDB().removeInvoice(5);
        new UserDB().removeUser(3);
        new UserDB().removeUser(4);
        new UserDB().removeUser(5);
        new ParkDB().removePark("3");
        new ParkDB().removePark("4");
        new ParkDB().removePark("5");
        new ElectricDB().removeElectric("2");
        new BicycleDB().removeBike("2");
        new ElectricDB().removeElectric("3");
        new BicycleDB().removeBike("3");
        new ElectricDB().removeElectric("4");
        new BicycleDB().removeBike("4");

    }
}
