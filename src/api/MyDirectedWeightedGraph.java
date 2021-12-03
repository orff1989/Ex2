package api;

import java.util.*;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    HashMap<Integer, NodeData> Nodes;
    HashMap<Vector, EdgeData> Edges;
    Iterator itOfNodes;
    int itOfNodesCreated;
    Iterator itOfEdges;
    int itOfEdgesCreated;
    int changes;

    public MyDirectedWeightedGraph(HashMap nodes, HashMap edges){
        this.Nodes=nodes;
        this.Edges=edges;
        changes=0;
        createIteratorOfNodes();
        createIteratorOfEdges();
    }

    public DirectedWeightedGraph copy(){
        return new MyDirectedWeightedGraph(this.Nodes, this.Edges);
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

            EdgeData e = new Edge(src,dest,0,w,null);

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
        NodeData nData =Nodes.get(key);
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
