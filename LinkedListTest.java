package edu.touro.mco264;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

//REMEMBER: Always arrange, act and assert.

class LinkedListTest {

    //Arrange
    private LinkedList list = new LinkedList();

    @Test
    void size() {
        //Arrange
        list.add("Hello");
        list.add("There");
        list.add("World");
        //Act & Assert
        assertEquals(list.size(), 3);
        assertEquals(list.get(1),"There");
    }

    @Test
    void isEmpty() {
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        //Act & Assert
        assertFalse(list.isEmpty());
    }

    @Test
    void contains(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        //Act & Assert
        assertTrue(list.contains("C"), list.get(2));
        assertTrue(list.contains("E"));
    }

    @Test
    void add() {
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        //Assert
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "A");
    }

    @Test
    void addIndex(){
        // Arrange
        list.add("B");
        list.add("C");
        list.add("E");
        // Act
        list.add(0,"A");
        // Assert
        assertEquals(list.get(0),"A");
        // Act for adding at index > 0
        list.add(3,"D");
        // Assert for adding at index > 0
        assertEquals(list.get(3),"D");
    }

    @Test
    void containsAll(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("F");
        LinkedList coll = new LinkedList();
        coll.add("C");
        coll.add("D");
        coll.add("E");
        list.addAll(2,coll);
        //Act & Assert
        assertTrue(list.containsAll(coll));
        assertEquals(list.get(2),"C");
    }

    @Test
    void addAll(){
        //Arrange
        list.add("List Node A");
        list.add("List Node B");
        list.add("List Node C");
        LinkedList coll = new LinkedList();
        coll.add("List Node D");
        coll.add("List Node E");
        coll.add("List Node F");
        //Act & Assert
        assertTrue(list.addAll(coll));
        assertEquals(list.get(3), "List Node D");
        assertEquals(list.get(4), "List Node E");
        assertEquals(list.get(5), "List Node F");
    }

    @Test
    void addAllIndex(){
        //Arrange
        list.add("List Node A");
        list.add("List Node B");
        list.add("List Node F");
        LinkedList coll = new LinkedList();
        coll.add("List Node C");
        coll.add("List Node D");
        coll.add("List Node E");
        //Act & Assert
        list.addAll(2,coll);
        //Assert
        assertTrue(list.addAll(coll));
        assertEquals(list.get(2),"List Node C");
        assertEquals(list.get(5),"List Node F");
    }

    @Test
    void removeAll(){
        //Arrange
        list.add("List Node A");
        list.add("List Node B");
        list.add("List Node C");
        list.add("List Node D");
        list.add("List Node E");
        list.add("List Node F");
        list.add("List Node G");
        LinkedList coll = new LinkedList();
        coll.add("List Node D");
        coll.add("List Node E");
        coll.add("List Node F");
        //Act & Assert
        assertTrue(list.removeAll(coll));
        assertEquals(list.get(3),"List Node G");
    }

    @Test
    void retainAll(){
        //Arrange
        list.add("List Node A");
        list.add("List Node B");
        list.add("List Node C");
        list.add("List Node D");
        list.add("List Node E");
        list.add("List Node F");
        list.add("List Node G");
        LinkedList coll = new LinkedList();
        coll.add("List Node A");
        coll.add("List Node B");
        coll.add("List Node C");
        //Act & Assert
        assertTrue(list.retainAll(coll));
    }

    @Test
    void clear(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list.size());
        //Act
        list.clear();
        System.out.println(list.size());
        //Assert
        assertEquals(list.size(), 0);
    }

    @Test
    void get(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        //Assert
        assertEquals(list.get(2),"C");
    }

    @Test
    void remove(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        //Act
        list.remove("B");
        //Assert
        assertEquals(list.get(1), "C");
        assertEquals(list.get(1), list.get(list.size()-1));
    }

    @Test
    void removeIndex(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        //Act & Assert
        assertEquals(list.remove(1),"Node B");
    }

    @Test
    void indexOf(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node D");
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node D");
        //Act & Assert
        assertEquals(list.indexOf("Node A"),0);
        assertEquals(list.indexOf("Node D"),3);
        assertEquals(list.indexOf("Node B"),1);
    }

    @Test
    void lastIndexOf() {
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        //Act & Assert
        assertEquals(list.lastIndexOf("Node A"), 4);
        assertEquals(list.lastIndexOf("Node B"), 5);
        assertEquals(list.lastIndexOf("Node C"), 6);
    }

    @Test
    void set() {
        //Arrange
        list.add("A");
        list.add("C");
        list.add("B");
        list.add("D");
        //Act
        list.set(1,"B");
        list.set(2,"C");
        //Assert
        assertEquals(list.get(1), "B");
        assertEquals(list.get(2), "C");
    }

    @Test
    void getFirst(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        //Act & Assert
        assertEquals(list.get(0),"Node A");
        assertEquals(list.getFirst(),"Node A");
    }

    @Test
    void getLast(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        //Act & Assert
        assertEquals(list.get(2),"Node C");
        assertEquals(list.getLast(),"Node C");
    }

    @Test
    void removeFirstOccurrence() {
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        //Act & Assert
        assertTrue(list.removeFirstOccurrence("Node B"), list.get(1));
    }

    @Test
    void addFirst(){
        //Arrange
        list.add("Node B");
        list.add("Node C");
        list.add("Node D");
        list.add("Node E");
        list.add("Node F");
        //Act
        list.addFirst("Node A");
        //Assert
        assertEquals(list.get(0), "Node A");
    }

    @Test
    void addLast(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node D");
        list.add("Node E");
        //Act
        list.addLast("Node F");
        //Assert
        assertEquals(list.get(5), "Node F");
    }

    @Test
    void removeFirst(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        //Act
        list.removeFirst();
        //Assert
        assertEquals(list.get(0),"B");
        assertEquals(list.size(),7);
    }

    @Test
    void removeLast(){
        //Arrange
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        //Act
        list.removeLast();
        //Assert
        assertEquals(list.get(6),"G");
        assertEquals(list.size(),7);
    }

    @Test
    void iterator(){
        //Arrange
        list.add("Node A");
        list.add("Node B");
        list.add("Node C");
        list.add("Node D");
        //Act
        Iterator<String> iterate = list.iterator();
        //Assert
        assertTrue(iterate.hasNext());
        assertEquals("Node A",iterate.next());
        assertEquals("Node B",iterate.next());
        assertEquals("Node C",iterate.next());
        assertEquals("Node D",iterate.next());
        assertFalse(iterate.hasNext());
    }
}