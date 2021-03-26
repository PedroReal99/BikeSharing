/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.controller.GraphController;
import lapr.project.model.BicycleRegistry;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Electric;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Leandro
 */
public class CalculatorTest {

    @Test
    public void calculateAwardPointsTest() {
        Company.setParkRegistry(new ParkRegistry());

        Park p1 = new Park(12, 3, 1, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park p2 = new Park(12, 3, 51, "10.324, -5.324", "Casa da Musica", 4, 5);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        int a1 = p1.getAltitude();
        int a2 = p2.getAltitude();

        int expected = 15;
        int result = Calculator.calculateAwardPoints(a1, a2);
        assertEquals(expected, result);

    }

    @Test
    public void calculateAwardPointsTest2() {
        System.out.println("award points betwwen 2 parks test");

        Company.setParkRegistry(new ParkRegistry());

        Park p1 = new Park(12, 3, 1, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park p2 = new Park(12, 3, 26, "10.324, -5.324", "Casa da Musica", 4, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        int a1 = p1.getAltitude();
        int a2 = p2.getAltitude();

        int expected = 5;
        int result = Calculator.calculateAwardPoints(a1, a2);
        assertEquals(expected, result);
    }

    @Test
    public void calculateAwardPointsTest3() {
        System.out.println("award points betwwen 2 parks test");

        Company.setParkRegistry(new ParkRegistry());

        Park p1 = new Park(12, 3, 20, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park p2 = new Park(12, 3, 20, "10.324, -5.324", "Casa da Musica", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        int a1 = p1.getAltitude();
        int a2 = p2.getAltitude();

        int expected = 0;
        int result = Calculator.calculateAwardPoints(a1, a2);
        assertEquals(expected, result);
    }

    @Test
    public void calculateAwardPointsTest4() {
        System.out.println("award points betwwen 2 parks test");

        Company.setParkRegistry(new ParkRegistry());

        Park p1 = new Park(12, 3, 12, "10.324, -5.324", "Casa da Musica", 3, 4);
        Park p2 = new Park(12, 3, 2, "10.324, -5.324", "Casa da Musica", 3, 4);

        Company.getParkRegistry().addNewPark(p1);
        Company.getParkRegistry().addNewPark(p2);

        int a1 = p1.getAltitude();
        int a2 = p2.getAltitude();

        int expected = 0;
        int result = Calculator.calculateAwardPoints(a1, a2);
        assertEquals(expected, result);
    }

    @Test
    public void chargeElectricBicycleBaterryTest() {
        System.out.println("chargeElectricBicycleBaterry");

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);

        Electric bike = new Electric(250, 30, 5, "1", true, 60, 30, 3, 4);

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        double capacityExpected = 30;
        Calculator.chargeElectricBicycleBaterry(p, bike, maxCapacity, atualCapacity, 1);
        double capacityResult = (double) bike.getAtualBaterry();

        assertEquals(capacityExpected, (int) capacityResult);
    }

    @Test
    public void chargeElectricBicycleBaterryTest2() {

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);

        Electric bike = new Electric(250, 80, 5, "1", true, 60, 30, 3, 4);

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        double capacityExpected = 80;
        Calculator.chargeElectricBicycleBaterry(p, bike, maxCapacity, atualCapacity, 2);
        double capacityResult = (double) bike.getAtualBaterry();

        assertEquals(capacityExpected, (int) capacityResult);
    }

    @Test
    public void chargeElectricBicycleBaterryTest3() {
        System.out.println("chargeElectricBicycleBaterry");

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);

        Electric bike = new Electric(250, 80, 5, "1", true, 60, 30, 3, 4);

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        double capacityExpected = 81;
        Calculator.chargeElectricBicycleBaterry(p, bike, maxCapacity, atualCapacity, 3);
        double capacityResult = (double) bike.getAtualBaterry();

        assertEquals(capacityExpected, (int) capacityResult);
    }

    @Test
    public void chargeElectricBicycleBaterryTest4() {
        System.out.println("chargeElectricBicycleBaterry");

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);

        Electric bike = new Electric(100, 73, 5, "1", true, 60, 30, 3, 4);

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        double capacityExpected = 77;
        Calculator.chargeElectricBicycleBaterry(p, bike, maxCapacity, atualCapacity, 4);
        double capacityResult = (double) bike.getAtualBaterry();

        assertEquals(capacityExpected, (int) capacityResult);
    }

    @Test
    public void chargeElectricBicycleBaterryTest5() {
        System.out.println("chargeElectricBicycleBaterry");
        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);
        Electric bike = new Electric(250, 80, 5, "1", true, 60, 30, 3, 4);
        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        double capacityExpected = 82;
        Calculator.chargeElectricBicycleBaterry(p, bike, maxCapacity, atualCapacity, 5);
        double capacityResult = (double) bike.getAtualBaterry();

        assertEquals(capacityExpected, (int) capacityResult);
    }

