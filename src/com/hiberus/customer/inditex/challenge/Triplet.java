package com.hiberus.customer.inditex.challenge;

import java.util.*;

// We are going to store the 3 tuple nodes with this class
class Triplet<I extends Number, I1 extends Number, I2 extends Number> implements Comparator<Integer>{
    public final Integer first;       // the first node of a triplet
    public final Integer second;      // the second node of a triplet
    public final Integer third;       // the third node of a triplet

    // Constructs a new triplet with the given values
    private Triplet(Integer first, Integer second, Integer third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        /* Checks specified object is "equal to" the current object or not */

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triplet<Number, Number, Number> triplet = (Triplet<Number, Number, Number>) o;

        // call `equals()` method of the underlying objects
        if (!first.equals(triplet.first) ||
                !second.equals(triplet.second) ||
                !third.equals(triplet.third)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        /* Computes hash code for an object by using hash codes of
        the underlying objects */

        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }



    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

    // Factory method for creating a typed immutable instance of triplet
    public static Triplet <Integer, Integer,Integer > of(Integer a, Integer b, Integer c) {

        // Key point here : We are sorting the 3 points to evict duplicated
        // {1, 3, 5} and {1 ,5, 3}
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(a);
        values.add(b);
        values.add(c);
        Collections.sort(values);

        return new Triplet <>(values.get(0), values.get(1), values.get(2));
    }
}


