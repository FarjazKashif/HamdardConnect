package datastructures;

public class LinkedList<T> {
    
    // Inner Node class
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Insert at beginning
    public void insertAtHead(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    // Insert at end
    public void insertAtEnd(T data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }
    
    // Insert at specific position (0-indexed)
    public void insertAt(int position, T data) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position!");
            return;
        }
        
        if (position == 0) {
            insertAtHead(data);
            return;
        }
        
        Node newNode = new Node(data);
        Node current = head;
        
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    // Delete from beginning
    public T deleteFromHead() {
        if (head == null) {
            System.out.println("List is empty!");
            return null;
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    // Delete from end
    public T deleteFromEnd() {
        if (head == null) {
            System.out.println("List is empty!");
            return null;
        }
        
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    // Delete at specific position
    public T deleteAt(int position) {
        if (position < 0 || position >= size) {
            System.out.println("Invalid position!");
            return null;
        }
        
        if (position == 0) {
            return deleteFromHead();
        }
        
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    // Search for an element
    public boolean search(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Get element at position
    public T get(int position) {
        if (position < 0 || position >= size) {
            System.out.println("Invalid position!");
            return null;
        }
        
        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return head == null;
    }
    
    // Clear the list
    public void clear() {
        head = null;
        size = 0;
    }
    
    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        System.out.print("LinkedList: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // Display with details
    public void displayDetailed() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        System.out.println("\n--- LinkedList Contents ---");
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println("Index " + index + ": " + current.data);
            current = current.next;
            index++;
        }
        System.out.println("Total elements: " + size);
    }
}