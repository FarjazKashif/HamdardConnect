package datastructures;

public class Stack<T> {
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node top;
    private int size;
    
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    // Push
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // Pop
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    // Peek
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return size;
    }
    
    // Clear stack
    public void clear() {
        top = null;
        size = 0;
    }
    
    // Display
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public void displayDetailed() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        System.out.println("\n--- Stack Contents (Top to Bottom) ---");
        Node current = top;
        int position = 0;
        while (current != null) {
            System.out.println("Position " + position + ": " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("Total elements: " + size);
    }
}