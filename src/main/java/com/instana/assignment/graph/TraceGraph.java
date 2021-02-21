package com.instana.assignment.graph;

import com.instana.assignment.exception.NoSuchTraceException;
import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;

import java.util.*;

//@FunctionalInterface
//interface Filter {
//    boolean filter(Vertex p, Vertex to, int depth, int avgLatency, List<Vertex> cp);
//}

public class TraceGraph {
    List<Vertex> nodes;

    public TraceGraph(List<Vertex> nodes) {
        this.nodes = nodes;
    }

    public int avgLatencyByHops(Vertex... vertices) {
        int avgLatency = 0;

        if (vertices.length > 0) {
            Vertex endVertex = vertices[vertices.length - 1];

            Queue<Vertex> q = new ArrayDeque<>();
            q.add(vertices[0]);

            int indexInTrace = 1;

            while (!q.isEmpty()) {
                Vertex current = q.poll();
                if (current == endVertex) {
                    break;
                }

                Vertex next = vertices[indexInTrace];
                Edge edge = current.findEdge(next);

                if (edge == null || !edge.getTo().equals(next)) {
                    throw new NoSuchTraceException("NO SUCH TRACE");
                }

                avgLatency += edge.getCost();
                indexInTrace++;
                q.add(edge.getTo());
            }
        }
        return avgLatency;
    }

    public int avgLatencyByTrace(Vertex source, Vertex dest) {
        //Store the min distance from start node
        Map<Vertex, Integer> dist = new HashMap<>();
        //Store the immediate parent node from the start node
        Map<Vertex, Vertex> cameFrom = new HashMap<>();

        Set<Vertex> visited = new HashSet<>();
        //Always pick node which has the smallest distance from the start node
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Vertex v : this.nodes) {
            dist.put(v, Integer.MAX_VALUE);
            cameFrom.put(v, null);
        }

        for (Edge e : source.getEdges()) {
            pq.add(e.getTo());
            dist.put(e.getTo(), e.getCost());
            cameFrom.put(e.getTo(), source);
        }
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
//            if (current.equals(dest)) {
//                break;
//            }
            visited.add(current);

            for (Edge edge : current.getEdges()) {
                Vertex next = edge.getTo();
                if (!visited.contains(next)) {
                    int newDist = dist.get(current) + edge.getCost();
                    if (newDist < dist.get(next)) {
                        dist.put(next, newDist);
                        cameFrom.put(next, current);
                    }
                    pq.add(next);
                }
            }
        }

        return dist.get(dest);
    }
}