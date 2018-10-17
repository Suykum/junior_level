package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContainerOnLinkList<E> implements Iterable<E> {
    private Node<E> first;
    private int modCount;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void addFirst(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        modCount++;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }
    public E deleteFirst() {
        Node<E> temp;
        if (first == null) {
            return null;
        } else {
            temp = first;
            first = first.next;
        }
        return temp.date;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCounter = modCount;
            private Node<E> current = first;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCounter) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> previous = current;
                current = current.next;
                return previous.date;
            }
        };
    }
}
