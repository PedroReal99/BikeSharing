/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.User;

/**
 *
 * @author USER
 */
public class UserLoginController {
    
    public boolean userLogin(String mail, String pass){
        for(User u : Company.getUserRegistry().getUserMap().values()){           
            if(u.getEmail().equals(mail)){
                return u.getPassword().equals(pass);
            }          
        }
        return false;
    }
}
