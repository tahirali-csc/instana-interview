package com.instana.assignment.model;

import java.util.*;

public class Vertex {
    private final char value;
    private final Set<Edge> edges;

    public Vertex(char value) {
        this.value = value;
        this.edges = new HashSet<>();
    }

    public char getValue() {
        return value;
    }

    public void addEdge(Vertex to, int cost) {
        this.edges.add(new Edge(to, cost));
    }

    public Iterable<Edge> getEdges() {
        return this.edges;
    }

    public boolean has(Edge edge) {
        return this.edges.contains(edge);
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
