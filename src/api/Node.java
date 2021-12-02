package api;

public class Node implements NodeData {
    private int key, tag;
    private GeoLocation location;
    private double weight;
    private String info;

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
        return info;
    }

    @Override
    public void setInfo(String s) {
    this.info= new String(s);
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }
}
