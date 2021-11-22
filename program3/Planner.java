// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

public class Planner {
    private int V, E;
    private HashMap<String, Integer> code2Int;
    private ArrayList<String> int2Code;
    private ArrayList<Time> connectTimeList;
    private Graph graph;

    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        mapAirportCode(portList);
        makeGraph(fltList);
    }

    private void mapAirportCode(LinkedList<Airport> portList) {
        V = portList.size();
        connectTimeList = new ArrayList<>();
        code2Int = new HashMap<>();
        int2Code = new ArrayList<>();

        int i = 0;
        for (Airport airport : portList) {
            code2Int.put(airport.code, i++);
            int2Code.add(airport.code);
            connectTimeList.add(airport.time);
        }
    }

    private void makeGraph(LinkedList<Flight> fltList) {
        E = fltList.size();
        graph = new Graph(V);
        for (Flight flight : fltList) {
            Integer s = code2Int.get(flight.start);
            Integer e = code2Int.get(flight.end);
            Time d = flight.departure;
            Time a = flight.arrival;
            if (d.compareTo(a) > 0)
                a.addD(1);
            graph.addEdge(s, e, d, a, flight);
        }
    }

    public Itinerary Schedule(String start, String end, String departure) {
        Integer st = code2Int.get(start), ed = code2Int.get(end);
        if (st == null || ed == null)
            return new Itinerary();

        Time dep = Time.parseTime(departure);
        DistData[] dist;
        //if(E < V*V)  dist = getDistNaive(st, ed, dep);
        //else dist = getDistNaive(st, ed, dep);
        
        dist = getDist(st, ed, dep);

        return getItinerary(st, ed, dep, dist);
    }

    private void pushDistNaive(DistData[] dist, Integer now, Integer next, Time time, Flight flight){
        if (dist[next].time.compareTo(time) <= 0) return;
        dist[next] = new DistData(now, time, flight);
    }

    private DistData[] getDistNaive(Integer st, Integer ed, Time de){
        DistData[] dist = new DistData[V];
        Boolean[] S = new Boolean[V];

        for (int i = 0; i < V; i++){
            dist[i] = new DistData(Integer.valueOf(-1), new Time(-1, -1, -1));
            S[i] = false;
        }
        
        pushDistNaive(dist, -1, st, de, null);

        for(int T=0; T<V; T++) {
            Time time = new Time(-1, -1, -1);
            int airport = V;
            for(int i=0; i<V; i++){
                if(S[i]) continue;
                if(dist[i].time.compareTo(time) < 0) {
                    time = dist[i].time; 
                    airport = i;
                }
            }
            S[airport] = true;

            if (airport == ed)
                break;

            for (Edge edge : graph.getEdgeList(airport, time)) {
                Integer next = edge.next;
                Time arrive = Time.getNextTime(edge.arrive, edge.departure, time, connectTimeList.get(next), next == ed);
                pushDistNaive(dist, airport, next, arrive, edge.flight);
            }
        }

        return dist;
    }

    private void pushDist(PriorityQueue<DistData> pq, DistData[] dist, Integer now, Integer next, Time time,
            Flight flight) {
        if (dist[next].time.compareTo(time) <= 0)
            return;
        pq.remove(dist[next]);
        dist[next] = new DistData(now, time, flight);
        pq.add(dist[next]);
    }

    private DistData[] getDist(Integer st, Integer ed, Time de) {
        PriorityQueue<DistData> pq = new PriorityQueue<>();
        DistData[] dist = new DistData[V];
        for (int i = 0; i < V; i++)
            dist[i] = new DistData(Integer.valueOf(-1), new Time(-1, -1, -1));

        pushDist(pq, dist, -1, st, de, null);

        int airport;
        Time time;
        while (!pq.isEmpty()) {
            DistData now = pq.poll(); // return top and delete value
            try{airport = code2Int.get(now.flight.end);}
            catch(NullPointerException e){airport = st;}
            time = now.time;

            if (dist[airport].time.compareTo(time) < 0)
                continue;
            if (airport == ed)
                break;

            for (Edge edge : graph.getEdgeList(airport, time)) {
                Integer next = edge.next;
                Time arrive = Time.getNextTime(edge.arrive, edge.departure, time, connectTimeList.get(next), next == ed);
                pushDist(pq, dist, airport, next, arrive, edge.flight);
            }
        }

        return dist;
    }

    private Itinerary getItinerary(Integer st, Integer ed, Time departure, DistData[] dist) {
        Integer now = ed;
        if (dist[now].airport == -1)
            return new Itinerary(false);

        Itinerary ret = new Itinerary(true);
        while (dist[now].airport != -1) {
            ret.addFront(dist[now].flight);
            now = dist[now].airport;
        }

        return ret;
    }

}
