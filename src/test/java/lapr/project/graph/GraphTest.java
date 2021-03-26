package lapr.project.graph;

import java.util.Iterator;
import lapr.project.model.Connection;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author DEI-ISEP
 */
public class GraphTest {

    Graph instance = new Graph(true);
    String l = "trindade";
    String l2 = "drintade";
    String l3 = "drindate";
    String l4 = "drandite";
    String l5 = "drandeti";
    String l6 =  "trandeti";

    Connection c = new Connection(1,45, 3);
    Connection c2 = new Connection(1,70, 2);
    Connection c3 = new Connection(1,90, 3);
    Connection c4 = new Connection(1,115, 4);
    Connection c5 = new Connection(1,115, 5);
    Connection c6 = new Connection(1,155, 6);
    Connection c7 = new Connection(1,115, 7);
    Connection c8 = new Connection(1,115, 8);

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertTrue("result should be zero", (instance.numVertices() == 0));

        instance.insertVertex(l);
        assertTrue("result should be one", (instance.numVertices() == 1));

        instance.insertVertex(l2);
        assertTrue("result should be two", (instance.numVertices() == 2));

        instance.removeVertex(l);
        assertTrue("result should be one", (instance.numVertices() == 1));

        instance.removeVertex(l2);
        assertTrue("result should be zero", (instance.numVertices() == 0));
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<String> itVerts = instance.vertices().iterator();

        assertTrue("vertices should be empty", itVerts.hasNext() == false);

        instance.insertVertex(l);
        instance.insertVertex(l2);

        itVerts = instance.vertices().iterator();

        assertTrue("first vertice should be A", (itVerts.next().equals(l)));
        assertTrue("second vertice should be B", (itVerts.next().equals(l2)));

        instance.removeVertex(l);

        itVerts = instance.vertices().iterator();
        assertTrue("first vertice should now be B", (itVerts.next().equals(l2)));

        instance.removeVertex(l2);

        itVerts = instance.vertices().iterator();
        assertTrue("vertices should now be empty", itVerts.hasNext() == false);
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertTrue("result should be zero", (instance.numEdges() == 0));

        instance.insertEdge(l, l2, c, 6);
        assertTrue("result should be one", (instance.numEdges() == 1));

        instance.insertEdge(l, l3, c2, 1);
        assertTrue("result should be two", (instance.numEdges() == 2));

        instance.removeEdge(l, l2);
        assertTrue("result should be one", (instance.numEdges() == 1));

        instance.removeEdge(l, l3);
        assertTrue("result should be zero", (instance.numEdges() == 0));
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge> itEdge = instance.edges().iterator();

        assertTrue("edges should be empty", (itEdge.hasNext() == false));

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        itEdge = instance.edges().iterator();

        itEdge.next();
        itEdge.next();
        assertTrue("third edge should be B-D", itEdge.next().getElement().equals(c3) == true);

        itEdge.next();
        itEdge.next();
        assertTrue("sixth edge should be D-A", itEdge.next().getElement().equals(c6) == true);

        instance.removeEdge(l, l2);

        itEdge = instance.edges().iterator();
        assertTrue("first edge should now be A-C", itEdge.next().getElement().equals(c2) == true);

