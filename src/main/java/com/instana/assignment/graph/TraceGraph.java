package com.instana.assignment.graph;

import com.instana.assignment.exception.NoSuchTraceException;
import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;

import java.util.*;

/**
 * This class maintains the graph of vertices and processes trace requests
 */
public class TraceGraph {
    List<Vertex> nodes;

    /**
     * Initialize a trace graph
     * @param nodes
     */
    public TraceGraph(List<Vertex> nodes) {
        this.nodes = nodes;
    }

    /**
     * Finds the average latency based on minimum number of hops. The traversal exactly matches
     * the specified input trace.
     * @param vertices Input trace
     * @return average latency
     */
    public int avgLatencyByHops(Vertex... vertices) {
        int avgLatency = 0;

        if (vertices.length > 0) {
            Vertex endVertex = vertices[vertices.length - 1];

            //Because we are using minimum number of hops, we are using BFS
            //approach to find the distance.
            Queue<Vertex> q = new ArrayDeque<>();
            q.add(vertices[0]);

            int indexInTrace = 1;
            while (!q.isEmpty()) {
                Vertex current = q.poll();
                if (current.equals(endVertex)) {
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

    /**
     * Finds the shortest trace based on average latency.
     * @param source
     * @param dest
     * @return
     */
    public int avgLatencyByTrace(Vertex source, Vertex dest) {
        //We use Dijkstra algorithm to find the shortest distance

        //Store the min distance from start node
        Map<Vertex, Integer> dist = new HashMap<>();
        //Store the immediate parent node from the start node
        Map<Vertex, Vertex> cameFrom = new HashMap<>();

        Set<Vertex> visited = new HashSet<>();
        //Always pick node which has the smallest distance from the start node
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        //Initialize the data
        for (Vertex v : this.nodes) {
            dist.put(v, Integer.MAX_VALUE);
            cameFrom.put(v, null);
        }

        //Add the source node edges in Queue
        for (Edge e : source.getEdges()) {
            pq.add(e.getTo());
            dist.put(e.getTo(), e.getCost());
            cameFrom.put(e.getTo(), source);
        }

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (current.equals(dest)) {
                break;
            }
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

    /**
     * Finds the trace between nodes using specified filter
     * @param from
     * @param filter
     * @return
     */
    public int getTraceCount(Vertex from, TraceFilter filter) {
        //We use DFS approach to expand all possible paths in a trace
        CountWrapper wrapper = new CountWrapper();
        for (Edge e : from.getEdges()) {
            expandTrace(e.getTo(), e.getCost(), 1, filter, wrapper);
        }
        return wrapper.count;
    }

    private void expandTrace(Vertex parent, int latencySoFar, int hops, TraceFilter filter, CountWrapper wrapper) {
        FilterStatus res = filter.Filter(parent, hops, latencySoFar);
        if (!res.isExpandTrace()) {
            return;
        }

        if (res.isTraceFound()) {
            wrapper.count++;
        }

        for (Edge e : parent.getEdges()) {
            expandTrace(e.getTo(), latencySoFar + e.getCost(), hops + 1, filter, wrapper);
        }
    }

    class CountWrapper {
        int count;
    }
}

