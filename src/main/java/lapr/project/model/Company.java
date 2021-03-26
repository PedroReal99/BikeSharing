package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private Company() {
    }
    private static ParkRegistry parkRegistry = new ParkRegistry();
    private static BicycleRegistry bicycleRegistry = new BicycleRegistry();
    private static UserRegistry userRegistry = new UserRegistry();
    private static List<Invoice> invoiceList = new ArrayList<>();

    public static ParkRegistry getParkRegistry() {
        return parkRegistry;
    }

    public static void setParkRegistry(ParkRegistry parkRegistry) {
        Company.parkRegistry = parkRegistry;
    }

    public static BicycleRegistry getBicycleRegistry() {
        return bicycleRegistry;
    }

    public static void setBicycleRegistry(BicycleRegistry bicycleRegistry) {
        Company.bicycleRegistry = bicycleRegistry;
    }

    public static UserRegistry getUserRegistry() {
        return userRegistry;
    }

    public static void setUserRegistry(UserRegistry userRegistry) {
        Company.userRegistry = userRegistry;
    }

    public static List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public static void setInvoiceList(List<Invoice> invoiceList) {
        Company.invoiceList = invoiceList;
    }

}
