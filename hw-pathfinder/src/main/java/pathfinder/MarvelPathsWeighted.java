package pathfinder;

import graph.DirectedGraph;
import marvel.MarvelPaths;

import java.util.*;

/**
 * MarvelPathsWeighted class reads data from a file, and use the data to build a graph,
 * allowing user to find the shortest path via weights between nodes in the graph.
 *
 */
public class MarvelPathsWeighted {

    /**
     * Find the shortest path from one node to another node using Dijkstra's algorithm.
     *
     * @param g         the graph to find the shortest path between two nodes
     * @param source    the source node
     * @param dest      the destination node
     * @param <T>       generic type representing the node
     * @param <E>       generic type representing the edge weight
     * @spec.requires   graph != null &amp;&amp; source != null &amp;&amp; dest != null &amp;&amp;
     *                  source and dest nodes are in the graph
     * @return          the shortest path from source node to the destination node, return null if no path exist
     */
    public static <T, E extends Number> List<DirectedGraph.LabeledEdge<T, Double>> Dijkstra (DirectedGraph<T, E> g, T source, T dest) {
        if (g == null || source == null || dest == null || !g.containsNode(source) || !g.containsNode(dest)) {
            throw new IllegalArgumentException();
        }

        Queue<ArrayList<DirectedGraph.LabeledEdge<T, Double>>> active = new PriorityQueue<>((o1, o2) -> {
            double sum1 = o1.stream().mapToDouble(DirectedGraph.LabeledEdge::getEdgeLabel).sum();
            double sum2 = o2.stream().mapToDouble(DirectedGraph.LabeledEdge::getEdgeLabel).sum();
            if (sum1 - sum2 > 0) {
                return 1;
            } else if (sum1 - sum2 < 0) {
                return -1;
            } else {
                return 0;
            }
        });
        Set<T> finished = new HashSet<>();

        List<DirectedGraph.LabeledEdge<T, Double>> init = new ArrayList<>();
        init.add(new DirectedGraph.LabeledEdge<>(source, 0.0));
        active.add(new ArrayList<>(init));

        while (!active.isEmpty()) {
            List<DirectedGraph.LabeledEdge<T, Double>> minPath = active.remove();
            T minDest = minPath.get(minPath.size() - 1).getDest();
            if (minDest.equals(dest)) {
                return minPath;
            }

            if (finished.contains(minDest)) {
                continue;
            }

            for (DirectedGraph.LabeledEdge<T, E> le : g.getEdges(minDest)) {
                if (!finished.contains(le.getDest())) {
                    ArrayList<DirectedGraph.LabeledEdge<T, Double>> newPath = new ArrayList<>(minPath);
                    newPath.add(new DirectedGraph.LabeledEdge<>(le.getDest(), le.getEdgeLabel().doubleValue()));
                    active.add(newPath);
                }
            }
            finished.add(minDest);
        }
        return null;
    }
}
