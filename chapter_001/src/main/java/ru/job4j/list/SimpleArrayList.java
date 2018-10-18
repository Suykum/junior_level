package ru.job4j.list;

public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    public SimpleArrayList() {
        size = 0;
        first = null;
    }

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E deleteFirst() {
        Node<E> temp;
        if (size == 0) {
            return null;
        } else {
            temp = first;
            first = first.next;
            size--;
        }
        return temp.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
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

    public boolean hasCycle() {

        Node<E> fast = first;
        Node<E> slow = first;
        while(fast!= null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow ){
                return true;
            }
        }
        return false;
    }

    public void setNext(E key) {
        Node<E> node = getNode(key);
        node.next = first;
    }

    public Node getNode(E key) {
        Node<E> current = first;
        while (current != null) {
            if (current.date == key) {
                break;
            }
            current = current.next;
        }
        return current;
    }
}
