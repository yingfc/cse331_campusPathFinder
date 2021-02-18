package marvel.junitTests;

import graph.DirectedGraph;
import marvel.MarvelPaths;
import org.junit.Test;

public class MarvelPathExceptionTest {

    @Test (expected = IllegalArgumentException.class)
    public void testBuildNullGraph() {
        MarvelPaths.buildGraph(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBFSonNullGraph() {
        MarvelPaths.BFS(null, "source", "dest");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBFSonNullSourceNode() {
        MarvelPaths.BFS(new DirectedGraph(), null, "dest");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBFSonNullDestNode() {
        MarvelPaths.BFS(new DirectedGraph(), "source", null);
    }
}
