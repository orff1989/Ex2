package api;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MyDirectedWeightedGraphAlgorithmsTest {

    @Test
    void init() {
        NodeData n = new Node("5,5,5",0);
        HashMap<Integer, NodeData> hm = new HashMap();
        hm.put(n.getKey(),n);
        DirectedWeightedGraph g = new MyDirectedWeightedGraph(hm, new HashMap<Vector,EdgeData>());
        DirectedWeightedGraphAlgorithms gh = new MyDirectedWeightedGraphAlgorithms();
        gh.init(g);
        System.out.println(g.getNode(0).toString());
        assertEquals(g.getNode(0).toString(),"pos=5,5,5, id=0");
    }

//
//    @Test
//    void copy() {
//    }
//
    @Test
    void isConnected() {
        MyDirectedWeightedGraphAlgorithms g1 = new MyDirectedWeightedGraphAlgorithms();
        boolean r1 =g1.load("data/G1.json");

        MyDirectedWeightedGraphAlgorithms g2 = new MyDirectedWeightedGraphAlgorithms();
        boolean r2 =g2.load("data/G2.json");

        MyDirectedWeightedGraphAlgorithms gg1 = new MyDirectedWeightedGraphAlgorithms();
        boolean r3 =gg1.load("data/GG1.json");

        assertTrue(g1.isConnected());
        assertTrue(g2.isConnected());
        assertFalse(gg1.isConnected());
    }

    @Test
    void shortestPathDist() {
        MyDirectedWeightedGraphAlgorithms g = new MyDirectedWeightedGraphAlgorithms();
        boolean r =g.load("data/G1.json");

        assertEquals(4.096793421922225, g.shortestPathDist(0,3));
        assertEquals(3.031440459773105, g.shortestPathDist(2,7));
        assertEquals(8.665140841052668, g.shortestPathDist(15,4));
    }

    @Test
    void shortestPath() {
        MyDirectedWeightedGraphAlgorithms g = new MyDirectedWeightedGraphAlgorithms();
        boolean r =g.load("data/Gsmal1.json");
        System.out.println(g.shortestPathDist(0,2));
        g.shortestPath(0,2);

    }
//
//    @Test
//    void center() {
//    }
//
//    @Test
//    void tsp() {
//    }
//
    @Test
    void save() {
        String str1= "35.19589389346247,32.10152879327731,0.0";
        String str2= "36.19589389346247,31.10152879327731,0.0";
        NodeData n1 = new Node(str1,0);
        NodeData n2 = new Node(str2,1);
        HashMap<Integer,NodeData> nodes = new HashMap();
        nodes.put(n1.getKey(),n1);
        nodes.put(n2.getKey(),n2);
        MyDirectedWeightedGraph graph1 = new MyDirectedWeightedGraph(nodes, new HashMap());
        graph1.connect(n1.getKey(),n2.getKey(),1);
        MyDirectedWeightedGraphAlgorithms gAlgo = new MyDirectedWeightedGraphAlgorithms();
        gAlgo.init(graph1);
        gAlgo.save("GG1.json");
        String str=null;
        try {
             str = new String(Files.readAllBytes(Paths.get("GG1.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(str,"{\"Nodes\":[{\"pos\":\"35.19589389346247,32.10152879327731,0.0\",\"id\":0},{\"pos\":\"36.19589389346247,31.10152879327731,0.0\",\"id\":1}],\"Edges\":[{\"src\":0,\"w\":1.0,\"dest\":1}]}");
    }

    @Test
    void load() {
        MyDirectedWeightedGraphAlgorithms g = new MyDirectedWeightedGraphAlgorithms();
        boolean r =g.load("data/G1.json");
        MyDirectedWeightedGraph gg = (MyDirectedWeightedGraph) g.graph;

        assertEquals(gg.getNodes().get(0).toString(),"pos=35.19589389346247,32.10152879327731,0.0, id=0");
        assertEquals(gg.getNodes().get(5).toString(),"pos=35.212111165456015,32.106235628571426,0.0, id=5");
    }
}