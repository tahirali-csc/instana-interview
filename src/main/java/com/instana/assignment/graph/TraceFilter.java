package com.instana.assignment.graph;

import com.instana.assignment.model.Vertex;

@FunctionalInterface
public interface TraceFilter {
    FilterStatus Filter(Vertex current, int hops, int totalLatency);
}

