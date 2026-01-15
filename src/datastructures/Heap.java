package datastructures;

import java.util.Comparator;

public class Heap<T> {
    
    private Object[] array;
    private int size;
    private int capacity;
    private Comparator<T> comparator;
    
    // Constructor with custom comparator
    public Heap(Comparator<T> comparator) {
        this.capacity = 16;
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }
    
    public Heap(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }
    
    // Get parent index
    private int parent(int index) {
        return (index - 1) / 2;
    }
    
    // Get left child index
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    
    // Get right child index
    private int rightChild(int index) {
        return 2 * index + 2;
    }
    
    // Check if has parent
    private boolean hasParent(int index) {
        return parent(index) >= 0;
    }
    
    // Check if has left child
    private boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }
    
    // Check if has right child
    private boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }
    
    // Swap two elements
    private void swap(int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    
    // Resize array if needed
    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
    
    // Insert element
    public void insert(T data) {
        ensureCapacity();
        array[size] = data;
        size++;
        heapifyUp(size - 1);
    }
    
    // Heapify up (for insertion)
    @SuppressWarnings("unchecked")
    private void heapifyUp(int index) {
        while (hasParent(index) && 
               comparator.compare((T)array[index], (T)array[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    
    // Extract min/max (based on comparator)
    @SuppressWarnings("unchecked")
    public T extract() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return null;
        }
        
        T data = (T) array[0];
        array[0] = array[size - 1];
        size--;
        heapifyDown(0);
        return data;
    }
    
    // Heapify down (for extraction)
    @SuppressWarnings("unchecked")
    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChild(index);
            if (hasRightChild(index) && 
                comparator.compare((T)array[rightChild(index)], 
                (T)array[leftChild(index)]) < 0) {
                smallerChildIndex = rightChild(index);
            }
            
            if (comparator.compare((T)array[index], 
                (T)array[smallerChildIndex]) < 0) {
                    break;
            }
            
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }
    
    // Peek at top element
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return null;
        }
        return (T) array[0];
    }
    
    // Get size
    public int size() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Display heap
    public void display() {
        if (isEmpty()) {
            System.out.println("Heap is empty!");
            return;
        }
        
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}