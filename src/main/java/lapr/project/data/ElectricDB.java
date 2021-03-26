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
import lapr.project.model.Electric;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class ElectricDB extends DataHandler {

    public Electric getEletric(String bicycleDesc) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getEletric(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, bicycleDesc);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                //int bikeId = rSet.getInt(1);
                Float elecAtualBattery = rSet.getFloat(2);
                Float elecBatteryCapacity = rSet.getFloat(3);
                Float batteryWeight = rSet.getFloat(4);

                return new Electric(elecBatteryCapacity, elecAtualBattery, batteryWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Electric with ID:" + bicycleDesc);
    }

    public boolean addElectric(Electric electric) {
        if (addElectric(electric.getBaterrycapacity(), electric.getAtualBaterry(), electric.getBicycleDesc(), electric.getBatteryWeight())) {
            return true;
        }
        return false;
    }

    private boolean addElectric( float batterycapacity, float atualBattery, String bicycleDesc, float batteryWeight) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addEletric(?,?,?,?) }");

            callStmt.setString(1, bicycleDesc);
            callStmt.setFloat(2, atualBattery);
            callStmt.setFloat(3, batterycapacity);
            callStmt.setFloat(4, batteryWeight);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeElectric(String bicycleId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeEletric(?) }");

            callStmt.setString(1, bicycleId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