    @Test
    public void calculateTimeForChargingBicycleTest() {

        System.out.println("chargeElectricBicycleBaterry");

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 220, 16);

        Electric bike = new Electric(1, 90, 5, "1", true, 60, 30, 3, 4);

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();

        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;

        double timeResult = Calculator.calculateTimeForChargingBicycle(potencyPerSlot, atualCapacity, maxCapacity);
        assertEquals(timeExpected, timeResult);

    }

    @Test
    public void calculateTimeForChargingBicycleTest2() {

        System.out.println("chargeElectricBicycleBaterry");

        BicycleRegistry br = new BicycleRegistry();

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);

        Electric bike = new Electric(0.89f, 80, 5, "1", true, 60, 30, 3, 4);
        br.addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;

        double timeResult = Calculator.calculateTimeForChargingBicycle(potencyPerSlot, atualCapacity, maxCapacity);

        assertEquals(timeExpected, timeResult);

    }

    @Test
    public void calculateTimeForChargingBicycleTest3() {

        System.out.println("chargeElectricBicycleBaterry");

        BicycleRegistry br = new BicycleRegistry();

        Park p = new Park(12, 3, 50, "10.324, -5.324", "Casa da Musica", 3, 4);

        Electric bike = new Electric(0.7f, 60, 5, "1", true, 60, 30, 3, 4);
        br.addNewbicycle(bike, p.getDescription());

        float atualCapacity = bike.getAtualBaterry();
        float maxCapacity = bike.getBaterrycapacity();
        float energyNeeded = maxCapacity - maxCapacity * atualCapacity / 100;
        double potencyPerSlot = p.splitPotency();
        double timeExpected = (double) energyNeeded / potencyPerSlot;

        double timeResult = Calculator.calculateTimeForChargingBicycle(potencyPerSlot, atualCapacity, maxCapacity);

        assertEquals(timeExpected, timeResult);

    }

    /**
     * Test of EnergyBetweenParks method, of class Park.
     */
    @Test
    public void testEnergyBetweenParks() {
        System.out.println("energyBetweenParks");
        Park park = new Park(30, 7, 200, "42.152699, -9.209267", "Trindade", 220, 16);
        Park park2 = new Park(0, 0, 40, "42.152699, -9.213167", "Casa da Musica", 220, 16);
        User user = new User(1, "123456", 180, 80, 12368521, "email", 30, 12, "Patrick");
        Company.setParkRegistry(new ParkRegistry());
        Company.getParkRegistry().addNewPark(park);
        Company.getParkRegistry().addNewPark(park2);
        Company.getUserRegistry().addNewUser(user);
        Connection c = new Connection(1, 45, 3);
        Connection c2 = new Connection(1, 315, 5);
        Company.getParkRegistry().getGraph().insertVertex(park.getName());
        Company.getParkRegistry().getGraph().insertVertex(park2.getName());
        GraphController.addConnection(park.getName(), park2.getName(), c, 300);
        GraphController.addConnection(park2.getName(), park.getName(), c2, 300);
        double altitudeDiference = (double) park.getAltitude() - park2.getAltitude();
        double distance = Calculator.getDistance(park.getLocation(), park2.getLocation());
        double resultOfAngle = Math.abs(altitudeDiference) / distance;
        double angleBetweenParks = Math.toDegrees(Math.asin(resultOfAngle));
        int windDirection = GraphController.getEdge(park.getName(), park2.getName()).getElement().getWindDirection();
        double windspeed = GraphController.getEdge(park.getName(), park2.getName()).getElement().getWindSpeed();
        double anglePark = Calculator.bearing(park.getLatitude(), park.getLongitude(), park2.getLatitude(), park2.getLongitude());
        if (anglePark < 0) {
            anglePark = 360 + anglePark;
        }
        double angle = Math.abs(windDirection - anglePark);
        double airDensity = 1.2754;
        double dragForce = 0.7;
        double bodyArea = (Math.sqrt((user.getWeight() * user.getHeight()) / 3600)) / 3;
        double speed = user.getAverageSpeed() - windspeed * Math.cos(Math.toRadians(angle));
        int bikeWeight = 12;
        double airdinamic = (double) 1 / 2 * airDensity * dragForce * bodyArea * Math.pow(speed, 2);
        double gravitacional = (user.getWeight() + bikeWeight) * 10 * Math.sin(Math.toRadians(angleBetweenParks));
        double atrito = (0.625 * (user.getWeight() + bikeWeight) * 10 * Math.cos(Math.toRadians(angleBetweenParks)));
        double epResult = ((airdinamic + gravitacional + atrito) * distance / 1000) / 1000;
        double result = Calculator.energyBetweenParks(park.getLatitude(), park.getLongitude(), park2.getLatitude(), park2.getLongitude(), user.getUsername());
        assertEquals(epResult, result);

    }
}
