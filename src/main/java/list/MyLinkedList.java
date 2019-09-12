package list;

class MyLinkedList {
    Node dh;
    Node last;

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this(val, null);
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        dh = new Node(-1);
        last = dh;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        Node cur = dh;
        for (int i = 0; i <= index && cur != null; i++) {
            cur = cur.next;
        }
        return cur == null ? -1 : cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        last.next = new Node(val);
        last = last.next;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        /*查找 the node before index-th node */
        Node prev = index < 0 ? dh : search(index - 1);
        if (prev != null) {
            if (prev.next == null) {
                addAtTail(val);
            } else {
                prev.next = new Node(val, prev.next);
            }
        }
    }

    private Node search(int j) {
        if (j < -1) return null;
        Node cur = dh;
        int i = 0;
        for (; i <= j && cur != null; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0) return;
        Node prev = search(index - 1);
        if (prev != null && prev.next != null) {
            if (last == prev.next) {
                last = prev;
            }
            prev.next = prev.next.next;
        }
    }

    public ListNode middleNode(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast.next!=null){
            slow=slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(4, 2);
        System.out.println(linkedList.get(1));
        linkedList.deleteAtIndex(-1);
        System.out.println(linkedList.get(1));
    }
}