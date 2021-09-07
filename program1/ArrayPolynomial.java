import java.util.*;

public class ArrayPolynomial {
  // ArrayList for store polynomial
  private ArrayList<Pair> poly;
  
  // Create an empty polynomial
  public ArrayPolynomial() {
    this.poly = new ArrayList<>();
  }

  // Create a single-term polynomial
  public ArrayPolynomial(int coef, int exp) {
    this.poly = new ArrayList<>();
    this.poly.add(new Pair(exp, coef));
  }

  // Add opnd to 'this' polynomial; 'this' is returned
  public ArrayPolynomial add(ArrayPolynomial opnd) {
    for(Pair term : opnd.poly) this.poly.add(term);
    return this;
  }

  // Subtract opnd from 'this' polynomial; 'this' is returned
  public ArrayPolynomial sub(ArrayPolynomial opnd) {
    for(Pair term : opnd.poly) this.poly.add(term.getNegative());
    return this;
  }
  
  // Make poly as a unique Polynomial that needs to be printed
  private void cleanup(){
    if(poly.size() == 0) return;
    Collections.sort(poly);
    Integer r = 0;
    for(int i=1; i<this.poly.size(); i++){
      if(this.poly.get(r).getExp() != this.poly.get(i).getExp()){
        if(poly.get(r).getCoef() != 0) r++;
        this.poly.set(r, poly.get(i));
      }else{
        Pair next = this.poly.get(r).getAdd(poly.get(i));
        this.poly.set(r, next);
      }
    }
    if(this.poly.get(r).getCoef() != 0) r++;
    
    if(r < this.poly.size()) this.poly.subList(r, this.poly.size()).clear();
  }

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
  public void print() {
    cleanup();
    for(Pair term : this.poly) term.print();
  }
}

// storeing single term of polynomial
class Pair implements Comparable<Pair> {
  private Integer exp, coef;
  public Pair(){
    exp = 0;
    coef = 0;
  }
  public Pair(int E, int C){
    exp = E;
    coef = C;
  }
  
  public Integer getExp(){
    return this.exp;
  }
  public Integer getCoef(){
    return this.coef;
  }
  
  public Pair getNegative(){
    return new Pair(exp, -coef);
  }
  public Pair getAdd(Pair p){
    return new Pair(this.exp, this.coef + p.getCoef());
  }
  public void print(){
    System.out.print(coef + " " + exp + " ");
  }
  @Override
  public int compareTo(Pair p){
    if(this.exp > p.getExp()) return -1;
    else if(this.exp < p.getExp()) return 1;
    return 0;
  }
}
