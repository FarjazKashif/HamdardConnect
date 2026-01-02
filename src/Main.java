import datastructures.HashMap;
import datastructures.LinkedList;
import datastructures.Stack;
import datastructures.Heap;
import datastructures.Graph;
import models.Student;
import models.Driver;
import models.Request;

import java.util.Scanner;

public class Main {
    
    // System-wide data structures
    private static HashMap<Integer, Student> studentMap = new HashMap<>();
    private static HashMap<Integer, Driver> driverMap = new HashMap<>();
    private static HashMap<Integer, Request> requestMap = new HashMap<>();
    private static Stack<String> actionHistory = new Stack<>();
    private static Graph campusGraph = new Graph();
    private static int requestCounter = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Known IDs for iteration
    private static int[] knownStudentIDs = {101, 102, 103};
    private static int[] knownDriverIDs = {201, 202, 203};
    
    public static void main(String[] args) {
        
        // Initialize with some sample data
        initializeSampleData();
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    registerDriver();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    viewAllDrivers();
                    break;
                case 5:
                    generateRideRequest();
                    break;
                case 6:
                    viewAllRequests();
                    break;
                case 7:
                    matchDriverToRequest();
                    break;
                case 8:
                    autoMatchBestDriver();
                    break;
                case 9:
                    viewStudentRideHistory();
                    break;
                case 10:
                    viewDriverAcceptedRequests();
                    break;
                case 11:
                    updateDriverLocation();
                    break;
                case 12:
                    viewCampusRoutes();
                    break;
                case 13:
                    undoLastAction();
                    break;
                case 14:
                    viewActionHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using Hamdard Connect!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    // Display main menu
    private static void displayMainMenu() {
        System.out.println("\n================================================");
        System.out.println("         HAMDARD CONNECT - MAIN MENU           ");
        System.out.println("================================================");
        System.out.println("1.  Register Student");
        System.out.println("2.  Register Driver");
        System.out.println("3.  View All Students");
        System.out.println("4.  View All Drivers");
        System.out.println("5.  Generate Ride Request");
        System.out.println("6.  View All Requests");
        System.out.println("7.  Manual Match Driver to Request");
        System.out.println("8.  Auto Match Best Driver to Request");
        System.out.println("9.  View Student Ride History");
        System.out.println("10. View Driver Accepted Requests");
        System.out.println("11. Update Driver Location");
        System.out.println("12. View Campus Routes");
        System.out.println("13. Undo Last Action");
        System.out.println("14. View Action History");
        System.out.println("0.  Exit");
        System.out.println("================================================");
    }
    
    // Initialize sample data
    private static void initializeSampleData() {
        System.out.println("Initializing system with sample data...\n");
        
        // Sample students
        Student s1 = new Student(101, "Ali Ahmed", "03001234567");
        Student s2 = new Student(102, "Sara Khan", "03009876543");
        Student s3 = new Student(103, "Hassan Raza", "03001111111");
        
        studentMap.put(s1.getStudentID(), s1);
        studentMap.put(s2.getStudentID(), s2);
        studentMap.put(s3.getStudentID(), s3);
        
        // Sample drivers
        Driver d1 = new Driver(201, "Ahmed Ali", "03002222222", "Bike", "ABC-123");
        Driver d2 = new Driver(202, "Bilal Khan", "03003333333", "Car", "XYZ-456");
        Driver d3 = new Driver(203, "Imran Shah", "03004444444", "Bike", "DEF-789");
        
        d1.setCurrentLocation("Gate");
        d2.setCurrentLocation("Gulshan");
        d3.setCurrentLocation("Clifton");
        
        driverMap.put(d1.getDriverID(), d1);
        driverMap.put(d2.getDriverID(), d2);
        driverMap.put(d3.getDriverID(), d3);
        
        // Setup campus graph
        campusGraph.addLocation("Gate");
        campusGraph.addLocation("Gulshan");
        campusGraph.addLocation("Clifton");
        campusGraph.addLocation("DHA");
        campusGraph.addLocation("Saddar");
        
        campusGraph.addRoute("Gate", "Gulshan", 5.0);
        campusGraph.addRoute("Gulshan", "Clifton", 3.0);
        campusGraph.addRoute("Gate", "DHA", 7.0);
        campusGraph.addRoute("DHA", "Clifton", 4.0);
        campusGraph.addRoute("Gate", "Saddar", 6.0);
        campusGraph.addRoute("Saddar", "Clifton", 5.0);
        
        System.out.println("Sample data loaded successfully!");
        System.out.println("   - 3 Students registered");
        System.out.println("   - 3 Drivers registered");
        System.out.println("   - 5 Campus locations added");
        System.out.println("   - 6 Routes configured");
    }
    
    // Register new student
    private static void registerStudent() {
        System.out.println("\n=== REGISTER STUDENT ===");
        
        int id = getIntInput("Enter Student ID: ");
        
        if (studentMap.containsKey(id)) {
            System.out.println("Student with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        Student student = new Student(id, name, phone);
        studentMap.put(id, student);
        
        actionHistory.push("REGISTER_STUDENT:" + id);
        
        System.out.println("Student registered successfully!");
        student.displayInfo();
    }
    
    // Register new driver
    private static void registerDriver() {
        System.out.println("\n=== REGISTER DRIVER ===");
        
        int id = getIntInput("Enter Driver ID: ");
        
        if (driverMap.containsKey(id)) {
            System.out.println("Driver with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Vehicle Type (Bike/Car): ");
        String vehicleType = scanner.nextLine();
        
        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = scanner.nextLine();
        
        Driver driver = new Driver(id, name, phone, vehicleType, vehicleNumber);
        driverMap.put(id, driver);
        
        actionHistory.push("REGISTER_DRIVER:" + id);
        
        System.out.println("Driver registered successfully!");
        driver.displayInfo();
    }
    
    // View all students
    private static void viewAllStudents() {
        System.out.println("\n=== ALL REGISTERED STUDENTS ===");
        
        if (studentMap.isEmpty()) {
            System.out.println("No students registered yet!");
            return;
        }
        
        System.out.println("Total Students: " + studentMap.size());
        System.out.println("------------------------------------------------");
        
        for (int id : knownStudentIDs) {
            Student s = studentMap.get(id);
            if (s != null) {
                System.out.println("\n[ID: " + id + "] " + s.getName());
                System.out.println("   Phone: " + s.getPhoneNumber());
                System.out.println("   Location: " + s.getCurrentLocation());
                System.out.println("   Status: " + (s.isActive() ? "Active" : "Inactive"));
                System.out.println("   Total Rides: " + s.getTotalRides());
            }
        }
        System.out.println("------------------------------------------------");
    }
    
    // View all drivers with proper list
    private static void viewAllDrivers() {
        System.out.println("\n=== ALL REGISTERED DRIVERS ===");
        
        if (driverMap.isEmpty()) {
            System.out.println("No drivers registered yet!");
            return;
        }
        
        System.out.println("Total Drivers: " + driverMap.size());
        System.out.println("------------------------------------------------");
        
        for (int id : knownDriverIDs) {
            Driver d = driverMap.get(id);
            if (d != null) {
                System.out.println("\n[ID: " + id + "] " + d.getName());
                System.out.println("   Vehicle: " + d.getVehicleType() + " (" + d.getVehicleNumber() + ")");
                System.out.println("   Phone: " + d.getPhoneNumber());
                System.out.println("   Location: " + d.getCurrentLocation());
                System.out.println("   Rating: " + String.format("%.1f", d.getRating()) + "/5.0");
                System.out.println("   Status: " + (d.isAvailable() ? "AVAILABLE" : "BUSY"));
                System.out.println("   Total Rides: " + d.getTotalRides());
            }
        }
        System.out.println("------------------------------------------------");
    }
    
    // Generate ride request
    private static void generateRideRequest() {
        System.out.println("\n=== GENERATE RIDE REQUEST ===");
        
        int studentID = getIntInput("Enter Student ID: ");
        
        Student student = studentMap.get(studentID);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter Pickup Location: ");
        String pickup = scanner.nextLine();
        
        System.out.print("Enter Drop Location: ");
        String drop = scanner.nextLine();
        
        System.out.println("Select Priority:");
        System.out.println("1. Low");
        System.out.println("2. Medium");
        System.out.println("3. High");
        System.out.println("4. Emergency");
        int priority = getIntInput("Enter priority (1-4): ");
        
        if (priority < 1 || priority > 4) {
            System.out.println("Invalid priority!");
            return;
        }
        
        Request request = new Request(requestCounter++, studentID, pickup, drop, priority);
        requestMap.put(request.getRequestID(), request);
        
        actionHistory.push("GENERATE_REQUEST:" + request.getRequestID());
        
        System.out.println("Ride request generated successfully!");
        request.displayInfo();
    }
    
    // View all requests
    private static void viewAllRequests() {
        System.out.println("\n=== ALL RIDE REQUESTS ===");
        
        if (requestMap.isEmpty()) {
            System.out.println("No requests available!");
            return;
        }
        
        System.out.println("Total Requests: " + requestMap.size());
        System.out.println("------------------------------------------------");
        
        for (int i = 1; i < requestCounter; i++) {
            Request req = requestMap.get(i);
            if (req != null) {
                System.out.println("\n[Request ID: " + i + "]");
                System.out.println("   Student ID: " + req.getStudentID());
                System.out.println("   From: " + req.getPickupLocation());
                System.out.println("   To: " + req.getDropLocation());
                System.out.println("   Priority: " + req.getPriority());
                System.out.println("   Status: " + req.getStatus());
                if (req.getAssignedDriverID() != null) {
                    System.out.println("   Driver ID: " + req.getAssignedDriverID());
                }
            }
        }
        System.out.println("------------------------------------------------");
    }
    
    // Manual match driver to request
    private static void matchDriverToRequest() {
        System.out.println("\n=== MANUAL MATCH DRIVER TO REQUEST ===");
        
        // First show available drivers
        System.out.println("\nAvailable Drivers:");
        viewAvailableDriversShort();
        
        int requestID = getIntInput("\nEnter Request ID: ");
        Request request = requestMap.get(requestID);
        
        if (request == null) {
            System.out.println("Request not found!");
            return;
        }
        
        if (!request.getStatus().equals("Pending")) {
            System.out.println("Request is already " + request.getStatus());
            return;
        }
        
        int driverID = getIntInput("Enter Driver ID from above list: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Driver not found!");
            return;
        }
        
        if (!driver.isAvailable()) {
            System.out.println("Driver is not available!");
            return;
        }
        
        // Match driver to request
        request.setAssignedDriverID(driverID);
        request.setStatus("Accepted");
        driver.setAvailable(false);
        driver.incrementTotalRides();
        driver.addAcceptedRequest(requestID);
        
        // Add to student history
        Student student = studentMap.get(request.getStudentID());
        if (student != null) {
            student.addRideToHistory(requestID);
        }
        
        actionHistory.push("MATCH_DRIVER:" + requestID + ":" + driverID);
        
        System.out.println("Driver matched successfully!");
        System.out.println("Driver: " + driver.getName() + " assigned to Request #" + requestID);
    }
    
    // Auto match best driver using Heap and Graph
    private static void autoMatchBestDriver() {
        System.out.println("\n=== AUTO MATCH BEST DRIVER ===");
        
        int requestID = getIntInput("Enter Request ID: ");
        Request request = requestMap.get(requestID);
        
        if (request == null) {
            System.out.println("Request not found!");
            return;
        }
        
        if (!request.getStatus().equals("Pending")) {
            System.out.println("Request is already " + request.getStatus());
            return;
        }
        
        // Find best driver using Heap
        Heap<Driver> driverHeap = new Heap<>((d1, d2) -> {
            String pickup = request.getPickupLocation();
            
            // Get distances
            double dist1 = campusGraph.getDistance(d1.getCurrentLocation(), pickup);
            double dist2 = campusGraph.getDistance(d2.getCurrentLocation(), pickup);
            
            // If distance not found, use large number
            if (dist1 == -1.0) dist1 = 999.0;
            if (dist2 == -1.0) dist2 = 999.0;
            
            // Compare by distance first
            if (dist1 < dist2) return -1;
            if (dist1 > dist2) return 1;
            
            // If same distance, compare by rating
            return Double.compare(d2.getRating(), d1.getRating());
        });
        
        // Add all available drivers to heap
        for (int id : knownDriverIDs) {
            Driver d = driverMap.get(id);
            if (d != null && d.isAvailable()) {
                driverHeap.insert(d);
            }
        }
        
        if (driverHeap.isEmpty()) {
            System.out.println("No drivers available!");
            return;
        }
        
        // Get best driver
        Driver bestDriver = driverHeap.extract();
        
        // Match
        request.setAssignedDriverID(bestDriver.getDriverID());
        request.setStatus("Accepted");
        bestDriver.setAvailable(false);
        bestDriver.incrementTotalRides();
        bestDriver.addAcceptedRequest(requestID);
        
        // Add to student history
        Student student = studentMap.get(request.getStudentID());
        if (student != null) {
            student.addRideToHistory(requestID);
        }
        
        actionHistory.push("MATCH_DRIVER:" + requestID + ":" + bestDriver.getDriverID());
        
        System.out.println("Best driver automatically selected!");
        System.out.println("Driver: " + bestDriver.getName());
        System.out.println("Rating: " + String.format("%.1f", bestDriver.getRating()));
        System.out.println("Distance from pickup: " + 
            campusGraph.getDistance(bestDriver.getCurrentLocation(), request.getPickupLocation()) + " km");
    }
    
    // Helper method to show available drivers briefly
    private static void viewAvailableDriversShort() {
        for (int id : knownDriverIDs) {
            Driver d = driverMap.get(id);
            if (d != null && d.isAvailable()) {
                System.out.println("  [" + id + "] " + d.getName() + " - " + 
                    d.getVehicleType() + " - Rating: " + 
                    String.format("%.1f", d.getRating()));
            }
        }
    }
    
    // View student ride history
    private static void viewStudentRideHistory() {
        System.out.println("\n=== STUDENT RIDE HISTORY ===");
        
        int studentID = getIntInput("Enter Student ID: ");
        Student student = studentMap.get(studentID);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        student.displayInfo();
        student.displayRideHistory();
    }
    
    // View driver accepted requests
    private static void viewDriverAcceptedRequests() {
        System.out.println("\n=== DRIVER ACCEPTED REQUESTS ===");
        
        int driverID = getIntInput("Enter Driver ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Driver not found!");
            return;
        }
        
        driver.displayInfo();
        driver.displayAcceptedRequests();
    }
    
    // Update driver location
    private static void updateDriverLocation() {
        System.out.println("\n=== UPDATE DRIVER LOCATION ===");
        
        int driverID = getIntInput("Enter Driver ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Driver not found!");
            return;
        }
        
        System.out.println("Current location: " + driver.getCurrentLocation());
        System.out.print("Enter new location: ");
        String newLocation = scanner.nextLine();
        
        driver.setCurrentLocation(newLocation);
        System.out.println("Location updated successfully!");
    }
    
    // View campus routes
    private static void viewCampusRoutes() {
        System.out.println("\n=== CAMPUS ROUTES ===");
        
        String[] locations = {"Gate", "Gulshan", "Clifton", "DHA", "Saddar"};
        
        for (String location : locations) {
            campusGraph.displayLocation(location);
        }
    }
    
    // Undo last action
    private static void undoLastAction() {
        System.out.println("\n=== UNDO LAST ACTION ===");
        
        if (actionHistory.isEmpty()) {
            System.out.println("No actions to undo!");
            return;
        }
        
        String lastAction = actionHistory.pop();
        System.out.println("Last action: " + lastAction);
        
        String[] parts = lastAction.split(":");
        String actionType = parts[0];
        
        switch (actionType) {
            case "REGISTER_STUDENT":
                int studentID = Integer.parseInt(parts[1]);
                studentMap.remove(studentID);
                System.out.println("Student registration undone!");
                break;
                
            case "REGISTER_DRIVER":
                int driverID = Integer.parseInt(parts[1]);
                driverMap.remove(driverID);
                System.out.println("Driver registration undone!");
                break;
                
            case "GENERATE_REQUEST":
                int requestID = Integer.parseInt(parts[1]);
                requestMap.remove(requestID);
                System.out.println("Request generation undone!");
                break;
                
            case "MATCH_DRIVER":
                int reqID = Integer.parseInt(parts[1]);
                int drvID = Integer.parseInt(parts[2]);
                Request req = requestMap.get(reqID);
                Driver drv = driverMap.get(drvID);
                if (req != null) {
                    req.setStatus("Pending");
                    req.setAssignedDriverID(null);
                }
                if (drv != null) {
                    drv.setAvailable(true);
                }
                System.out.println("Driver matching undone!");
                break;
                
            default:
                System.out.println("Unknown action type!");
        }
    }
    
    // View action history
    private static void viewActionHistory() {
        System.out.println("\n=== ACTION HISTORY ===");
        actionHistory.displayDetailed();
    }
    
    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}