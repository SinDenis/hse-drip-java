package cow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("MyCopyOnWriteArrayList — concurrency / JMM")
class MyCopyOnWriteArrayListConcurrencyTest {

    @Test
    @DisplayName("параллельные add не теряют элементы (Monitor Lock Rule)")
    void parallelAddsDoNotLoseElements() throws Exception {
        int writers = 8;
        int addsPerWriter = 5_000;
        int expected = writers * addsPerWriter;

        MyCopyOnWriteArrayList<Integer> list = new MyCopyOnWriteArrayList<>();
        runConcurrent(writers, 0, list, addsPerWriter);

        assertEquals(expected, list.size(),
                "потерянные обновления — мутации идут не под одним monitor lock");
    }

    /**
     * Запускает N writer-ов и M reader-ов одновременно через start-gate.
     * Каждый writer делает addsPerWriter вызовов add(int). Каждый reader
     * крутится в цикле size()/get(size-1), пока writer-ы не закончат.
     * Возвращает первую ошибку, которую поймал любой из reader-ов.
     */
    private static AtomicReference<Throwable> runConcurrent(
            int writers, int readers,
            MyCopyOnWriteArrayList<Integer> list,
            int addsPerWriter) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(writers + Math.max(readers, 1));
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch writersDone = new CountDownLatch(writers);
        AtomicReference<Throwable> firstError = new AtomicReference<>();

        for (int w = 0; w < writers; w++) {
            final int writerId = w;
            pool.submit(() -> {
                try {
                    start.await();
                    for (int i = 0; i < addsPerWriter; i++) {
                        list.add(writerId * addsPerWriter + i);
                    }
                } catch (Throwable t) {
                    firstError.compareAndSet(null, t);
                } finally {
                    writersDone.countDown();
                }
            });
        }

        for (int r = 0; r < readers; r++) {
            pool.submit(() -> {
                try {
                    start.await();
                    while (writersDone.getCount() > 0) {
                        int s = list.size();
                        if (s > 0) {
                            list.get(s - 1);
                        }
                    }
                } catch (Throwable t) {
                    firstError.compareAndSet(null, t);
                }
            });
        }

        start.countDown();
        assertTrue(writersDone.await(15, TimeUnit.SECONDS), "writers timed out");
        pool.shutdownNow();
        assertTrue(pool.awaitTermination(5, TimeUnit.SECONDS), "pool did not shut down");
        return firstError;
    }
}
