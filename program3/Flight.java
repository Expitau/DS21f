// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight {
  String start, end;
  Time departure, arrival;
  String orgD, orgA;

  // constructor
  public Flight(String src, String dest, String stime, String dtime) {
    this.start = src;
    this.end = dest;
    this.departure = Time.parseTime(stime);
    this.arrival = Time.parseTime(dtime);
    this.orgD = stime;
    this.orgA = dtime;
  }

  public String toString() {
    return "[" + start + "->" + end + ":" + orgD + "->" + orgA + "]";
  }

  public void print() {
    System.out.println(toString());
  }

}
