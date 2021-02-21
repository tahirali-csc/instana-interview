package com.instana.assignment.graph.filters;

import com.instana.assignment.graph.FilterStatus;
import com.instana.assignment.graph.TraceFilter;
import com.instana.assignment.model.Vertex;

public class ToCWithExact4Hops implements TraceFilter {
    private final Vertex endVertex;

    public ToCWithExact4Hops(Vertex endVertex){
        this.endVertex = endVertex;
    }

    @Override
    public FilterStatus Filter(Vertex current, int hops, int totalLatency) {
        if (hops > 4) {
            return new FilterStatus(false, false);
        }

        if (current.equals(endVertex) && hops == 4) {
            return new FilterStatus(true, true);
        }
        return new FilterStatus(true, false);
    }
}
