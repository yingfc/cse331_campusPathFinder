package marvel;

import graph.DirectedGraph;

import java.util.*;

/**
 * MarvelPaths class reads data from a file, and use the data to build a graph,
 * allowing user to find the shortest path between nodes in the graph.
 *
 */
public class MarvelPaths {

    /**
     * Build a graph with the data from the given .tsv file
     *
     * @param filename file used to build the graph
     * @spec.requires filename != null
     * @spec.effects build/create a graph
     * @return a DirectedGraph built from the given file
     */
    public static DirectedGraph buildGraph(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename is null");
        }
        DirectedGraph marvelGraph = new DirectedGraph();
        HashSet<String> heroes = new HashSet<>();
        HashMap<String, Set<String>> books = new HashMap<>();
        Iterator<MarvelModel> it = MarvelParser.parseData(filename);

        while(it.hasNext()) {
            MarvelModel curr = it.next();
            String currHero = curr.getHero();
            String currBook = curr.getBook();

            if (currHero != null) {
                heroes.add(currHero);
            }
            if (currHero != null && currBook != null) {
                Set<String> values = books.get(currBook);
                if (values == null) {
                    Set<String> tmp = new HashSet<>();
                    tmp.add(currHero);
                    books.put(currBook, tmp);
                } else {
                    values.add(currHero);
                    books.put(currBook, values);
                }
            }
        }

        for (String hero : heroes) {
            marvelGraph.addNode(hero);
        }

        for (String book : books.keySet()) {
            Set<String> list = books.get(book);
            for (String source : list) {
                for (String dest : list) {
                    if (!(source.equals(dest))) {
                        marvelGraph.addEdge(source, dest, book);
                    }
                }
            }
        }
        return marvelGraph;
    }

    /**
     * Find the shortest path from one node to another node using BFS
     *
     * @param g the graph to find the shortest path between two nodes
     * @param source the source node
     * @param dest the destination node
     * @spec.requires graph != null &amp;&amp; source != null &amp;&amp; dest != null &amp;&amp;
     *                source and dest nodes are in the graph
     * @return the shortest path from source node to the destination node, return null if no path exist
     */
    public static List<DirectedGraph.LabeledEdge> BFS(DirectedGraph g, String source, String dest) {
        if (g == null || source == null || dest == null || !g.containsNode(source) || !g.containsNode(dest)) {
            throw new IllegalArgumentException();
        }
        Queue<String> nodesToVisit = new LinkedList<>();
        Map<String, List<DirectedGraph.LabeledEdge>> paths = new HashMap<>();
        nodesToVisit.add(source);
        paths.put(source, new ArrayList<>());

        while (!nodesToVisit.isEmpty()) {
            String currNode = nodesToVisit.poll();
            if (currNode.equals(dest)) {
                return paths.get(currNode);
            }

            List<DirectedGraph.LabeledEdge> currEdges = new ArrayList<>(g.getEdges(currNode));
            currEdges.sort((o1, o2) -> {
                if (!(o1.getDest().equals(o2.getDest()))) {
                    return o1.getDest().compareTo(o2.getDest());
                }
                if (!(o1.getEdgeLabel().equals(o2.getEdgeLabel()))) {
                    return o1.getEdgeLabel().compareTo(o2.getEdgeLabel());
                }
                return 0;
            });

            for (DirectedGraph.LabeledEdge le : currEdges) {
                String connectedNode = le.getDest();
                if (!paths.containsKey(connectedNode)) {
                    nodesToVisit.add(connectedNode);
                    List<DirectedGraph.LabeledEdge> newPath = new ArrayList<>(paths.get(currNode));
                    newPath.add(le);
                    paths.put(connectedNode, newPath);
                }
            }
        }
        return null;
    }

    /**
     * Main method that allows the user to interactively enter two nodes to find the shortest path.
     *
     * @param args for main method
     * @spec.requires the given file being in the same folder as this java file
     * @throws Exception when the given filename is null
     */
    public static void main(String[] args) throws Exception {

        String filename = "marvel.tsv";
        DirectedGraph g = MarvelPaths.buildGraph(filename);
        System.out.println("Finding minimum number of books connecting two marvel heroes...");

        Scanner reader = new Scanner(System.in);
        String source, dest;
        boolean exit = false;

        while (!exit) {
            System.out.println("Start with your source hero here: ");
            source = reader.nextLine();
            System.out.println("Then enter your destination hero: ");
            dest = reader.nextLine();

            if (!g.containsNode(source) && !g.containsNode(dest)) {
                System.out.println("Both heroes not found in current graph");
            } else if (!g.containsNode(source)) {
                System.out.println(source + " not found in current graph");
            } else if (!g.containsNode(dest)) {
                System.out.println(source + " not found in current graph");
            } else {
                exit = true;
                String node = source;
                String res = "path from " + source + " to " + dest + ":";
                List<DirectedGraph.LabeledEdge> path = MarvelPaths.BFS(g, source, dest);
                if (path == null) {
                    res += "\n" + "no path found";
                } else {
                    for (DirectedGraph.LabeledEdge le : path) {
                        res += "\n" + node + " to " + le.getDest() + " via " + le.getEdgeLabel();
                        node = le.getDest();
                    }
                }
                System.out.println(res);
            }
        }
        reader.close();
    }
}
