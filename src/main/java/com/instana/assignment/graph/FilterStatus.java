package com.instana.assignment.graph;

public class FilterStatus {
    private boolean expandTrace;
    private boolean traceFound;

    public FilterStatus(boolean expandTrace, boolean traceFound) {
        this.expandTrace = expandTrace;
        this.traceFound = traceFound;
    }

    public boolean isExpandTrace() {
        return expandTrace;
    }

    public boolean isTraceFound() {
        return traceFound;
    }
}