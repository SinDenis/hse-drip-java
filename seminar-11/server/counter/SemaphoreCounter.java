package server.counter;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter implements CommandCounter {
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
