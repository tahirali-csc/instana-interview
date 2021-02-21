package com.instana.assignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    final static String DELIMITER = ",";

    public static List<TraceInput> readFile(String path) throws IOException, InvalidInputException {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        List<TraceInput> traces = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CsvReader.DELIMITER);
                for (String val : values) {
                    val = val.trim();
                    if (val.length() >= 3) {
                        //Parse Trace Input. For example AB5
                        char from = val.charAt(0);
                        char to = val.charAt(1);
                        int dist = Integer.parseInt(val.substring(2));
                        traces.add(new TraceInput(from, to, dist));
                    } else {
                        throw new InvalidInputException(String.format("Invalid trace input: %s", val));
                    }
                }
            }
        }
        return traces;
    }
}
