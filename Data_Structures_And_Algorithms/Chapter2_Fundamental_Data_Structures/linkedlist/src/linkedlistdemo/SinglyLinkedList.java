package linkedlistdemo;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class SinglyLinkedList<E> {
    private int size = 0;
    private Node<E> head = null;
    private Node<E> tail = null;

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E first(){
        if (isEmpty()) return  null;
        return head.getElement();
    }
    public E last(){
        if (isEmpty()) return null;
        return tail.getElement();
    }
    public void addFirst(E element){
        head = new Node<E>(element,head);
        if (size == 0){
            tail = head;
        }
        size ++;

    }
    public void addLast(E element){
        Node<E> newest = new Node<E>(element,null);
        if(isEmpty()){
            head = newest;
        }else {
            tail.setNext(newest);
        }
        tail = newest;
        size ++;
    }
    public E removeFirst(){
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size --;
        if (size == 0){
            tail = null;
        }
        return answer;
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
