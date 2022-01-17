package com.hiberus.customer.inditex.challenge;

class Base1 {
   //final public void show() { --> If it's declared as final can not override
    public void show() {
        System.out.println("Base::show() called");
    }
}
class Derived1 extends Base1 {
    public void show() {
        System.out.println("Derived::show() called");
    }
}
class Main {
    public static void main(String[] args) {
        Base1 b = new Derived1();;
        b.show();
    }
}
