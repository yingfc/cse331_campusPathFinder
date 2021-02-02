package graph;

/**
 * <b>LabeledEdge</b> represents an edge with label and destination.
 *
 * @param <T> The type of the destination node of the edge.
 * @param <E> The type of the label of the edge.
 */
public class LabeledEdge<T extends Comparable<T>, E extends Comparable<E>> implements Comparable<LabeledEdge<T, E>> {

    /**
     * Creates a labeled edge.
     *
     * @param dest the destination of the edge to be constructed
     * @param edgeLabel the edge label of the edge to be constructed
     * @spec.requires dest != null &amp;&amp; edgeLabel != null
     * @spec.effects Constructs a new labeled edge e, with e.dest = dest, and e.edgeLabel = edgeLabel
     */
    public LabeledEdge(T dest, E edgeLabel) {
        throw new RuntimeException("LabeledEdge constructor is not yet implemented");
    }

    /**
     * Gets the destination node of this edge.
     *
     * @return the destination node of this edge
     */
    public T getDest() {
        throw new RuntimeException("getDest method is not yet implemented");
    }

    /**
     * Gets the edge label of this edge.
     *
     * @return the edge label of this edge
     */
    public E getEdgeLabel() {
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

    /**
     * Compares this object with object obj for order. Returns a negative integer, zero,
     * or a positive integer in the case of this object is less than, equal to, or greater
     * than the compared object obj.
     *
     * @param obj the object to be compared
     * @return a negative integer, zero, or a positive integer in the case of this object
     * is less than, equal to, or greater than the compared object obj
     */
    @Override
    public int compareTo(LabeledEdge<T, E> obj) {
        throw new RuntimeException("compareTo method is not yet implemented");
    }
}
