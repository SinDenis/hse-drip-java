package server.counter;

public class SynchronizedCounter implements CommandCounter {
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
