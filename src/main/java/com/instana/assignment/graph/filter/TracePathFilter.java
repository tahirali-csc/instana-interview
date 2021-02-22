package com.instana.assignment.graph.filter;

import com.instana.assignment.graph.FilterStatus;
import com.instana.assignment.graph.TraceFilter;
import com.instana.assignment.model.Vertex;

/**
 * The custom filter used to filter trace based on user specified criteria
 */
public class TracePathFilter implements TraceFilter {
    private final Vertex endVertex;
    private final Criteria criteria;

    /**
     * Initialize a filter
     * @param endVertex
     * @param criteria
     */
    public TracePathFilter(Vertex endVertex, Criteria criteria) {
        this.endVertex = endVertex;
        this.criteria = criteria;
    }

    @Override
    public FilterStatus Filter(Vertex current, int hops, int totalLatency) {
        //Whether to further expand the trace
        if ((criteria.getValueType() == ValueType.Hops && hops > criteria.getValue()) ||
                (criteria.getValueType() == ValueType.AvgLatency && totalLatency > criteria.getValue())) {
            return new FilterStatus(false, false);
        }

        //If the end vertex is reached
        if (current.equals(endVertex)) {
            //Process the result based on Hops/Average latency
            if (criteria.getValueType() == ValueType.Hops) {
                if (criteria.getOperator() == Operator.LessThanAndEqual && hops <= criteria.getValue()) {
                    return new FilterStatus(true, true);
                } else if (criteria.getOperator() == Operator.Equal && hops == criteria.getValue()) {
                    return new FilterStatus(true, true);
                } else if (criteria.getOperator() == Operator.LessThan && hops < criteria.getValue()) {
                    return new FilterStatus(true, true);
                }
            } else {
                if (criteria.getOperator() == Operator.LessThanAndEqual && totalLatency <= criteria.getValue()) {
                    return new FilterStatus(true, true);
                } else if (criteria.getOperator() == Operator.Equal && totalLatency == criteria.getValue()) {
                    return new FilterStatus(true, true);
                } else if (criteria.getOperator() == Operator.LessThan && totalLatency < criteria.getValue()) {
                    return new FilterStatus(true, true);
                }
            }
        }

        return new FilterStatus(true, false);
    }
}
