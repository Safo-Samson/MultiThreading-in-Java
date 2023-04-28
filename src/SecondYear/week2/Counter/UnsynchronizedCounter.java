package SecondYear.week2.Counter;

import SecondYear.week2.Counter.Counter;

public class UnsynchronizedCounter implements Counter {
    private int c = 0;
    public void increment() {
        this.c++;
    }
    public void decrement() {
        this.c--;
    }
    public int value() {
        return this.c;
    }
}

