package models;

public class Driver {
    private int driverID;
    private String name;
    private String phoneNumber;
    private String vehicleType;
    private String vehicleNumber;
    private String currentLocation;
    private boolean isAvailable;
    private double rating;
    private int totalRides;
    
    // Constructor
    public Driver(int driverID, String name, String phoneNumber, 
                  String vehicleType, String vehicleNumber) {
        this.driverID = driverID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.currentLocation = "Gate";
        this.isAvailable = true;
        this.rating = 5.0;
        this.totalRides = 0;
    }
    
    // Getters
    public int getDriverID() {
        return driverID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public String getCurrentLocation() {
        return currentLocation;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public double getRating() {
        return rating;
    }
    
    public int getTotalRides() {
        return totalRides;
    }
    
    // Setters
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public void incrementTotalRides() {
        this.totalRides++;
    }
    
    public void updateRating(double newRating) {
        this.rating = ((this.rating * this.totalRides) + newRating) / (this.totalRides + 1);
    }
    
    // Display
    public void displayInfo() {
        System.out.println("\n--- Driver Info ---");
        System.out.println("ID: " + driverID);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Vehicle: " + vehicleType + " (" + vehicleNumber + ")");
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Rating: " + String.format("%.2f", rating) + "/5.0");
        System.out.println("Total Rides: " + totalRides);
    }
}