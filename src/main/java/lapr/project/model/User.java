package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User {

    private int userId;

    
    private String password;
    private int points;
    private float height;
    private float weight;
    private int creditCard;
    private String email;
    private Map<Integer, Ride> rideMap;
    private List<Ride> newRideList;
    private List<Invoice> invoiceList;
    private List<Receipt> receiptList;
    private double averageSpeed;
    private String username;

        public User(int userId, String password, float height, float weight, int creditCard, String email, int points, double averageSpeed, String username) {
        this.userId = userId;
        this.password = password;
        this.points = points;
        this.height = height;
        this.weight = weight;
        this.creditCard = creditCard;
        this.email = email;
        this.rideMap = new HashMap<>();
        newRideList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        receiptList = new ArrayList<>();
        this.averageSpeed = averageSpeed;
        this.username = username;
    }

    public User(String username, String email, float height, float weight, double averageSpeed, int visa) {
        this.username = username;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.averageSpeed = averageSpeed;
        this.creditCard = visa;
        this.rideMap = new HashMap<>();
        newRideList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        receiptList = new ArrayList<>();
        
    }
    public User() {
        this.rideMap = new HashMap<>();
        newRideList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        receiptList = new ArrayList<>(); 
    }

    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
        this.rideMap = new HashMap<>();
        newRideList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        receiptList = new ArrayList<>();
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Integer, Ride> getRideMap() {
        return rideMap;
    }

    public void setRideMap(Map<Integer, Ride> rideMap) {
        this.rideMap = rideMap;
    }

    public Ride getRideById(int rideId) {
        return rideMap.get(rideId);
    }
    
    

    public void addRide(Ride r) {
        if (rideMap.get(r.getRideId()) == null) {
            rideMap.put(r.getRideId(), r);
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ride> getNewRideList() {
        return newRideList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setNewRideList(List<Ride> newRideList) {
        this.newRideList = newRideList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

   
    @Override
    public int hashCode() {
        return 7;
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (Float.floatToIntBits(this.height) != Float.floatToIntBits(other.height)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        if (this.creditCard != other.creditCard) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if(this.points != other.points){
           return false; 
        }
        return Objects.equals(this.rideMap, other.rideMap);
    }

    public float invoicesTotalCost(){
        float total = 0;
        for(Invoice inv : this.getInvoiceList()){
            total = total + inv.getTotalCost();
        }
        return total;
    }
    
    public Invoice getInvoiceByMonth(String m){
        for(Invoice iL : this.getInvoiceList()){
            if(iL.getMonth().equalsIgnoreCase(m)){
                return iL;
            }
        }
        return null;
    }
    
    public void addInvoice(Invoice e){
        getInvoiceList().add(e);
    }
    
    public void addReceipt(Receipt rec){
        getReceiptList().add(rec);
    }

    /**
     * @return the averageSpeed
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * @param averageSpeed the averageSpeed to set
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    public float getUserCurrentDebt(){
        float debt=0;
        for(Invoice in : getInvoiceList()){
            debt = debt + in.getTotalCost();
        }
        return debt;
    }
    
    public int countRides(){
        return getRideMap().size();       
    }
    
    @Override
    public String toString() {
        return "user{" + "userId=" + userId + ", password=" + password + ", height=" + height + ", weight=" + weight + ", creditCard=" + creditCard + ", email=" + email + ", rideMap=" + rideMap + ", newRideList=" + newRideList + '}' + ", points: " + points;
    }

}
