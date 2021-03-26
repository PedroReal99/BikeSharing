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
import lapr.project.model.RideBilling;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class RideBillingDB extends DataHandler{
    
    
   
    public List<RideBilling> getAllRideBillings(int invoiceId){
        List<RideBilling> listaRideBillings = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllRideBillings(?) }");
            callStmt.setInt(2, invoiceId);
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                float cost = rSet.getFloat(1);
                //int r_invoiceId = rSet.getInt(2);
                int rideId = rSet.getInt(3);
                String month = rSet.getString(4);
                
                listaRideBillings.add(new RideBilling(cost, rideId, month));
                
            }
            return listaRideBillings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Invoices found!!");
    }

    public RideBilling getRideBilling(int invoiceId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invoiceId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                float cost = rSet.getFloat(1);
                //int r_invoiceId = rSet.getInt(2);
                int rideId = rSet.getInt(3);
                String month = rSet.getString(4);

                return new RideBilling(cost, rideId, month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + invoiceId);
    }

    public boolean addRideBilling(RideBilling rb, int invoiceId) {
        if(addInvoice(rb.getCost(),rb.getRideId(),rb.getMonth(),invoiceId)){
            return true;
        } return false;
    }

    private boolean addInvoice(float cost, int rideId, String month, int invoiceId) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addRideBilling(?,?,?,?) }");

            callStmt.setFloat(1, cost);
            callStmt.setInt(2, rideId);
            callStmt.setString(3,month);
            callStmt.setInt(4, invoiceId);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeRideBilling(int invId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeRideBilling(?) }");

            callStmt.setInt(1, invId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
}
