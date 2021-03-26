/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.graph.Edge;
import lapr.project.model.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import lapr.project.graph.Vertex;



/**
 *
 * @author User
 */
public class GraphControllerTest {
    
    @Test
    public void testAddWindSupportInformation() {
        System.out.println("addWindSupportInformation");
        String parkId = "abc";
        String parkId2 = "123";
        int windDirection = 10;
        int windSpeed = 20;
        int id = 1;
        GraphController.loadVertex(parkId);
        GraphController.loadVertex(parkId2);
        Connection c = new Connection (id, windDirection, windSpeed);
        GraphController.addConnection(parkId, parkId2, new Connection(), id);
        GraphController.addWindSupportInformation(parkId, parkId2, c.getWindDirection(), c.getWindSpeed(), id);
        Edge e = GraphController.getEdge(parkId, parkId2);
        assertEquals(c.getWindDirection(),e.getElement().getWindDirection());
        assertEquals(c.getWindSpeed(),e.getElement().getWindSpeed());
    }

    /**
     * Test of loadVertex method, of class GraphController.
     */
    @Test
    public void testLoadVertex() {
        System.out.println("loadVertex");
        String vertex = "1234";
        GraphController.loadVertex(vertex);
        Vertex exp = GraphController.getVertexById(vertex);
        assertEquals(vertex, exp.getElement());
    }

    /**
     * Test of getEdge method, of class GraphController.
     */
    @Test
    public void testGetEdge() {
       String parkId = "abc";
        String parkId2 = "123";
        double w = 3;
        GraphController.loadVertex(parkId);
        GraphController.loadVertex(parkId2);
        GraphController.addConnection(parkId, parkId2, new Connection(), w);
        assertEquals(GraphController.getEdge(parkId, parkId2).getWeight(),w);
    }

    /**
     * Test of getVertexById method, of class GraphController.
     */
    @Test
    public void testGetVertexById() {
         System.out.println("getVertexById");
        String vertex = "12";
        GraphController.loadVertex(vertex);
        Vertex exp = GraphController.getVertexById(vertex);
        assertEquals(vertex, exp.getElement());
    }

    /**
     * Test of addConnection method, of class GraphController.
     */
    @Test
    public void testAddConnection() {
        System.out.println("addConnection");
        String parkId = "acb";
        String parkId2 = "132";
        double w = 3;
        GraphController.loadVertex(parkId);
        GraphController.loadVertex(parkId2);
        GraphController.addConnection(parkId, parkId2, new Connection(), w);
        assertEquals(GraphController.getEdge(parkId, parkId2).getWeight(),w);
    }
    
}
