package com.instana.assignment.graph;

import com.instana.assignment.model.Edge;
import com.instana.assignment.model.Vertex;

import java.util.*;

@FunctionalInterface
interface Filter {
    boolean filter(Vertex p, Vertex to, int depth, int avgLatency, List<Vertex> cp);
}

public class TraceGraph {
    List<Vertex> nodes;

    public TraceGraph(List<Vertex> nodes) {
        this.nodes = nodes;
    }

    public int avgLatency(List<Vertex> trace) {
        Queue<Vertex> queue = new ArrayDeque();
        queue.add(trace.get(0));

        int next = 1;
        int dist = 0;
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();

            boolean f = false;
            for (Edge e : v.getEdges()) {
                if (e.getTo() == trace.get(next)) {
                    dist += e.getCost();
                    next++;
                    if (next == trace.size()) {
                        f = true;
                        break;
                    }
                    f = true;
                    queue.add(e.getTo());
                    break;
                }
            }

            if (!f) {
                System.out.println("JJJJJ");
                return -1;
            }

        }
//        Vertex start = trace.get(0);
//        int next = 1;
//        boolean found = false;
//        int di = 0;
//        for (Edge e : start.getEdges()) {
//            if (e.getTo() == trace.get(next)) {
//                found = true;
//                di += e.getCost();
//                next++;
//            }
//        }
//        return 0;

        return dist;
    }

    Map<Vertex, Map<Vertex, VertexInfo>> info = new HashMap<>();

    public void run() {


        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    Vertex from = nodes.get(i);
                    Vertex to = nodes.get(j);
//                    buildAll(from, this.nodes.get(3));
                    buildAll(from, to);
//                    return;
                }
            }
        }

        System.out.println(info);
    }

    void deepTrace1(Vertex s, Vertex d) {
        for (Edge e : s.getEdges()) {
            List<Vertex> cp = new ArrayList<>();
            cp.add(s);
            expand1(e.getTo(), d, e.getCost(), cp);
        }
    }

    void expand1(Vertex f, Vertex t, int sum, List<Vertex> cp) {
        if (sum > 30){
            cp.remove(cp.size()-1);
            return;
        }

        cp.add(f);
        if (f == t&& sum < 30){
            System.out.println("*********");
            for (Vertex v: cp) {
                System.out.print(v.getValue() + ",");
            }
//            cp.remove(cp.size()-1);
            System.out.println();
        }

        for (Edge e : f.getEdges()) {
            expand1(e.getTo(), t, e.getCost() + sum, cp);
        }

    }

    void deepTrace(Vertex s, Vertex d, Filter filter) {
        int curr = 0;

        for (Edge e : s.getEdges()) {
            List<Vertex> cp = new ArrayList<>();
            cp.add(s);
            inflate(e.getTo(), d, 1, filter, e.getCost(), cp);
            System.out.println("\n*******\n");
        }
        System.out.println(curr);

    }


    void inflate(Vertex parent, Vertex to, int depth, Filter filter, int sumAvg, List<Vertex> cp) {

        cp.add(parent);
        if (!filter.filter(parent, to, depth, sumAvg, cp)) {
            return;
        }


        for (Edge e : parent.getEdges()) {
            inflate(e.getTo(), to, depth + 1, filter, sumAvg + e.getCost(), cp);
        }
    }

    void buildAll(Vertex s, Vertex d) {
        List<Vertex> currentPath = new ArrayList<>();
        List<List<Vertex>> allPaths = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();

        currentPath.add(s);
        dfs(s, d, visited, currentPath, allPaths);
        System.out.println("---->" + allPaths);

//        VertexInfo ii = new VertexInfo();
//        ii.path = allPaths;
//
//        if (!info.containsKey(s)) {
//            info.put(s, new HashMap<>());
//        }
//        info.get(s).put(d, ii);

    }

    private void dfs(Vertex s, Vertex d, Set<Vertex> visited, List<Vertex> currentPath, List<List<Vertex>> allPaths) {

        visited.add(s);
        if (s.equals(d)) {
            System.out.println(currentPath);

            List<Vertex> st = new ArrayList<>();
            for (Vertex v : currentPath) {
                st.add(v);
            }
            allPaths.add(st);

            visited.remove(s);
            return;
        }

        for (Edge e : s.getEdges()) {
            Vertex to = e.getTo();
            if (!visited.contains(to)) {
                currentPath.add(to);
                dfs(to, d, visited, currentPath, allPaths);
                currentPath.remove(to);
//                visited.clear();
            } else {
//                System.out.println("Already:::"+ to);
            }
        }
    }

    public List<Vertex> getNodes() {
        return nodes;
    }
}

class VertexInfo {
    List<List<Vertex>> path;

}
