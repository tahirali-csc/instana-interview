package com.instana.assignment.utils;

public class TraceInput {
    private char from;
    private char to;

    public char getFrom() {
        return from;
    }

    public char getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    private int distance;

    public TraceInput(char from, char to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}