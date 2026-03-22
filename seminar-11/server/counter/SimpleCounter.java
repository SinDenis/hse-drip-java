package server.counter;

public class SimpleCounter implements CommandCounter {
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
