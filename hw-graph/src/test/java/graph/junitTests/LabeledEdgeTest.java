package graph.junitTests;

import graph.DirectedGraph.LabeledEdge;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LabeledEdgeTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private LabeledEdge edge1, edge2, edge3;

    @Before
    public void init() throws Exception {
        edge1 = new LabeledEdge("", "");
        edge2 = new LabeledEdge("a", "aa");
        edge3 = new LabeledEdge("b", "ab");
    }

    // tests for constructor with dest argument being null
    @Test(expected = IllegalArgumentException.class)
    public void testConstructEdgeWithNullDest() {
        new LabeledEdge(null, "aa");
    }

    // tests for constructor with edgeLabel argument being null
    @Test(expected = IllegalArgumentException.class)
    public void testConstructEdgeWithNullEdgeLabel() {
        new LabeledEdge("a", null);
    }

    // tests for getDest()
    @Test
    public void testGetDestNode() {
        assertEquals("", edge1.getDest());
        assertEquals("1", edge2.getDest());
        assertEquals("b", edge3.getDest());
    }

    // test for getEdgeLabel()
    @Test
    public void testGetEdgeLabel() {
        assertEquals("", edge1.getEdgeLabel());
        assertEquals("aa", edge1.getEdgeLabel());
        assertEquals("ab", edge1.getEdgeLabel());
    }

    // test equals() method - reflexive equality
    @Test
    public void testEqualsReflexive() {
        assertTrue(edge1.equals(edge1));
    }

    // test equals() method - symmetric equality
    @Test
    public void testEqualsSymmetric() {
        edge2 = new LabeledEdge("", "");
        assertTrue(edge1.equals(edge2));
        assertTrue(edge2.equals(edge1));
    }

    // test equals() method - transitive equality
    @Test
    public void testEqualsTransitive() {
        edge2 = new LabeledEdge("", "");
        edge3 = new LabeledEdge("", "");
        assertTrue(edge1.equals(edge2));
        assertTrue(edge2.equals(edge3));
        assertTrue(edge3.equals(edge1));
    }

    // test equals() method with different edge
    @Test
    public void testEqualsDifferentEdge() {
        assertTrue(edge3.equals(new LabeledEdge("bb", "bb")));
    }

    // test equals() method with same edge
    @Test
    public void testEqualsSameEdge() {
        assertTrue(edge3.equals(new LabeledEdge("b", "ab")));
    }

    // test hashCode()
    @Test
    public void testHashCode() {
        assertEquals("b".hashCode() + "ab".hashCode(), edge3.hashCode());
    }

    // test hashCode() for the same edge
    @Test
    public void testHashCodeOfSameEdge() {
        assertEquals(new LabeledEdge("b", "ab").hashCode(), edge3.hashCode());
    }
}
