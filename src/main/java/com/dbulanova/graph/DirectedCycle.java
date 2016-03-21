package com.dbulanova.graph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Daria on 20.03.2016.
 */
public class DirectedCycle {
    private  boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public LinkedList<Stack<Integer>> getListOfCycles() {
        return listOfCycles;
    }

    public LinkedList<Stack<Integer>> listOfCycles = new LinkedList<Stack<Integer>>();
    public boolean isStartsFromOne;

    public DirectedCycle(Digraph digraph) {
        isStartsFromOne = digraph.isStartsFromOne;
        onStack = new boolean[digraph.getVertexCount()];
        marked  = new boolean[digraph.getVertexCount()];
        edgeTo  = new int[digraph.getVertexCount()];
        for(Integer v : digraph.adjacency.keySet()) {
            if(!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    public void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for(int w : digraph.adj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for(int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                listOfCycles.add(cycle);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Stack<Integer> cycle() {
        //возвращает вершины цикла если он существует
        return cycle;
    }

    public String toString() {
        if (this.listOfCycles.size() == 0) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (Stack<Integer> current : listOfCycles) {
            int size = current.size();
            for (int k = 0; k < size; k++) {
                if(isStartsFromOne) {
                    result.append(current.pop() + 1).append(" ");
                } else {
                    result.append(current.pop()).append(" ");
                }
            }
            result.append("\n");
        }
        return new String(result);
    }
}
