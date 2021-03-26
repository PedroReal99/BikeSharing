/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import lapr.project.model.Invoice;
import lapr.project.model.Receipt;
import lapr.project.model.User;

/**
 *
 * @author hugov
 */
public class UpdateInvoiceCostController {
    
    public int updateInvoiceCost(User u){
        int points = u.getPoints();
        Calendar now = Calendar.getInstance();
        float cost = u.getInvoiceByMonth(new SimpleDateFormat("MMM").format(now.getTime())).getTotalCost();
        
        while(points>=10 && cost>=1){
            cost--;
            points = points - 10;           
        }
        u.setPoints(points);  // altera os pontos do user de acordo com a fatura a pagar.
        u.getInvoiceByMonth(new SimpleDateFormat("MMM").format(now.getTime())).setTotalCost(cost); // altera o custo da fatura de acordo com os pontos usados.
        
        return points;
    }
    
    public Receipt invoicePayment(User u){
        Calendar now = Calendar.getInstance();
        String m = new SimpleDateFormat("MMM").format(now.getTime());
        Invoice inv = u.getInvoiceByMonth(m);
        
        int invID = inv.getInvoiceId();
        int userID = u.getUserId();
        float totalValue = inv.getTotalCost();
        
        Receipt r = new Receipt(invID, userID, "Last day of " + m, totalValue);
        inv.setTotalCost(0);
        return r;
    }
}
