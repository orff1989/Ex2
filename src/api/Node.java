package api;

import java.util.LinkedList;
import java.util.Vector;

public class Node implements NodeData {
    private int key, tag;
    private GeoLocation location;
    private double weight;
    private String info;
    private LinkedList<EdgeData> nodeEdges;

    public Node(GeoLocation location, int key){
        this.location=location;
        this.key=key;
    }

    public EdgeData getEdges(int i) {
        return nodeEdges.get(i);
    }

    public void addEdge(EdgeData ed) {
        nodeEdges.addFirst(ed);
    }

    public int degree(){ return nodeEdges.size(); }

    public void removeEdge(EdgeData ed){ nodeEdges.remove(ed); }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
    this.location = new Location(p.x(),p.y(), p.z());
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
    this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
    this.info= new String(s);
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }
}
