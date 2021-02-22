package com.instana.assignment.utils;

import com.instana.assignment.model.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphImporter {
    final static String DELIMITER = ",";

    public static List<Vertex> importFile(String path) throws IOException, InvalidInputException {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        HashMap<Character, Vertex> vertexMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(GraphImporter.DELIMITER);
                for (String val : values) {
                    val = val.trim();
                    if (val.length() >= 3) {
                        //Parse Trace Input. For example AB5
                        char from = val.charAt(0);
                        char to = val.charAt(1);
                        int dist = Integer.parseInt(val.substring(2));

                        Vertex fromVertex = vertexMap.getOrDefault(from, new Vertex(from));
                        Vertex sourceVertex = vertexMap.getOrDefault(to, new Vertex(to));

                        fromVertex.addEdge(sourceVertex, dist);

                        vertexMap.put(from, fromVertex);
                        vertexMap.put(to, sourceVertex);
                    } else {
                        throw new InvalidInputException(String.format("Invalid trace input: %s", val));
                    }
                }
            }
        }

        List<Vertex> vertices = new ArrayList<>();
        for (Map.Entry<Character, Vertex> kv : vertexMap.entrySet()) {
            vertices.add(kv.getValue());
        }

        return vertices;
    }
}
