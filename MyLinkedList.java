package hus.oop.statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
    }

    @Override
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void add(double data) {
        if (top == null) {
            top = new MyNode(data);
            return;
        }

        MyNode current = top;
        while (current.next != null) {
            current = current.next;
        }

        MyNode newNode = new MyNode(data, null, current);
        current.next = newNode;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (index == 0) {
            MyNode newNode = new MyNode(data, top, null);
            if (top != null) {
                top.previous = newNode;
            }
            top = newNode;
            return;
        }

        MyNode current = getNodeByIndex(index - 1);
        MyNode newNode = new MyNode(data, current.next, current);
        if (current.next != null) {
            current.next.previous = newNode;
        }
        current.next = newNode;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (index == 0) {
            top = top.next;
            if (top != null) {
                top.previous = null;
            }
            return;
        }

        MyNode nodeToRemove = getNodeByIndex(index);
        nodeToRemove.previous.next = nodeToRemove.next;
        if (nodeToRemove.next != null) {
            nodeToRemove.next.previous = nodeToRemove.previous;
        }
    }

    @Override
    public MyLinkedList sortIncreasing() {
        MyLinkedList sortedList = new MyLinkedList();

        MyNode current = top;
        while (current != null) {
            sortedList.add(current.data);
            current = current.next;
        }

        int size = sortedList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                MyNode node1 = sortedList.getNodeByIndex(j);
                MyNode node2 = sortedList.getNodeByIndex(j + 1);

                if (node1.data > node2.data) {
                    double temp = node1.data;
                    node1.data = node2.data;
                    node2.data = temp;
                }
            }
        }
        return sortedList;
    }

    @Override
    public int binarySearch(double data) {
            MyLinkedList sortedList = sortIncreasing();
            return sortedList.binarySearchHelper(data, 0, sortedList.size() - 1);
    }

    private int binarySearchHelper(double data, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        MyNode midNode = getNodeByIndex(mid);

        if (midNode.data == data) {
            return mid;
        }

        if (midNode.data > data) {
            return binarySearchHelper(data, left, mid - 1);
        }

        return binarySearchHelper(data, mid + 1, right);
    }

    /**
     * Tạo iterator để cho phép duyệt qua các phần tử của list.
     * @return
     */
    @Override
    public MyIterator iterator(int start) {
        return new MyLinkedListIterator(start);
    }

    /**
     * Lấy node ở vị trí index.
     * @param index
     * @return
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private class MyLinkedListIterator implements MyIterator {
        /*
         * Vị trí hiện tại của iterator trong list.
         */
        private int currentPosition;
        private MyNode currentNode;
        /**
         * Khởi tạo cho iterator ở vị trí position trong MyLinkedList.
         * @param position
         */
        public MyLinkedListIterator(int position) {
            if (position < 0 || (position > 0 && position >= size())) {
                throw new IndexOutOfBoundsException("Position: " + position);
            }
            currentPosition = position;
            currentNode = position < size() ? getNodeByIndex(position) : null;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements to iterate");
            }

            double data = currentNode.data;
            currentNode = currentNode.next;
            currentPosition++;
            return data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(--currentPosition);
            currentNode = getNodeByIndex(currentPosition);
        }
    }
}
