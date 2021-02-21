package com.instana.assignment.graph.filters;

import com.instana.assignment.graph.FilterStatus;
import com.instana.assignment.graph.TraceFilter;
import com.instana.assignment.model.Vertex;

public class ToCWithAvgLatencyLessThanThirty implements TraceFilter {
    private final Vertex endVertex;

    public ToCWithAvgLatencyLessThanThirty(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    @Override
    public FilterStatus Filter(Vertex current, int hops, int totalLatency) {
        if (totalLatency >= 30) {
            return new FilterStatus(false, false);
        }

        if (current.equals(endVertex) && totalLatency < 30) {
            return new FilterStatus(true, true);
        }
        return new FilterStatus(true, false);
    }
}
