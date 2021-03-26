/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.DateFormatSymbols;
import java.time.Month;
import lapr.project.model.Company;
import lapr.project.model.Invoice;
import lapr.project.model.User;

/**
 *
 * @author Pedro
 */
public class UserRegistryController {

    public void addUser(User u) {
        Company.getUserRegistry().addNewUser(u);
    }

    public float getUserCurrentDebt(String username) {
        User u = Company.getUserRegistry().getUserByUsername(username);
        return u.getUserCurrentDebt();
    }

//    public Invoice getInvoiceForMonth(int month, String username ) {
//        String m = new DateFormatSymbols().getMonths()[month-1];
//        String []line = m.split("");
//        String mon = line[0] + line[1] + line[2];
//        
//        User u = Company.getUserRegistry().getUserByUsername(username);
//        Invoice in = u.getInvoiceByMonth(mon);
//        return in;
//    }
}
