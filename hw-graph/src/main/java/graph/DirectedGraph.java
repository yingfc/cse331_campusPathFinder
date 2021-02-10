package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>DirectedGraph</b> class represents a mutable, directed graph.
 * This graph does not contain two identical edges with same source, destination, and label.
 * It is a collection of nodes (also called vertices) and edges.
 * Each edge contains an destination node and an edge label.
 */
public class DirectedGraph {

    public static final boolean DEBUG = true;

    // the graph that holds nodes and nodes' connecting edges with labels.
    private final HashMap<String, Set<LabeledEdge>> g;

    // Abstraction Function:
    // AF(this) = a graph, g, such that
    //      g = {} when it is an empty graph
    //      g = {node1=[], ...} when node1 has no outgoing edges
    //      g = {node1=[(node2,edge12), (node3,edge13), ...], node11=[...], node22=[...]}
    //          when node2 and node3 are destination node of the edge12 and edge13 connecting
    //          from node1.

    // Representation Invariant:
    //      graph != null
    //      && each node/edge != null
    //      && graph must have the node that is included in a edge info

    /**
     * Creates an empty directed graph.
     *
     * @spec.effects Constructs an empty directed graph
     */
    public DirectedGraph() {
        g = new HashMap<String, Set<LabeledEdge>>();
        checkRep();
    }

    /**
     * Add a new node to the graph in case of it is not present already.
     *
     * @param node the node to be added to the graph
     * @return true if node being successfully added to the graph, otherwise return false
     * @spec.requires node != null
     * @spec.modifies this
     * @spec.effects add the node to the graph if it is not already present
     */
    public boolean addNode(String node) {
        checkRep();
        if (node == null) {
            throw new IllegalArgumentException("Node should not be null");
        }
        if (containsNode(node)) {
            return false;
        } else {
            g.put(node, new HashSet<LabeledEdge>());
            return true;
        }
    }

    /**
     * Add a new edge(source, destination, label) to the graph if both end nodes exist in
     * the graph and the edge connecting two ends is not present in the graph yet.
     *
     * @param source source node of the edge
     * @param dest destination node of the edge
     * @param label the label of the edge
     * @return true if the edge being successfully added to the graph, otherwise return false
     * @spec.requires source != null &amp;&amp; dest != null &amp;&amp; label != null &amp;&amp;
     *                source and dest nodes are in the graph
     * @spec.modifies this
     * @spec.effects add the edge to the graph if it is not already present
     */
    public boolean addEdge(String source, String dest, String label) {
        checkSpecRequires(source, dest, label);
        LabeledEdge curr = new LabeledEdge(dest, label);
        if (g.get(source).contains(curr)) {
            return false;
        } else {
            g.get(source).add(curr);
            return true;
        }
    }

    private void checkSpecRequires(String source, String dest, String label) {
        if (source == null || dest == null || label == null) {
            throw new IllegalArgumentException("Nodes and label should not be null");
        }
        if (!(containsNode(source) || !(containsNode(dest)))) {
            throw new IllegalArgumentException("source and dest nodes should be in the graph");
        }
    }

    /**
     * Check if the node is in the graph.
     *
     * @param node the node to be check if present in the graph
     * @return true if the node is in the graph, otherwise return false
     * @spec.requires node != null
     */
    public boolean containsNode(String node) {
        if (node == null) {
            throw new IllegalArgumentException("node should not be null");
        }
        return g.containsKey(node);
    }

    /**
     * Check if the edge is in the graph.
     *
     * @param source the source node of the edge to be checked
     * @param dest the destination node of the edge to be checked
     * @param label the edge label of the edge to be checked
     * @return ture if the edge is present in the graph, otherwise return false
     * @spec.requires source != null &amp;&amp; dest != null &amp;&amp; label != null &amp;&amp;
     *                source and dest nodes are in the graph
     */
    public boolean containsEdge(String source, String dest, String label) {
        if (source != null && dest != null && label != null && containsNode(source) && containsNode(dest)) {
            LabeledEdge curr = new LabeledEdge(dest, label);
            checkRep();
            return getEdges(source).contains(curr);
        }
        return false;
    }

