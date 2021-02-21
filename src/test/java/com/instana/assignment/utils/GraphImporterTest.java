package com.instana.assignment.utils;

import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphImporterTest {
    @Test
    public void testGraphImport() throws IOException, InvalidInputException {
        String path = "src/test/resources/input.csv";
        List<TraceInput> trace = CsvReader.readFile(path);
        List<Vertex> vertices = GraphImporter.importGraph(trace);

        assertEquals(5, vertices.size());

        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        assertEquals(vertices.get(0), vA);
        assertEquals(vertices.get(1), vB);
        assertEquals(vertices.get(2), vC);
        assertEquals(vertices.get(3), vD);
        assertEquals(vertices.get(4), vE);

        assertTrue(vA.has(new Edge(vB,5)));
        assertTrue(vA.has(new Edge(vD,5)));
        assertTrue(vA.has(new Edge(vE,7)));

        assertTrue(vB.has(new Edge(vC,4)));

        assertTrue(vC.has(new Edge(vD,8)));
        assertTrue(vC.has(new Edge(vE,2)));

        assertTrue(vD.has(new Edge(vE,6)));
        assertTrue(vD.has(new Edge(vC,8)));

        assertTrue(vE.has(new Edge(vB,3)));
    }
}
