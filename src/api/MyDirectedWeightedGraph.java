package api;

import java.util.*;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {

    private HashMap<Vector, EdgeData> Edges;
    private HashMap<Integer, NodeData> Nodes;

    private Iterator itOfNodes;
    private int itOfNodesCreated;
    private Iterator itOfEdges;
    private int itOfEdgesCreated;
    private int changes;


    public MyDirectedWeightedGraph(HashMap Nodes, HashMap Edges){
        this.Nodes = Nodes;
        this.Edges = Edges;
        changes=0;
        createIteratorOfNodes();
        createIteratorOfEdges();
    }

    //this method returns the reversed graph without considering the weight of edges
    public MyDirectedWeightedGraph reversedGraph(){
        HashMap<Vector,EdgeData> newEdges = new HashMap<>();
        for (NodeData  a : Nodes.values()) {
            for (NodeData  b : Nodes.values()) {
                if (a!=b && getEdge(a.getKey(),b.getKey())==null) {
                    EdgeData ed = new Edge(a.getKey(),0 ,b.getKey());
                    Vector v = new Vector(2);
                    v.add(ed.getSrc());
                    v.add(ed.getDest());
                    newEdges.put(v,ed);
                }
            }
        }
        MyDirectedWeightedGraph rg = new MyDirectedWeightedGraph(Nodes, newEdges);
        return rg;
    }

    public HashMap<Integer, NodeData> getNodes() {
        return Nodes;
    }

    public HashMap<Vector, EdgeData> getEdges() {
        return Edges;
    }

    public DirectedWeightedGraph copy(){
        return new MyDirectedWeightedGraph(Nodes, Edges);
    }

    private void createIteratorOfNodes(){
        Iterator it = Nodes.values().iterator();
        itOfNodesCreated=changes;
        itOfNodes=it;
    }

    private void createIteratorOfEdges(){
        Iterator it = Edges.values().iterator();
        itOfEdgesCreated=changes;
        itOfEdges=it;
    }
    @Override
    public NodeData getNode(int key) {
        return Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Vector<Integer> v = new Vector<Integer>(2);
        v.add(src);
        v.add(dest);
         return Edges.get(v);
    }

    @Override
    public void addNode(NodeData n) {
    Nodes.put(n.getKey(), n);
    changes++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (Nodes.containsKey(src) && Nodes.containsKey(dest)) {
            Vector<Integer> v = new Vector<Integer>(2);
            v.add(src);
            v.add(dest);

            EdgeData e = new Edge(src,w,dest);

            Node srcNode =(Node) Nodes.get(src);
            Node destNode =(Node) Nodes.get(dest);
            srcNode.addEdge(e);
            destNode.addEdge(e);
            changes++;
            Edges.put(v,e);
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        try {
            if (itOfNodesCreated == changes) {
                return itOfNodes;
            } else throw new RuntimeException();
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        try {
            if (itOfEdgesCreated == changes) {
                return itOfEdges;
            } else throw new RuntimeException();
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Iterator<EdgeData> it = edgeIter();
        if (it==null) return null;

        Collection finalCollection = null;

        while (it.hasNext()){
           EdgeData ed=it.next();
            if (ed.getSrc()==node_id) finalCollection.add(ed);
        }
        return finalCollection.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData nData = Nodes.get(key);
        Node n = (Node) nData;
        while (n.degree()>0){
            removeEdge(n.getEdges(0).getSrc(),n.getEdges(0).getDest());
        }
        Nodes.remove(key);
        changes++;
        return nData;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector vec = new Vector(2);
        vec.add(src);
        vec.add(dest);
        EdgeData ed = Edges.get(vec);
        Node srcNode= (Node) Nodes.get(src);
        Node destNode= (Node) Nodes.get(dest);
        srcNode.removeEdge(ed);
        destNode.removeEdge(ed);
        Edges.remove(vec);
        changes++;
        return ed;
    }

    @Override
    public int nodeSize() {
        return Nodes.size();
    }

    @Override
    public int edgeSize() {
        return Edges.size();
    }

    @Override
    public int getMC() {
        return this.changes;
    }
}
