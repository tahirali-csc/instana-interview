package com.instana.assignment.graph.filter;

public class Criteria {
    private Operator operator;
    private ValueType valueType;
    private int value;

    public Criteria(Operator operator, ValueType valueType, int value) {
        this.operator = operator;
        this.valueType = valueType;
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public int getValue() {
        return value;
    }
}



