public class Edge {
    public Integer now, next;
    public Time departure, arrive;
    public Flight flight;

    public Edge(Integer start, Integer end, Time departure, Time arrive, Flight flight){
        this.now = start;
        this.next = end;
        this.departure = departure;
        this.arrive = arrive;
        this.flight = flight;
    }

    public Edge(Edge e){
        this.now = e.now;
        this.next = e.next;
        this.departure = e.departure;
        this.arrive = e.arrive;
        this.flight = e.flight;
    }
}
