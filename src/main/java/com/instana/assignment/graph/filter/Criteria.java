package com.instana.assignment.graph.filter;

/**
 * Represents the trace filter criteria
 */
public class Criteria {
    private Operator operator;
    private ValueType valueType;
    private int value;

    /**
     * Initialize a criteria
     * @param operator
     * @param valueType
     * @param value
     */
    public Criteria(Operator operator, ValueType valueType, int value) {
        this.operator = operator;
        this.valueType = valueType;
        this.value = value;
    }

    /**
     * Get an operator
     * @return
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Get value type
     * @return
     */
    public ValueType getValueType() {
        return valueType;
    }

    /**
     * Get a value
     * @return
     */
    public int getValue() {
        return value;
    }
}



