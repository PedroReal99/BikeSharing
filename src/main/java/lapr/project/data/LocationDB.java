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
import lapr.project.model.Company;
import lapr.project.model.Location;
import oracle.jdbc.OracleTypes;
import lapr.project.controller.GraphController;

/**
 *
 * @author Pedro
 */
public class LocationDB extends DataHandler{
    
    GraphController gc = new GraphController();
    
    
        public List<Location> getAllLocation(){
        List<Location> listaLocations = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllLocations }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                String location = rSet.getString(1);
                String name = rSet.getString(2);
                int altitude = rSet.getInt(3);
                
                //Tenho que adicionar no grafo...
                //Company.getParkRegistry().getGraph().insertVertex(new Location(location, name, id_location, altitude));
                //Acho que é este
                GraphController.loadVertex(name);
                
                listaLocations.add(new Location(location, name, altitude));
            }
            return listaLocations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Locations found!!");
    }

    public Location getLocation(int locId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLocation(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, locId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String location = rSet.getString(1);
                String description = rSet.getString(2);//PrimRY KEY
                int altitude = rSet.getInt(3);

                return new Location(location, description, altitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Location with ID:" + locId);
    }

    public boolean addLocation(Location loc) {
        if(addLocation(loc.getLocation(),loc.getName(),loc.getAltitude())){
            return true;
        } return false;
    }

    private boolean addLocation(String location, String desc, int altitude) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addLocation(?,?,?) }");

            callStmt.setString(1, location);
            callStmt.setString(2, desc);
            callStmt.setInt(3, altitude);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeLocation(String locId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeLocations(?) }");

            callStmt.setString(1, locId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    
    public boolean updateLocation(Location loc) {
        if(updateLocation(loc.getLocation(),loc.getName(),loc.getAltitude())){
            return true;
        } return false;
    }

    private boolean updateLocation(String location, String desc, int altitude) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call updateLocation(?,?,?) }");

            callStmt.setString(1, location);
            callStmt.setString(2, desc);
            callStmt.setInt(3, altitude);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
