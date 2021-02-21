package com.instana.assignment.utils;

import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImporter {

    public static List<Vertex> importGraph(List<TraceInput> trace) {
        HashMap<Character, Vertex> vertexMap = new HashMap<>();

        for (TraceInput t : trace) {
            Vertex from = vertexMap.getOrDefault(t.getFrom(), new Vertex(t.getFrom()));
            Vertex to = vertexMap.getOrDefault(t.getTo(), new Vertex(t.getTo()));

            from.addEdge(to, t.getDistance());

            vertexMap.put(t.getFrom(), from);
            vertexMap.put(t.getTo(), to);
        }

        List<Vertex> vertices = new ArrayList<>();
        for (Map.Entry<Character, Vertex> kv : vertexMap.entrySet()) {
            vertices.add(kv.getValue());
        }

        return vertices;
    }
}
