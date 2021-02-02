package graph;

import java.util.Set;

/**
 * <b>MyGraph</b> represents a mutable, directed graph.
 * This graph does not contain two identical edges with same source, destination, and label.
 * It is a collection of nodes (also called vertices) and edges. Each edge contains an destination
 * node and an edge label.
 *
 * @param <T> The type of the destination node of the edge.
 * @param <E> The type of the label of the edge.
 */
public class MyGraph<T extends Comparable<T>, E extends Comparable<E>> {

    /**
     * Creates an empty directed graph.
     *
     * @spec.effects Constructs an empty directed graph
     */
    public MyGraph() {
        throw new RuntimeException("MyGraph constructor is not yet implemented");
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
    public boolean addNode(T node) {
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
    public boolean addEdge(T source, T dest, E label) {
        throw new RuntimeException("addEdge method is not yet implemented");
    }

    /**
     * Remove a node with its connecting edges from the graph.
     *
     * @param node the node to be removed from the graph
     * @return true if the node being successfully removed from the graph, otherwise return false
     * @spec.requires node != null
     * @spec.modifies this
     * @spec.effects remove the node and its connecting edges from the graph
     */
    public boolean removeNode(T node) {
        throw new RuntimeException("removeNode method is not yet implemented");
    }

    /**
     * Remove an edge from the graph.
     *
     * @param source source node of the edge
     * @param dest destination node of the edge
     * @param label the label of the edge
     * @return true if the edge being successfully removed from the graph, otherwise return false
     * @spec.requires source != null &amp;&amp; dest != null &amp;&amp; label != null &amp;&amp;
     *                source and dest nodes are in the graph
     * @spec.modifies this
     * @spec.effects remove the edge from the graph if possible
     */
    public boolean removeEdge(T source, T dest, E label) {
        throw new RuntimeException("removeEdge method is not yet implemented");
    }

    /**
     * Check if the node is in the graph.
     *
     * @param node the node to be check if present in the graph
     * @return true if the node is in the graph, otherwise return false
     * @spec.requires node != null
     */
    public boolean containsNode(T node) {
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
    public boolean containsEdge(T source, T dest, E label) {
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
    public boolean isConnected(T source, T dest) {
        throw new RuntimeException("isConnected method is not yet implemented");
    }

    /**
     * Get the nodes that are directly connected by a single edge to the given node.
     *
     * @param node the node treated as the head node to be traced
     * @return the set of nodes directly connected to the given node
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public Set<T> adjacentNodes(T node) {
        throw new RuntimeException("adjacentNodes method is not yet implemented");
    }

    /**
     * Get the edges that connects to the given node.
     *
     * @param node the node treated as the head node to be traced for connecting edges
     * @return the set of of edges that are connecting to the given node
     * @spec.requires node != null &amp;&amp; node is in the graph
     */
    public Set<MyEdge<T, E>> getEdges(T node) {
        throw new RuntimeException("getEdges method is not yet implemented");
    }

    /**
     * Get all the nodes in the graph.
     *
     * @return the set of all nodes in the graph
     * @spec.requires the graph is not null
     */
    public Set<T> getAllNodes() {
        throw new RuntimeException("getAllNodes method is not yet implemented");
    }

    /**
     * Get all the edges in the graph.
     *
     * @return the set of of all edges in the graph
     * @spec.requires the graph is not null
     */
    public Set<MyEdge<T, E>> getAllEdges() {
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
     * @return true if obj represents the same MyGraph instance as this edge
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
}
