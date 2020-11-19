package ProgrammingAssignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == items.length) {
            resize(items.length * 2);
        }
        items[N++] = item;

    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int r = StdRandom.uniform(N);
        Item item = items[r];
        items[r] = items[--N];
        if (N > 0 && N == items.length / 4) {
            resize(items.length / 2);
        }
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int r = StdRandom.uniform(N);
        return items[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] randomOrder = new int[N];
        private int current = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < N; i++) {
                randomOrder[i] = i;
            }
            StdRandom.shuffle(randomOrder);
        }

        @Override
        public boolean hasNext() {
            return current < N;
        }

        @Override
        public Item next() {
            if (current == N) {
                throw new NoSuchElementException();
            }
            return items[randomOrder[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(462);
        rq.enqueue(936);
        rq.enqueue(654);
        for (int i : rq) {
            System.out.println(i);
        }
    }

}