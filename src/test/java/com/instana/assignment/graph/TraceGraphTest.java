package com.instana.assignment.graph;

import com.instana.assignment.exception.NoSuchTraceException;
import com.instana.assignment.graph.filter.Criteria;
import com.instana.assignment.graph.filter.Operator;
import com.instana.assignment.graph.filter.TracePathFilter;
import com.instana.assignment.graph.filter.ValueType;
import com.instana.assignment.model.Vertex;
import com.instana.assignment.utils.GraphImporter;
import com.instana.assignment.utils.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TraceGraphTest {
    private final String path = "src/test/resources/input.csv";
    private List<Vertex> vertices;
    private Vertex vA, vB, vC, vD, vE;

    @BeforeEach
    private void setup() throws IOException, InvalidInputException {
        vertices = GraphImporter.importFile(path);
        vA = vertices.get(0);
        vB = vertices.get(1);
        vC = vertices.get(2);
        vD = vertices.get(3);
        vE = vertices.get(4);
    }

    /**
     * Question # 1
     */
    @Test
    public void avgLatencyABC() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(9, graph.avgLatencyByHops(vA, vB, vC));
    }

    /**
     * Question # 2
     */
    @Test
    public void avgLatencyAD() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(5, graph.avgLatencyByHops(vA, vD));
    }

    /**
     * Question # 3
     */
    @Test
    public void avgLatencyADC() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(13, graph.avgLatencyByHops(vA, vD, vC));
    }

    /**
     * Question # 4
     */
    @Test
    public void avgLatencyAEBCD() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(22, graph.avgLatencyByHops(vA, vE, vB, vC, vD));
    }

    /**
     * Question # 5
     */
    @Test
    public void avgLatencyAED() {
        TraceGraph graph = new TraceGraph(vertices);
        assertThrows(NoSuchTraceException.class, () -> graph.avgLatencyByHops(vA, vE, vD));
    }

    /**
     * Question # 6
     */
    @Test
    public void fromCtoCWithMaxThreeHops() {
        TraceGraph graph = new TraceGraph(vertices);
        Criteria criteria = new Criteria(Operator.LessThanAndEqual, ValueType.Hops, 3);
        assertEquals(2, graph.getTraceCount(vC, new TracePathFilter(vC, criteria)));
    }

    /**
     * Question # 7
     */
    @Test
    public void fromAtoCWithFourHops() {
        TraceGraph graph = new TraceGraph(vertices);
        Criteria criteria = new Criteria(Operator.Equal, ValueType.Hops, 4);
        assertEquals(3, graph.getTraceCount(vA, new TracePathFilter(vC, criteria)));
    }

    /**
     * Question # 8
     */
    @Test
    public void avgLatencyShortestAC() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(9, graph.avgLatencyByTrace(vA, vC));
    }

    /**
     * Question # 9
     */
    @Test
    public void avgLatencyShortestBB() {
        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(9, graph.avgLatencyByTrace(vB, vB));
    }

    /**
     * Question # 10
     */
    @Test
    public void fromCtCWithAvgLatencyLessThanThirty() {
        TraceGraph graph = new TraceGraph(vertices);
        Criteria criteria = new Criteria(Operator.LessThan, ValueType.AvgLatency, 30);
        assertEquals(7, graph.getTraceCount(vC, new TracePathFilter(vC, criteria)));
    }
}


