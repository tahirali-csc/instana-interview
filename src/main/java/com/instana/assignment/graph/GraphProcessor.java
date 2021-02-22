package com.instana.assignment.graph;

import com.instana.assignment.graph.filter.Criteria;
import com.instana.assignment.graph.filter.Operator;
import com.instana.assignment.graph.filter.TracePathFilter;
import com.instana.assignment.graph.filter.ValueType;
import com.instana.assignment.model.Vertex;
import com.instana.assignment.utils.GraphImporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GraphProcessor {
    public void process(String inputFile, String outputFile) {
        List<Vertex> vertices;
        try {
            vertices = GraphImporter.importFile(inputFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        TraceGraph graph = new TraceGraph(vertices);
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        List<Object> output = getOutput(graph, vA, vB, vC, vD, vE);

        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (Object o : output) {
                printWriter.println(o);
            }
            System.out.println("File is created at ::" + outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Object> getOutput(TraceGraph graph, Vertex vA, Vertex vB, Vertex vC, Vertex vD, Vertex vE) {
        List<Object> output = new ArrayList<>();
        output.add(graph.avgLatencyByHops(vA, vB, vC));
        output.add(graph.avgLatencyByHops(vA, vD));
        output.add(graph.avgLatencyByHops(vA, vD, vC));
        output.add(graph.avgLatencyByHops(vA, vE, vB, vC, vD));

        try {
            graph.avgLatencyByHops(vA, vE, vD);
        } catch (Exception ex) {
            output.add(ex.getMessage());
        }

        output.add(graph.getTraceCount(vC, new TracePathFilter(vC,
                new Criteria(Operator.LessThanAndEqual, ValueType.Hops, 3))));
        output.add(graph.getTraceCount(vA, new TracePathFilter(vC,
                new Criteria(Operator.Equal, ValueType.Hops, 4))));

        output.add(graph.avgLatencyByTrace(vA, vC));
        output.add(graph.avgLatencyByTrace(vB, vB));

        output.add(graph.getTraceCount(vC, new TracePathFilter(vC,
                new Criteria(Operator.LessThan, ValueType.AvgLatency, 30))));
        return output;
    }
}