    /**
     * Check if two given nodes are connected by any edge.
     *
     * @param source the source node to be checked
     * @param dest the destination node to be checked
     * @return true if there are any edge connecting these two nodes, otherwise return false
     * @spec.requires source != null &amp;&amp; dest != null &amp;&amp; source and dest nodes are in the graph
     */
    public boolean isConnected(String source, String dest) {
        if (source != null && dest != null && containsNode(source) && containsNode(dest)) {
            for (LabeledEdge le : g.get(source)) {
                if (le.getDest().equals(dest)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the children nodes of the given node.
     *
     * @param node the node treated as the head node to be traced for its children nodes
     * @return the set of nodes that are the children of the given nodes
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public Set<String> childrenOf(String node) {
        if (node == null || !g.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        Set<String> childrenNodes = new HashSet<>();
        for (LabeledEdge le: g.get(node)) {
            childrenNodes.add(le.getDest());
        }
        return childrenNodes;
    }

    /**
     * Get the edges that connects to the given node.
     *
     * @param node the node treated as the head node to be traced for connecting edges
     * @return the set of edges that are connecting to the given node
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public Set<LabeledEdge> getEdges(String node) {
        if (node == null || !g.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        return new HashSet<>(g.get(node));
    }

    /**
     * Get all the nodes in the graph.
     *
     * @return the set of all nodes in the graph
     * @spec.requires the graph is not null
     */
    public Set<String> getAllNodes() {
        return Collections.unmodifiableSet(g.keySet());
    }

    /**
     * Get all the edges in the graph.
     *
     * @return the set of of all edges in the graph
     * @spec.requires the graph is not null
     */
    public Set<LabeledEdge> getAllEdges() {
        Set<LabeledEdge> res = new HashSet<>();
        for (String source: g.keySet()) {
            res.addAll(g.get(source));
        }
        return res;
    }

    /**
     * Gets the number of nodes in the graph.
     *
     * @return the number of nodes in the graph
     */
    public int size() {
        checkRep();
        return g.size();
    }

    /**
     * Checks if the graph is empty or not.
     *
     * @return true if the graph is empty
     */
    public boolean isEmpty() {
        checkRep();
        return g.isEmpty();
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also return
     */
    @Override
    public int hashCode() {
        checkRep();
        return g.hashCode();
    }

    /**
     * Standard equality operation.
     *
     * @param obj the object to be compared for equality
     * @return true if obj represents the same DirectedGraph instance as this graph
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DirectedGraph) {
            DirectedGraph graph = (DirectedGraph) obj;
            if (this.g.size() != graph.size()) {
                return false;
            }
            return g.equals(graph.g);
        }
        return false;
    }

    /**
     * Returns a string representation of this directed graph.
     *
     * @return a String representation of the graph represented by this.
     */
    @Override
    public String toString() {
        checkRep();
        return g.toString();
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        if (DEBUG) {
            assert(this.g != null): "NULL GRAPH";
            for (String node : g.keySet()) {
                assert(node != null): "NULL NODE";
                for (LabeledEdge le : g.get(node)) {
                    assert(le != null): "NULL LABELED EDGE";
                    assert(g.containsKey(le.getDest())): "NON-EXIST NODE IN GRAPH";
                }
            }
        }
    }

    /**
     * <b>LabeledEdge</b> represents an edge with label and destination node.
     *
     * An example labeled edge could be LabeledEdge("node1", "edge11") showing its
     * destination node being "node1" and its edge label being "edge11".
     */
    public static class LabeledEdge {

        public static final boolean DEBUG = true;

        // Abstraction Function:
        // AF(this) = a labeled edge without source node, le, such that
        //      le.dest = the destination node of this edge
        //      le.edgeLabel = the edge label of this edge

        // Representation Invariant:
        // dest != null && edgeLabel != null

        /**
         * Destination of this edge
         */
        private final String dest;

        /**
         * Label of this edge
         */
        private final String edgeLabel;

        /**
         * Creates a labeled edge.
         *
         * @param dest the destination of the edge to be constructed
         * @param edgeLabel the edge label of the edge to be constructed
         * @spec.requires dest != null &amp;&amp; edgeLabel != null
         * @spec.effects Constructs a new labeled edge e, with e.dest = dest, and e.edgeLabel = edgeLabel
         */
        public LabeledEdge(String dest, String edgeLabel) {
            if (dest == null || edgeLabel == null) {
                throw new IllegalArgumentException();
            }
            this.dest = dest;
            this.edgeLabel = edgeLabel;
            checkRep();
        }

        /**
         * Gets the destination node of this edge.
         *
         * @return the destination node of this edge
         */
        public String getDest() {
            checkRep();
            return this.dest;
        }

        /**
         * Gets the edge label of this edge.
         *
         * @return the edge label of this edge
         */
        public String getEdgeLabel() {
            checkRep();
            return this.edgeLabel;
        }

        /**
         * Returns a string representation of this edge.
         *
         * @return a String representation of the edge represented by this.
         */
        @Override
        public String toString() {
            checkRep();
            String res = "(" + this.dest + "," + this.edgeLabel + ")";
            checkRep();
            return res;
        }

        /**
         * Standard equality operation.
         *
         * @param obj the object to be compared for equality
         * @return true if o represents the same dest and edgeLabel as this edge
         */
        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (!(obj instanceof LabeledEdge)) {
                return false;
            }
            LabeledEdge le = (LabeledEdge) obj;
            checkRep();
            return this.dest.equals(le.dest) && this.edgeLabel.equals(le.edgeLabel);
        }

        /**
         * Standard hashCode function.
         *
         * @return an int that all objects equal to this will also return
         */
        @Override
        public int hashCode() {
            checkRep();
            return this.dest.hashCode() * this.edgeLabel.hashCode();
        }

        /**
         * Throws an exception if the representation invariant is violated.
         */
        private void checkRep() {
            if (DEBUG) {
                assert (dest != null) : "NULL DESTINATION NODE";
                assert (edgeLabel != null) : "NULL EDGE LABEL";
            }
        }
    }
}
