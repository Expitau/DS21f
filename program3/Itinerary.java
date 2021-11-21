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

  public void print() {
    if(hasValue){
      for(Flight flight : flights) System.out.print(flight.toString());
      System.out.println("");
    }
    else System.out.println("No Flight Schedule Found.");
  }

}
