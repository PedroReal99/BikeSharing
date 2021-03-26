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
import lapr.project.model.Invoice;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class InvoiceDB extends DataHandler{
    //Deve ser void(?) ou return duma lista ?? Eu aqui ja adiciono aos registos
    public List<Invoice> getAllInvoices(){
        List<Invoice> listaLocations = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllInvoices }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                float totalCost = rSet.getFloat(1);
                int id = rSet.getInt(2);
                String paymentDate = rSet.getString(3);
                String month = rSet.getString(4);
                int userId = rSet.getInt(5);
                
                
                Company.getUserRegistry().getUserById(userId).getInvoiceList().add(new Invoice(totalCost,id,paymentDate,month));
                listaLocations.add(new Invoice(totalCost,id,paymentDate,month));
            }
            return listaLocations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Invoices found!!");
    }

    public Invoice getInvoice(int invoiceId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invoiceId);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                float totalCost = rSet.getFloat(1);
                int id = rSet.getInt(2);
                String paymentDate = rSet.getString(3);
                String month = rSet.getString(4);

                return new Invoice(totalCost, id, paymentDate, month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Invoice with ID:" + invoiceId);
    }

    public boolean addInvoice(Invoice inv, int userId) {
        if(addInvoice(inv.getTotalCost(),inv.getInvoiceId(),inv.getPaymentLimitDate(),inv.getMonth(),userId)){
            return true;
        } return false;
    }

    private boolean addInvoice(float totalCost, int invId, String paymentDate, String month, int userId) {

        try {
            openConnection();
  
            CallableStatement callStmt = getConnection().prepareCall("{ call addInvoice(?,?,?,?,?) }");

            callStmt.setFloat(1, totalCost);
            callStmt.setInt(2, invId);
            callStmt.setString(3,paymentDate);
            callStmt.setString(4, month);
            callStmt.setInt(5, userId);
            
            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeInvoice(int invId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeInvoices(?) }");

            callStmt.setInt(1, invId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    

}
