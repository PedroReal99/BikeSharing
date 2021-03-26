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
import lapr.project.model.Receipt;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pedro
 */
public class ReceiptDB extends DataHandler{
    
    
    //Adiciona automaticamente a lista de receitas no user
    public List<Receipt> getAllReceipts() {
        CallableStatement callStmt = null;
        List<Receipt> listaReceipts = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllReceipts }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int invoiceId = rSet.getInt(1);
                int userId = rSet.getInt(2);
                String paymentDate = rSet.getString(3);
                float totalValue = rSet.getFloat(4);
                
                Company.getUserRegistry().getUserById(userId).getReceiptList().add(new Receipt(invoiceId, userId, paymentDate, totalValue));
            }
            return listaReceipts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Receipts found!!");
    }

    public Receipt getReceipt(int invId) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getReceipt(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, invId);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int invoiceId = rSet.getInt(1);
                int userId = rSet.getInt(2);
                String paymentDate = rSet.getString(3);
                float totalValue = rSet.getFloat(4);
                
                return new Receipt(invoiceId, userId, paymentDate, totalValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Bike with ID:" + invId);
    }

    public boolean addReceipt(Receipt receipt) {
        if (addReceipt(receipt.getInvoiceId(), receipt.getUserId(),receipt.getPaymentDate(),receipt.getTotalValue())) {
            return true;
        }
        return false;
    }

    private boolean addReceipt(int invoiceId, int userId, String paymentDate, float totalValue) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addReceipt(?,?,?,?) }");

            callStmt.setInt(1, invoiceId);
            callStmt.setInt(2, userId);
            callStmt.setString(3, paymentDate);
            callStmt.setFloat(4, totalValue);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeReceipt(int invId) {

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call removeReceipt(?) }");

            callStmt.setInt(1, invId);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
}
