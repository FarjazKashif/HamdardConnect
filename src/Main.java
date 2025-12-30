import datastructures.HashMap;
import datastructures.LinkedList;
import datastructures.Stack;
import datastructures.Heap;
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
    private static int requestCounter = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
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
                    viewStudentRideHistory();
                    break;
                case 9:
                    viewDriverAcceptedRequests();
                    break;
                case 10:
                    undoLastAction();
                    break;
                case 11:
                    viewActionHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n✅ Thank you for using Hamdard Connect!");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    // Display main menu
    private static void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║     HAMDARD CONNECT - MAIN MENU           ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("1.  Register Student");
        System.out.println("2.  Register Driver");
        System.out.println("3.  View All Students");
        System.out.println("4.  View All Drivers");
        System.out.println("5.  Generate Ride Request");
        System.out.println("6.  View All Requests");
        System.out.println("7.  Match Driver to Request");
        System.out.println("8.  View Student Ride History");
        System.out.println("9.  View Driver Accepted Requests");
        System.out.println("10. Undo Last Action");
        System.out.println("11. View Action History");
        System.out.println("0.  Exit");
        System.out.println("════════════════════════════════════════════");
    }
    
    // Initialize sample data
    private static void initializeSampleData() {
        System.out.println("🔄 Initializing system with sample data...\n");
        
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
        
        driverMap.put(d1.getDriverID(), d1);
        driverMap.put(d2.getDriverID(), d2);
        driverMap.put(d3.getDriverID(), d3);
        
        System.out.println("✅ Sample data loaded successfully!");
        System.out.println("   - 3 Students registered");
        System.out.println("   - 3 Drivers registered");
    }
    
    // Register new student
    private static void registerStudent() {
        System.out.println("\n═══ REGISTER STUDENT ═══");
        
        int id = getIntInput("Enter Student ID: ");
        
        if (studentMap.containsKey(id)) {
            System.out.println("❌ Student with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        Student student = new Student(id, name, phone);
        studentMap.put(id, student);
        
        actionHistory.push("REGISTER_STUDENT:" + id);
        
        System.out.println("✅ Student registered successfully!");
        student.displayInfo();
    }
    
    // Register new driver
    private static void registerDriver() {
        System.out.println("\n═══ REGISTER DRIVER ═══");
        
        int id = getIntInput("Enter Driver ID: ");
        
        if (driverMap.containsKey(id)) {
            System.out.println("❌ Driver with ID " + id + " already exists!");
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
        
        System.out.println("✅ Driver registered successfully!");
        driver.displayInfo();
    }
    
    // View all students
    private static void viewAllStudents() {
        System.out.println("\n═══ ALL REGISTERED STUDENTS ═══");
        
        if (studentMap.isEmpty()) {
            System.out.println("❌ No students registered yet!");
            return;
        }
        
        System.out.println("Total Students: " + studentMap.size());
        System.out.println("────────────────────────────────────────");
        
        // Note: Since we can't iterate HashMap directly, we'll ask user for IDs
        // In a complete implementation, you'd add an getAllKeys() method to HashMap
        System.out.println("Student IDs registered: 101, 102, 103 (sample data)");
        System.out.println("Enter Student ID to view details (or 0 to go back): ");
        
        int id = getIntInput("");
        if (id != 0) {
            Student student = studentMap.get(id);
            if (student != null) {
                student.displayInfo();
            } else {
                System.out.println("❌ Student not found!");
            }
        }
    }
    
    // View all drivers
    private static void viewAllDrivers() {
        System.out.println("\n═══ ALL REGISTERED DRIVERS ═══");
        
        if (driverMap.isEmpty()) {
            System.out.println("❌ No drivers registered yet!");
            return;
        }
        
        System.out.println("Total Drivers: " + driverMap.size());
        System.out.println("────────────────────────────────────────");
        
        System.out.println("Driver IDs registered: 201, 202, 203 (sample data)");
        System.out.println("Enter Driver ID to view details (or 0 to go back): ");
        
        int id = getIntInput("");
        if (id != 0) {
            Driver driver = driverMap.get(id);
            if (driver != null) {
                driver.displayInfo();
            } else {
                System.out.println("❌ Driver not found!");
            }
        }
    }
    
    // Generate ride request
    private static void generateRideRequest() {
        System.out.println("\n═══ GENERATE RIDE REQUEST ═══");
        
        int studentID = getIntInput("Enter Student ID: ");
        
        Student student = studentMap.get(studentID);
        if (student == null) {
            System.out.println("❌ Student not found!");
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
            System.out.println("❌ Invalid priority!");
            return;
        }
        
        Request request = new Request(requestCounter++, studentID, pickup, drop, priority);
        requestMap.put(request.getRequestID(), request);
        
        actionHistory.push("GENERATE_REQUEST:" + request.getRequestID());
        
        System.out.println("✅ Ride request generated successfully!");
        request.displayInfo();
    }
    
    // View all requests
    private static void viewAllRequests() {
        System.out.println("\n═══ ALL RIDE REQUESTS ═══");
        
        if (requestMap.isEmpty()) {
            System.out.println("❌ No requests available!");
            return;
        }
        
        System.out.println("Total Requests: " + requestMap.size());
        System.out.println("────────────────────────────────────────");
        
        System.out.println("Request IDs: 1, 2, 3... (enter ID to view details)");
        int id = getIntInput("Enter Request ID (or 0 to go back): ");
        
        if (id != 0) {
            Request request = requestMap.get(id);
            if (request != null) {
                request.displayInfo();
            } else {
                System.out.println("❌ Request not found!");
            }
        }
    }
    
    // Match driver to request
    private static void matchDriverToRequest() {
        System.out.println("\n═══ MATCH DRIVER TO REQUEST ═══");
        
        int requestID = getIntInput("Enter Request ID: ");
        Request request = requestMap.get(requestID);
        
        if (request == null) {
            System.out.println("❌ Request not found!");
            return;
        }
        
        if (!request.getStatus().equals("Pending")) {
            System.out.println("❌ Request is already " + request.getStatus());
            return;
        }
        
        int driverID = getIntInput("Enter Driver ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("❌ Driver not found!");
            return;
        }
        
        if (!driver.isAvailable()) {
            System.out.println("❌ Driver is not available!");
            return;
        }
        
        // Match driver to request
        request.setAssignedDriverID(driverID);
        request.setStatus("Accepted");
        driver.setAvailable(false);
        driver.incrementTotalRides();
        
        actionHistory.push("MATCH_DRIVER:" + requestID + ":" + driverID);
        
        System.out.println("✅ Driver matched successfully!");
        System.out.println("Driver: " + driver.getName() + " assigned to Request #" + requestID);
    }
    
    // View student ride history
    private static void viewStudentRideHistory() {
        System.out.println("\n═══ STUDENT RIDE HISTORY ═══");
        
        int studentID = getIntInput("Enter Student ID: ");
        Student student = studentMap.get(studentID);
        
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        student.displayInfo();
        
        // Find all requests for this student
        System.out.println("\n📋 Ride History:");
        boolean found = false;
        
        for (int i = 1; i < requestCounter; i++) {
            Request req = requestMap.get(i);
            if (req != null && req.getStudentID() == studentID) {
                System.out.println("\nRequest #" + i);
                System.out.println("  From: " + req.getPickupLocation());
                System.out.println("  To: " + req.getDropLocation());
                System.out.println("  Status: " + req.getStatus());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No ride history found for this student.");
        }
    }
    
    // View driver accepted requests
    private static void viewDriverAcceptedRequests() {
        System.out.println("\n═══ DRIVER ACCEPTED REQUESTS ═══");
        
        int driverID = getIntInput("Enter Driver ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("❌ Driver not found!");
            return;
        }
        
        driver.displayInfo();
        
        System.out.println("\n📋 Accepted Requests:");
        boolean found = false;
        
        for (int i = 1; i < requestCounter; i++) {
            Request req = requestMap.get(i);
            if (req != null && req.getAssignedDriverID() != null && 
                req.getAssignedDriverID() == driverID) {
                System.out.println("\nRequest #" + i);
                System.out.println("  Student ID: " + req.getStudentID());
                System.out.println("  From: " + req.getPickupLocation());
                System.out.println("  To: " + req.getDropLocation());
                System.out.println("  Status: " + req.getStatus());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No accepted requests for this driver.");
        }
    }
    
    // Undo last action
    private static void undoLastAction() {
        System.out.println("\n═══ UNDO LAST ACTION ═══");
        
        if (actionHistory.isEmpty()) {
            System.out.println("❌ No actions to undo!");
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
                System.out.println("✅ Student registration undone!");
                break;
                
            case "REGISTER_DRIVER":
                int driverID = Integer.parseInt(parts[1]);
                driverMap.remove(driverID);
                System.out.println("✅ Driver registration undone!");
                break;
                
            case "GENERATE_REQUEST":
                int requestID = Integer.parseInt(parts[1]);
                requestMap.remove(requestID);
                System.out.println("✅ Request generation undone!");
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
                System.out.println("✅ Driver matching undone!");
                break;
                
            default:
                System.out.println("❌ Unknown action type!");
        }
    }
    
    // View action history
    private static void viewActionHistory() {
        System.out.println("\n═══ ACTION HISTORY ═══");
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
        scanner.nextLine(); // Consume newline
        return value;
    }
}