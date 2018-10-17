package ru.job4j.list;

public class SimpleStack<E> {
    private ContainerOnLinkList<E> stack;
    public SimpleStack() {
        stack = new ContainerOnLinkList<>();
    }
    public void push(E value) {
        stack.addFirst(value);
    }
    public E poll() {
        return stack.deleteFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

