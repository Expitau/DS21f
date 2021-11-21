public class DistData implements Comparable<DistData> {
    public Integer airport;
    public Time time;
    public Flight flight;

    public DistData(Integer airport, Time time) {
        this.airport = airport;
        this.time = time;
    }

    public DistData(Integer airport, Time time, Flight flight) {
        this.airport = airport;
        this.time = time;
        this.flight = flight;
    }

    @Override
    public int compareTo(DistData d) {
        return time.compareTo(d.time);
    }
}
