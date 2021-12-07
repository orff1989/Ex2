package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDirectedWeightedGraphTest {

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
        DirectedWeightedGraphAlgorithms ga = new MyDirectedWeightedGraphAlgorithms();
        ga.load("data\\G1.json");
        DirectedWeightedGraph graph = ga.getGraph();

        assertEquals(graph.getNode(0).toString(),"pos=35.19589389346247,32.10152879327731,0.0, id=0");
        graph.removeNode(0);
        assertNull(graph.getNode(0));
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}