package com.instana.assignment.model;

import java.util.*;

public class Vertex {
    private final char value;
    private final Map<Character, Edge> edges;

    public Vertex(char value) {
        this.value = value;
        this.edges = new HashMap<>();
    }

    public char getValue() {
        return value;
    }

    public void addEdge(Vertex to, int cost) {
        this.edges.put(to.value, new Edge(to, cost));
    }

    public Iterable<Edge> getEdges() {
        return this.edges.values();
    }

    public boolean has(Edge edge) {
        return this.edges.containsKey(edge.getTo().value);
    }

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
