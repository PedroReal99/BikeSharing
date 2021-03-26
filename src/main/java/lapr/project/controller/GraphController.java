/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Map;
import lapr.project.graph.Edge;
import lapr.project.graph.Vertex;
import lapr.project.model.Connection;
import lapr.project.model.Location;
import lapr.project.model.Company;

/**
 *
 * @author User
 */
public class GraphController {

    public static void addWindSupportInformation(String parkId, String parkId2, int windDirection, double windSpeed, int id) {
        Company.getParkRegistry().getGraph().getEdge(parkId, parkId2).setElement(new Connection(id, windDirection, windSpeed));
    }

    public static void loadVertex(String vertex) {
        Company.getParkRegistry().getGraph().insertVertex(vertex);
    }

    public static Edge getEdge(String l, String l2) {
        return Company.getParkRegistry().getGraph().getEdge(l, l2);
    }

    public static Vertex getVertexById(String l) {
        return Company.getParkRegistry().getGraph().getVertices().get(l);
    }

    public static void addConnection(String vOrig, String vDest, Connection c, double distance) {
        Company.getParkRegistry().getGraph().insertEdge(vOrig, vDest, c, distance);
    }
}
