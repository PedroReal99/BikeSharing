package lapr.project.graph;

import java.util.Iterator;
import java.util.LinkedList;
import lapr.project.model.Company;
import lapr.project.model.Connection;
import lapr.project.model.Location;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author DEI-ISEP
 */
public class GraphAlgorithmsTest {

    static String porto = "Porto";
    static String braga = "Braga";
    static String vila = "Vila";
    static String aveiro = "Aveiro";
    static String coimbra = "Coimbra";
    static String leiria = "Leiria";
    static String viseu = "Viseu";
    static String guarda = "Guarda";
    static String castelo = "Castelo";
    static String lisboa = "Lisboa";
    static String faro = "Faro";
    static String l = "Trindade";

    static Location portoL = new Location("1.0, 2.0", "Porto");
    static Location bragaL = new Location("3.0, 4.0", "Braga");
    static Location vilaL = new Location("5.0, 6.0", "Vila");
    static Location aveiroL = new Location("7.0, 8.0", "Aveiro");
    static Location coimbraL = new Location("9.0, 10.0", "Coimbra");
    static Location leiriaL = new Location("11.0, 12.0", "Leiria");
    static Location viseuL = new Location("13.0, 14.0", "Viseu");
    static Location guardaL = new Location("15.0, 16.0", "Guarda");
    static Location casteloL = new Location("17.0, 18.0", "Castelo");
    static Location lisboaL = new Location("19.0, 20.0", "Lisboa");
    static Location faroL = new Location("21.0, 22.0", "Faro");
    static Location lL = new Location("23.0, 24.0", "trindade");

    static Graph completeMap = new Graph(false);
    static Graph incompleteMap = new Graph(false);

    static Connection c = new Connection(1, 45, 1);
    static Connection c2 = new Connection(2, 70, 2);
    static Connection c3 = new Connection(3, 90, 3);
    static Connection c4 = new Connection(4, 115, 4);
    static Connection c5 = new Connection(5, 115, 5);
    static Connection c6 = new Connection(6, 115, 6);
    static Connection c7 = new Connection(7, 115, 7);
    static Connection c8 = new Connection(8, 115, 8);
    static Connection c9 = new Connection(12, 15, 9);
    static Connection c10 = new Connection(11, 5, 10);
    static Connection c11 = new Connection(9, 115, 11);
    static Connection c12 = new Connection(10, 115, 12);
    static Connection c13 = new Connection(13, 115, 13);
    static Connection c14 = new Connection(14, 30, 14);

