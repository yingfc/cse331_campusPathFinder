package pathfinder;

import graph.DirectedGraph;
import marvel.MarvelPaths;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.util.*;

public class MarvelPathsWeighted {

    public static DirectedGraph<String, Double> buildGraph(String filename) {
        DirectedGraph<String, String> g = MarvelPaths.buildGraph(filename);
        DirectedGraph<String, Double> weightedGraph = new DirectedGraph<>();
        Map<String, Map<String, Integer>> data = new HashMap<>();

        for (String source: g.getAllNodes()) {
            weightedGraph.addNode(source);
            data.put(source, new HashMap<>());
        }

        for (String source: g.getAllNodes()) {
            Set<DirectedGraph.LabeledEdge<String, String>> s = g.getEdges(source);
            for (DirectedGraph.LabeledEdge<String, String> le : s) {
                if (!data.get(source).containsKey(le.getDest())) {
                    data.get(source).put(le.getDest(), 1);
                } else {
                    data.get(source).put(le.getDest(), data.get(source).get(le.getDest()) + 1);
                }
            }
        }

        for (String source : data.keySet()) {
            for (String dest : data.get(source).keySet()) {
                weightedGraph.addEdge(source, dest, 1.0/data.get(source).get(dest));
            }
        }
        return weightedGraph;
    }

    public static <T, E extends Number> List<DirectedGraph.LabeledEdge<T, Double>> Dijkstra (DirectedGraph<T, E> g, T source, T dest) {
        if (g == null || source == null || dest == null) {
            throw new IllegalArgumentException();
        }

        Queue<ArrayList<DirectedGraph.LabeledEdge<T, Double>>> active = new PriorityQueue<>((o1, o2) -> {
            DirectedGraph.LabeledEdge<T, Double> le1 = o1.get(o1.size() - 1);
            DirectedGraph.LabeledEdge<T, Double> le2 = o2.get(o2.size() - 1);
            if (!(le1.getEdgeLabel().equals(le2.getEdgeLabel()))) {
                return le1.getEdgeLabel().compareTo(le2.getEdgeLabel());
            }
            return o1.size() - o2.size();
        });
        Set<T> finished = new HashSet<>();

        List<DirectedGraph.LabeledEdge<T, Double>> init = new ArrayList<>();
        init.add(new DirectedGraph.LabeledEdge<>(source, 0.0));
        active.add(new ArrayList<>(init));

        while (!active.isEmpty()) {
            List<DirectedGraph.LabeledEdge<T, Double>> minPath = active.remove();
            T minDest = minPath.get(minPath.size() - 1).getDest();
            double minCost = minPath.get(minPath.size() - 1).getEdgeLabel();
            if (minDest.equals(dest)) {
                return minPath;
            }

            if (finished.contains(minDest)) {
                continue;
            }

            for (DirectedGraph.LabeledEdge<T, E> le : g.getEdges(minDest)) {
                if (!finished.contains(le.getDest())) {
                    ArrayList<DirectedGraph.LabeledEdge<T, Double>> newPath = new ArrayList<>(minPath);
                    double newCost = minCost + le.getEdgeLabel().doubleValue();
                    newPath.add(new DirectedGraph.LabeledEdge<>(le.getDest(), newCost));
                    active.add(newPath);
                }
            }
            finished.add(minDest);
        }
        return null;
    }
}
