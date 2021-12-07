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
        boolean[] visi = new boolean[graph.nodeSize()];
        dfs(0,visi);
        int c = 0;
        for (int i = 0; i <graph.nodeSize() ; i++) {
            if(visi[i]==false) return false;
        }
        return true;
    }

    private void dfs(int NodeId,boolean[] visi) {
        MyDirectedWeightedGraph gg = (MyDirectedWeightedGraph) graph;
        visi[NodeId]=true;

        for (Object o: gg.getEdges().values()) {
        EdgeData ed = (Edge) o;
        if (gg.getEdge(NodeId, ed.getDest())!=null){
            if (visi[ed.getDest()]==false) dfs(ed.getDest(),visi);
        }
        }
    }
    //this method is adding every node its edges
    private void fixNodesNeighbors(){
        MyDirectedWeightedGraph gg = (MyDirectedWeightedGraph) graph;

        for (Object o1 : gg.getNodes().values()){
            Node n = (Node) o1;
            n.neighbors = new LinkedList<EdgeData>();
        }

        for (Object o : gg.getEdges().values()){
            EdgeData ed = (Edge) o;
            Node srcNode = (Node) gg.getNode(ed.getSrc());
            srcNode.addNeighbor(ed);
        }
    }
    private double[] dijkDist(int src, LinkedList<NodeData>[] thePath){
        MyDirectedWeightedGraph gg = (MyDirectedWeightedGraph) graph;
        double[] distance = new double[graph.nodeSize()];
        boolean[] visi = new boolean[graph.nodeSize()];
        Set<Integer> mySet = new HashSet<>();

        for(int k = 0; k <graph.nodeSize(); k++)
        {
            visi[k] = false;
            distance[k] = 999999;
        }
        distance[src]=0;
        int thisNode=src;

        while (1==1){
            visi[thisNode]=true;
            Node n = (Node) gg.getNode(thisNode);

            for (EdgeData ed : n.getNeighbors()) {
                if(visi[ed.getDest()]) continue;;

                mySet.add(ed.getDest());
                double tempLenghth = distance[thisNode]+ ed.getWeight();
                if (tempLenghth<distance[ed.getDest()]){
                    distance[ed.getDest()]=tempLenghth;
                    thePath[ed.getDest()]= new LinkedList<>();
                    thePath[ed.getDest()]= (LinkedList<NodeData>) thePath[thisNode].clone();
                    thePath[ed.getDest()].addLast(gg.getNode(thisNode));
                }
            }
            mySet.remove(thisNode);
            if (mySet.size()==0) break;

            double minimumDistance=999999;
            int j=0;
            for (int i :mySet){
                if (minimumDistance>distance[i]){
                    minimumDistance=distance[i];
                    j=i;
                }
            }
            thisNode=j;
        }
        return distance;
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        fixNodesNeighbors();
        LinkedList<NodeData>[] thePath = new LinkedList[graph.nodeSize()];
        for (int i = 0; i < thePath.length; i++) {
            thePath[i]= new LinkedList<>();
        }
        double[] distance = dijkDist(src,thePath);
        return distance[dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        fixNodesNeighbors();
        LinkedList<NodeData>[] thePath = new LinkedList[graph.nodeSize()];
        for (int i = 0; i < thePath.length; i++) {
            thePath[i]= new LinkedList<>();
        }
        double[] distance = dijkDist(src,thePath);
        thePath[dest].add(graph.getNode(dest));
        List<NodeData> ans = thePath[dest];
        return ans;
    }

    @Override
    public NodeData center() {
        if (isConnected()){


        }
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
