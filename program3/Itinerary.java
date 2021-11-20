// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.*;

public class Itinerary
{
  private String start, end;
  private Time departure, arrival;
  private LinkedList<Flight> flights = new LinkedList<>();
  public boolean hasValue = false;
  // constructor
  public Itinerary(){
  }

  public Itinerary(String st, String ed, Time d, Time a){
    start = st;
    end = ed;
    departure = d;
    arrival = a;
    hasValue = true;
  }

  public boolean isFound() {
    return hasValue;
  }

  public void addFront(Flight flight){
    flights.addFirst(flight);
    hasValue = true;
  }

  public String toString(){
    
    String ret = "";
    if(hasValue) for(Flight flight : flights) ret += flight.toString();
    else ret = "No Flight Schedule Found."
    return ret;
  }

  public void print() {
    System.out.println(this);
  }

}
