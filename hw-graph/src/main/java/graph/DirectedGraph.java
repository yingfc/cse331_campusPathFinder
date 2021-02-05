package graph;

import java.util.HashMap;
import java.util.Set;

/**
 * <b>DirectedGraph</b> class represents a mutable, directed graph.
 * This graph does not contain two identical edges with same source, destination, and label.
 * It is a collection of nodes (also called vertices) and edges.
 * Each edge contains an destination node and an edge label.
 */
public class DirectedGraph {

    /**
     * Creates an empty directed graph.
     *
     * @spec.effects Constructs an empty directed graph
     */
    public DirectedGraph() {
        throw new RuntimeException("DirectedGraph constructor is not yet implemented");
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
        throw new RuntimeException("addNode method is not yet implemented");
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
        throw new RuntimeException("addEdge method is not yet implemented");
    }

    /**
     * Check if the node is in the graph.
     *
     * @param node the node to be check if present in the graph
     * @return true if the node is in the graph, otherwise return false
     * @spec.requires node != null
     */
    public boolean containsNode(String node) {
        throw new RuntimeException("containsNode method is not yet implemented");
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
        throw new RuntimeException("containsEdge method is not yet implemented");
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
        throw new RuntimeException("isConnected method is not yet implemented");
    }

    /**
     * Get the children nodes of the given node.
     *
     * @param node the node treated as the head node to be traced for its children nodes
     * @return the set of nodes that are the children of the given nodes
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public Set<String> childrenOf(String node) {
        throw new RuntimeException("childrenOf method is not yet implemented");
    }

    /**
     * Get the edges that connects to the given node.
     *
     * @param node the node treated as the head node to be traced for connecting edges
     * @return the set of edges that are connecting to the given node
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public HashMap<String, String> getEdges(String node) {
        throw new RuntimeException("getEdges method is not yet implemented");
    }

    /**
     * Get all the nodes in the graph.
     *
     * @return the set of all nodes in the graph
     * @spec.requires the graph is not null
     */
    public Set<String> getAllNodes() {
        throw new RuntimeException("getAllNodes method is not yet implemented");
    }

    /**
     * Get all the edges in the graph.
     *
     * @return the set of of all edges in the graph
     * @spec.requires the graph is not null
     */
    public Set<LabeledEdge> getAllEdges() {
        throw new RuntimeException("getAllEdges method is not yet implemented");
    }

    /**
     * Gets the number of nodes in the graph.
     *
     * @return the number of nodes in the graph
     */
    public int size() {
        throw new RuntimeException("size method is not yet implemented");
    }

    /**
     * Checks if the graph is empty or not.
     *
     * @return true if the graph is empty
     */
    public boolean isEmpty() {
        throw new RuntimeException("isEmpty method is not yet implemented");
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also return
     */
    @Override
    public int hashCode() {
        throw new RuntimeException("hashCode method is not yet implemented");
    }

    /**
     * Standard equality operation.
     *
     * @param obj the object to be compared for equality
     * @return true if obj represents the same DirectedGraph instance as this edge
     */
    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("equals method is not yet implemented");
    }

    /**
     * Returns a string representation of this directed graph.
     *
     * @return a String representation of the graph represented by this.
     */
    @Override
    public String toString() {
        throw new RuntimeException("toString method is not yet implemented");
    }

    /**
     * <b>LabeledEdge</b> represents an edge with label and destination node.
     *
     * An example labeled edge could be LabeledEdge("node1", "edge11") showing its
     * destination node being "node1" and its edge label being "edge11".
     */
    public static class LabeledEdge {

        /**
         * Creates a labeled edge.
         *
         * @param dest the destination of the edge to be constructed
         * @param edgeLabel the edge label of the edge to be constructed
         * @spec.requires dest != null &amp;&amp; edgeLabel != null
         * @spec.effects Constructs a new labeled edge e, with e.dest = dest, and e.edgeLabel = edgeLabel
         */
        public LabeledEdge(String dest, String edgeLabel) {
            throw new RuntimeException("LabeledEdge constructor is not yet implemented");
        }

        /**
         * Gets the destination node of this edge.
         *
         * @return the destination node of this edge
         */
        public String getDest() {
            throw new RuntimeException("getDest method is not yet implemented");
        }

        /**
         * Gets the edge label of this edge.
         *
         * @return the edge label of this edge
         */
        public String getEdgeLabel() {
            throw new RuntimeException("getEdgeLabel method is not yet implemented");
        }

        /**
         * Returns a string representation of this edge.
         *
         * @return a String representation of the edge represented by this.
         */
        @Override
        public String toString() {
            throw new RuntimeException("toString method is not yet implemented");
        }

        /**
         * Standard equality operation.
         *
         * @param obj the object to be compared for equality
         * @return true if o represents the same dest and edgeLabel as this edge
         */
        @Override
        public boolean equals(Object obj) {
            throw new RuntimeException("equals method is not yet implemented");
        }

        /**
         * Standard hashCode function.
         *
         * @return an int that all objects equal to this will also return
         */
        @Override
        public int hashCode() {
            throw new RuntimeException("hashCode method is not yet implemented");
        }
    }
}
