package lapr.project.model;

public class RideBilling {

    private float cost;
    private int rideId;
    private String month;

    public RideBilling(float cost, int rideId, String month) {
        this.cost = cost;
        this.rideId = rideId;
        this.month = month;
    }

    public RideBilling() {
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
