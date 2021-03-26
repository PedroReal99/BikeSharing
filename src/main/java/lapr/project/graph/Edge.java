package lapr.project.graph;

import java.util.Objects;
import lapr.project.model.Connection;

/**
 *
 * @author Pedro Fonseca
 */
public class Edge implements Comparable<Edge> {

    private Connection element;           // Edge information
    private double weight;       // Edge weight
    private Vertex vOrig;  // vertex origin
    private Vertex vDest;  // vertex destination

    public Edge() {
        element = null;
        weight = 0.0;
        vOrig = null;
        vDest = null;
    }

    public Edge(Connection eInf, double ew, Vertex vo, Vertex vd) {
        element = eInf;
        weight = ew;
        vOrig = vo;
        vDest = vd;
    }

    public Connection getElement() {
        return element;
    }

    public void setElement(Connection eInf) {
        element = eInf;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double ew) {
        weight = ew;
    }

    public String getVOrig() {
        if (this.vOrig != null) {
            return vOrig.getElement();
        }
        return null;
    }

    public void setVOrig(Vertex vo) {
        vOrig = vo;
    }

    public String getVDest() {
        if (this.vDest != null) {
            return vDest.getElement();
        }
        return null;
    }

    public void setVDest(Vertex vd) {
        vDest = vd;
    }

    public String[] getEndpoints() {

        String oElem = null, dElem = null;

        if (this.vOrig != null) {
            oElem = vOrig.getElement();
        }

        if (this.vDest != null) {
            dElem = vDest.getElement();
        }

        if (oElem == null && dElem == null) {
            return null;
        }

        String[] endverts;
        endverts = new String[2];

        endverts[0] = oElem;
        endverts[1] = dElem;

        return endverts;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.element);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.vOrig);
        hash = 43 * hash + Objects.hashCode(this.vDest);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (!Objects.equals(this.element, other.element)) {
            return false;
        }
        if (!Objects.equals(this.vOrig, other.vOrig)) {
            return false;
        }
        if (!Objects.equals(this.vDest, other.vDest)) {
            return false;
        }
        return true;
    }

    

    @Override
    public int compareTo(Edge other) {

        if (this.weight < other.weight) {
            return -1;
        }
        if (Double.doubleToLongBits(this.weight) == Double.doubleToLongBits(other.weight)) {
            return 0;
        }
        return 1;
    }

    @Override
    public Edge clone() {

        Edge newEdge = new Edge();

        newEdge.element = element;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null) {
            st = "      (" + element + ") - ";
        } else {
            st = "\t ";
        }

        if (Double.doubleToLongBits(weight) != 0) {
            st += weight + " - " + vDest.getElement() + "\n";
        } else {
            st += vDest.getElement() + "\n";
        }

        return st;
    }
}
