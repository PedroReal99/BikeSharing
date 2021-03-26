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
import java.util.Map;
import lapr.project.model.Company;
import lapr.project.model.Ride;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class RideDB extends DataHandler{
    
    
        public void getAllRides(){
        //List<ride> listaRides = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllRides }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                String rideDate = rSet.getString(1);
                String startTime = rSet.getString(2);
                String endTime = rSet.getString(3);
                String bicycleId = rSet.getString(4);
                int userId = rSet.getInt(5);
                String idParkInitial = rSet.getString(6);
                String idParkFinal = rSet.getString(7);
                int rideId = rSet.getInt(8);
                Float calories = rSet.getFloat(9);
                
                Map<Integer,User> map = Company.getUserRegistry().getUserMap();
                map.get(userId).addRide(new Ride(rideDate, startTime, endTime, idParkInitial, idParkFinal, rideId, calories,bicycleId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //throw new IllegalArgumentException("No Rides found!!");
    }

    public Ride getRide(int rideId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getRide(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, rideId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                
                String rideDate = rSet.getString(1);
                String startTime = rSet.getString(2);
                String endTime = rSet.getString(3);
                String bicycleId = rSet.getString(4);
                //int userId = rSet.getInt(5);
                String idParkInitial = rSet.getString(6);
                String idParkFinal = rSet.getString(7);
                int r_rideId = rSet.getInt(8);
                Float calories = rSet.getFloat(9);

                return new Ride(rideDate, startTime, endTime, idParkInitial, idParkFinal, r_rideId, calories,bicycleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Ride with ID:" + rideId);
    }

    public boolean addRide(Ride ride, int userId) {
        if(addRide(ride.getRideDate(), ride.getStartTime(), ride.getEndTime(), ride.getStartParkDesc(), ride.getEndParkDesc(), ride.getRideId(), ride.getCaloriesBurned(), ride.getBicycleDesc(), userId)){
            return true;
        } return false;
    }

    private boolean addRide(String rideDate, String startTime, String endTime, String parkInitial,String parkFinal, int rideId, double caloriesBurned, String bicycleDesc,int userId) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addRide(?,?,?,?,?,?,?,?,?) }");

            callStmt.setString(1,rideDate);
            callStmt.setString(2,startTime);
            callStmt.setString(3,endTime);
            callStmt.setString(4,bicycleDesc);
            callStmt.setInt(5,userId);
            callStmt.setString(6,parkInitial);
            callStmt.setString(7,parkFinal);
            callStmt.setInt(8,rideId);
            callStmt.setDouble(9,caloriesBurned);
            
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void removeRide(int rideId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeRide(?) }");

            callStmt.setInt(1, rideId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
}
