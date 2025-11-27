package org.example.stack;

import java.util.ArrayList;

public class CacheFriendlyStack<T> implements Stack<T> {

    private final ArrayList<T> list;

    public CacheFriendlyStack(){
        this.list = new ArrayList<>();
    }

    @Override
    public void push(T element) {
        this.list.add(element);
    }

    @Override
    public T pop() {
        final int topOfStackIdx = getTopOfStackIndex();
        if (topOfStackIdx < 0) {
            return null;
        }
        final T value = this.list.get(topOfStackIdx);
        this.list.remove(topOfStackIdx);
        return value;
    }

    @Override
    public T peek() {
        final int topOfStackIdx = getTopOfStackIndex();
        if (topOfStackIdx < 0) {
            return null;
        }
        return this.list.get(topOfStackIdx);
    }

    @Override
    public long size() {
        return this.list.size();
    }

    private int getTopOfStackIndex() {
        if (this.list.isEmpty()) {
            return -1;
        }
        return this.list.size() -1;
    }
}
