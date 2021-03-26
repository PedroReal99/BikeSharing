package lapr.project.model;

public class Administrator extends User {

    public Administrator(int userId, String password, float height, float weight, int creditCard, String email, int points,double averageSpeed,String username) {
        super(userId, password, height, weight, creditCard, email, points,averageSpeed,username);
    }

    
    
    public Administrator() {
    }
    
}