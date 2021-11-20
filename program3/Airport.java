// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport
{
  public String code;
  public Time time;


  public Airport(String port, String connectTime) {
    code = port;
    this.time = Time.parseTime(connectTime);
  }

  public void print() {
    
  }

}
