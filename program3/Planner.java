// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

public class Planner {
  private int size;
  private HashMap<String, Integer> code2Int;
  private ArrayList<String> int2Code;
  private ArrayList<Time> connectTimeList;
  private Graph graph;

  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList){
      mapAirportCode(portList);
      makeGraph(fltList);
  }

  private void mapAirportCode(LinkedList<Airport> portList){
      size = portList.size();
      connectTimeList = new ArrayList<>();
      code2Int = new HashMap<>();
      int2Code = new ArrayList<>();

      int i=0;
      for(Airport airport : portList){
          code2Int.put(airport.code, i++);
          int2Code.add(airport.code);
          connectTimeList.add(airport.time);
      }
  }

  private void makeGraph(LinkedList<Flight> fltList){
    graph = new Graph(size);
    for(Flight flight : fltList){
      Integer s = code2Int.get(flight.start);
      Integer e = code2Int.get(flight.end);
      Time d = flight.departure;
      Time a = flight.arrival;
      if(d.compareTo(a) <= 0) a.addD(1);
      graph.addEdge(s, e, d, a, flight);
    }
  }

  public Itinerary Schedule(String start, String end, String departure){
      Integer st = code2Int.get(start), ed = code2Int.get(end);
      Time dep = Time.parseTime(departure);
      List<DistData> dist = getDist(st, ed, dep);
      return getItinerary(st, ed, dep, dist);
  }

  private void pushDist(PriorityQueue<DistData> pq, ArrayList<DistData> dist, Integer now, Integer next, Time time, Flight flight){
      if(dist.get(next).time.compareTo(time) < 0) return;
      dist.set(next, new DistData(now, time, flight));
      pq.add(new DistData(next,time));
  }

  private List<DistData> getDist(Integer st, Integer ed, Time de){
      PriorityQueue<DistData> pq = new PriorityQueue<>();
      ArrayList<DistData> dist = new ArrayList<DistData>();
      for(int i=0; i<size; i++) dist.add(new DistData(Integer.valueOf(-1), new Time(-1, -1, -1))); 

      pushDist(pq, dist, -1, st, de, null);

      while(!pq.isEmpty()){
          DistData now = pq.poll(); // return top and delete value
          for(Edge edge : graph.getEdge(now.airport)){
              Integer next = edge.next;
              Time arrive = new Time(edge.arrive);
              Time departure = new Time(edge.departure);
              arrive.add(connectTimeList.get(next));
              arrive.addD(Time.getDelayDay(now.time, departure));
              pushDist(pq, dist, now.airport, next, arrive, edge.flight);
          }
      }

      return dist;
  }

  private Itinerary getItinerary(Integer st, Integer ed, Time departure, List<DistData> dist){
      Integer now = ed;
      if(dist.get(now).time.d == -1) return new Itinerary(); 

      Itinerary ret = new Itinerary(int2Code.get(st), int2Code.get(ed), departure, dist.get(now).time);
      while(now != -1){
          ret.addFront(dist.get(now).flight);
          now = dist.get(now).airport;
      }
      
      return ret;
  }

}

