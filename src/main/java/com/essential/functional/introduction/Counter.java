package com.essential.functional.introduction;

public final class Counter {
    private final int count;

    public Counter() {
        this.count = 0;
    }

    private Counter(int count) {
        this.count = count;
    }

    // Pure function: returns a new Counter without modifying the current state
    public Counter increment() {
        return new Counter(this.count + 1);
    }

    public int getCount() {
        return count;
    }
}
