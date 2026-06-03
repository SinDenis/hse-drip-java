package cow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyCopyOnWriteArrayList<E> implements Iterable<E> {

    private volatile Object[] array = new Object[0];

    private final Object lock = new Object();

    public int size() {
        return array.length;
    }

    public E get(int index) {
        Object[] snapshot = array;
        return (E) snapshot[index];
    }

    public boolean add(E element) {
        synchronized (lock) {
            Object[] current = array;
            Object[] next = Arrays.copyOf(current, current.length + 1);
            next[current.length] = element;
            array = next;
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        synchronized (lock) {
            Object[] current = array;
            if (index < 0 || index >= current.length) {
                throw new IndexOutOfBoundsException(index);
            }
            Object[] next = current.clone();
            E previous = (E) next[index];
            next[index] = element;
            array = next;
            return previous;
        }
    }

    public boolean remove(Object o) {
        synchronized (lock) {
            Object[] current = array;
            int indexToRemove = indexOf(current, o);
            if (indexToRemove < 0) {
                return false;
            }
            Object[] next = new Object[current.length - 1];
            System.arraycopy(current, 0, next, 0, indexToRemove);
            System.arraycopy(current, indexToRemove + 1, next, indexToRemove,
                    current.length - indexToRemove - 1);
            array = next;
            return true;
        }
    }

    private static int indexOf(Object[] elements, Object target) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(target, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        final Object[] snapshot = array;
        return new Iterator<E>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < snapshot.length;
            }

            @Override
            public E next() {
                if (cursor >= snapshot.length) {
                    throw new NoSuchElementException();
                }
                return (E) snapshot[cursor++];
            }
        };
    }
}
