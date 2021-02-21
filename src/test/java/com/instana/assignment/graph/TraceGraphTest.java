package com.instana.assignment.graph;

import com.instana.assignment.exception.NoSuchTraceException;
import com.instana.assignment.graph.filters.ToCWithAvgLatencyLessThanThirty;
import com.instana.assignment.graph.filters.ToCWithExact4Hops;
import com.instana.assignment.graph.filters.ToCWithMaxThreeHops;
import com.instana.assignment.model.Vertex;
import com.instana.assignment.utils.CsvReader;
import com.instana.assignment.utils.GraphImporter;
import com.instana.assignment.utils.InvalidInputException;
import com.instana.assignment.utils.TraceInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TraceGraphTest {
    final String path = "src/test/resources/input.csv";

    @Test
    public void avgLatencyABC() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatencyByHops(vA, vB, vC);
        assertEquals(9, latency);
    }

    @Test
    public void avgLatencyAD() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vD = vertices.get(3);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatencyByHops(vA, vD);
        assertEquals(5, latency);
    }

    @Test
    public void avgLatencyADC() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatencyByHops(vA, vD, vC);
        assertEquals(13, latency);
    }

    @Test
    public void avgLatencyAEBCD() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatencyByHops(vA, vE, vB, vC, vD);
        assertEquals(22, latency);
    }

    @Test()
    public void avgLatencyAED() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        TraceGraph graph = new TraceGraph(vertices);
        assertThrows(NoSuchTraceException.class, () -> graph.avgLatencyByHops(vA, vE, vD));
    }

    @Test()
    public void avgLatencyShortestAC() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(9, graph.avgLatencyByTrace(vA, vC));
    }

    @Test()
    public void avgLatencyShortestAB() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);

        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(9, graph.avgLatencyByTrace(vB, vB));
    }

    @Test()
    public void totalTracesCtoCWithMaxThreeHops() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(2, graph.getTraceCount(vC, new ToCWithMaxThreeHops(vC)));
    }

    @Test()
    public void toCWithExactFourHops() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(3, graph.getTraceCount(vA, new ToCWithExact4Hops(vC)));
    }

    @Test()
    public void totalTracesCWithAvgLatencyLessThanThirty() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        assertEquals(7, graph.getTraceCount(vC, new ToCWithAvgLatencyLessThanThirty(vC)));
    }

    private List<Vertex> importGraph() throws IOException, InvalidInputException {
        List<TraceInput> traceInputs = CsvReader.readFile(path);
        return GraphImporter.importGraph(traceInputs);
    }
}


