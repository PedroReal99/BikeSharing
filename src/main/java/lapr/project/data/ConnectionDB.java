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
import lapr.project.controller.GraphController;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Location;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class ConnectionDB extends DataHandler{
    
       public List<Connection> getAllConnection(){
        List<Connection> listaConnections = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllConnections }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int idConnection = rSet.getInt(1);
                String locDesc1 = rSet.getString(2);
                String locDesc2 = rSet.getString(3);
                double windSpeed = rSet.getDouble(4);
                int windDirection = rSet.getInt(5);
                double weight = rSet.getDouble(6);
                
                Map<String,Location> map = Company.getParkRegistry().getParkMap();
                GraphController.addConnection(locDesc1, locDesc2, new Connection(idConnection, windDirection, windSpeed), weight);
              
            }
            return listaConnections;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Connections found found!!");
    }

    public Connection getConnection(int conId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLocation(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, conId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int idConnection = rSet.getInt(1);
                //String locDesc1 = rSet.getString(2);
                //String locDesc2 = rSet.getString(3);
                double windSpeed = rSet.getDouble(4);
                int windDirection = rSet.getInt(5);
                //double weight = rSet.getDouble(6);

                return new Connection(idConnection, windDirection, windSpeed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Connection with ID:" + conId);
    }

    public boolean addConnection(Connection con,String locDesc1,String locDesc2, double weight) {
        if(addConnection(con.getId(),locDesc1,locDesc2,con.getWindSpeed(),con.getWindDirection(),weight)){
            return true;
        } return false;
    }

    private boolean addConnection(int id, String locDesc1, String locDesc2, double windSpeed, int windDirec, double weight) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addConnection(?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, locDesc1);
            callStmt.setString(3,locDesc2);
            callStmt.setDouble(4, windSpeed);
            callStmt.setInt(5, windDirec);
            callStmt.setDouble(6, weight);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeConnection(int conId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeConnections(?) }");

            callStmt.setInt(1, conId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
