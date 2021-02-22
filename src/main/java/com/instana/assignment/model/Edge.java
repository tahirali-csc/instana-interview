package com.instana.assignment.model;

import java.util.Objects;

/**
 * Represents Edge between vertices
 */
public class Edge {
    private final Vertex to;
    private final int cost;

    /**
     * Initialize and Edge
     * @param to
     * @param cost
     */
    public Edge(Vertex to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    /**
     * Get to vertex
     * @return
     */
    public Vertex getTo() {
        return to;
    }

    /**
     * Get distance between vertices
     * @return
     */
    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return cost == edge.cost &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, cost);
    }
}
