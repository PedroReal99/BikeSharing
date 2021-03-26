/*
* A collection of graph algorithms.
 */
package lapr.project.graph;

import java.util.Iterator;
import java.util.LinkedList;
import lapr.project.model.Company;
import lapr.project.model.Location;
import lapr.project.utils.Calculator;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g Graph instance
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public GraphAlgorithms() {

    }

    protected static void shortestPathEnergySpent(Graph g, String vOrig, String[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {
        int iOrig = g.getKey(vOrig);
        dist[iOrig] = 0;
        while (iOrig != -1) {
            String v = vertices[iOrig];
            int index = g.getKey(v);
            visited[index] = true;
            for (Edge outgoingEdge : g.outgoingEdges(v)) {
                String vAdj = g.opposite(v, outgoingEdge);
                int iAdj = g.getKey(vAdj);
                Location l1 = Company.getParkRegistry().getParkMap().get(outgoingEdge.getVOrig());
                Location l2 = Company.getParkRegistry().getParkMap().get(outgoingEdge.getVDest());
                if (!visited[iAdj] && dist[iAdj] > (dist[index] + Calculator.energySpent(l1, l2))) {
                    dist[iAdj] = dist[index] + Calculator.energySpent(l1, l2);
                    pathKeys[iAdj] = index;
                }
            }
            iOrig = -1;
            double minimum = Double.MAX_VALUE;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minimum) {
                    minimum = dist[i];
                    iOrig = i;
                }
            }
        }

    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static void getPath(Graph g, String vOrig, String vDest, String[] verts, int[] pathKeys, LinkedList<String> path) {

        path.addFirst(vDest);
        if (vOrig != vDest) {
            String v = verts[pathKeys[g.getKey(vDest)]];
            getPath(g, vOrig, v, verts, pathKeys, path);
        }
    }

    //shortest-path between vOrig and vDest
    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static LinkedList<String> revPath(LinkedList<String> path) {

        LinkedList<String> pathcopy = new LinkedList<>(path);
        LinkedList<String> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }

    //shortest-path between vOrig and vDest
    protected static void shortestPathLength(Graph g, String vOrig, String[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {
        int iOrig = g.getKey(vOrig);
        dist[iOrig] = 0;
        while (iOrig != -1) {
            String v = vertices[iOrig];
            int index = g.getKey(v);
            visited[index] = true;
            for (Edge outgoingEdge : g.outgoingEdges(v)) {
                String vAdj = g.opposite(v, outgoingEdge);
                int iAdj = g.getKey(vAdj);
                if (!visited[iAdj] && dist[iAdj] > (dist[index] + outgoingEdge.getWeight())) {
                    dist[iAdj] = dist[index] + outgoingEdge.getWeight();
                    pathKeys[iAdj] = index;
                }
            }
            iOrig = -1;
            double minimum = Double.MAX_VALUE;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minimum) {
                    minimum = dist[i];
                    iOrig = i;
                }
            }
        }
    }

    private static LinkedList<LinkedList<String>> shortestPathIP(Graph g, String vOrig, String vDest, LinkedList<String> shortPath, int n, LinkedList<LinkedList<String>> listsFound) {
        if (shortPath.isEmpty()) {
            GraphAlgorithms.shortestPath(g, vOrig, vDest, shortPath);
        }
        boolean flag = false;
        LinkedList<LinkedList<String>> finalList = new LinkedList<>();
        if (shortPath.size() >= n) {
            finalList.add(shortPath);
        }
        LinkedList<String> p = revPath(shortPath);
        LinkedList<LinkedList<String>> paths = new LinkedList<>();
        LinkedList<Double> dists = new LinkedList<>();
        String dest = p.pop();
        String prev = dest;
        while (!p.isEmpty()) {
            LinkedList<String> path = new LinkedList<>();
            String l = p.pop();
            double distance;
            Iterator<String> it = g.adjVertices(l).iterator();
            while (it.hasNext()) {
                String aux = it.next();
                if (!(aux.equals(prev) || aux.equals(p.peek()))) {
                    distance = GraphAlgorithms.shortestPathIP(g, aux, dest, path, l);

                    LinkedList<String> p2 = new LinkedList<>(p);
                    path.addFirst(l);

                    while (!p2.isEmpty()) {
                        path.addFirst(p2.pop());
                    }
                    paths.add(path);
                    dists.add(distance);
                    path = new LinkedList<>();
                    prev = l;
                }
            }
        }

        double min = Double.MAX_VALUE;
        Iterator<LinkedList<String>> it = paths.iterator();
        while (it.hasNext()) {
            LinkedList<String> l1 = it.next();
            double tam = dists.peek();
            if (l1.size() >= n) {
                finalList.add(l1);
            }
            listsFound.add(l1);
        }

        return finalList;
    }

    private static double shortestPathIP(Graph g, String vOrig, String vDest, LinkedList<String> shortPath, String l) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        String[] vertices = g.allkeyVerts();
        int[] pathKeys = new int[g.numVertices()];
        double[] dist = new double[g.numVertices()];
        boolean[] visited = new boolean[g.numVertices()];

        for (int i = 0; i < g.numVertices(); i++) {
            visited[i] = false;
            vertices[i] = null;
            dist[i] = Double.MAX_VALUE;
        }
        visited[g.getKey(l)] = true;

        for (String vertex : g.vertices()) {
            vertices[g.getKey(vertex)] = vertex;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        shortPath.clear();

        if (!visited[g.getKey(vDest)]) {
            return 0;
        }

        getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
        return dist[g.getKey(vDest)];
    }

    public static LinkedList<LinkedList<String>> orderByDistance(LinkedList<LinkedList<String>> list, Graph g) {
        LinkedList<Double> dists = new LinkedList<>();
        Iterator<LinkedList<String>> it = list.iterator();
        while (it.hasNext()) {
            LinkedList<String> l = it.next();
            double d = 0;
            Iterator<String> it2 = l.iterator();
            String prev = it2.next();
            while (it2.hasNext()) {
                String loc = it2.next();
                d += g.getEdge(loc, prev).getWeight();
                prev = loc;
            }
            dists.add(d);
        }
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        int n = list.size();
        for (int j = 0; j < n; j++) {
            double min = Double.MAX_VALUE;
            int pos = 0;
            for (int i = 0; i < n; i++) {
                if (dists.get(i) < min) {
                    min = dists.get(i);
                    pos = i;
                }
            }
            lists.add(list.get(pos));
            dists.set(pos, Double.MAX_VALUE);
        }
        return lists;
    }

    public static LinkedList<LinkedList<String>> ShortestInterestPoint(Graph g, String orig, String dest, int n) {
        LinkedList<LinkedList<String>> finalList;

        LinkedList<String> shortPath = new LinkedList<>();
        LinkedList<LinkedList<String>> listsFound = new LinkedList<>();
        GraphAlgorithms.shortestPath(g, orig, dest, shortPath);
        finalList = GraphAlgorithms.shortestPathIP(g, orig, dest, shortPath, n, listsFound);

        if (finalList.size()<=1) {
            Iterator<LinkedList<String>> it = listsFound.iterator();
            while (it.hasNext()) {
                LinkedList<String> aux = it.next();
                LinkedList<LinkedList<String>> listAux = new LinkedList<>();
                LinkedList<LinkedList<String>> lAux = GraphAlgorithms.shortestPathIP(g, orig, dest, aux, n, listAux);
                for (LinkedList<String> l : lAux) {
                    finalList.add(l);
                }
            }
        }
        return finalList;
    }

    public static double shortestPath(Graph g, String vOrig, String vDest, LinkedList<String> shortPath) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        String[] vertices = g.allkeyVerts();
        int[] pathKeys = new int[g.numVertices()];
        double[] dist = new double[g.numVertices()];
        boolean[] visited = new boolean[g.numVertices()];

        for (int i = 0; i < g.numVertices(); i++) {
            visited[i] = false;
            vertices[i] = null;
            dist[i] = Double.MAX_VALUE;
        }

        for (String vertex : g.vertices()) {
            vertices[g.getKey(vertex)] = vertex;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        shortPath.clear();

        if (!visited[g.getKey(vDest)]) {
            return 0;
        }

        getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
        return dist[g.getKey(vDest)];
    }

    public static double shortestPathEnergySpent(Graph g, String vOrig, String vDest, LinkedList<String> shortPath) {
        
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        String[] vertices = g.allkeyVerts();
        int[] pathKeys = new int[g.numVertices()];
        double[] dist = new double[g.numVertices()];
        boolean[] visited = new boolean[g.numVertices()];

        for (int i = 0; i < g.numVertices(); i++) {
            visited[i] = false;
            vertices[i] = null;
            dist[i] = Double.MAX_VALUE;
        }

        for (String vertex : g.vertices()) {
            vertices[g.getKey(vertex)] = vertex;
        }

        shortestPathEnergySpent(g, vOrig, vertices, visited, pathKeys, dist);
        shortPath.clear();

        if (!visited[g.getKey(vDest)]) {
            return 0;
        }

        getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
        return dist[g.getKey(vDest)];
    }

}
