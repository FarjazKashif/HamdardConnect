package datastructures;

public class HashMap<K, V> {
    
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
    
    private Object[] buckets;  // Change: Node[] → Object[]
    private int size;
    private int capacity;
    
    // Constructor
    public HashMap() {
        this.capacity = 16;
        this.buckets = new Object[capacity];  // ✅ Direct Object array
        this.size = 0;
    }
    
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Object[capacity];  // ✅ Direct Object array
        this.size = 0;
    }
    
    // Hash function
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }
    
    // Insert or Update
    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];  // ✅ Cast here
        
        // Check if key already exists
        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        
        // Insert new node
        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
        size++;
        
        // Rehash if needed
        double loadFactor = (double) size / capacity;
        if (loadFactor > 0.75) {
            rehash();
        }
    }
    
    // Search
    @SuppressWarnings("unchecked")
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];  // ✅ Cast here
        
        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null;
    }
    
    // Check if key exists
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    // Remove
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node head = (Node) buckets[bucketIndex];  // ✅ Cast here
        
        if (head == null) {
            return null;
        }
        
        // If head contains the key
        if (head.key.equals(key)) {
            V removedValue = head.value;
            buckets[bucketIndex] = head.next;
            size--;
            return removedValue;
        }
        
        // Search in rest
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
        
        return null;
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Rehashing
    @SuppressWarnings("unchecked")
    private void rehash() {
        Object[] oldBuckets = buckets;
        capacity = capacity * 2;
        buckets = new Object[capacity];  // ✅ Direct Object array
        size = 0;
        
        // Reinsert all elements
        for (Object bucket : oldBuckets) {
            Node head = (Node) bucket;  // ✅ Cast here
            Node current = head;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
    
    // Display
    @SuppressWarnings("unchecked")
    public void display() {
        System.out.println("\n--- HashMap Contents ---");
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                System.out.print("Bucket " + i + ": ");
                Node current = (Node) buckets[i];  // ✅ Cast here
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