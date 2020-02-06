package edu.touro.mco264;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList implements List<String> {

    // Private inner class. Static since it doesn't
    // need access to outer class and Nodes should
    // only be used for Linked Lists.
    private static class Node {
        String data;
        Node prev, next;
    }

    private Node head, tail;
    private int size = 0;


    // Constructors make the head a dummy-
    // node: To simplify & clean up code.

    // Default: Constructs an empty list.
    public LinkedList(){
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    //CONVERSION CONSTRUCTOR
    // Constructs a list containing the elements of the specified
    // collection, in the order they are returned by the collection's
    // iterator.
    public LinkedList(Collection<? extends String> c){
        this(); // default constructor sets the head
        addAll(c);
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() { return size == 0;}


    @Override
    public boolean contains(Object o) {
        Node current = head;
        int foundAtIndex = 0;
        int counter = 0;
        while(current.next != tail){
            current = current.next;
            if(current.data.equals(o)) {
                foundAtIndex = counter;
                break;
            }
            counter++;
        }
        // foundAtIndex >= 0 is a condition
        return foundAtIndex >= 0;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    // O(1)
    @Override
    public boolean add(String s) {

        Node newNode = new Node();
        newNode.data = s;
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
        return true;
    }


    //O(N)
    @Override
    public void add(int index, String element) {

        // The head Nodes size is 0. Every time we add a new
        // node we increment size++. So, a Node with element
        // "A" has size 1 but we use size()-1 to reference "A"
        // as if it's in index 0!
        checkIndexInRange(index, true);

        Node newNode = new Node();
        newNode.data = element;

        if (index == 0) {
            addFirst(element);
        }
        else {
            Node spliceNode = getNode(index-1); //O(N)
            newNode.next = spliceNode.next;
            spliceNode.next = newNode;

        }
        size++;
    }


    //O(M x N)
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isTrue = false;
        for(Object o: c){
            isTrue = contains(o);
        }
        return isTrue;
    }


    //O(M)
    @Override
    public boolean addAll(Collection<? extends String> c) {
        for(String s: c){
            add(s);
        }
        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        for (String s: c){
            add(index, s);
            index++;
        }
        return true;
    }


    //O(M x N)
    @Override
    public boolean removeAll(Collection<?> c) {
        if(containsAll(c)){
            for(Object o: c){
                this.remove(o);
            }
            return true;
        }
        else{
            return false;
        }
    }


    //O(M x N)
    // Retains only the elements in this list that are contained
    // in the specified collection (optional operation).
    @Override
    public boolean retainAll(Collection<?> c) {
        if(containsAll(c)){
            for(int i = 0; i < size; i++){
                boolean remove = true;
                for(Object o: c){
                    if(getNode(i).data.equals(o)){
                        remove = false;
                    }
                }
                if(remove){
                    remove(i);
                    i--;
                }
            }
            return true;
        }
        else{
            return false;
        }


    }


    @Override
    public void clear() {
        head = null;
        size = 0;
    }


    //O(N)
    @Override
    public String get(int index) {
        return getNode(index).data;
    }


    private Node getNode(int index) {
        checkIndexInRange(index, false);
        Node current = head.next;
        int counter = 0;
        while (counter < index) {
            current = current.next;
            counter++;
        }
        return current;
    }


    //O(N)
    // Removes the first occurrence of the specified
    // element from this list, if it is present.
    @Override
    public boolean remove(Object o) {
        Node current = head;
        while(current.next != null){
            if(current.next.data.equals(o)){
                current.next = current.next.next;
                current.prev = current.prev.prev;
                break;
            }
            current = current.next;
        }
        size--;
        return true;
    }


    //O(N)
    // Removes the element at the specified position
    // in this list.
    @Override
    public String remove(int index) {
        checkIndexInRange(index, false); //O(1)
        Node current = head;
        String removedElement;

        if (index == 0) {
            removedElement = removeFirst(); //O(1)
        } else {
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            removedElement = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removedElement;
    }


    // Returns the index of the first occurrence
    // of the specified element in this list,
    // or -1 if this list does not contain the element.
    @Override
    public int indexOf(Object o) {
        Node current = head;
        int foundAtIndex = 0;
        int counter = 0;

        while(current.next != tail){
            current = current.next;

            if(current.data.equals(o)) {
                foundAtIndex = counter;
                break;
            }
            counter++;
        }

        if(foundAtIndex >= 0){
            return foundAtIndex;
        }
        else{
            foundAtIndex = -1;
            return foundAtIndex;
        }

    }


    // Returns the index of the last occurrence
    // of the specified element in this list,
    // or -1 if this list does not contain the element.
    @Override
    public int lastIndexOf(Object o) {
        Node current = head;
        int foundAtIndex = 0;
        int counter = 0;

        while(current.next != null){

            // Why does == work here and not .equals()?
            if(current.data == o) {
                foundAtIndex = counter;
            }
            current = current.next;
            counter++;
        }

        if(foundAtIndex >= 0){
            return foundAtIndex;
        }
        else{
            foundAtIndex = -1;
            return foundAtIndex;
        }
    }


    //O(N)
    @Override
    public String set(int index, String element) {
        checkIndexInRange(index,true);
        Node current = head;

        for(int i = 0; i <= index; i++){
            current = current.next;
        }

        return current.data = element;
    }


    private void checkIndexInRange(int index, boolean isSizeLegal) //O(1)
    {
        int maxLegalValue = isSizeLegal ? size() : size()-1;
        if (index > maxLegalValue || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index [%d] is greater than maximum value[%d] List size [&d]",
                    index, maxLegalValue, size()));
    }


    // Returns the first element in
    // this list.
    String getFirst(){
        String firstElement;
        firstElement = head.next.data;
        return firstElement;
    }


    // Returns the last element in
    // this list.
    String getLast(){
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        return current.data;
    }


    // Removes the first occurrence of the specified
    // element in this list (when traversing the list
    // from head to tail).
    boolean removeFirstOccurrence(Object o){
        Node current = head;
        int counter = 0;

        while(current.data != o){
            current = current.next;
            counter++;
        }
        Node spliceNode = getNode(counter - 1);
        spliceNode.next = current.next.next;
        return true;
    }


    // Inserts the specified element
    // at the beginning of this list.
    void addFirst(String s){
        Node newNode = new Node();
        newNode.data = s;
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }


    // Appends the specified element
    // to the end of this list.
    void addLast(String s){
        Node newNode = new Node();
        newNode.data = s;
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }


    // Removes and returns the first element from this
    // list.
    public String removeFirst() {
        //Accessing the first node's element.
        String removedNodeElement;
        Node removedNode = head.next;
        removedNodeElement = removedNode.data;
        //Removing the first node.
        head.next = removedNode.next;
        removedNode.next.prev = head;
        size--;
        return removedNodeElement;
    }


    // Returns the last element from the list, then removes it.
    public String removeLast(){
        //Accessing the last node's element.
        String removedNodeElement;
        Node removedNode = tail.prev;
        removedNodeElement = removedNode.data;
        //Removing the last node.
        tail.prev = removedNode.prev;
        removedNode.prev.next = tail;
        size--;
        return removedNodeElement;
    }


    @Override
    public ListIterator<String> listIterator() {
        return null;
    }


    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }


    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }


    // For Linked Lists, The iterator inherited from Collection returns
    // list elements in order of increasing index, and it's next()
    // method ALWAYS moves "forward" through the list.
    @Override
    public Iterator<String> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<String> {
        Node current = head;

        @Override
        public boolean hasNext() {
            return current.next != tail;
        }

        @Override
        public String next() {
            if(hasNext()){
                current = current.next;
            }
            return current.data;
        }
    }
}