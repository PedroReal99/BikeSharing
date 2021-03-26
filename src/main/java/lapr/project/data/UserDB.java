/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class UserDB extends DataHandler{
    
    
    public List<User> getAllUser(){
        List<User> listaUsers = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllUser }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int userID = rSet.getInt(1);
                String userPassword = rSet.getString(2);
                float userHeight = rSet.getFloat(3);
                float userWeight = rSet.getFloat(4);
                int userCreditCard = rSet.getInt(5);
                String userEmail = rSet.getString(6);
                int points = rSet.getInt(7);
                double averageSpeed = rSet.getDouble(8);
                String username = rSet.getString(9);

                listaUsers.add(new User(userID, userPassword, userHeight,userWeight,userCreditCard,userEmail,points,averageSpeed,username));
            }
            return listaUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Users found!!");
    }

    public User getUser(int userId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUser(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, userId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                 int userID = rSet.getInt(1);
                String userPassword = rSet.getString(2);
                float userHeight = rSet.getFloat(3);
                float userWeight = rSet.getFloat(4);
                int userCreditCard = rSet.getInt(5);
                String userEmail = rSet.getString(6);
                int points = rSet.getInt(7);
                double averageSpeed = rSet.getDouble(8);
                String username = rSet.getString(9);

                return new User(userID, userPassword, userHeight, userWeight, userCreditCard, userEmail,points,averageSpeed,username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No User with ID:" + userId);
    }

    public boolean addUser(User user) {
        if(addUser(user.getUserId(), user.getPassword(),user.getHeight(),user.getWeight(),user.getCreditCard(),user.getEmail(),user.getPoints(),user.getAverageSpeed(),user.getUsername())){
            return true;
        } return false;
    }

    private boolean addUser(int userId,String password, float height, float weight, int creditCard, String email, int points,double AverageSpeed,String username) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addUser(?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, userId);
            callStmt.setString(2, password);
            callStmt.setFloat(3,height);
            callStmt.setFloat(4, weight);
            callStmt.setInt(5, creditCard);
            callStmt.setString(6, email);
            callStmt.setInt(7, points);
            callStmt.setDouble(8, AverageSpeed);
            callStmt.setString(9, username);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeUser(int userId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeUser(?) }");

            callStmt.setInt(1, userId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    
    public boolean updateUser(User user) {
        if(updateUser(user.getUserId(), user.getPassword(),user.getHeight(),user.getWeight(),user.getCreditCard(),user.getEmail(),user.getPoints(),user.getAverageSpeed(),user.getUsername())){
            return true;
        } return false;
    }

    private boolean updateUser(int userId,String password, float height, float weight, int creditCard, String email, int points,double averageSpeed,String username) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call updateUser(?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, userId);
            callStmt.setString(2, password);
            callStmt.setFloat(3,height);
            callStmt.setFloat(4, weight);
            callStmt.setInt(5, creditCard);
            callStmt.setString(6, email);
            callStmt.setInt(7, points);
            callStmt.setDouble(8, averageSpeed);
            callStmt.setString(9, username);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    

}
