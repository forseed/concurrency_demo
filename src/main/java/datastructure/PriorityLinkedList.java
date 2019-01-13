package datastructure;

public class PriorityLinkedList<E extends Comparable<E>> {
    private int size;
    private Node<E> first;

    public PriorityLinkedList() {
        size = 0;
        first = null;
    }

    private static class Node<E> {
        Node<E> next;
        E value;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            }
            return "null";
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elements) {
        PriorityLinkedList<E> linkedList = new PriorityLinkedList<>();
        if (elements.length > 0) {
            for (E e : elements) {
                linkedList.addFirst(e);
            }
        }
        return linkedList;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> preNode = null;
        Node<E> currentNode = first;
        while (currentNode != null && e.compareTo(currentNode.value) > 0) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }

        if (preNode == null) {
            first = newNode;
        } else {
            preNode.next = newNode;
        }
        newNode.next = currentNode;
        size++;
        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (current != null) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("err;PriorityLinkedList is empty");
        }
        Node<E> oldNode = first;
        first = oldNode.next;
        size--;
        return oldNode.value;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            builder.append(current.toString()).append(",");
            current = current.next;
        }
        builder.replace(builder.length() - 1, builder.length(), "]");
        return builder.toString();
    }

    public static void main(String[] args) {
        PriorityLinkedList<Integer> linkedList = PriorityLinkedList.of(1,3,-1,2,7,5,4);
        System.out.println(linkedList);

    }
}
