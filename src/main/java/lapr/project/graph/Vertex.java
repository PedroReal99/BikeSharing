package lapr.project.graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Pedro Fonseca
 */
public class Vertex {

    private int key;                     //Vertex key number
    private String element;                 //Vertex information
    private Map<String, Edge> outVerts; //adjacent vertices

    public Vertex() {
        key = -1;
        element = null;
        outVerts = new LinkedHashMap<>();
    }

    public Vertex(int k, String vInf) {
        key = k;
        element = vInf;
        outVerts = new LinkedHashMap<>();
    }

    public Vertex(Vertex v) {
        key = v.getKey();
        element = v.getElement();
        outVerts = new LinkedHashMap<>();
        for (String vert : v.outVerts.keySet()) {
            Edge edge = v.outVerts.get(vert);
            outVerts.put(vert, edge);
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int k) {
        key = k;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String vInf) {
        element = vInf;
    }

    public void addAdjVert(String vAdj, Edge edge) {
        outVerts.put(vAdj, edge);
    }

    public String getAdjVert(Edge edge) {

        for (String vert : outVerts.keySet()) {
            if (edge.equals(outVerts.get(vert))) {
                return vert;
            }
        }

        return null;
    }

    public void remAdjVert(String vAdj) {
        outVerts.remove(vAdj);
    }

    public Edge getEdge(String vAdj) {
        return outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return outVerts.size();
    }

    public Iterable<String> getAllAdjVerts() {
        return outVerts.keySet();
    }

    public Iterable<Edge> getAllOutEdges() {
        return outVerts.values();
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        Vertex otherVertex = (Vertex) otherObj;

        if (this.key != otherVertex.key) {
            return false;
        }

        if (this.element != null && otherVertex.element != null
                && !this.element.equals(otherVertex.element)) {
            return false;
        }

        //adjacency vertices should be equal
        if (this.numAdjVerts() != otherVertex.numAdjVerts()) {
            return false;
        }

        //and edges also
        Iterator<Edge> it1 = this.getAllOutEdges().iterator();
        while (it1.hasNext()) {
            Iterator<Edge> it2 = otherVertex.getAllOutEdges().iterator();
            boolean exists = false;
            while (it2.hasNext()) {
                if (it1.next().equals(it2.next())) {
                    exists = true;
                }
            }
            if (!exists) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Vertex clone() {

        Vertex newVertex = new Vertex();

        newVertex.setKey(key);
        newVertex.setElement(element);

        for (String vert : outVerts.keySet()) {
            newVertex.addAdjVert(vert, this.getEdge(vert));
        }

        return newVertex;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null) {
            st = element + " (" + key + "): \n";
        }
        if (!outVerts.isEmpty()) {
            for (String vert : outVerts.keySet()) {
                st += outVerts.get(vert);
            }
        }

        return st;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.key;
        hash = 71 * hash + Objects.hashCode(this.element);
        return hash;
    }
 
}
