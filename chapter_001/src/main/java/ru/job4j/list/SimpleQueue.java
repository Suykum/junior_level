package ru.job4j.list;

public class SimpleQueue<E> {
    private SimpleStack<E> inputStack;
    private SimpleStack<E> outputStack;

    public SimpleQueue() {
        inputStack = new SimpleStack<>();
        outputStack = new SimpleStack<>();
    }

    public void push(E value) {
        inputStack.push(value);
    }

    private void transferToOutput() {
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.poll());
        }
    }

    public E poll() {
        if (outputStack.isEmpty()) {
            transferToOutput();
        }
        return outputStack.poll();
    }
}
