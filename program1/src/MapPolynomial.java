import java.util.*;

public class MapPolynomial {
  private TreeMap<Integer, Integer> mp;
  
  // Create an empty polynomial
  public MapPolynomial() {
    mp = new TreeMap<>(Collections.reverseOrder());
  }

  // Create a single-term polynomial
  public MapPolynomial(int coef, int exp) {
    mp = new TreeMap<>(Collections.reverseOrder());
    mp.put(exp, coef);
  }
  
  public void add_one(int coef, int exp){
    if(mp.containsKey(exp)){
      coef += mp.get(exp);
      if(coef == 0) mp.remove(exp);
    }
    if(coef != 0) mp.put(exp, coef);
  }

  // Add opnd to 'this' polynomial; 'this' is returned
  public MapPolynomial add(MapPolynomial opnd) {
    Iterator<Integer> it = opnd.mp.keySet().iterator();
    while(it.hasNext()){
      int key = it.next();
      add_one(opnd.mp.get(key), key);
    }
    return this;
  }

  // Subtract opnd from 'this' polynomial; 'this' is returned
  public MapPolynomial sub(MapPolynomial opnd) {
    Iterator<Integer> it = opnd.mp.keySet().iterator();
    while(it.hasNext()){
      int key = it.next();
      add_one(-opnd.mp.get(key), key);
    }
    return this;
  }

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
  public void print() {
    Iterator<Integer> it = mp.keySet().iterator();
    while(it.hasNext()){
      int key = it.next();
      System.out.print(mp.get(key)+" "+key+" ");
    }
  }
}
