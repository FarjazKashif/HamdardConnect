package datastructures;

public class HashMap<K, V> {
    
    // Inner class for storing key-value pairs (Nodes in linked list for chaining)
    private class Node {
        K key;
        V value;
        Node next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    private Node[] buckets;  // Array of linked lists
    private int size;        // Number of key-value pairs
    private int capacity;    // Array size
    
    // Constructor
    public HashMap() {
        this.capacity = 16;  // Default capacity
        this.buckets = (Node[]) new Object[capacity];
        this.size = 0;
    }
    
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = (Node[]) new Object[capacity];
        this.size = 0;
    }
    
    // Hash function - converts key to index
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }
    
    // Insert or Update
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        
        // Check if key already exists (update case)
        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;  // Update existing value
                return;
            }
            current = current.next;
        }
        
        // Key doesn't exist - insert new node at head
        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
        size++;
        
        // Check if rehashing is needed (load factor > 0.75)
        double loadFactor = (double) size / capacity;
        if (loadFactor > 0.75) {
            rehash();
        }
    }
    
    // Search - returns value if key exists, null otherwise
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        
        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null;  // Key not found
    }
    
    // Check if key exists
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    // Remove a key-value pair
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        
        // If bucket is empty
        if (head == null) {
            return null;
        }
        
        // If head node contains the key
        if (head.key.equals(key)) {
            V removedValue = head.value;
            buckets[bucketIndex] = head.next;
            size--;
            return removedValue;
        }
        
        // Search in the rest of the linked list
        Node prev = head;
        Node current = head.next;
        
        while (current != null) {
            if (current.key.equals(key)) {
                V removedValue = current.value;
                prev.next = current.next;
                size--;
                return removedValue;
            }
            prev = current;
            current = current.next;
        }
        
        return null;  // Key not found
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Rehashing - double the capacity when load factor exceeds threshold
    private void rehash() {
        Node[] oldBuckets = buckets;
        capacity = capacity * 2;
        this.buckets = (Node[]) new Object[capacity];
        size = 0;
        
        // Reinsert all elements
        for (Node head : oldBuckets) {
            Node current = head;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
    
    // Display all key-value pairs
    public void display() {
        System.out.println("\n--- HashMap Contents ---");
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                System.out.print("Bucket " + i + ": ");
                Node current = buckets[i];
                while (current != null) {
                    System.out.print("[" + current.key + " -> " + current.value + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }
        System.out.println("Total entries: " + size);
    }
}