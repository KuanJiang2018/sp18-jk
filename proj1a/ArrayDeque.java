public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    private double ratio() {
        return (double) size / items.length;
    }

    private int minusOne(int index) {
        if (index - 1 < 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    private int plusOne(int index) {
        if (index + 1 >= items.length) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private void resizing(int newLen) {
        if (plusOne(nextFirst) <= minusOne(nextLast)) { // ____xxxx____
            T[] newItems = (T[]) new Object[newLen];
            System.arraycopy(items, plusOne(nextFirst), newItems, 0, size);
            nextFirst = newItems.length - 1;
            nextLast = size;
            items = newItems;
        } else { // xxxx______xxxx
            T[] newItems = (T[]) new Object[newLen];
            System.arraycopy(items, 0, newItems, 0, nextLast);
            System.arraycopy(items, plusOne(nextFirst), newItems, newLen - size + nextFirst, size - nextFirst);
            items = newItems;
            nextFirst = minusOne(newLen - size + nextFirst);
        }
    }

    private boolean resizeHalf() {
        if (items.length >= 16 && ratio() < 0.25) return true;
        return false;
    }

    private boolean resizeDouble() {
        return ratio() == 1;
    }

    public void  addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = plusOne(nextFirst);
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }

    public T removeFirst() {
        int first = plusOne(nextFirst);
        T item = items[first];
        items[first] = null;
        nextFirst = first;
        size = size - 1;
        return item;
    }

    public T removeLast() {
        int last = minusOne(nextLast);
        T item = items[last];
        items[last] = null;
        nextLast = last;
        size = size - 1;
        return item;
    }

    public T get(int index) {
        int i = (index + 1 + nextFirst) % items.length;
        return items[i];
    }
}