    /**
     * Test of shortestPathEstacoes method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPathEstacoes() {
        completeMap = new Graph(false);
        incompleteMap = new Graph(false);

        completeMap.insertVertex(porto);
        Company.getParkRegistry().getParkMap().put(porto, portoL);
        completeMap.insertVertex(braga);
        Company.getParkRegistry().getParkMap().put(braga, bragaL);
        completeMap.insertVertex(vila);
        Company.getParkRegistry().getParkMap().put(vila, vilaL);
        completeMap.insertVertex(aveiro);
        Company.getParkRegistry().getParkMap().put(aveiro, aveiroL);
        completeMap.insertVertex(coimbra);
        Company.getParkRegistry().getParkMap().put(coimbra, coimbraL);
        completeMap.insertVertex(leiria);
        Company.getParkRegistry().getParkMap().put(leiria, leiriaL);

        completeMap.insertVertex(viseu);
        Company.getParkRegistry().getParkMap().put(viseu, viseuL);
        completeMap.insertVertex(guarda);
        Company.getParkRegistry().getParkMap().put(guarda, guardaL);
        completeMap.insertVertex(castelo);
        Company.getParkRegistry().getParkMap().put(castelo, casteloL);
        completeMap.insertVertex(lisboa);
        Company.getParkRegistry().getParkMap().put(lisboa, lisboaL);
        completeMap.insertVertex(faro);
        Company.getParkRegistry().getParkMap().put(faro, faroL);

        completeMap.insertEdge(porto, aveiro, c, 75);
        completeMap.insertEdge(porto, braga, c2, 60);
        completeMap.insertEdge(porto, vila, c3, 100);
        completeMap.insertEdge(viseu, guarda, c4, 75);
        completeMap.insertEdge(guarda, castelo, c5, 100);
        completeMap.insertEdge(aveiro, coimbra, c6, 60);
        completeMap.insertEdge(coimbra, lisboa, c7, 200);
        completeMap.insertEdge(coimbra, leiria, c8, 80);
        completeMap.insertEdge(aveiro, leiria, c9, 120);
        completeMap.insertEdge(leiria, lisboa, c10, 150);

        completeMap.insertEdge(aveiro, viseu, c11, 85);
        completeMap.insertEdge(leiria, castelo, c12, 170);
        completeMap.insertEdge(lisboa, faro, c13, 280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge(aveiro, viseu);
        incompleteMap.removeEdge(leiria, castelo);
        incompleteMap.removeEdge(lisboa, faro);

        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<>();
        double lenpath = 0;

        Company.getParkRegistry().setGraph(completeMap);
        lenpath = GraphAlgorithms.shortestPathEnergySpent(completeMap, porto, l, shortPath);
        assertTrue("Length path should be 0 if vertex does not exist", lenpath == 0);

        Company.getParkRegistry().setGraph(incompleteMap);
        lenpath = GraphAlgorithms.shortestPathEnergySpent(incompleteMap, porto, faro, shortPath);
        assertTrue("Length path should be 0 if there is no path", lenpath == 0);

        Company.getParkRegistry().setGraph(completeMap);
        lenpath = GraphAlgorithms.shortestPath(completeMap, porto, porto, shortPath);
        assertTrue("Number of nodes should be 1 if source and vertex are the same", lenpath == 0);

        Company.getParkRegistry().setGraph(incompleteMap);
        lenpath = GraphAlgorithms.shortestPath(incompleteMap, porto, lisboa, shortPath);
        assertTrue("Path between Porto and Lisboa should be 335 Km", lenpath == 335);

        Iterator<String> it = shortPath.iterator();
        assertTrue("First in path should be Porto", it.next().equals(porto));
        assertTrue("then Aveiro", it.next().equals(aveiro));
        assertTrue("then Coimbra", it.next().equals(coimbra));
        assertTrue("then Lisboa", it.next().equals(lisboa));
        completeMap.insertEdge(porto, lisboa, c, 10);

        Company.getParkRegistry().setGraph(incompleteMap);
        lenpath = GraphAlgorithms.shortestPathEnergySpent(incompleteMap, braga, leiria, shortPath);
        assertEquals("Path between Braga and Leiria should be close to 152.89", lenpath, 152, 1);

        it = shortPath.iterator();

        assertTrue("First in path should be Braga", it.next().equals(braga));
        assertTrue("then Porto", it.next().equals(porto));
        assertTrue("then Aveiro", it.next().equals(aveiro));
        assertTrue("then Coimbra", it.next().equals(coimbra));
        assertTrue("then Leiria", it.next().equals(leiria));

        shortPath.clear();
        Company.getParkRegistry().setGraph(completeMap);
        lenpath = GraphAlgorithms.shortestPathEnergySpent(completeMap, porto, castelo, shortPath);
        assertEquals("Path between Porto and Castelo Branco should be close to 202.86", lenpath, 202, 1);
        assertTrue("N. cities between Porto and Castelo Branco should be 5 ", shortPath.size() == 5);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().equals(porto));
        assertTrue("then Aveiro", it.next().equals(aveiro));
        assertTrue("then Viseu", it.next().equals(viseu));
        assertTrue("then Viseu", it.next().equals(guarda));
        assertTrue("then Castelo Branco", it.next().equals(castelo));

        //Changing Edge: aveiro-viseu with Edge: leiria-C.Branco 
        //should change shortest path between porto and castelo Branco
        completeMap.removeEdge(aveiro, viseu);
        completeMap.insertEdge(leiria, castelo, c12, 170);
        shortPath.clear();

        Company.getParkRegistry().setGraph(completeMap);
        lenpath = GraphAlgorithms.shortestPath(completeMap, porto, castelo, shortPath);
        assertTrue("Path between Porto and Castelo Branco should now be 330 Km", lenpath == 330);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", shortPath.size() == 4);

    }

    @Test
    public void testShortestInterestPoint() {
        completeMap = new Graph(false);

        completeMap.insertVertex(porto);
        Company.getParkRegistry().getParkMap().put(porto, portoL);
        completeMap.insertVertex(braga);
        Company.getParkRegistry().getParkMap().put(braga, bragaL);
        completeMap.insertVertex(vila);
        Company.getParkRegistry().getParkMap().put(vila, vilaL);
        completeMap.insertVertex(aveiro);
        Company.getParkRegistry().getParkMap().put(aveiro, aveiroL);
        completeMap.insertVertex(coimbra);
        Company.getParkRegistry().getParkMap().put(coimbra, coimbraL);
        completeMap.insertVertex(leiria);
        Company.getParkRegistry().getParkMap().put(leiria, leiriaL);

        completeMap.insertVertex(viseu);
        Company.getParkRegistry().getParkMap().put(viseu, viseuL);
        completeMap.insertVertex(guarda);
        Company.getParkRegistry().getParkMap().put(guarda, guardaL);
        completeMap.insertVertex(castelo);
        Company.getParkRegistry().getParkMap().put(castelo, casteloL);
        completeMap.insertVertex(lisboa);
        Company.getParkRegistry().getParkMap().put(lisboa, lisboaL);
        completeMap.insertVertex(faro);
        Company.getParkRegistry().getParkMap().put(faro, faroL);

        completeMap.insertEdge(porto, aveiro, c, 75);
        completeMap.insertEdge(porto, braga, c2, 60);
        completeMap.insertEdge(porto, vila, c3, 100);
        completeMap.insertEdge(viseu, guarda, c4, 75);
        completeMap.insertEdge(guarda, castelo, c5, 100);
        completeMap.insertEdge(aveiro, coimbra, c6, 60);
        completeMap.insertEdge(coimbra, lisboa, c7, 200);
        completeMap.insertEdge(coimbra, leiria, c8, 80);
        completeMap.insertEdge(aveiro, leiria, c9, 120);
        completeMap.insertEdge(leiria, lisboa, c10, 150);

        completeMap.insertEdge(aveiro, viseu, c11, 85);
        completeMap.insertEdge(leiria, castelo, c12, 170);
        completeMap.insertEdge(lisboa, faro, c13, 280);

        System.out.println("Test of shortest path by interest point");

        completeMap.insertEdge(porto, lisboa, c, 10);
        LinkedList<LinkedList<String>> paths = GraphAlgorithms.ShortestInterestPoint(completeMap, porto, lisboa, 5);
        LinkedList<String> l1 = new LinkedList<>();
        LinkedList<String> l2 = new LinkedList<>();
        LinkedList<LinkedList<String>> exp = new LinkedList<>();
        l1.add(porto);
        l1.add(aveiro);
        l1.add(coimbra);
        l1.add(leiria);
        l1.add(lisboa);
        l2.add(porto);
        l2.add(aveiro);
        l2.add(viseu);
        l2.add(guarda);
        l2.add(castelo);
        l2.add(leiria);
        l2.add(lisboa);
        exp.add(l1);
        exp.add(l2);
        Iterator<LinkedList<String>> it = paths.iterator();
        int i = 1;
        while (it.hasNext()) {
            Iterator<String> it2 = it.next().iterator();
            LinkedList<String> aux = exp.pop();
            while (it2.hasNext()) {
                assertTrue(it2.next().equals(aux.pop()));
            }
        }

        paths = GraphAlgorithms.ShortestInterestPoint(completeMap, viseu, vila, 5);
        l1.clear();
        l2.clear();
        LinkedList<String> l3 = new LinkedList<>();
        exp.clear();

        l1.add(viseu);
        l1.add(aveiro);
        l1.add(coimbra);
        l1.add(lisboa);
        l1.add(porto);
        l1.add(vila);

        l2.add(viseu);
        l2.add(aveiro);
        l2.add(leiria);
        l2.add(lisboa);
        l2.add(porto);
        l2.add(vila);

        l3.add(viseu);
        l3.add(guarda);
        l3.add(castelo);
        l3.add(leiria);
        l3.add(lisboa);
        l3.add(porto);
        l3.add(vila);

        exp.add(l1);
        exp.add(l2);
        exp.add(l3);

        it = paths.iterator();
        i = 1;
        while (it.hasNext()) {
            Iterator<String> it2 = it.next().iterator();
            LinkedList<String> aux = exp.pop();
            while (it2.hasNext()) {
                assertTrue(it2.next().equals(aux.pop()));
            }
        }
    }

    @Test
    public void testOrderBy() {
        completeMap = new Graph(false);

        completeMap.insertVertex(porto);
        Company.getParkRegistry().getParkMap().put(porto, portoL);
        completeMap.insertVertex(braga);
        Company.getParkRegistry().getParkMap().put(braga, bragaL);
        completeMap.insertVertex(vila);
        Company.getParkRegistry().getParkMap().put(vila, vilaL);
        completeMap.insertVertex(aveiro);
        Company.getParkRegistry().getParkMap().put(aveiro, aveiroL);
        completeMap.insertVertex(coimbra);
        Company.getParkRegistry().getParkMap().put(coimbra, coimbraL);
        completeMap.insertVertex(leiria);
        Company.getParkRegistry().getParkMap().put(leiria, leiriaL);

        completeMap.insertVertex(viseu);
        Company.getParkRegistry().getParkMap().put(viseu, viseuL);
        completeMap.insertVertex(guarda);
        Company.getParkRegistry().getParkMap().put(guarda, guardaL);
        completeMap.insertVertex(castelo);
        Company.getParkRegistry().getParkMap().put(castelo, casteloL);
        completeMap.insertVertex(lisboa);
        Company.getParkRegistry().getParkMap().put(lisboa, lisboaL);
        completeMap.insertVertex(faro);
        Company.getParkRegistry().getParkMap().put(faro, faroL);

        completeMap.insertEdge(porto, aveiro, c, 75);
        completeMap.insertEdge(porto, braga, c2, 60);
        completeMap.insertEdge(porto, vila, c3, 100);
        completeMap.insertEdge(viseu, guarda, c4, 75);
        completeMap.insertEdge(guarda, castelo, c5, 100);
        completeMap.insertEdge(aveiro, coimbra, c6, 60);
        completeMap.insertEdge(coimbra, lisboa, c7, 200);
        completeMap.insertEdge(coimbra, leiria, c8, 80);
        completeMap.insertEdge(aveiro, leiria, c9, 120);
        completeMap.insertEdge(leiria, lisboa, c10, 150);

        completeMap.insertEdge(aveiro, viseu, c11, 85);
        completeMap.insertEdge(leiria, castelo, c12, 170);
        completeMap.insertEdge(lisboa, faro, c13, 280);

        completeMap.insertEdge(porto, lisboa, c, 10);

        Company.getParkRegistry().setGraph(completeMap);

        LinkedList<String> l1 = new LinkedList<>();
        LinkedList<String> l2 = new LinkedList<>();
        LinkedList<LinkedList<String>> res = new LinkedList<>();
        LinkedList<LinkedList<String>> exp = new LinkedList<>();
        l2.add(porto);
        l2.add(aveiro);
        l2.add(coimbra);
        l2.add(leiria);
        l2.add(lisboa);

        l1.add(porto);
        l1.add(aveiro);
        l1.add(viseu);
        l1.add(guarda);
        l1.add(castelo);
        l1.add(leiria);
        l1.add(lisboa);
        res.add(l1);
        res.add(l2);
        exp.add(l2);
        exp.add(l1);
        res = GraphAlgorithms.orderByDistance(res, completeMap);
        Iterator<LinkedList<String>> it = res.iterator();
        while (it.hasNext()) {
            Iterator<String> it2 = it.next().iterator();
            LinkedList<String> aux = new LinkedList<>(exp.pop());
            while (it2.hasNext()) {
                assertTrue(it2.next().equals(aux.pop()));

            }
        }

        l1.clear();
        l2.clear();
        LinkedList<String> l3 = new LinkedList<>();
        res.clear();
        exp.clear();

        l1.add(viseu);
        l1.add(aveiro);
        l1.add(coimbra);
        l1.add(lisboa);
        l1.add(porto);
        l1.add(vila);

        l2.add(viseu);
        l2.add(aveiro);
        l2.add(leiria);
        l2.add(lisboa);
        l2.add(porto);
        l2.add(vila);

        l3.add(viseu);
        l3.add(guarda);
        l3.add(castelo);
        l3.add(leiria);
        l3.add(lisboa);
        l3.add(porto);
        l3.add(vila);

        res.add(l2);
        res.add(l3);
        res.add(l1);

        exp.add(l1);
        exp.add(l2);
        exp.add(l3);
        res = GraphAlgorithms.orderByDistance(res, completeMap);
        it = res.iterator();
        while (it.hasNext()) {
            Iterator<String> it2 = it.next().iterator();
            LinkedList<String> aux = new LinkedList<>(exp.pop());
            while (it2.hasNext()) {
                assertTrue(it2.next().equals(aux.pop()));

            }
        }
    }
}
