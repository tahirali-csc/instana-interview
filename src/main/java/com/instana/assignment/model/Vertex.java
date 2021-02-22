package com.instana.assignment.model;

import java.util.*;

/**
 * Represents a Vertex in a Graph
 */
public class Vertex {
    private final char value;
    private final Map<Character, Edge> edges;

    public Vertex(char value) {
        this.value = value;
        this.edges = new HashMap<>();
    }

    /**
     * Get Vertex value
     * @return
     */
    public char getValue() {
        return value;
    }

    /**
     * Add an edge with specified cost
     * @param to
     * @param cost distance between vertices
     */
    public void addEdge(Vertex to, int cost) {
        this.edges.put(to.value, new Edge(to, cost));
    }

    /**
     * Returns list of edges
     * @return
     */
    public Iterable<Edge> getEdges() {
        return this.edges.values();
    }

    /**
     * Checks whether there is a path to specified vertex
     * @param to
     * @return
     */
    public Edge findEdge(Vertex to) {
        return this.edges.get(to.value);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return value == vertex.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
