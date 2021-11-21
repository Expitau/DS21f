// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.*;

public class Itinerary
{
  private LinkedList<Flight> flights = new LinkedList<>();
  public boolean hasValue = false;

  public Itinerary(){}

  public Itinerary(boolean value){hasValue = value;}


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
    else ret = "No Flight Schedule Found.";
    return ret;
  }

  public void print() {
    System.out.println(this);
  }

}
