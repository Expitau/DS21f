Program 1 - ADT for Polynomials
====


## Problem Statement 

For this programming assignment, we design an abstract data type (ADT) for single-variable polynomials. A polynomial has zero or more terms, each of which consists of an integer coefficient and an integer exponent. This ADT defines methods for addition and subtraction operations on polynomials as well as constructors and other methods.

## Requirements & Implementation

- You are to write a Java application that implements the Polynomial ADT. A skeleton of the ADT is provided at eTL. The skeleton file is named `PolynomialSkel.java`. You can copy it to your working directory and rename it to `Polynomial.java`. You should then provide implementations of all the methods defined in the class skeleton. You can add more methods as you wish, but are not allowed to remove any method defined in the class skeleton.
- No `main()` method is necessary in your class implementation. We will test and evaluate your class implementation with our own test program (`MainPoly.java`) containing the `main()` method, which is provided at eTL. This test program uses TextInputStream class for input and output, which is also provided. So, there is no need to worry about I/O and no need to turn in any additional class containing the `main()` method.
- Each input file contains two polynomials connected by an operator. Each polynomial is represented by a sequence of (coefficient, exponent) pairs. For example, a polynomial $x^3 + 2x + 4x + 6x^7$ is encoded as '1 3 2 1 4 1 6 7' in the input file. Note that the polynomial terms are not necessarily sorted in any particular order in the input file. However, when a polynomial is printed by the `print` method, the terms must be printed out in decreasing order of exponents. Although two or more terms with the same exponent may appear in an input polynomial, an output polynomial must have only one term for a distinct exponent with a non-zero coefficient.
- For any term in a polynomial, its coefficient and exponent are an integer that can be represented by a 32-bit integer variable. The number of terms in a polynomial can only be limited by the memory capacity of your Java Virtual Machine.


## My Implementation

I made two version of Polynomial class, `Polynomial` and `MapPolynomial`.

First, `Polynomial` uses ArrayList to store data and directrly push data given by constructor, `add` and `sub` methods.
And there are method, `cleanup`, which make unique ArrayList given by above Requirements. So in `print` method calls `cleanup` before print polynomial.

Second, `MapPolynomial` uses map and obviously, it uses Exp as key and Coef as value. `add`and `sub` call `add_once` for each of operender's element.

I decided to make `Polynomial` with ArrayList because `MainPoly` only call `print` onec but `add` and `sub` many time.

So total time complexity is O(n log n) and space complexity is O(n)

By the way `MapPolynomial`'s time complexity is O(n log n) (`add_once`'s cost if O(log n)) and space complexity is O(n log n)

more details is described in code comments.