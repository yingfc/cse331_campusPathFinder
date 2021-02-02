package graph.junitTests;

import graph.MyEdge;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyEdgeTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private MyEdge<String, String> edge1, edge2, edge3;

    @Before
    public void setUp() throws Exception {
        edge1 = new MyEdge<>("", "");
        edge2 = new MyEdge<>("a", "aa");
        edge3 = new MyEdge<>("b", "ab");
    }

    // tests for constructor
    @Test(expected = IllegalArgumentException.class)
    public void testConstructEdgeWithNullDest() {
        new MyEdge<>(null, "aa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructEdgeWithNullEdgeLabel() {
        new MyEdge<>("a", null);
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

    // test toString()
    @Test
    public void testToString() {
        assertEquals("(,)", edge1.toString());
        assertEquals("(a,aa)", edge2.toString());
        assertEquals("(b,ab)", edge3.toString());
    }

    // test equals()
    @Test
    public void testEqualsReflexive() {
        assertTrue(edge1.equals(edge1));
    }

    @Test
    public void testEqualsSymmetric() {
        edge2 = new MyEdge<>("", "");
        assertTrue(edge1.equals(edge2));
        assertTrue(edge2.equals(edge1));
    }

    @Test
    public void testEqualsTransitive() {
        edge2 = new MyEdge<>("", "");
        edge3 = new MyEdge<>("", "");
        assertTrue(edge1.equals(edge2));
        assertTrue(edge2.equals(edge3));
        assertTrue(edge3.equals(edge1));
    }

    @Test
    public void testEqualsDifferentEdge() {
        assertTrue(edge3.equals(new MyEdge<>("bb", "bb")));
    }

    @Test
    public void testEqualsSameEdge() {
        assertTrue(edge3.equals(new MyEdge<>("b", "ab")));
    }

    // test hashCode()
    @Test
    public void testHashCode() {
        assertEquals("b".hashCode() + "ab".hashCode(), edge3.hashCode());
    }

    @Test
    public void testHashCodeOfSameEdge() {
        assertEquals(new MyEdge<>("b", "ab").hashCode(), edge3.hashCode());
    }

    // test compareTo()
    @Test
    public void testCompareToSameEdge() {
        assertTrue(edge3.compareTo(new MyEdge<>("b", "ab")) == 0);
    }

    @Test
    public void testCompareToSmallerDest() {
        assertTrue(edge3.compareTo(new MyEdge<>("a", "ab")) > 0);
    }

    @Test
    public void testCompareToBiggerDest() {
        assertTrue(edge3.compareTo(new MyEdge<>("d", "ab")) < 0);
    }

    @Test
    public void testCompareToSmallerEdgeLabel() {
        assertTrue(edge3.compareTo(new MyEdge<>("a", "aa")) > 0);
    }

    @Test
    public void testCompareToBiggerEdgeLabel() {
        assertTrue(edge3.compareTo(new MyEdge<>("a", "cc")) < 0);
    }
}
