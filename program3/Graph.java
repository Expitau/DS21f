import java.util.*;

public class Graph {
    public ArrayList<LinkedList<Edge>> adj;
    public int size;

    public Graph(int size){
        this.size = size;

        adj = new ArrayList<>(size);
        for(int i=0; i<size; i++) adj.add(new LinkedList<>());
    }

    public void addEdge(Integer s, Integer e, Time d, Time a, Flight f){
        adj.get(s).add(new Edge(s, e, d, a, f));
    }

    public LinkedList<Edge> getEdge(Integer s){
        return adj.get(s);
    }
}
