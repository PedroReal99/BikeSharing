/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.controller.GraphController;
import lapr.project.graph.Vertex;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Location;
import lapr.project.model.Park;
import lapr.project.model.User;

/**
 *
 * @author User
 */
public class Calculator {

    private static final double BIKEWEIGTH = 12;

    public static double getDistance(String l1, String l2) {

        String[] parkLocation = l1.split(",");
        String[] parameterLocation = l2.split(",");

        double parkY = Double.parseDouble(parkLocation[0].trim());
        double parkX = Double.parseDouble(parkLocation[1].trim());
        double paramY = Double.parseDouble(parameterLocation[0].trim());
        double paramX = Double.parseDouble(parameterLocation[1].trim());

        double R = 6371000;
        double x1 = Math.toRadians(parkY);
        double x2 = Math.toRadians(paramY);
        double dif1 = Math.toRadians((parkY - paramY));
        double dif2 = Math.toRadians(parkX - paramX);

        double a = Math.sin(dif1 / 2) * Math.sin(dif1 / 2)
                + Math.cos(x1) * Math.cos(x2)
                * Math.sin(dif2 / 2) * Math.sin(dif2 / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;

    }

    public static double getTime(String t1, String t2) {
        String[] auxT1 = t1.split(":");
        String[] auxT2 = t2.split(":");
        double time = Double.parseDouble(auxT2[0]);
        time -= Double.parseDouble(auxT1[0]);
        double auxMin = Double.parseDouble(auxT2[1]);
        auxMin -= Double.parseDouble(auxT1[1]);
        if (auxMin < 0) {
            auxMin += 60;
            time -= 1;
        }
        double auxS = Double.parseDouble(auxT2[2]);
        auxS -= Double.parseDouble(auxT1[2]);
        if (auxS < 0) {
            auxS += 60;
            auxMin -= 1;
        }
        auxMin += auxS / 60;
        time = auxMin / 60;
        return time;
    }

    public static double getCaloriesBurned(double time, float weight) {
        return time * 288 * weight / 72;
    }

    public static int calculateAwardPoints(int a1, int a2) {
        int points = 0;

        if (a2 - a1 >= 25 && a2 - a1 < 50) {
            points = 5;
        } else if (a2 - a1 <= 50 && a2 - a1 > 25) {
            points = 15;
        }
        return points;
    }

    public static void chargeElectricBicycleBaterry(Park p, Electric eb, float maxCapacity, float actualCapacity, double time) {
        double potencyPerSlot = p.splitPotency();

// time in hour
        double energyCharged = time * potencyPerSlot; //P = E / T where E=P*T
        double atual = energyCharged * 100 / maxCapacity;
        eb.setAtualBaterry((float) atual + actualCapacity);
    }

    public static double calculateTimeForChargingBicycle(double potencyPerSlot, float actualCapacity, float maxCapacity) {
        double energy = maxCapacity - actualCapacity * maxCapacity / 100;
        return energy / potencyPerSlot;
    }

    public static double energySpent(Location location, Location location0) {
        return energyBetweenParks(location.getLatitude(), location.getLongitude(), location0.getLatitude(), location0.getLongitude(), "-1");
         //To change body of generated methods, choose Tools | Templates.
    }

    public static double bearing(double lat1, double long1, double lat2, double long2) {
        double degToRad = Math.PI / 180.0;
        double phi1 = lat1 * degToRad;
        double phi2 = lat2 * degToRad;
        double lam1 = long1 * degToRad;
        double lam2 = long2 * degToRad;

        return Math.atan2(Math.sin(lam2 - lam1) * Math.cos(phi2),
                Math.cos(phi1) * Math.sin(phi2) - Math.sin(phi1) * Math.cos(phi2) * Math.cos(lam2 - lam1)
        ) * 180 / Math.PI;
    }

    public static double energyBetweenParks(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String username) {
        String coord1 = String.valueOf(originLatitudeInDegrees) + ", " + String.valueOf(originLongitudeInDegrees);
        String coord2 = String.valueOf(destinationLatitudeInDegrees) + ", " + String.valueOf(destinationLongitudeInDegrees);
        Location location1 = Company.getParkRegistry().getLocByLocation(coord1);
        Location location2 = Company.getParkRegistry().getLocByLocation(coord2);
        double userWeight = 1;
        double userHeight = 1;
        User user = new User(-1, "", 1, 1, 1, "", 0, 1, "-1");
        if (!username.equals("-1")) {
            user = Company.getUserRegistry().getUserByUsername(username);
            userWeight = user.getWeight();
            userHeight = user.getHeight();
        }
        double bodyArea = bodyArea(userWeight, userHeight);
        double distance = getDistance(coord1, coord2);

        double altitudeDiference = (double) location1.getAltitude() - location2.getAltitude();
        double angleBetweenParks = angleCalculate(distance, altitudeDiference);
        double attrition = attritionForce(userWeight, angleBetweenParks);

        double windSpeed = GraphController.getEdge(location1.getName(), location2.getName()).getElement().getWindSpeed();
        int windAngle = GraphController.getEdge(location1.getName(), location2.getName()).getElement().getWindDirection();
        double anglePark = Calculator.bearing(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());
        if (anglePark < 0) {
            anglePark = 360 + anglePark;
        }
        double angle = Math.abs(windAngle - anglePark);
        //totalSpeed=V-Va where Va= Vw*cos(o)
        double totalSpeed = user.getAverageSpeed() - (windSpeed * Math.cos(Math.toRadians(angle)));
        double aerodinamicForce = aerodinamicForce(bodyArea, totalSpeed);
        double gravitationalForce = gravitationalForce(userWeight, angleBetweenParks);
        double totalEnergy = calculateEnergyBetweenParks(gravitationalForce, aerodinamicForce, distance/1000, attrition);
        //in Kw
        return totalEnergy / 1000;
    }

    private static double bodyArea(double userWeight, double userHeight) {
        return (Math.sqrt((userWeight * userHeight) / 3600)) / 3;
    }

    private static double angleCalculate(double distance, double altitude) {
        double resultOfAngle = Math.abs(altitude) / distance;

        return Math.toDegrees(Math.asin(resultOfAngle));
    }

    private static double attritionForce(double userWeight, double angle) {
        //Fa=µ*N where µ=0.625 and N=m*g*Cos(o)
        return 0.625 * (userWeight + BIKEWEIGTH) * 10 * Math.cos(Math.toRadians(angle));
    }

    private static double aerodinamicForce(double bodyArea, double totalSpeed) {
        //F=1/2*P*V^2*Cd*A
        //air density=P=1.2754
        //Drag coeficient=Cd=0.7
        return (double) 1 / 2 * 1.2754 * Math.pow(totalSpeed, 2) * 0.7 * bodyArea;
    }

    private static double gravitationalForce(double userWeight, double angle) {
        //Fg=m*g*sen(o)
        return (userWeight + BIKEWEIGTH) * 10 * Math.sin(Math.toRadians(angle));
    }

    private static double calculateEnergyBetweenParks(double gravitationalForce, double dragF, double distance, double attrition) {
        //E=F*totalSpeed
        //Etotal=(attrition+drag+gravitational)*totalSpeed
        return (attrition + dragF + gravitationalForce) * distance;
    }
}
