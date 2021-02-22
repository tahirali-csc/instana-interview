package com.instana.assignment.graph;

/**
 * Represents the filter output value
 */
public class FilterStatus {
    private boolean expandTrace;
    private boolean traceFound;

    /**
     * Initialize a filter status
     * @param expandTrace
     * @param traceFound
     */
    public FilterStatus(boolean expandTrace, boolean traceFound) {
        this.expandTrace = expandTrace;
        this.traceFound = traceFound;
    }

    /**
     * Whether to further expand the trace.
     * @return
     */
    public boolean isExpandTrace() {
        return expandTrace;
    }

    /**
     * If the requested trace is found
     * @return
     */
    public boolean isTraceFound() {
        return traceFound;
    }
}