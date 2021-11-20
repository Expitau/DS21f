# Probramming Assignment 3
# Airline Travel Scheduler

## Problem Statement
Dijkstra's single-source shortest path algorithm


## Requirements & Implementation

two table as input`airport table`, `flight table`

`airport table` : airpots , minimum connecting time

`flight table` : departure and destination airpots, departure time, arrival time.

customer submits `origin airport`, `final destination airport`, `earliest departure time`

Airport names are 3-letter symbols, times are military time

Implement `Airport`, `Flight`, `Itinerary`, `Planner`...


## Airport class
String port
Time connecttime

public Airport(String port, String connectTime)
public void print()

## Flight class
String src, dest
Time stime, dtime

public Flight(String src, String dest, String stime, String dtime)
public void print()

## Itinerary class
Itinerary(...)
public boolean isFound()
public void print()



## Planner class

private TreeMap<String, Integer> code2Int = new TreeMap<>();
private ArrayList<String> int2Code = new ArrayList<>();
private ArrayList<Integer> connectTimeList = new ArrayList<>();

public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList){
    mapAirportCode(portList); // Planner 에서 Map을 이용해서 Airport code -> Integer간의 관계 정립...
    makeGraph(fltList); // Graph fltList를 이용해 Graph class를 생성.
}

private mapAirportCode(LinkedList<Airport> portList){
    int n = portList.length();
    
    connectTimeList = new ArrayList<>();
    TreeMap<String, Integer> code2Int = new TreeMap<>();
    ArrayList<String> int2Code = new ArrayList<>();

    for(Airport airport : portList){
        int2Code.add(airport.code);
        connectTimeList.add(airport.time);
        
    }
}

public Itinerary Schedule(String start, String end, String departure){
    Integer st = code2Int.get(start), ed = code2Int.get(end);
    List<DistData> dist = getDist(st, ed, Time.parseTime(departure));
    return getItineraray(st, ed, dist);
}

private void pushDist(PriorityQueue pq, List<DistData> dist, Integer now, Integer next, Time time){
    if(dist[airport].time <= time) return;
    dist[airport] = new DistDat(now, time);
    pq.push(new Distdata(next,time));
}

private List<DistData> getDist(Integer st, Integer ed, Time departure){
    PriorityQueue pq = new ...;
    List<DistData> dist = new ArrayList<DistData>

    pushDist(pq, dist, -1, st, departure);

    while(!pq.empty()){
        DistData now = pq.pop(); // return top and delete value
        for(EdgeData edge : adj[now.airport]){
            Integer next = edge.next;
            Time arrive = edge.arrive.copy();
        
            if(next != ed) arrive.add(connectTime[ed]);
            if(edge.departure < now.time) arrive.add(new Time(1, 0, 0));
            
            pushDist(pq, dist, now, next, arrive);
        }
    }

    return dist;
}

private Itineraray getItineraray(Integer st, Integer ed, List<DistData> dist){
    Integer now = ed;
    Itineraray ret = new ....;

    if(dist[now].time == null) return ret; 

    while(now != -1){
        ret.add(now, dist[now].time);
        now = dist[now].airport;
    }
    
    return ret;
}

## Time class

## Graph class

A, B, C, D
adj[A] = {B, C, D, ......}

### AdjustData
int next
Time depTime, arvTime;

## PriorityQueue class
priority queue에 들어갈 내용은 다음과 같다. 
해당하는 클래스를 하나 만들어야 됨!!!
(공항 코드(int), 도착 시간 + 연결 시간(이떄 종착점의 경우 연결 시간을 0으로 가정한다.)) -> DistData class

### DistData class
Int
