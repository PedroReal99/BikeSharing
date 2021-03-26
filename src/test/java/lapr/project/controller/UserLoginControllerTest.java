/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author USER
 */
public class UserLoginControllerTest {

    public UserLoginControllerTest() {
    }

    /**
     * Test of userLogin method, of class UserLoginController.
     */
    @Test
    public void testUserLogin() {
        System.out.println("userLogin");
        UserLoginController instance = new UserLoginController();
        Company.setUserRegistry(new UserRegistry());

        String mail = "hugovinhal98@gmail.com";
        String pass = "amo_lapr3";
        
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com",30,2.0,"Pedro");
        Company.getUserRegistry().addNewUser(u);
        boolean result = instance.userLogin(mail, pass);
        assertTrue(result);
        
        String mail2 = "falseMail@gmail.com";
        boolean result2 = instance.userLogin(mail2, pass);
        assertTrue(!result2);
        
        String pass2 = "falsePassword";
        boolean result3 = instance.userLogin(mail, pass2);
        assertTrue(!result3);
    }

}
