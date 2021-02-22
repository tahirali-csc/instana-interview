package com.instana.assignment.graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphProcessorTest {
    private final String path = "src/test/resources/input.csv";

    @Test
    void testGraph() throws IOException {
        File file = new File(path);
        String inputFile = file.getAbsolutePath();
        Path tmp = Files.createTempFile("", ".txt");
        String outputFile = tmp.toString();

        GraphProcessor graphProcessor = new GraphProcessor();
        graphProcessor.process(inputFile, outputFile);

//        System.out.println(outputFile);

        List<String> lines = Files.readAllLines(tmp);
        assertEquals(10, lines.size());
        assertEquals("1. 9", lines.get(0));
        assertEquals("2. 5", lines.get(1));
        assertEquals("3. 13", lines.get(2));
        assertEquals("4. 22", lines.get(3));
        assertEquals("5. NO SUCH TRACE", lines.get(4));
        assertEquals("6. 2", lines.get(5));
        assertEquals("7. 3", lines.get(6));
        assertEquals("8. 9", lines.get(7));
        assertEquals("9. 9", lines.get(8));
        assertEquals("10. 7", lines.get(9));
    }
}
