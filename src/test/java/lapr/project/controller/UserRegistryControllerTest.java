/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Invoice;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pedro
 */
public class UserRegistryControllerTest {

    /**
     * Test of saveUser method, of class UserRegistryController.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User p = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        UserRegistryController instance = new UserRegistryController();
        Company.setUserRegistry(new UserRegistry());
        instance.addUser(p);
        User result = Company.getUserRegistry().getUserMap().get(p.getUserId());
        assertEquals(result, p);
    }

    /**
     * Test of getUserCurrentDebt method, of class UserRegistryController.
     */
    @Test
    public void testGetUserCurrentDebt() {
        System.out.println("getUserCurrentDebt");
        String username = "Pedro";
        User p = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        Invoice i = new Invoice(12, 1, "", "");
        Invoice i2 = new Invoice(10, 2, "", "");
        p.addInvoice(i);
        p.addInvoice(i2);
        UserRegistryController instance = new UserRegistryController();
        Company.setUserRegistry(new UserRegistry());
        Company.getUserRegistry().addNewUser(p);
        float expResult = 22F;
        float result = instance.getUserCurrentDebt(username);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of getInvoiceForMonth method, of class UserRegistryController.
//     */
//    @Test
//    public void testGetInvoiceForMonth() {
//        System.out.println("getInvoiceForMonth");
//        int month = 2;
//        String username = "hugo";
//        Company.setUserRegistry(new UserRegistry());
//
//        User u = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "hugo");
//        Invoice i = new Invoice(12, 1, "", "Fev");
//        u.addInvoice(i);
//        Company.getUserRegistry().addNewUser(u);
//        UserRegistryController instance = new UserRegistryController();
//        Invoice expResult = i;
//        Invoice result = instance.getInvoiceForMonth(month, username);
//        assertEquals(expResult, result);
//        
//    }

}
