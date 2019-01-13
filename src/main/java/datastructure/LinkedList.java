package datastructure;

public class LinkedList<E> {
    private int size;
    private Node<E> first;

    public LinkedList() {
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

    public static <E> LinkedList<E> of(E... elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        if (elements.length > 0) {
            for (E e : elements) {
                linkedList.addFirst(e);
            }
        }
        return linkedList;
    }

    public LinkedList<E> addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = first;
        first = newNode;
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
            throw new RuntimeException("err;linkedList is empty");
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
        LinkedList<String> linkedList = LinkedList.of("hello", "java", "netty", "nio", "aio");
        linkedList.addFirst("websocket").addFirst("linux");
        System.out.println(linkedList);
        while (!linkedList.isEmpty()){
            System.out.println(linkedList.removeFirst());
        }
    }
}
