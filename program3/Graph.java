import java.util.*;

public class Graph {
    public ArrayList<HashMap<Integer, ArrayList<Edge>>> adj;
    public int size;
    private Boolean sorted[];

    public Graph(int size) {
        this.size = size;
        adj = new ArrayList<>(size);
        sorted = new Boolean[size];

        for(int i=0; i<size; i++){
            adj.add(new HashMap<>());
            sorted[i] = true;
        }
        
    }

    public void addEdge(Integer s, Integer e, Time d, Time a, Flight f) {
        sorted[s] = false;

        if(adj.get(s).get(e) == null) adj.get(s).put(e, new ArrayList<Edge>());
        adj.get(s).get(e).add(new Edge(s, e, d, a, f));
    }

    public void sortAllEdge(){
        for(int i=0; i<size; i++) sortEdge(i);
    }

    private void sortEdge(Integer s){
        for(Integer e : adj.get(s).keySet()){
            ArrayList<Edge> orgEdgeList = adj.get(s).get(e);
            Collections.sort(orgEdgeList, new Comparator<Edge>(){
                @Override
                public int compare(Edge e1, Edge e2){
                    int res = e1.arrive.compareTo(e2.arrive);
                    if(res == 0) res = e2.departure.compareTo(e1.departure);
                    return res;
                }
            });

            ArrayList<Edge> edgeList = new ArrayList<>();
            edgeList.add(orgEdgeList.get(0));
            for(Edge edge : orgEdgeList){
                if(edgeList.get(edgeList.size()-1).departure.compareTo(edge.departure) < 0){
                    edgeList.add(edge);
                } 
            }
            adj.get(s).put(e, edgeList);
        }
        sorted[s] = true;
    }

    private Edge getEdge(Integer s, Integer e, Time dep){
        //if(!sorted[s]) sortEdge(s);
        ArrayList<Edge> edges = adj.get(s).get(e);
        
        Time d = new Time(dep);
        d.d = 0;

        int l = 0, r = edges.size();
        while(l < r){
            int m = (l+r)/2;
            if (d.compareTo(edges.get(m).departure) <= 0)  r = m;
		    else l = m + 1; 
        }
        if(l == edges.size()) return edges.get(0);
        return edges.get(l);
    }

    public LinkedList<Edge> getEdgeList(Integer s, Time d) {
        LinkedList<Edge> ret = new LinkedList<>();
        for(Integer e : adj.get(s).keySet()) ret.add(getEdge(s, e, d));
        return ret;
    }
}
