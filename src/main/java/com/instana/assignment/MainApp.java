package com.instana.assignment;

import com.instana.assignment.graph.GraphProcessor;

public class MainApp {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Arguments missing. <input.csv> <output.txt>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        if (inputFile.trim().length() == 0) {
            System.out.println("Input file is missing");
            return;
        }

        if (outputFile.trim().length() == 0) {
            System.out.println("Output file is missing");
            return;
        }

        GraphProcessor graphProcessor = new GraphProcessor();
        graphProcessor.process(inputFile, outputFile);
    }
}
