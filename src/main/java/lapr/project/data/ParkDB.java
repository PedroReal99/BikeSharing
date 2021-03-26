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
import lapr.project.model.Park;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class ParkDB extends DataHandler{
    
        public List<Park> getAllPark(){

        List<Park> listaParks = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllParks }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parÃ¢metro de entrada da funÃ§Ã£o "getSailor".
            //callStmt.setInt(2, userId);

            // Executa a invocaÃ§Ã£o da funÃ§Ã£o "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int parkCapacityMountainRoad = rSet.getInt(1);
                int parkCapacityEletric = rSet.getInt(2);
                String parkDescription = rSet.getString(3);
                int parkAltitude = rSet.getInt(4);
                String parkLocation = rSet.getString(5);
                int parkTension = rSet.getInt(6);
                int parkCurrent = rSet.getInt(7);

                listaParks.add(new Park(parkCapacityMountainRoad, parkCapacityEletric, parkAltitude, parkLocation, parkDescription, parkTension, parkCurrent));
            }
           // closeAll();
            return listaParks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Parks found!!");
    }
    

    public Park getPark(String parkDesc) {


        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPark(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parÃ¢metro de entrada da funÃ§Ã£o "getSailor".
            callStmt.setString(2, parkDesc);

            // Executa a invocaÃ§Ã£o da funÃ§Ã£o "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int parkCapacityMountainRoad = rSet.getInt(1);
                int parkCapacityEletric = rSet.getInt(2);
                String parkDescription = rSet.getString(3);
                int parkAltitude = rSet.getInt(4);
                String parkLocation = rSet.getString(5);
                int parkTension = rSet.getInt(6);
                int parkCurrent = rSet.getInt(7);

                return new Park(parkCapacityMountainRoad, parkCapacityEletric, parkAltitude, parkLocation, parkDescription, parkTension, parkCurrent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Park with ID:" + parkDesc);
    }

    public boolean addPark(Park park) {
        if(addPark(park.getCapacityMountainRoad(),park.getCapacityElectric(),park.getAltitude(),park.getLocation(),park.getDescription(),park.getTension(),park.getCurrent())){
            return true;
        } return false;
    }

    private boolean addPark(int capacityMountainRoad, int capacityElectric, int altitude, String location, String parkDesc, int tension, int current) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPark(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, capacityMountainRoad);
            callStmt.setInt(2, capacityElectric);
            callStmt.setString(3,parkDesc);
            callStmt.setInt(4, altitude);
            callStmt.setString(5, location);
            callStmt.setInt(6, tension);
            callStmt.setInt(7, current);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removePark(String parkDesc) {

        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removePark(?) }");

            callStmt.setString(1, parkDesc);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        
        public boolean updatePark(Park park) {
        if(updatePark(park.getCapacityMountainRoad(),park.getCapacityElectric(),park.getAltitude(),park.getLocation(),park.getDescription(),park.getTension(),park.getCurrent())){
            return true;
        } return false;
    }

    private boolean updatePark(int capacityMountainRoad, int capacityElectric, int altitude, String location, String parkDesc, int tension, int current) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call updatePark(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, capacityMountainRoad);
            callStmt.setInt(2, capacityElectric);
            callStmt.setString(3,parkDesc);
            callStmt.setInt(4, altitude);
            callStmt.setString(5, location);
            callStmt.setInt(6, tension);
            callStmt.setInt(7, current);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        
        
        
        
        
        

    }

    

