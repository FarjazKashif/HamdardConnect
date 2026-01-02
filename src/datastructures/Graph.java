package datastructures;
import datastructures.HashMap;

public class Graph {
    
    private class Edge {
        String destination;
        double distance;
        Edge next;
        
        Edge(String destination, double distance) {
            this.destination = destination;
            this.distance = distance;
            this.next = null;
        }
    }
    
    private HashMap<String, Edge> adjacencyList;
    
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }
    
    // Add a location to the graph
    public void addLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            adjacencyList.put(location, null);
        }
    }
    
    // Add a route between two locations
    public void addRoute(String from, String to, double distance) {
        // Add locations if they don't exist
        addLocation(from);
        addLocation(to);
        
        // Add edge from -> to
        Edge newEdge = new Edge(to, distance);
        Edge head = adjacencyList.get(from);
        newEdge.next = head;
        adjacencyList.put(from, newEdge);
        
        // Add edge to -> from (bidirectional)
        Edge reverseEdge = new Edge(from, distance);
        Edge reverseHead = adjacencyList.get(to);
        reverseEdge.next = reverseHead;
        adjacencyList.put(to, reverseEdge);
    }
    
    // Get distance between two locations
    public double getDistance(String from, String to) {
        if (!adjacencyList.containsKey(from)) {
            return -1.0;
        }
        
        Edge current = adjacencyList.get(from);
        while (current != null) {
            if (current.destination.equals(to)) {
                return current.distance;
            }
            current = current.next;
        }
        
        return -1.0;
    }
    
    // Check if route exists
    public boolean hasRoute(String from, String to) {
        return getDistance(from, to) != -1.0;
    }
    
    // Get all connected locations from a source
    public String[] getConnectedLocations(String location) {
        if (!adjacencyList.containsKey(location)) {
            return new String[0];
        }
        
        // Count connections
        int count = 0;
        Edge current = adjacencyList.get(location);
        while (current != null) {
            count++;
            current = current.next;
        }
        
        // Create array
        String[] connections = new String[count];
        current = adjacencyList.get(location);
        int index = 0;
        while (current != null) {
            connections[index++] = current.destination;
            current = current.next;
        }
        
        return connections;
    }
    
    // Display graph
    public void display() {
        System.out.println("\n--- Graph Structure ---");
        // Note: We can't iterate HashMap directly
        // So we'll display based on known locations
        System.out.println("Graph contains routes between locations");
        System.out.println("Use getDistance(from, to) to check specific routes");
    }
    
    // Display specific location's connections
    public void displayLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            System.out.println("Location not found in graph");
            return;
        }
        
        System.out.println("\nConnections from " + location + ":");
        Edge current = adjacencyList.get(location);
        
        if (current == null) {
            System.out.println("  No connections");
            return;
        }
        
        while (current != null) {
            System.out.println("  -> " + current.destination + " (" + current.distance + " km)");
            current = current.next;
        }
    }
}