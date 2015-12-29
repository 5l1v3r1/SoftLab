package linkedlistdemo;

/**
 * Why linked list instead of Array?
 * ---------------------------------
 * - In array, capacity must be fixed at the time of creation.
 * - In array, insertion/deletion at interior position is time consuming
 *
 * What is linked list ?
 * ---------------------
 * A linked list is collection of nodes that collectively forms a liner sequence.
 *
 * Types of linked list
 * --------------------
 *  - Singly Linked List
 *  - Circular Linked list
 *  - Doubly Linked list
 *
 *  Singly Linked List
 *  -------------------
 *  In singly linked list, each node stores a reference to an object that is an element
 *  of that sequence as well as a reference to the next node of list.
 *
 *  Head: Starting node of list
 *  Tail: Ending node of list
 *
 *  node0----->node1----->node2
 *
 *  Circular Linked List
 *  --------------------
 *  - Can be used to efficiently implement round-robin algorithm.
 *  - It is essentially a singly linked list in which the next reference of the tail
 *    node is set to refer back to the head of the list rather than null.
 *
 *    node0---->node1----->node2----->node0
 *
 *  Doubly Linked List
 *  ------------------
 *  - In singly linked list, we can insert node at either end of a list and can delete
 *    a node at the head of a list but hardly delete at the tail or at interior position
 *    efficiently.
 *  - Doubly linked list has facility to deleter/update/insert nodes at any position efficiently.
 *
 *  - Sentinels are used in the implementation of doubly linked list to make consistent insertion/
 *    deletion at interior position though can be implemented this without sentinels
 *
 *  node0<---->node1<---->node2
 *
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Singly Linked List demo running....");
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<String>();

        System.out.println("Addming two elements at first position...");
        singlyLinkedList.addFirst("Hikmat");
        singlyLinkedList.addFirst("Ram");

        System.out.println("First element of list: " + singlyLinkedList.first());
        System.out.println("Last element of list: " + singlyLinkedList.last());

        System.out.println("Adding two elements at last");
        singlyLinkedList.addLast("Hari");
        singlyLinkedList.addLast("Hari");

        System.out.println("Removed element: " +  singlyLinkedList.removeFirst());
        System.out.println("Size: " + singlyLinkedList.size());





        System.out.println("Circular Linked List demo running....");
        CircularLinkedList<String> circularLinkedList = new CircularLinkedList<String>();

        System.out.println("Addming two elements at first position...");
        circularLinkedList.addFirst("Hikmat");
        circularLinkedList.addFirst("Ram");

        System.out.println("First element of list: " + circularLinkedList.first());
        System.out.println("Last element of list: " + circularLinkedList.last());

        System.out.println("Removed element: " +  circularLinkedList.removeFirst());

        System.out.println("Adding two elements at last");
        circularLinkedList.addLast("Hari");
        circularLinkedList.addLast("Krishna");


        System.out.println("Rotating by on position..");
        circularLinkedList.rotate();
        System.out.println("Now last element is: " + circularLinkedList.last());

        System.out.println("Size: " + singlyLinkedList.size());


        System.out.println("Doubly Linked List demo running....");
        DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<String>();

        System.out.println("Addming two elements at first position...");
        doublyLinkedList.addFirst("Hikmat");
        doublyLinkedList.addFirst("Ram");

        System.out.println("First element of list: " + doublyLinkedList.first());
        System.out.println("Last element of list: " + doublyLinkedList.last());

        System.out.println("Removed element: " +  doublyLinkedList.removeFirst());

        System.out.println("Adding two elements at last");
        doublyLinkedList.addLast("Hari");
        doublyLinkedList.addLast("Krishna");

        System.out.println("Now last element is: " + doublyLinkedList.last());

        System.out.println("Size: " + doublyLinkedList.size());
    }
}
