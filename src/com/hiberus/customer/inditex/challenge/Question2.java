package com.hiberus.customer.inditex.challenge;

class Base2 {
    public void Print() {
        System.out.println("Base");
    }
}
class Derived2 extends Base2 {
    public void Print() {
        System.out.println("Derived");
    }
}
class Question2{
    public static void DoPrint( Base2 o ) {
        o.Print();
    }
    public static void main(String[] args) {
        Base2 x = new Base2();
        Base2 y = new Derived2();
        Derived2 z = new Derived2();
        DoPrint(x);
        DoPrint(y);
        DoPrint(z);
    }

    /** Trace shows :
        Base
        Derived
        Derived
     */
}

