/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.graph;

import lapr.project.model.Connection;
import lapr.project.model.Park;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pedro
 */
public class EdgeTest {

    Edge instance = new Edge();
    String l = "trindade";
    String l2 = "drintade";
    String l3 = "drindate";
    Connection c = new Connection(1,45, 3);
    Connection c2 = new Connection(1,70, 4);
    Connection c3 = new Connection(1,90, 5);

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");

        Connection expResult = null;
        assertEquals(expResult, instance.getElement());

        Edge instance1 = new Edge(new Connection(1,45, 3), 3, null, null);
        expResult = new Connection(1,45, 3);
        assertEquals(expResult, instance1.getElement());
    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");

        instance.setElement(c);

        assertEquals(c, instance.getElement());
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        instance.setWeight(15);
        double expResult = 15;
        assertEquals(expResult, instance.getWeight());
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double ew = 2.0;
        instance.setWeight(ew);

        double expResult = 2.0;
        assertEquals(expResult, instance.getWeight(), 2.0);
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig() {
        System.out.println("getVOrig");

        Object expResult = null;
        assertEquals(expResult, instance.getVOrig());

        Vertex vertex1 = new Vertex(1, l);
        Vertex vertex2 = new Vertex(1, l2);
        Edge otherEdge = new Edge(c, 1.0, vertex1, vertex2);
        assertEquals(vertex1.getElement(), otherEdge.getVOrig());
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig() {
        System.out.println("setVOrig");

        Vertex vertex1 = new Vertex(1, l);
        instance.setVOrig(vertex1);
        assertEquals(vertex1.getElement(), instance.getVOrig());
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest() {
        System.out.println("getVDest");

        Object expResult = null;
        assertEquals(expResult, instance.getVDest());

        Vertex vertex1 = new Vertex(1, l);
        Edge otherEdge = new Edge(c, 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVDest());
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest() {
        System.out.println("setVDest");

        Vertex vertex1 = new Vertex(1, l);
        instance.setVDest(vertex1);
        assertEquals(vertex1.getElement(), instance.getVDest());
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");

        String[] expResult = null;
        String[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);

        Vertex vertex1 = new Vertex(1, l);
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String[] expResult1 = {l, l};
        assertArrayEquals(expResult1, instance.getEndpoints());
    }

    /**
     * Test of hashCode method, of class Edge.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Edge instance = new Edge();
        int expResult = 23931607;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean result = instance.equals(obj);
        assertTrue(!result);

        Edge e1 = new Edge(c, 0, new Vertex(), new Vertex());
        Edge e2 = new Edge(c2, 0, new Vertex(), new Vertex());
        boolean result2 = e1.equals(e2);
        assertTrue(!result2);

        Edge e3 = new Edge(c, 12, new Vertex(), new Vertex());
        boolean result3 = e1.equals(e3);
        assertTrue(!result3);

        Edge e4 = new Edge(c, 0, new Vertex(0, l), new Vertex());
        boolean result4 = e1.equals(e4);
        assertTrue(!result4);

        Edge e5 = new Edge(c, 0, new Vertex(), new Vertex(0, l));
        boolean result5 = e1.equals(e5);
        assertTrue(!result5);

        Park b = new Park();
        boolean result6 = e1.equals(b);
        assertTrue(!result6);
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Edge e1 = new Edge(c, 3, new Vertex(), new Vertex());
        Edge e2 = new Edge(c2, 4, new Vertex(), new Vertex());
        int expResult = -1;
        int result = e1.compareTo(e2);
        assertEquals(expResult, result);

        Edge e3 = new Edge(c2, 1, new Vertex(), new Vertex());
        int expResult2 = 1;
        int result2 = e1.compareTo(e3);
        assertEquals(expResult2, result2);

        Edge e4 = new Edge(c2, 3, new Vertex(), new Vertex());
        int expResult3 = 0;
        int result3 = e1.compareTo(e4);
        assertEquals(expResult3, result3);

    }

    /**
     * Test of clone method, of class Edge.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Edge e1 = new Edge(c, 3, new Vertex(), new Vertex());
        Edge expResult = new Edge(c, 3, new Vertex(), new Vertex());
        Edge result = e1.clone();
        assertEquals(expResult, result);

    }

}
