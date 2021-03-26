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
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Electric;
import lapr.project.model.Mountain;
import lapr.project.model.Road;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class BicycleDB extends DataHandler {

    public List<Bicycle> getAllBikes() {
        List<Bicycle> listaBikes = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllBikes }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                String bikeDesc = rSet.getString(1);//Primary key
                int bikeIsAvailable = rSet.getInt(2);
                String parkDesc = rSet.getString(3);
                String bikeType = rSet.getString(4);
                float bikeCostHour = rSet.getFloat(5);
                float weight = rSet.getFloat(6);
                float aerodynamicCoeficient = rSet.getFloat(7);
                float frontalArea = rSet.getFloat(8);

                if (bikeType.equalsIgnoreCase("Eletrical")) {
                    Electric e = new ElectricDB().getEletric(bikeDesc);
                    boolean flag;
                    if (bikeIsAvailable == 1) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    listaBikes.add(new Electric(e.getBaterrycapacity(),e.getAtualBaterry(),e.getBatteryWeight(), bikeDesc, flag, bikeCostHour,weight, aerodynamicCoeficient,frontalArea));
                    Company.getParkRegistry().getParkByDescription(parkDesc).addBicycle(new Electric(e.getBaterrycapacity(),e.getAtualBaterry(), e.getBatteryWeight(),bikeDesc, flag, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
;                } else if (bikeType.equalsIgnoreCase("Mountain")) {
                    if (bikeIsAvailable == 1) {
                        listaBikes.add(new Mountain(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                        Company.getParkRegistry().getParkByDescription(parkDesc).addBicycle(new Mountain(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                    }
                    listaBikes.add(new Mountain(bikeDesc, false, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                    Company.getParkRegistry().getParkByDescription(parkDesc).addBicycle(new Mountain(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                } else {
                    if (bikeIsAvailable == 1) {
                        listaBikes.add(new Road(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                        Company.getParkRegistry().getParkByDescription(parkDesc).addBicycle(new Road(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                    } else{
                    listaBikes.add(new Road(bikeDesc, false, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                    Company.getParkRegistry().getParkByDescription(parkDesc).addBicycle(new Road(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea));
                    }
                }
            }
            return listaBikes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Bikes found!!");
    }

    public Bicycle getBike(String bicycleDescription) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getBicycle(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parÃ¢metro de entrada da funÃ§Ã£o "getSailor".
            callStmt.setString(2, bicycleDescription);

            // Executa a invocaÃ§Ã£o da funÃ§Ã£o "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String bikeDesc = rSet.getString(1);//Primary key
                int bikeIsAvailable = rSet.getInt(2);
                String bikeType = rSet.getString(4);
                float bikeCostHour = rSet.getFloat(5);
                float weight = rSet.getFloat(6);
                float aerodynamicCoeficient = rSet.getFloat(7);
                float frontalArea = rSet.getFloat(8);

                if ("Eletrical".equalsIgnoreCase(bikeType)) {
                    if (bikeIsAvailable == 1) {
                        return new Electric(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                    }
                    return new Electric(bikeDesc, false, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                } else if ("Mountain".equalsIgnoreCase(bikeType)) {
                    if (bikeIsAvailable == 1) {
                        return new Mountain(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                    }
                    return new Mountain(bikeDesc, false, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                } else {
                    if (bikeIsAvailable == 1) {
                        return new Road(bikeDesc, true, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                    }
                    return new Road(bikeDesc, false, bikeCostHour,weight,aerodynamicCoeficient,frontalArea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Bike with ID:" + bicycleDescription);
    }

    public boolean addBike(Bicycle bike, String type, String parkId) {
        if (addBike(bike.getBicycleDesc(), bike.isIsAvailable(), parkId, type, bike.getCostPerHour(),bike.getWeight(),bike.getAerodynamicCoeficient(),bike.getFrontalArea())) {
            return true;
        }
        return false;
    }

    private boolean addBike(String bikeId, boolean isAvailable, String parkId, String type, float costPerHour,float weight, float aerodynamicCoeficient, float frontalArea) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addBike(?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, bikeId);
            if (isAvailable) {
                callStmt.setInt(2, 1);
            } else {
                callStmt.setInt(2, 0);
            }
            callStmt.setString(3, parkId);
            callStmt.setString(4, type);
            callStmt.setFloat(5, costPerHour);
            callStmt.setFloat(6, weight);
            callStmt.setFloat(7, aerodynamicCoeficient);
            callStmt.setFloat(8,frontalArea);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeBike(String bikeId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeBike(?) }");

            callStmt.setString(1, bikeId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
