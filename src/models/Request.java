package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request {
    private int requestID;
    private int studentID;
    private String pickupLocation;
    private String dropLocation;
    private String timestamp;
    private String status;  // "Pending", "Accepted", "Completed", "Cancelled"
    private int priority;   // 1=Low, 2=Medium, 3=High, 4=Emergency
    private Integer assignedDriverID;  // null if no driver assigned yet
    
    // Constructor
    public Request(int requestID, int studentID, String pickupLocation, 
                   String dropLocation, int priority) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.priority = priority;
        this.status = "Pending";
        this.assignedDriverID = null;
        
        // Generate timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = now.format(formatter);
    }
    
    // Getters
    public int getRequestID() {
        return requestID;
    }
    
    public int getStudentID() {
        return studentID;
    }
    
    public String getPickupLocation() {
        return pickupLocation;
    }
    
    public String getDropLocation() {
        return dropLocation;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getStatus() {
        return status;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public Integer getAssignedDriverID() {
        return assignedDriverID;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setAssignedDriverID(Integer assignedDriverID) {
        this.assignedDriverID = assignedDriverID;
    }
    
    // Display
    public void displayInfo() {
        System.out.println("\n--- Request Info ---");
        System.out.println("Request ID: " + requestID);
        System.out.println("Student ID: " + studentID);
        System.out.println("From: " + pickupLocation);
        System.out.println("To: " + dropLocation);
        System.out.println("Priority: " + getPriorityString());
        System.out.println("Status: " + status);
        System.out.println("Timestamp: " + timestamp);
        if (assignedDriverID != null) {
            System.out.println("Assigned Driver ID: " + assignedDriverID);
        }
    }
    
    private String getPriorityString() {
        switch(priority) {
            case 1: return "Low";
            case 2: return "Medium";
            case 3: return "High";
            case 4: return "Emergency";
            default: return "Unknown";
        }
    }
}