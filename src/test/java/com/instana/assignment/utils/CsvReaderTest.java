package com.instana.assignment.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {
    @Test
    public void testFileRead() throws IOException, InvalidInputException {
        String path = "src/test/resources/input.csv";
        List<TraceInput> traces = CsvReader.readFile(path);

        assertEquals(9, traces.size());
        assertInputTrace(traces, 0, 'A', 'B', 5);
        assertInputTrace(traces, 1, 'B', 'C', 4);
        assertInputTrace(traces, 2, 'C', 'D', 8);
        assertInputTrace(traces, 3, 'D', 'C', 8);
        assertInputTrace(traces, 4, 'D', 'E', 6);
        assertInputTrace(traces, 5, 'A', 'D', 5);
        assertInputTrace(traces, 6, 'C', 'E', 2);
        assertInputTrace(traces, 7, 'E', 'B', 3);
        assertInputTrace(traces, 8, 'A', 'E', 7);
    }

    private static void assertInputTrace(List<TraceInput> traces, int index, char expectedFrom, char expectedTo, int expectedDist) {
        assertEquals(expectedFrom, traces.get(index).getFrom());
        assertEquals(expectedTo, traces.get(index).getTo());
        assertEquals(expectedDist, traces.get(index).getDistance());
    }
}
