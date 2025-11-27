package org.example.stack;

import org.jetbrains.annotations.Nullable;

public class SimpleStack <T> implements Stack<T> {

    private record Node<T>(T value, Node<T> next) {}

    private SimpleStack.Node<T> head;
    private long stackSize;

    public SimpleStack() {
        this.head = null;
        this.stackSize = 0;
    }

    @Override
    public void push(T element) {
        this.head = new Node<>(element, this.head);
        this.stackSize += 1;
    }

    @Override
    public @Nullable T pop() {
        if (stackSize <= 0) {
            return null;
        }

        stackSize -= 1;
        final T value = this.head.value();
        this.head = this.head.next;

        return value;
    }

    @Override
    public @Nullable T peek() {
        if (stackSize <=0) {
            return null;
        }

        return this.head.value();
    }

    @Override
    public long size() {
        return this.stackSize;
    }
}
