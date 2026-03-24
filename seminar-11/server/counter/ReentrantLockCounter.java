package server.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements CommandCounter {
    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int get() {
        return count;
    }
}
