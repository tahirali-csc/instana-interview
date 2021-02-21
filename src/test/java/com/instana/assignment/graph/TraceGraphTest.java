package com.instana.assignment.graph;

import com.instana.assignment.exception.NoSuchTraceException;
import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;
import com.instana.assignment.utils.CsvReader;
import com.instana.assignment.utils.GraphImporter;
import com.instana.assignment.utils.InvalidInputException;
import com.instana.assignment.utils.TraceInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class TraceGraphTest {
    final String path = "src/test/resources/input.csv";

    @Test
    public void avgLatencyABC() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatency(vA, vB, vC);
        Assertions.assertEquals(9, latency);
    }

    @Test
    public void avgLatencyAD() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vD = vertices.get(3);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatency(vA, vD);
        Assertions.assertEquals(5, latency);
    }

    @Test
    public void avgLatencyADC() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatency(vA, vD, vC);
        Assertions.assertEquals(13, latency);
    }

    @Test
    public void avgLatencyAEBCD() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vB = vertices.get(1);
        Vertex vC = vertices.get(2);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        TraceGraph graph = new TraceGraph(vertices);
        int latency = graph.avgLatency(vA, vE, vB, vC, vD);
        Assertions.assertEquals(22, latency);
    }

    @Test()
    public void avgLatencyAED() throws IOException, InvalidInputException {
        List<Vertex> vertices = importGraph();
        Vertex vA = vertices.get(0);
        Vertex vD = vertices.get(3);
        Vertex vE = vertices.get(4);

        TraceGraph graph = new TraceGraph(vertices);
        Assertions.assertThrows(NoSuchTraceException.class, ()-> graph.avgLatency(vA, vE, vD));
    }


    private List<Vertex> importGraph() throws IOException, InvalidInputException {
        List<TraceInput> traceInputs = CsvReader.readFile(path);
        return GraphImporter.importGraph(traceInputs);
    }

//    @Test
//    public void Hello() throws IOException {
//        List<TraceInput> traces = readInput();
//        List<Vertex> vertices = convertToVertices(traces);
//
//        TraceGraph traceGraph = new TraceGraph(vertices);
////        traceGraph.run();
//        Vertex vA = vertices.get(0);
//        Vertex vB = vertices.get(1);
//        Vertex vC = vertices.get(2);
//        Vertex vD = vertices.get(3);
//        Vertex vE = vertices.get(4);
//
//        List<Vertex> mm = new ArrayList<>();
//        mm.add(vA);
//        mm.add(vB);
//        mm.add(vC);
//        System.out.println(traceGraph.avgLatency(mm));
//
//        mm = new ArrayList<>();
//        mm.add(vA);
//        mm.add(vD);
//        System.out.println(traceGraph.avgLatency(mm));
//
//        mm = new ArrayList<>();
//        mm.add(vA);
//        mm.add(vD);
//        mm.add(vC);
//        System.out.println(traceGraph.avgLatency(mm));
//
//        mm = new ArrayList<>();
//        mm.add(vA);
//        mm.add(vE);
//        mm.add(vB);
//        mm.add(vC);
//        mm.add(vD);
//        System.out.println(traceGraph.avgLatency(mm));
//
//        mm = new ArrayList<>();
//        mm.add(vA);
//        mm.add(vE);
//        mm.add(vD);
//        System.out.println(traceGraph.avgLatency(mm));
//
////        traceGraph.buildAll(vC, vC);
//
////        traceGraph.deepTrace(vC, vC, 3);
////        traceGraph.deepTrace(vA, vC, (p, t, depth, sumAv)->{
////            if (depth > 4){
////                return false;
////            }
////            if(p == vC && depth != 4){
////                System.out.println("MMM");
////                return false;
////            }
////            return true;
////        });
//
//        traceGraph.deepTrace1(vC, vC);
//
////        traceGraph.deepTrace(vC, vC, (p, t, depth, sumAv, cp) -> {
//////            System.out.print("-" + p.getValue() + "-");
////
////            if (sumAv > 30) {
////                return false;
////            }
////            if (p == vC && sumAv < 30) {
//////                System.out.print("GMM");
////                System.out.print(sumAv + "\n");
////                for (Vertex v : cp) {
////                    System.out.print(v.getValue() + ",");
////                }
////                System.out.println();
////
//////                return false;
////            }
////            return true;
////        });
//
//
//    }
//
//    private List<Vertex> convertToVertices(List<TraceInput> traces) {
//        HashMap<Character, Vertex> map = new HashMap<>();
//
//        for (TraceInput i : traces) {
//            Vertex from = map.getOrDefault(i.getFrom(), new Vertex(i.getFrom()));
//            Vertex to = map.getOrDefault(i.getTo(), new Vertex(i.getTo()));
//
//            from.addEdge(to, i.getDistance());
//
//            map.put(i.getFrom(), from);
//            map.put(i.getTo(), to);
//        }
//
//        List<Vertex> vertices = new ArrayList<>();
//        for (Map.Entry<Character, Vertex> kv : map.entrySet()) {
//            System.out.println(kv);
//            for (Edge e : kv.getValue().getEdges()) {
//                System.out.println(e.getTo() + "," + e.getCost());
//            }
//            System.out.println();
//            vertices.add(kv.getValue());
//        }
//
//        return vertices;
//    }
//
//    private List<TraceInput> readInput() throws IOException {
//        String path = "src/test/resources/input.csv";
//
//        File file = new File(path);
//        String absolutePath = file.getAbsolutePath();
//        System.out.println(absolutePath);
//
//        List<TraceInput> traces = new ArrayList<>();
////        List<List<String>> records = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//
//
//                for (String val : values) {
//                    val = val.trim();
//                    if (val.length() >= 3) {
//                        char from = val.charAt(0);
//                        char to = val.charAt(1);
//                        int dist = Integer.valueOf(val.substring(2, val.length()));
//                        TraceInput trace = new TraceInput(from, to, dist);
//                        traces.add(trace);
//                    }
//                }
////                System.out.println(traces);
//            }
//        }
//        return traces;
//    }

}


