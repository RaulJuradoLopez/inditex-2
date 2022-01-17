package com.hiberus.customer.inditex.challenge;

final class Question5 {

    private double re, im;
    public Question5(double re, double im) {
        this.re = re;
        this.im = im;
    }
    Question5(Question5 c)
        {
            System.out.println("Copy constructor called");
            re = c.re;
            im = c.im;
        }
        public String toString() {
            return "(" + re + " + " + im + "i)";
        }
    }
    class Main5 {
        public static void main(String[] args) {
            Question5 c1 = new Question5(10, 15);
            Question5 c2 = new Question5(c1);
            Question5 c3 = c1;
            System.out.println(c2);
        }
        /** The c3 object is a reference, so we are not using the Object constructor
         * Trace :
         * Copy constructor called
         * (10.0 + 15.0i)
         */
    }

