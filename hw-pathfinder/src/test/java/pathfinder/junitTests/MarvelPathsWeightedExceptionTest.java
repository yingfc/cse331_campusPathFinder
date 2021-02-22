package pathfinder.junitTests;

import graph.DirectedGraph;
import org.junit.Test;
import pathfinder.MarvelPathsWeighted;

public class MarvelPathsWeightedExceptionTest {

    @Test (expected = IllegalArgumentException.class)
    public void testBuildNullGraph() throws Exception {
        MarvelPathsWeighted.buildGraph(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDijkstraNullGraph() {
        MarvelPathsWeighted.Dijkstra(null, "start", "end");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDijkstraNullSourceNode() {
        MarvelPathsWeighted.Dijkstra(new DirectedGraph<>(), null, "end");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDijkstraNullDestNode() {
        MarvelPathsWeighted.Dijkstra(new DirectedGraph<>(), "start", null);
    }
}
