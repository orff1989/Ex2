package api;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    DirectedWeightedGraph graph;

    @Override
    public void init(DirectedWeightedGraph gr) {
        this.graph=gr;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        MyDirectedWeightedGraph gra= (MyDirectedWeightedGraph) graph;
        return gra.copy();
    }

    @Override
    public boolean isConnected() {
        MyDirectedWeightedGraph g= (MyDirectedWeightedGraph) this.graph;
        if (g.getNodes().isEmpty()==false){
        Node myNode= (Node) g.getNode(0);

        MyDirectedWeightedGraph graph=g;
        MyDirectedWeightedGraph reversedg = g.reversedGraph();
        boolean b1=false;
        boolean b2=false;
        for (NodeData n : g.getNodes().values()) {
            if (myNode != n && shortestPathDist(myNode.getKey(),n.getKey()) !=-1) {
                b1=true;
            }
            init(reversedg);
            if (myNode != n && shortestPathDist(myNode.getKey(),n.getKey()) !=-1){
                b2=true;
            }
            init(graph);
            if (b1 || b2) return false;
        }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        try {
            graphHelper gr = new graphHelper((MyDirectedWeightedGraph) graph) ;
            FileWriter fwriter = new FileWriter(file);
            Gson gson = new GsonBuilder().create();
            gson.toJson(gr, fwriter);
            fwriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        try{
            Gson gson = new Gson();
            String str = new String(Files.readAllBytes(Paths.get(file)));
            graphHelper gh = gson.fromJson(str,graphHelper.class);
            DirectedWeightedGraph gg = gh.LinkedListToGraph();
            init(gg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String str1= "35.19589389346247,32.10152879327731,0.0";
        String str2= "36.19589389346247,31.10152879327731,0.0";

//        NodeData n1 = new Node(str1,0);
//        NodeData n2 = new Node(str2,1);
//
//        Vector v = new Vector(2);
//        v.add(0);
//        v.add(1);
//        EdgeData ed = new Edge(0,1,1);
//        HashMap nods= new HashMap();
//        nods.put(0,n1);
//        nods.put(1,n2);
//        HashMap edgs= new HashMap();
//        edgs.put(v,ed);
//        DirectedWeightedGraph g=  new MyDirectedWeightedGraph(nods,edgs);
//        graph=g;
//        Gson gson = new Gson();
//        MyDirectedWeightedGraph gr = (MyDirectedWeightedGraph) graph;
//        graphHelper gh = new graphHelper(gr.getEdges().values(), gr.getNodes().values());
//        String j =  gson.toJson(gh);
//        System.out.println(j);

    }
}
