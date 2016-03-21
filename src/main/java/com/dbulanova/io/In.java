package com.dbulanova.io;

import java.io.*;
import java.util.*;

/**
 * Created by Daria on 19.03.2016.
 */
public class In {

    private StringBuffer buffer = new StringBuffer();

    public In(String path) {
        try {
            InputStream inStream = getClass().getResourceAsStream("/" + path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch (IOException ioe) {
            System.err.println("Could not open " + path);
        }
    }

    public int readEdgeCount() throws IOException {
        LineNumberReader reader  = new LineNumberReader(new StringReader(buffer.toString()));
        while (reader.readLine() != null) {}
        int count = reader.getLineNumber();
        reader.close();
        return count;
    }

    public int readVertexCount() {
        Set<Integer> vertex = new HashSet<Integer>();
        Scanner scanner = new Scanner(buffer.toString());
        while (scanner.hasNext()) {
            Integer current = scanner.nextInt();
            vertex.add(current);
        }
        scanner.close();
        return vertex.size();
    }

    public int readStartsWith() {
        TreeSet<Integer> set = new TreeSet<Integer>();
        Scanner scanner = new Scanner(buffer.toString());
        while (scanner.hasNext()) {
            Integer current = scanner.nextInt();
            set.add(current);
        }
        scanner.close();
        return set.first();
    }

    public Scanner getScanner() {
        return new Scanner(buffer.toString());
    }
}
