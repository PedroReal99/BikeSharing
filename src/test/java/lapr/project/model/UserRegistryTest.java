/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author titi1
 */
public class UserRegistryTest {

    public UserRegistryTest() {
    }

    /**
     * Test of getUserMap method, of class UserRegistry.
     */
    @Test
    public void testGetUserMap() {
        System.out.println("getUserMap");
        UserRegistry instance = new UserRegistry(new HashMap<>());
        User p = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        Map<Integer, User> userMap = new TreeMap<>();
        userMap.put(p.getUserId(), p);
        instance.getUserMap().put(p.getUserId(), p);
        Map<Integer, User> result = instance.getUserMap();
        assertEquals(userMap, result);
    }

    /**
     * Test of addNewUser method, of class UserRegistry.
     */
    @Test
    public void testAddNewUser() {
        System.out.println("addNewUser");
        User p = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        UserRegistry instance = new UserRegistry();
        instance.addNewUser(p);
        assertEquals(p, instance.getUserMap().get(p.getUserId()));
        List<Integer> listResult = new ArrayList<>();
        listResult.add(p.getUserId());
        assertEquals(listResult, instance.getNewUsers());
    }

    /**
     * Test of addUpdatedUser method, of class UserRegistry.
     */
    @Test
    public void testAddUpdatedUser() {
        System.out.println("addUpdatedUser");
        User p = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        UserRegistry instance = new UserRegistry();
        instance.addNewUser(p);
        p.setWeight(620);
        instance.addUpdatedUser(p);
        assertEquals(p, instance.getUserMap().get(p.getUserId()));
        List<Integer> listResult = new ArrayList<>();
        listResult.add(p.getUserId());
        assertEquals(listResult, instance.getUpdatedUsers());
    }

    /**
     * Test of addDeletedUser method, of class UserRegistry.
     */
    @Test
    public void testAddDeletedUser() {
        System.out.println("addDeletedUser");
        User u = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        UserRegistry instance = new UserRegistry();
        instance.addNewUser(u);
        instance.addDeletedUser(u.getUserId());
        assertEquals(null, instance.getUserMap().get(u.getUserId()));
        List<Integer> listResult = new ArrayList<>();
        listResult.add(u.getUserId());
        assertEquals(listResult, instance.getDeletedUsers());
    }

    /**
     * Test of checkUser method, of class UserRegistry.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        User u = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        UserRegistry instance = new UserRegistry();
        boolean result = instance.checkUser(u.getUserId());
        assertTrue(result);
    }

    /**
     * Test of getUserByUsername method, of class UserRegistry.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "Pedro";
        UserRegistry instance = new UserRegistry();

        User u = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        Company.setUserRegistry(new UserRegistry());
        instance.addNewUser(u);
        
        User result = instance.getUserByUsername(username);
        User expResult = new User(1, "qwerty", 1.0F, 3.0F, 1234567, "1234567", 30, 2.0, "Pedro");
        assertEquals(expResult, result);

    }

}
