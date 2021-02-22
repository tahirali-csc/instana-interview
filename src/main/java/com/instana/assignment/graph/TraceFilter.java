package com.instana.assignment.graph;

import com.instana.assignment.model.Vertex;

/**
 * This interface is used to specify custom filter on trace.
 */
@FunctionalInterface
public interface TraceFilter {
    /**
     * Filters the path in a trace
     * @param current current node
     * @param hops current hops
     * @param totalLatency total latency so far
     * @return
     */
    FilterStatus Filter(Vertex current, int hops, int totalLatency);
}

