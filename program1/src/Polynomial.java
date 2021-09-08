import java.util.*;

public class Polynomial extends ArrayPolynomial {
  // Create an empty polynomial
  public Polynomial() {
    super();
  }

  // Create a single-term polynomial
  public Polynomial(int coef, int exp) {
    super(coef, exp);
  }

  // Add opnd to 'this' polynomial; 'this' is returned
  //public Polynomial add(Polynomial opnd) {}

  // Subtract opnd from 'this' polynomial; 'this' is returned
  //public Polynomial sub(Polynomial opnd) {}

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
  //public void print() {}
}
