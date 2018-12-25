public class LinkedListDeque<T> {

    private class ListNode<T>{
        T item;
        ListNode<T> next;
        ListNode<T> prev;

        ListNode() {
        }

        ListNode(T item) {
            this.item = item;
        }
    }
    private int size;
    private ListNode<T> sentinel;
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode<T>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addFirst(T item) {
        ListNode node = new ListNode(item);
        if (size == 0) {
            sentinel.next = node;
            sentinel.prev = node;
            node.next = sentinel;
            node.prev = sentinel;
        } else {
            ListNode beforeFirstNode = sentinel.next;
            sentinel.next = node;
            node.next = beforeFirstNode;
            node.prev = sentinel;
            beforeFirstNode.prev = node;
        }
        size += 1;
    }

    public void addLast(T item) {
        ListNode node = new ListNode(item);
        if (size == 0) {
            sentinel.next = node;
            sentinel.prev = node;
            node.next = sentinel;
            node.prev = sentinel;
        } else {
            ListNode beforeLastNode = sentinel.prev;
            sentinel.prev = node;
            node.prev = beforeLastNode;
            node.next = sentinel;
            beforeLastNode.next = node;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode<T> iter = sentinel.next;
        while (iter != sentinel) {
            System.out.print(iter.item + " ");
            iter = iter.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        ListNode<T> firstNode = sentinel.next;
        ListNode<T> secondNode = firstNode.next;

        sentinel.next = secondNode;
        secondNode.prev = sentinel;
        size = size - 1;
        return firstNode.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        ListNode<T> lastNode = sentinel.prev;
        ListNode<T> secondLastNode = lastNode.next;

        sentinel.prev = secondLastNode;
        secondLastNode.next = sentinel;
        size = size - 1;
        return lastNode.item;
    }

    public T get(int index) {
        ListNode<T> iter = sentinel.next;
        while (index >= 0) {
            if (index == 0) {
                return iter.item;
            } else {
                iter = iter.next;
                index = index - 1;
            }
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecur(index, sentinel.next);
    }

    private T getRecur(int index, ListNode<T> node) {
        if (node == null) return null;
        if (index == 0) return node.item;

        return getRecur(index-1, node.next);
    }
}

