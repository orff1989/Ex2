package api;

import java.util.LinkedList;
import java.util.Vector;

public class Edge implements EdgeData {
    int src, dest, tag;
    double weight;
    String info;


    public Edge(int src, int dest, int tag, double weight, String info) {
        this.src = src;
        this.dest = dest;
        this.tag = tag;
        this.weight = weight;
        this.info = info;
    }
    public Edge(int src, double weight, int dest) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;

    }

    @Override
    public int getSrc() {return this.src;}

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
