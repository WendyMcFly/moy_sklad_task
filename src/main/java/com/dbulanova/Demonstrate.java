package com.dbulanova;

import com.dbulanova.graph.Digraph;
import com.dbulanova.graph.DirectedCycle;
import com.dbulanova.io.In;

import java.io.IOException;

/**
 * Created by Daria on 19.03.2016.
 */
public class Demonstrate {
    public static final String FILE_NAME = "cycle.txt";
    public static void main(String[] args) throws IOException {
        In in = new In(FILE_NAME);
        Digraph digraph = new Digraph(in);
//        System.out.println(digraph.toString());
        DirectedCycle cycle = new DirectedCycle(digraph);
        System.out.println(cycle.toString());
    }
}
