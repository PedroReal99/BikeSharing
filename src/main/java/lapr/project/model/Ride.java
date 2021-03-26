package lapr.project.model;

import java.util.Objects;

public class Ride {

    private String rideDate;
    private String startTime;
    private String endTime;
    private String startParkDesc;
    private String endParkDesc;
    private String bicycleDesc;
    private int rideId;
    private double caloriesBurned;

    public Ride(String rideDate, String startTime, String endTime, String startParkDesc, String endParkDesc, int rideId, double caloriesBurned, String bicycleDesc) {
        this.rideDate = rideDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startParkDesc = startParkDesc;
        this.endParkDesc = endParkDesc;
        this.rideId = rideId;
        this.caloriesBurned = caloriesBurned;
        this.bicycleDesc = bicycleDesc;
    }

    public Ride() {
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartParkDesc() {
        return startParkDesc;
    }

    public String getEndParkDesc() {
        return endParkDesc;
    }

    public void setStartParkDesc(String startParkDesc) {
        this.startParkDesc = startParkDesc;
    }

    public void setEndParkDesc(String endParkDesc) {
        this.endParkDesc = endParkDesc;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    /**
     * @return the bicycleId
     */
    public String getBicycleDesc() {
        return bicycleDesc;
    }

    /**
     * @param bicycleDesc the bicycleId to set
     */
    public void setBicycleDesc(String bicycleDesc) {
        this.bicycleDesc = bicycleDesc;
    }

    public int getHoursUsed() {
        int horas = 0;

        String[] start = getStartTime().split(":");
        String[] end = getEndTime().split(":");

        int startingHour = Integer.parseInt(start[0]);
        int endingHour = Integer.parseInt(end[0]);

        int startingMin = Integer.parseInt(start[1]);
        int endingMin = Integer.parseInt(end[1]);

        if (endingHour > startingHour) {
            horas = endingHour - startingHour;
        } else if (endingHour < startingHour) {
            int hoursTillMid = 24 - startingHour;
            horas = hoursTillMid + endingHour;
        } else {
            return horas;
        }

        if (startingMin > endingMin) {
            horas--;
        }
        return horas;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.rideDate);
        hash = 89 * hash + Objects.hashCode(this.startTime);
        hash = 89 * hash + Objects.hashCode(this.endTime);
        hash = 89 * hash + Objects.hashCode(this.startParkDesc);
        hash = 89 * hash + Objects.hashCode(this.endParkDesc);
        hash = 89 * hash + Objects.hashCode(this.bicycleDesc);
        hash = 89 * hash + Objects.hashCode(this.rideId);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.caloriesBurned) ^ (Double.doubleToLongBits(this.caloriesBurned) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ride other = (Ride) obj;
        if (Double.doubleToLongBits(this.caloriesBurned) != Double.doubleToLongBits(other.caloriesBurned)) {
            return false;
        }
        if (!Objects.equals(this.rideDate, other.rideDate)) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.endTime, other.endTime)) {
            return false;
        }
        if (!Objects.equals(this.startParkDesc, other.startParkDesc)) {
            return false;
        }
        if (!Objects.equals(this.endParkDesc, other.endParkDesc)) {
            return false;
        }
        if (!Objects.equals(this.bicycleDesc, other.bicycleDesc)) {
            return false;
        }
        return Objects.equals(this.rideId, other.rideId);
    }

    @Override
    public String toString() {
        return "Ride{" + "rideDate=" + rideDate + ", startTime=" + startTime + ", endTime=" + endTime + ", startParkDesc=" + startParkDesc + ", endParkDesc=" + endParkDesc + ", bicycleId=" + bicycleDesc + ", rideId=" + rideId + ", caloriesBurned=" + caloriesBurned + '}';
    }

}
