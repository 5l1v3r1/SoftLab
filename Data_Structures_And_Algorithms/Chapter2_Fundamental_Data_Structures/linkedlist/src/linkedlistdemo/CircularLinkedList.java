package linkedlistdemo;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class CircularLinkedList<E> {
    private int size = 0;
    private Node<E> tail = null;

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E first(){
        if (isEmpty()) return  null;
        return tail.getNext().getElement();
    }
    public E last(){
        if (isEmpty()) return null;
        return tail.getElement();
    }
    public void addFirst(E element){
        if (size == 0){
            tail = new Node<E>(element,null);
            tail.setNext(tail);
        }else {
            Node<E> newest = new Node<E>(element,tail.getNext());
            tail.setNext(newest);
        }
        size ++;

    }
    public void addLast(E element){
        addFirst(element);
        tail = tail.getNext();
        size ++;
    }
    public E removeFirst(){
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail){
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size --;
        return head.getElement();
    }

    public void rotate(){
        if (tail !=null){
            tail = tail.getNext();
        }
    }

    // node class
    public static class Node<E>{
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> node) {
            this.element = element;
            this.next = node;
        }

        public E getElement(){
            return element;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
