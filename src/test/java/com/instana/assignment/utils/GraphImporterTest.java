package com.instana.assignment.utils;

import com.instana.assignment.model.Vertex;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphImporterTest {
    @Test
    public void testFileRead() throws IOException, InvalidInputException {
        String path = "src/test/resources/input.csv";
        List<Vertex> vertices = GraphImporter.importFile(path);

        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        assertEquals(5, vertices.size());

        assertEquals(5, vA.findEdge(vB).getCost());
        assertEquals(5, vA.findEdge(vD).getCost());
        assertEquals(7, vA.findEdge(vE).getCost());

        assertEquals(4, vB.findEdge(vC).getCost());

        assertEquals(8, vC.findEdge(vD).getCost());
        assertEquals(2, vC.findEdge(vE).getCost());

        assertEquals(6, vD.findEdge(vE).getCost());
        assertEquals(8, vD.findEdge(vC).getCost());

        assertEquals(3, vE.findEdge(vB).getCost());
    }
}
