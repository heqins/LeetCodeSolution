package list;

public class Node {
    int val;

    Node prev;

    Node next;

    Node child;

    Node() {}

    Node(int val) { this.val = val; }

    Node(int val, Node next) { this.val = val; this.next = next;}

}
