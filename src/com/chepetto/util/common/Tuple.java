package com.chepetto.util.common;

public class Tuple<A, B, T> {
    A first;
    B second;
    T third;

    public Tuple(A first, B second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }
}
