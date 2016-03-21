package com.dbulanova.graph;

import com.dbulanova.io.In;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Daria on 19.03.2016.
 */
public class Digraph {

    public int STARTS_WITH;
    private final int vertexCount;
    private final int edgeCount;
    public LinkedHashMap<Integer, LinkedList<Integer>> adjacency = new LinkedHashMap<Integer, LinkedList<Integer>>();
    public boolean isStartsFromOne = false;

    public Digraph(final In in) throws IOException {
        this.vertexCount = in.readVertexCount();
        this.edgeCount = in.readEdgeCount();

        STARTS_WITH = in.readStartsWith();
        if (STARTS_WITH == 1) {
            isStartsFromOne = true;
            STARTS_WITH = 0;
        }

        int[] range;
        range = new int[vertexCount];
        for(int i = STARTS_WITH; i < range.length; i++) {
            range[i] = i;
        }
        for(int i = STARTS_WITH; i < range.length - 1; i += 2) {
            int temp = range[i];
            range[i] = range[i + 1];
            range[i + 1] = temp;
        }
        for(int i : range) {
            adjacency.put(i, new LinkedList<Integer>());
        }
        Scanner scanner = in.getScanner();
        for(int i = STARTS_WITH; i < edgeCount; i++) {
            int startEdgeVertex = scanner.nextInt();
            int endEdgeVertex   = scanner.nextInt();
            if(isStartsFromOne) startEdgeVertex--;
            if(isStartsFromOne) endEdgeVertex--;
            addEdge(startEdgeVertex, endEdgeVertex);
        }
        scanner.close();
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    void addEdge(int startEdge, int endEdge) {
        validateVertex(startEdge);
        validateVertex(endEdge);
        adjacency.get(startEdge).add(endEdge);
    }

    public LinkedList<Integer> adj(int vertex) {
        return adjacency.get(vertex);
    }

    public String toString() {
        String result = vertexCount + " вершин, " + edgeCount + " ребер\n";
        for(int v = STARTS_WITH; v < vertexCount; v++) {
            if(isStartsFromOne) {
                result += v + 1 + ": ";
            } else {
            result += v + ": ";
            }
            for(int w : this.adj(v)) {
                if(isStartsFromOne) {
                    result += w +1 + " ";
                } else {
                    result += w + " ";
                }
            }
            result += "\n";
        }
        return result;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            if(isStartsFromOne) {
                throw new IndexOutOfBoundsException("vertex " + (v + 1) + " is not between 1 and " + (vertexCount - 1));
            }
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount - 1));
        }
    }
}
