package graph;

/**
 * <b>MyEdge</b> represents an edge with label and destination.
 *
 * @param <T> The type of the destination node of the edge.
 * @param <E> The type of the label of the edge.
 */
public class MyEdge<T extends Comparable<T>, E extends Comparable<E>> implements Comparable<MyEdge<T, E>> {

    /**
     * Creates a labeled edge.
     *
     * @param dest the destination of the edge to be constructed
     * @param edgeLabel the edge label of the edge to be constructed
     * @spec.requires {@code dest != null && edgeLabel != null}
     * @spec.effects Constructs a new labeled edge e, with e.dest = dest, and e.edgeLabel = edgeLabel
     */
    public MyEdge(T dest, E edgeLabel) {
        throw new RuntimeException("MyEdge constructor is not yet implemented");
    }

    /**
     * Returns the destination of this edge.
     *
     * @return the destination of this edge
     */
    public T getDest() {
        throw new RuntimeException("getDest method is not yet implemented");
    }

    /**
     * Returns the edge label of this edge.
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
    public int compareTo(MyEdge<T, E> obj) {
        throw new RuntimeException("compareTo method is not yet implemented");
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        throw new RuntimeException("checkRep method is not yet implemented");
    }
}
