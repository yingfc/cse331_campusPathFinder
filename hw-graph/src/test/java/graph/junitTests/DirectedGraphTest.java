package graph.junitTests;

import graph.LabeledEdge;
import graph.DirectedGraph;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DirectedGraphTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private DirectedGraph graph1, graph2, graph3, graph4;
    private Set<String> nodes1, nodes2, nodes3, nodes4;
    private Set<LabeledEdge> edges1, edges2, edges3, edges4;


    @Before
    public void setUp() throws Exception {
        graph1 = new DirectedGraph();
        nodes1 = new HashSet<>();
        edges1 = new HashSet<>();

        graph2 = new DirectedGraph();
        nodes2 = new HashSet<>();
        edges2 = new HashSet<>();
        graph2.addNode("node1");
        nodes2.add("node1");

        graph3 = new DirectedGraph();
        nodes3 = new HashSet<>();
        edges3 = new HashSet<>();
        graph3.addNode("node1");
        graph3.addNode("node2");
        graph3.addNode("node3");
        nodes3.add("node1");
        nodes3.add("node2");
        nodes3.add("node3");
        graph3.addEdge("node1", "node2", "edge12");
        graph3.addEdge("node2", "node3", "edge23");
        graph3.addEdge("node3", "node1", "edge31");
        graph3.addEdge("node1", "node1", "edge11");
        graph3.addEdge("node2", "node2", "edge22");
        graph3.addEdge("node3", "node3", "edge33");
        edges3.add(new LabeledEdge("node2", "edge12"));
        edges3.add(new LabeledEdge("node3", "edge23"));
        edges3.add(new LabeledEdge("node1", "edge31"));
        edges3.add(new LabeledEdge("node1", "edge11"));
        edges3.add(new LabeledEdge("node2", "edge22"));
        edges3.add(new LabeledEdge("node3", "edge33"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(graph1.isEmpty());
        assertFalse(graph2.isEmpty());
        assertFalse(graph3.isEmpty());
    }

    @Test
    public void testIsEmptyAdding() {
        // before adding nodes
        assertTrue(graph1.isEmpty());
        assertFalse(graph2.isEmpty());
        assertFalse(graph3.isEmpty());

        // after adding nodes
        graph1.addNode("node8");
        graph2.addNode("node8");
        graph3.addNode("node8");
        assertFalse(graph1.isEmpty());
        assertFalse(graph2.isEmpty());
        assertFalse(graph3.isEmpty());
    }

    @Test
    public void testIsEmptyRemoving() {
        // before removing nodes
        assertFalse(graph2.isEmpty());
        assertFalse(graph3.isEmpty());

        // after removing nodes
        graph2.removeNode("node1");
        graph3.removeNode("node1");
        assertTrue(graph2.isEmpty());
        assertFalse(graph3.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, graph1.size());
        assertEquals(1, graph2.size());
        assertEquals(3, graph3.size());
    }

    @Test
    public void testSizeAdding() {
        // before adding nodes
        assertEquals(0, graph1.size());
        assertEquals(1, graph2.size());
        assertEquals(3, graph3.size());

        // after adding nodes
        graph1.addNode("node8");
        graph2.addNode("node8");
        graph3.addNode("node8");
        assertEquals(1, graph1.size());
        assertEquals(2, graph2.size());
        assertEquals(4, graph3.size());
    }

    @Test
    public void testSizeRemoving() {
        // before removing nodes
        assertEquals(1, graph2.size());
        assertEquals(3, graph3.size());

        // after removing nodes
        graph2.removeNode("node1");
        graph3.removeNode("node1");
        assertEquals(0, graph2.size());
        assertEquals(2, graph3.size());
    }

    @Test
    public void testContainsNode() {
        assertFalse(graph1.containsNode("node1"));
        assertTrue(graph2.containsNode("node1"));
        assertFalse(graph2.containsNode("node3"));
        assertTrue(graph3.containsNode("node1"));
        assertTrue(graph3.containsNode("node3"));
        assertFalse(graph3.containsNode("node8"));
    }

    @Test
    public void testContainsNodeAfterAdding() {
        // before adding nodes
        assertFalse(graph1.containsNode("node8"));
        assertFalse(graph2.containsNode("node8"));
        assertFalse(graph3.containsNode("node8"));

        // after adding nodes
        graph1.addNode("node8");
        graph2.addNode("node8");
        graph3.addNode("node8");
        assertTrue(graph1.containsNode("node8"));
        assertTrue(graph2.containsNode("node8"));
        assertTrue(graph3.containsNode("node8"));
    }

    @Test
    public void testContainsNodeAfterRemoving() {
        // before removing nodes
        assertTrue(graph2.containsNode("node1"));
        assertTrue(graph3.containsNode("node1"));

        // after removing nodes
        graph2.addNode("node1");
        graph3.addNode("node1");
        assertFalse(graph2.containsNode("node1"));
        assertFalse(graph3.containsNode("node1"));
    }

    @Test
    public void testContainsEdge() {
        assertFalse(graph1.containsEdge("node1", "node1", "edge11"));
        assertFalse(graph2.containsEdge("node1", "node1", "edge11"));
        assertTrue(graph3.containsEdge("node1", "node1", "edge11"));
        assertFalse(graph3.containsEdge("node2", "node3", "edge33"));
        assertFalse(graph3.containsEdge("node8", "node8", "edge88"));
    }

    @Test
    public void testContainsEdgeAfterAdding() {
        assertFalse(graph2.containsEdge("node1", "node1", "edge11"));
        graph2.addEdge("node1", "node1", "edge11");
        assertTrue(graph2.containsEdge("node1", "node1", "edge11"));
    }

    @Test
    public void testContainsEdgeAfterRemoving() {
        assertTrue(graph3.containsEdge("node1", "node1", "edge11"));
        graph2.removeEdge("node1", "node1", "edge11");
        assertFalse(graph3.containsEdge("node1", "node1", "edge11"));
    }

    @Test
    public void testIsConnected() {
        assertFalse(graph2.isConnected("node1", "node1"));
        assertTrue(graph3.isConnected("node1", "node2"));
        assertFalse(graph3.isConnected("node4", "node2"));
    }

    @Test
    public void testIsConnectedAfterAdding() {
        assertFalse(graph2.isConnected("node1", "node1"));
        graph2.addEdge("node1", "node1", "edge11");
        assertTrue(graph2.isConnected("node1", "node1"));
    }

    @Test
    public void testIsConnectedAfterRemoving() {
        assertTrue(graph3.isConnected("node1", "node2"));
        assertTrue(graph3.isConnected("node3", "node2"));
        graph3.removeEdge("node1", "node2", "edge12");
        graph3.removeEdge("node3", "node2", "edge32");
        assertFalse(graph3.isConnected("node1", "node2"));
        assertFalse(graph3.isConnected("node3", "node2"));
    }

    @Test
    public void testGetAllNodes() {
        assertEquals(nodes1, graph1.getAllNodes());
        assertEquals(nodes2, graph2.getAllNodes());
        assertEquals(nodes3, graph3.getAllNodes());
    }

    @Test
    public void testGetAllNodesAdding() {
        assertEquals(nodes1, graph1.getAllNodes());
        graph1.addNode("node8");
        nodes1.add("node8");
        assertEquals(nodes1, graph1.getAllNodes());
    }

    @Test
    public void testGetAllNodesRemoving() {
        assertEquals(nodes3, graph3.getAllNodes());
        graph3.removeNode("node1");
        nodes3.remove("node1");
        assertEquals(nodes3, graph3.getAllNodes());
    }

    // TODO: adding tests for adjacentNodes() and getEdges()

    @Test
    public void testGetAllEdges() {
        assertEquals(edges1, graph1.getAllEdges());
        assertEquals(edges2, graph2.getAllEdges());
        assertEquals(edges3, graph3.getAllEdges());
    }

    @Test
    public void testGetAllEdgesAdding() {
        assertEquals(edges2, graph2.getAllEdges());
        graph2.addEdge("node1", "node1", "edge1");
        edges2.add(new LabeledEdge("node1", "edge1"));
        assertEquals(edges2, graph2.getAllEdges());
    }

    @Test
    public void testGetAllEdgesRemoving() {
        assertEquals(edges3, graph3.getAllEdges());
        graph3.removeEdge("node1", "node2", "edge12");
        edges3.remove(new LabeledEdge("node2", "edge12"));
        assertEquals(edges3, graph3.getAllEdges());
    }

    @Test
    public void testToString() {
        assertEquals("{}", graph1.toString());
        assertEquals("{node1=[]}", graph2.toString());
        assertEquals("{node1=[(node1,edge31), (node1,edge11)], " +
                "node2=[(node2,edge12), (node2,edge22)], " +
                "node3=[(node3,edge23), (node3,edge33)]}", graph3.toString());
    }
}
