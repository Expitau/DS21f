import java.util.*;

public class Graph {
    public ArrayList<HashMap<Integer, ArrayList<Edge>>> adj;
    public int size;

    public Graph(int size) {
        this.size = size;
        adj = new ArrayList<>(size);

        for(int i=0; i<size; i++){
            adj.add(new HashMap<>());
        }
        
    }

    public void addEdge(Integer s, Integer e, Time d, Time a, Flight f) {
        if(adj.get(s).get(e) == null) adj.get(s).put(e, new ArrayList<Edge>());
        adj.get(s).get(e).add(new Edge(s, e, d, a, f));
    }

    public Edge getEdge(Integer s, Integer e, Time d){
        Edge ret = null;
        Time time = new Time(-1, -1, -1);

        for(Edge edge : adj.get(s).get(e)){
            Time nowTime = Time.getNextTime(edge.arrive, edge.departure, d, null, true);
            if(nowTime.compareTo(time) < 0){
                time = nowTime;
                ret = edge;
            }
        }

        return ret;
    }

    public LinkedList<Edge> getEdgeList(Integer s, Time d) {
        LinkedList<Edge> ret = new LinkedList<>();
        for(Integer e : adj.get(s).keySet()) ret.add(getEdge(s, e, d));
        return ret;
    }
}
