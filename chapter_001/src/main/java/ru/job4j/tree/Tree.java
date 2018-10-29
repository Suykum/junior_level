package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount;
    private int numberOfNodes;

    public Tree(E root) {
        this.root = new Node<>(root);
        numberOfNodes = 1;
    }

   public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        boolean binary = true;
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                binary = false;
                break;
            } else {
                for (Node<E> n : el.leaves()) {
                    data.offer(n);
                }
            }
        }
        return binary;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean addOperation = true;
        Optional<Node<E>> result = findBy(parent);
        Optional<Node<E>> isChildExist = findBy(child);
        if (result.isPresent()) {
            for (Node<E> n : result.get().leaves()) {
                if (n.eqValue(child)) {
                    addOperation = false;

                }
            }
        }
        if (addOperation && !(isChildExist.isPresent())) {
            result.get().add(new Node<>(child));
            modCount++;
            numberOfNodes++;
        }
        return addOperation;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private List<Node<E>> nodeList = new ArrayList<>();
            private int expectedModCounter = modCount;
            private int index = 0;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCounter) {
                    throw new ConcurrentModificationException();
                }
                return index < numberOfNodes;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                flatten(root);
                return nodeList.get(index++).getNodeValue();
            }
            private void flatten(Node<E> node) {
                if (node != null) {
                    nodeList.add(node);
                    Optional<List<Node<E>>> nodeList2 = Optional.ofNullable(node.leaves());
                    if (nodeList2.isPresent()) {
                        for (Node<E> n : nodeList2.get()) {
                            if (n != null) {
                                flatten(n);
                            }
                        }
                    }
                }
            }
        };


    }

}
