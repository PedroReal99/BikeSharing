package lapr.project.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lapr.project.model.Connection;

/**
 *
 * @author Pedro Fonseca
 */
public class Graph {

    private int numVert;
    private int numEdge;
    private boolean isDirected;
    private Map<String, Vertex> vertices;  //all Vertices of the graph 

    // Constructs an empty graph (either undirected or directed)
    public Graph(boolean directed) {
        numVert = 0;
        numEdge = 0;
        isDirected = directed;
        vertices = new LinkedHashMap<>();
    }

    public int numVertices() {
        return numVert;
    }

    public Iterable<String> vertices() {
        return vertices.keySet();
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public boolean validVertex(String vert) {
        if (vertices.get(vert) == null) {
            return false;
        }

        return true;
    }

    public int getKey(String vert) {
        return vertices.get(vert).getKey();
    }

    public String[] allkeyVerts() {

        String vertElem = new String();
        for (Vertex vert : vertices.values()) {
            vertElem = vert.getElement();            // To get type
        }
        String[] keyverts = (String[]) Array.newInstance(vertElem.getClass(), numVert);

        for (Vertex vert : vertices.values()) {
            keyverts[vert.getKey()] = vert.getElement();
        }

        return keyverts;
    }

    public Iterable<String> adjVertices(String vert) {

        if (!validVertex(vert)) {
            return null;
        }

        Vertex vertex = vertices.get(vert);

        return vertex.getAllAdjVerts();
    }

    public int numEdges() {
        return numEdge;
    }

    public Iterable<Edge> edges() {

        List<Edge> listEdges = new ArrayList<>();
        Iterator<Vertex> it = this.vertices.values().iterator();
        while (it.hasNext()) {
            Vertex v = it.next();
            Iterator<String> it2 = v.getAllAdjVerts().iterator();
            while (it2.hasNext()) {
                listEdges.add(v.getEdge(it2.next()));
            }
        }
        return listEdges;
    }

    public Edge getEdge(String vOrig, String vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return null;
        }

        Vertex vorig = vertices.get(vOrig);

        return vorig.getEdge(vDest);
    }

    public String[] endVertices(Edge edge) {

        if (edge == null) {
            return new String[1];
        }

        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest())) {
            return new String[1];
        }

        Vertex vorig = vertices.get(edge.getVOrig());

        if (!edge.equals(vorig.getEdge(edge.getVDest()))) {
            return new String[1];
        }

        return edge.getEndpoints();
    }

    public String opposite(String vert, Edge edge) {

        if (!validVertex(vert)) {
            return null;
        }

        Vertex vertex = vertices.get(vert);

        return vertex.getAdjVert(edge);
    }

    public int outDegree(String vert) {

        if (!validVertex(vert)) {
            return -1;
        }

        Vertex vertex = vertices.get(vert);

        return vertex.numAdjVerts();
    }

    public int inDegree(String vert) {

        if (!validVertex(vert)) {
            return -1;
        }

        int degree = 0;
        for (String otherVert : vertices.keySet()) {
            if (getEdge(otherVert, vert) != null) {
                degree++;
            }
        }

        return degree;
    }

    public Iterable<Edge> outgoingEdges(String vert) {

        if (!validVertex(vert)) {
            return null;
        }

        Vertex vertex = vertices.get(vert);

        return vertex.getAllOutEdges();
    }

    public Iterable<Edge> incomingEdges(String vert) {

        List<Edge> list = new ArrayList<>();
        if (!validVertex(vert)) {
            return null;
        }
        Iterator<Vertex> it = vertices.values().iterator();
        while (it.hasNext()) {
            Vertex v = it.next();
            Edge edge = v.getEdge(vert);
            if (edge != null) {
                list.add(edge);
            }
        }
        return list;
    }

    public boolean insertVertex(String vert) {

        if (validVertex(vert)) {
            return false;
        }

        Vertex vertex = new Vertex(numVert, vert);
        vertices.put(vert, vertex);
        numVert++;

        return true;
    }

    public boolean insertEdge(String vOrig, String vDest, Connection eInf, double eWeight) {

        if (getEdge(vOrig, vDest) != null && eInf.equals(getEdge(vOrig, vDest).getElement())) {
            return false;
        }

        if (!validVertex(vOrig)) {
            insertVertex(vOrig);
        }

        if (!validVertex(vDest)) {
            insertVertex(vDest);
        }

        Vertex vorig = vertices.get(vOrig);
        Vertex vdest = vertices.get(vDest);

        Edge newEdge = new Edge(eInf, eWeight, vorig, vdest);
        vorig.addAdjVert(vDest, newEdge);
        numEdge++;

        //if graph is not direct insert other edge in the opposite direction 
        if ((!isDirected) && (getEdge(vDest, vOrig) == null)) // if vDest different vOrig
        {
            Edge otherEdge = new Edge(eInf, eWeight, vdest, vorig);
            vdest.addAdjVert(vOrig, otherEdge);
            numEdge++;
        }

        return true;
    }

    public boolean removeVertex(String vert) {

        if (!validVertex(vert)) {
            return false;
        }

        //remove all edges that point to vert
        for (Edge edge : incomingEdges(vert)) {
            String vadj = edge.getVOrig();
            removeEdge(vadj, vert);
        }

        Vertex vertex = vertices.get(vert);

        //update the keys of subsequent vertices in the map
        for (Vertex v : vertices.values()) {
            int keyVert = v.getKey();
            if (keyVert > vertex.getKey()) {
                keyVert = keyVert - 1;
                v.setKey(keyVert);
            }
        }
        //The edges that live from vert are removed with the vertex    
        vertices.remove(vert);

        numVert--;

        return true;
    }

    public boolean removeEdge(String vOrig, String vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return false;
        }

        Edge edge = getEdge(vOrig, vDest);

        if (edge == null) {
            return false;
        }

        Vertex vorig = vertices.get(vOrig);

        vorig.remAdjVert(vDest);
        numEdge--;

        //if graph is not direct 
        if (!isDirected) {
            edge = getEdge(vDest, vOrig);
            if (edge != null) {
                Vertex vdest = vertices.get(vDest);
                vdest.remAdjVert(vOrig);
                numEdge--;
            }
        }
        return true;
    }

    //Returns a clone of the graph 
    @Override
    public Graph clone() {

        Graph newObject = new Graph(this.isDirected);

        //insert all vertices
        for (String vert : vertices.keySet()) {
            newObject.insertVertex(vert);
        }

        //insert all edges
        for (String vert1 : vertices.keySet()) {
            for (Edge e : this.outgoingEdges(vert1)) {
                if (e != null) {
                    String vert2 = this.opposite(vert1, e);
                    newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }
            }
        }

        return newObject;
    }

    /* equals implementation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph*/
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        Graph otherGraph = (Graph) otherObj;

        if (numVert != otherGraph.numVertices() || numEdge != otherGraph.numEdges()) {
            return false;
        }

        //graph must have same vertices
        boolean eqvertex;
        for (String v1 : this.vertices()) {
            eqvertex = false;
            for (String v2 : otherGraph.vertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }
        return true;
    }

    //string representation
    @Override
    public String toString() {
        String s = "";
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex vert : vertices.values()) {
                s += vert + "\n";
            }
        }
        return s;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.numVert;
        hash = 59 * hash + this.numEdge;
        hash = 59 * hash + Objects.hashCode(this.vertices);
        return hash;
    }

}