        instance.removeEdge(l, l3);
        instance.removeEdge(l2, l4);
        instance.removeEdge(l3, l4);
        instance.removeEdge(l3, l5);
        instance.removeEdge(l4, l);
        instance.removeEdge(l5, l4);
        instance.removeEdge(l5, l5);
        itEdge = instance.edges().iterator();
        assertTrue("edges should now be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        assertTrue("edge should be null", instance.getEdge(l, l5) == null);

        assertTrue("edge between B-D", instance.getEdge(l2, l4).getElement().equals(c3) == true);
        assertTrue("edge should be null", instance.getEdge(l4, l2) == null);

        instance.removeEdge(l4, l);
        assertTrue("edge should be null", instance.getEdge(l4, l) == null);

        assertTrue("edge should be edge8", instance.getEdge(l5, l5).getElement().equals(c8) == true);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        Edge edge0 = new Edge();

        //assertTrue("endVertices should be null", instance.endVertices(edge0)==null);
        Edge edge1 = instance.getEdge(l, l2);
        //vertices = instance.endVertices(edge1);
        assertTrue("first vertex should be A", instance.endVertices(edge1)[0].equals(l));
        assertTrue("second vertex should be B", instance.endVertices(edge1)[1].equals(l2));
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        Edge edge5 = instance.getEdge(l3, l5);
        String vert = instance.opposite(l, edge5);
        assertTrue("opposite should be null", vert == null);

        Edge edge1 = instance.getEdge(l, l2);
        vert = instance.opposite(l, edge1);
        assertTrue("opposite should be B", vert.equals(l2) == true);

        Edge edge8 = instance.getEdge(l5, l5);
        vert = instance.opposite(l5, edge8);
        assertTrue("opposite should be E", vert.equals(l5) == true);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        int outdeg = instance.outDegree(l6);
        assertTrue("degree should be -1", outdeg == -1);

        outdeg = instance.outDegree(l);
        assertTrue("degree should be 2", outdeg == 2);

        outdeg = instance.outDegree(l2);
        assertTrue("degree should be 1", outdeg == 1);

        outdeg = instance.outDegree(l5);
        assertTrue("degree should be 2", outdeg == 2);
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        int indeg = instance.inDegree(l6);
        assertTrue("in degree should be -1", indeg == -1);

        indeg = instance.inDegree(l);
        assertTrue("in degree should be 1", indeg == 1);

        indeg = instance.inDegree(l4);
        assertTrue("in degree should be 3", indeg == 3);

        indeg = instance.inDegree(l5);
        assertTrue("in degree should be 2", indeg == 2);
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        Iterator<Edge> itEdge = instance.outgoingEdges(l3).iterator();
        Edge first = itEdge.next();
        Edge second = itEdge.next();
        assertTrue("Outgoing Edges of vert C should be Edge4 and Edge5",
                ((first.getElement().equals(c4) == true && second.getElement().equals(c5) == true)
                || (first.getElement().equals(c5) == true && second.getElement().equals(c5) == true)));

        instance.removeEdge(l5, l5);

        itEdge = instance.outgoingEdges(l5).iterator();
        assertTrue("first edge should be Edge7", (itEdge.next().getElement().equals(c7) == true));

        instance.removeEdge(l5, l4);

        itEdge = instance.outgoingEdges(l5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        Iterator<Edge> itEdge = instance.incomingEdges(l4).iterator();

        assertTrue("first edge should be edge3", (itEdge.next().getElement().equals(c3) == true));
        assertTrue("second edge should be edge4", (itEdge.next().getElement().equals(c4) == true));
        assertTrue("third edge should be edge7", (itEdge.next().getElement().equals(c7) == true));

        itEdge = instance.incomingEdges(l5).iterator();

        assertTrue("first edge should be Edge5", (itEdge.next().getElement().equals(c5) == true));
        assertTrue("second edge should be Edge8", (itEdge.next().getElement().equals(c8) == true));

        instance.removeEdge(l5, l5);

        itEdge = instance.incomingEdges(l5).iterator();

        assertTrue("first edge should be Edge5", (itEdge.next().getElement().equals(c5) == true));

        instance.removeEdge(l3, l5);

        itEdge = instance.incomingEdges(l5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        Iterator<String> itVert = instance.vertices().iterator();

        assertTrue("first vertex should be A", (itVert.next().equals(l) == true));
        assertTrue("second vertex should be B", (itVert.next().equals(l2) == true));
        assertTrue("third vertex should be C", (itVert.next().equals(l3) == true));
        assertTrue("fourth vertex should be D", (itVert.next().equals(l4) == true));
        assertTrue("fifth vertex should be E", (itVert.next().equals(l5) == true));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");

        assertTrue("num. edges should be zero", (instance.numEdges() == 0));

        instance.insertEdge(l, l2, c, 6);
        assertTrue("num. edges should be 1", (instance.numEdges() == 1));

        instance.insertEdge(l, l3, c2, 1);
        assertTrue("num. edges should be 2", (instance.numEdges() == 2));

        instance.insertEdge(l2, l4, c3, 3);
        assertTrue("num. edges should be 3", (instance.numEdges() == 3));

        instance.insertEdge(l3, l4, c4, 4);
        assertTrue("num. edges should be 4", (instance.numEdges() == 4));

        instance.insertEdge(l3, l5, c5, 1);
        assertTrue("num. edges should be 5", (instance.numEdges() == 5));

        instance.insertEdge(l4, l, c6, 2);
        assertTrue("num. edges should be 6", (instance.numEdges() == 6));

        instance.insertEdge(l5, l4, c7, 1);
        assertTrue("num. edges should be 7", (instance.numEdges() == 7));

        instance.insertEdge(l5, l5, c8, 1);
        assertTrue("num. edges should be 8", (instance.numEdges() == 8));

        Iterator<Edge> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("third edge should be Edge3", (itEd.next().getElement().equals(c3) == true));
        itEd.next();
        itEd.next();
        assertTrue("sixth edge should be Edge6", (itEd.next().getElement().equals(c6) == true));
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.removeVertex(l3);
        assertTrue("Num vertices should be 4", (instance.numVertices() == 4));

        Iterator<String> itVert = instance.vertices().iterator();
        assertTrue("first vertex should be A", (itVert.next().equals(l) == true));
        assertTrue("second vertex should be B", (itVert.next().equals(l2) == true));
        assertTrue("third vertex should be D", (itVert.next().equals(l4) == true));
        assertTrue("fourth vertex should be E", (itVert.next().equals(l5) == true));

        instance.removeVertex(l);
        assertTrue("Num vertices should be 3", (instance.numVertices() == 3));

        itVert = instance.vertices().iterator();
        assertTrue("first vertex should be B", (itVert.next().equals(l2) == true));
        assertTrue("second vertex should be D", (itVert.next().equals(l4) == true));
        assertTrue("third vertex should be E", (itVert.next().equals(l5) == true));

        instance.removeVertex(l5);
        assertTrue("Num vertices should be 2", (instance.numVertices() == 2));

        itVert = instance.vertices().iterator();

        assertTrue("first vertex should be B", itVert.next().equals(l2) == true);
        assertTrue("second vertex should be D", itVert.next().equals(l4) == true);

        instance.removeVertex(l2);
        instance.removeVertex(l4);
        assertTrue("Num vertices should be 4", (instance.numVertices() == 0));
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("Test removeEdge");

        assertTrue("num. edges should be zero", (instance.numEdges() == 0));

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        assertTrue("Num. edges should be 8", (instance.numEdges() == 8));

        instance.removeEdge(l5, l5);
        assertTrue("Num. edges should be 7", (instance.numEdges() == 7));

        Iterator<Edge> itEd = instance.edges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("third edge should be Edge3", (itEd.next().getElement().equals(c3) == true));
        itEd.next();
        itEd.next();
        assertTrue("sixth edge should be Edge6", (itEd.next().getElement().equals(c6) == true));

        instance.removeEdge(l3, l4);
        assertTrue("Num. edges should be 6", (instance.numEdges() == 6));

        itEd = instance.edges().iterator();
        itEd.next();
        itEd.next();
        assertTrue("third edge should be Edge3", (itEd.next().getElement().equals(c3) == true));
        assertTrue("fourth edge should be Edge5", (itEd.next().getElement().equals(c5) == true));
        assertTrue("fifth edge should be Edge6", (itEd.next().getElement().equals(c6) == true));
        assertTrue("...last edge should be Edge7", (itEd.next().getElement().equals(c7) == true));
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        instance.insertVertex(l);
        instance.insertVertex(l2);
        instance.insertVertex(l3);
        instance.insertVertex(l4);
        instance.insertVertex(l5);

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        Graph instClone = instance.clone();

        assertTrue("number of vertices should be equal", instance.numVertices() == instClone.numVertices());
        assertTrue("number of edges should be equal", instance.numEdges() == instClone.numEdges());

        //vertices should be equal
        Iterator<String> itvertClone = instClone.vertices().iterator();
        Iterator<String> itvertSource = instance.vertices().iterator();
        while (itvertSource.hasNext()) {
            assertTrue("vertices should be equal ", (itvertSource.next().equals(itvertClone.next()) == true));
        }
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");

        instance.insertEdge(l, l2, c, 6);
        instance.insertEdge(l, l3, c2, 1);
        instance.insertEdge(l2, l4, c3, 3);
        instance.insertEdge(l3, l4, c4, 4);
        instance.insertEdge(l3, l5, c5, 1);
        instance.insertEdge(l4, l, c6, 2);
        instance.insertEdge(l5, l4, c7, 1);
        instance.insertEdge(l5, l5, c8, 1);

        assertFalse("should not be equal to null", instance.equals(null));

        assertTrue("should be equal to itself", instance.equals(instance));

        assertTrue("should be equal to a clone", instance.equals(instance.clone()));

        Graph other = instance.clone();

        other.removeEdge(l5, l5);
        assertFalse("instance should not be equal to other", instance.equals(other));

        other.insertEdge(l5, l5, c8, 1);
        assertTrue("instance should be equal to other", instance.equals(other));

        other.removeVertex(l4);
        assertFalse("instance should not be equal to other", instance.equals(other));

    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {

        System.out.println(instance);
    }

}
