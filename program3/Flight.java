// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight {
  String start, end;
  Time departure, arrival;

  // constructor
  public Flight(String src, String dest, String stime, String dtime) {
    this.start = src;
    this.end = dest;
    this.departure = Time.parseTime(stime);
    this.arrival = Time.parseTime(dtime);
  }

  public String toString() {
    return String.format("[%s->%s:%s->%s]", start, end, departure, arrival);
  }

  public void print() {
    System.out.println(this);
  }

}
