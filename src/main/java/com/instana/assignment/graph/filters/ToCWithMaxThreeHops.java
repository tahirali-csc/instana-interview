package com.instana.assignment.graph.filters;

import com.instana.assignment.graph.FilterStatus;
import com.instana.assignment.graph.TraceFilter;
import com.instana.assignment.model.Vertex;


import java.util.logging.LogRecord;

//enum Operator {
//    LessThanAndEqual,
//    Equal
//}
//
//enum ValueType {
//    Hops,
//    AvgLatency
//}
//
//class Criteria {
//    Operator operator;
//    ValueType valueType;
//    int value;
//}
//
//public class CtoCMaxThreeHops implements TraceFilter {
//    private final Vertex endVertex;
//    private final Criteria criteria;
//    private final int val;
//
//    public CtoCMaxThreeHops(Vertex endVertex, Criteria criteria, int val) {
//        this.endVertex = endVertex;
//        this.criteria = criteria;
//        this.val = val;
//    }
//
//    @Override
//    public FilterStatus Filter(Vertex current, int hops, int totalLatency) {
//        if (this.criteria.valueType == ValueType.Hops) {
//            if (hops > this.val) {
//                return new FilterStatus(false, false);
//            }
//        } else {
//            if (this.criteria.valueType == ValueType.AvgLatency) {
//                if (totalLatency > this.val) {
//                    return new FilterStatus(false, false);
//                }
//            }
//        }
////        if (hops > 3) {
////            return new FilterStatus(false, false);
////        }
////
//        if (current.equals(endVertex)) {
//
//            if (this.criteria.valueType == ValueType.Hops) {
//switch (this.criteria.operator){
//    case LessThanAndEqual:
//        if (lessThanAndEqual(this.criteria.value, hops)){
//
//        }
//}
//
//            } else {
//
//            }
//
//            if (this.criteria.operator == Operator.LessThanAndEqual) {
//                if (this.criteria.valueType == ValueType.Hops && hops < criteria.value) {
//                    return new FilterStatus(true, true);
//                }
//            } else if (this.criteria.operator == Operator.Equal) {
//                if (this.criteria.valueType == ValueType.Hops && hops == criteria.value) {
//                    return new FilterStatus(true, true);
//                }
//            }
//        }
//        return new FilterStatus(true, false);
//    }
//
//    private boolean lessThanAndEqual(int expected, int curr) {
//        return expected <= curr;
//    }
//}


public class ToCWithMaxThreeHops implements TraceFilter {
    private final Vertex vertex;

    public ToCWithMaxThreeHops(Vertex vertex) {
        this.vertex = vertex;
    }

    @Override
    public FilterStatus Filter(Vertex current, int hops, int totalLatency) {
        if (hops > 3) {
            return new FilterStatus(false, false);
        }

        if (current.equals(vertex) && hops <= 3) {
            return new FilterStatus(true, true);
        }
        return new FilterStatus(true, false);
    }
}