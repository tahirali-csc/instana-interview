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

/**
 * Processes the input graph and writes the output to the destination file
 */
public class GraphProcessor {
    /**
     * Process the graph
     *
     * @param inputFile
     * @param outputFile
     */
    public void process(String inputFile, String outputFile) {
        //Reads the input file
        List<Vertex> vertices;
        try {
            vertices = GraphImporter.importFile(inputFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        //Initialize a graph
        TraceGraph graph = new TraceGraph(vertices);
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        List<Object> output = getOutput(graph, vA, vB, vC, vD, vE);

        //Writes the output to a file
        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (int i = 0; i < output.size(); i++) {
                printWriter.println(String.format("%d. %s", i + 1, output.get(i)));
            }
            System.out.println("Output is generated at :: " + outputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Object> getOutput(TraceGraph graph, Vertex vA, Vertex vB, Vertex vC, Vertex vD, Vertex vE) {
        List<Object> output = new ArrayList<>();

        //Writes the output from questions 1->5
        output.add(graph.avgLatencyByHops(vA, vB, vC));
        output.add(graph.avgLatencyByHops(vA, vD));
        output.add(graph.avgLatencyByHops(vA, vD, vC));
        output.add(graph.avgLatencyByHops(vA, vE, vB, vC, vD));
        try {
            graph.avgLatencyByHops(vA, vE, vD);
        } catch (Exception ex) {
            output.add(ex.getMessage());
        }

        //Writes the output from questions 6->7
        output.add(graph.getTraceCount(vC, new TracePathFilter(vC,
                new Criteria(Operator.LessThanAndEqual, ValueType.Hops, 3))));
        output.add(graph.getTraceCount(vA, new TracePathFilter(vC,
                new Criteria(Operator.Equal, ValueType.Hops, 4))));

        //Writes the output from questions 8->9
        output.add(graph.avgLatencyByTrace(vA, vC));
        output.add(graph.avgLatencyByTrace(vB, vB));

        //Writes the output of question# 10.
        output.add(graph.getTraceCount(vC, new TracePathFilter(vC,
                new Criteria(Operator.LessThan, ValueType.AvgLatency, 30))));
        return output;
    }
}
