package com.hiberus.customer.inditex.challenge;

class Question8 {
    public static void swap(Integer i, Integer j) {
        Integer temp = new Integer(i);
        i = j;
        j = temp;
    }
    public static void main(String[] args) {
        Integer i = new Integer(10);
        Integer j = new Integer(20);
        swap(i, j);
        System.out.println("i = " + i + ", j = " + j);
        /**
         * i = 10, j = 20
         * We should return the values and assign into the main method if we want to change them
         */
    }
}
