package models;

public class Student {
    private int studentID;
    private String name;
    private String phoneNumber;
    private String currentLocation;
    private boolean isActive;
    
    // Constructor
    public Student(int studentID, String name, String phoneNumber) {
        this.studentID = studentID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentLocation = "Gate";
        this.isActive = true;
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
    
    // Setters
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    // Display
    public void displayInfo() {
        System.out.println("\n--- Student Info ---");
        System.out.println("ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Active: " + (isActive ? "Yes" : "No"));
    }
}