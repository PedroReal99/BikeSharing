/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//import lapr.project.model.bicycle;
//import lapr.project.model.bicycleRegistry;
//import lapr.project.model.company;
//import lapr.project.model.mountain;
//import lapr.project.model.park;
//import lapr.project.model.parkRegistry;
//import lapr.project.model.road;
//import lapr.project.model.user;
//import lapr.project.model.userRegistry;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;
//
//
//
///**
// *
// * @author Pedro
// */
//public class DataBaseControllerTest {
//
//    /**
//     * Test of loadWhenApplicationOpen method, of class DataBaseController.
//     */
//    @Test
//    public void testLoadWhenApplicationOpen() {
//        System.out.println("loadWhenApplicationOpen");
//        company.setUserRegistry(new userRegistry());
//        company.setParkRegistry(new parkRegistry());
//        company.setBicycleRegistry(new bicycleRegistry());
//        DataBaseController instance = new DataBaseController();
//        Map<Integer,user> mapa1 = new HashMap<>();
//        user u1 = new user(1,"a", 12.0F, 10.0F, 123456, "email");
//        user u2 = new user(2,"b",10.0F,35.0F,123489,"email");
//        mapa1.put(1, u1);
//        mapa1.put(2, u2);
//        instance.loadWhenApplicationOpen();
//        
//        //userRegistry ur = company.getUserRegistry();
//        Map<Integer,user> result1 = company.getUserRegistry().getUserMap();
//        assertEquals(result1,mapa1);
//        
//        Map<Integer,park> mapa2 = new HashMap<>();
//        park p1= new park(1, 1, 1, 1,"a","");
//        park p2 = new park(2,2,2,2,"b","");
//        mapa2.put(1, p1);
//        mapa2.put(2, p2);
//        Map<Integer,park> result2 = company.getParkRegistry().getParkMap();
//        assertEquals(result2,mapa2);
//        
//        Map<Integer,bicycle> mapa3 = new TreeMap<>();
//        road b1= new road(1,true,3);
//        mapa3.put(1, b1);
//        Map<Integer,bicycle> result3 = company.getBicycleRegistry().getBicycleMap();
//        assertEquals(result3,mapa3);
//        
//        
//    }
//
//    /**
//     * Test of SaveWhenApplicationClose method, of class DataBaseController.
//     */
////    @Test
////    public void testSaveWhenApplicationClose() {
////        System.out.println("SaveWhenApplicationClose");
////        userRegistry ur = null;
////        parkRegistry pr = null;
////        bicycleRegistry br = null;
////        DataBaseController instance = new DataBaseController();
////        instance.SaveWhenApplicationClose(ur, pr, br);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    
//}
