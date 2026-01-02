package models;

import datastructures.LinkedList;

public class Student {
    private int studentID;
    private String name;
    private String phoneNumber;
    private String currentLocation;
    private boolean isActive;
    private LinkedList<Integer> rideHistory;
    
    // Constructor
    public Student(int studentID, String name, String phoneNumber) {
        this.studentID = studentID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentLocation = "Gate";
        this.isActive = true;
        this.rideHistory = new LinkedList<>();
    }
    
    // Getters
    public int getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getCurrentLocation() {
        return currentLocation;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public LinkedList<Integer> getRideHistory() {
        return rideHistory;
    }
    
    // Setters
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    // Add ride to history
    public void addRideToHistory(int requestID) {
        rideHistory.insertAtEnd(requestID);
    }
    
    // Get total rides
    public int getTotalRides() {
        return rideHistory.size();
    }
    
    // Display
    public void displayInfo() {
        System.out.println("\n--- Student Info ---");
        System.out.println("ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Active: " + (isActive ? "Yes" : "No"));
        System.out.println("Total Rides: " + getTotalRides());
    }
    
    // Display ride history
    public void displayRideHistory() {
        System.out.println("\n--- Ride History for " + name + " ---");
        if (rideHistory.isEmpty()) {
            System.out.println("No rides yet");
        } else {
            System.out.println("Total rides taken: " + rideHistory.size());
            System.out.print("Request IDs: ");
            rideHistory.display();
        }
    }
}