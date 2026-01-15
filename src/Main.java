import datastructures.Graph;
import datastructures.HashMap;
import datastructures.Stack;
import java.util.Scanner;
import models.Driver;
import models.Request;
import models.Student;

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
                    registerRiderGoingHome();
                    break;
                case 2:
                    viewDriverAcceptedRequests();
                    break;
                case 3:
                    updateDriverLocation();
                    break;
                case 4:
                    studentNeedsRide();
                    break;
                case 5:
                    viewAvailableRidersForStudent();
                    break;
                case 6:
                    bookARider();
                    break;
                case 7:
                    viewStudentRideHistory();
                    break;
                case 8:
                    viewAllDrivers();
                    break;
                case 9:
                    viewAllStudents();
                    break;
                case 10:
                    viewAllRequests();
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
        System.out.println("            HAMDARD CONNECT                     ");
        System.out.println("   Connect with riders going your way!          ");
        System.out.println("================================================");
        System.out.println("\n--- RIDER OPTIONS ---");
        System.out.println("1. I am going home (Register as Rider)");
        System.out.println("2. View my accepted rides");
        System.out.println("3. Update my location");
        System.out.println("\n--- STUDENT OPTIONS ---");
        System.out.println("4. I need a ride (Create ride request)");
        System.out.println("5. View available riders going my way");
        System.out.println("6. Book a rider");
        System.out.println("7. View my ride history");
        System.out.println("\n--- ADMIN/VIEW OPTIONS ---");
        System.out.println("8. View all riders");
        System.out.println("9. View all students");
        System.out.println("10. View all ride requests");
        System.out.println("\n0. Exit");
        System.out.println("================================================");
    }
    
    // Initialize sample data
    private static void initializeSampleData() {
        System.out.println("Initializing Hamdard Connect...\n");
        
        // Sample students who need rides
        Student s1 = new Student(101, "Ali Ahmed", "03001234567");
        Student s2 = new Student(102, "Sara Khan", "03009876543");
        Student s3 = new Student(103, "Hassan Raza", "03001111111");
        
        studentMap.put(s1.getStudentID(), s1);
        studentMap.put(s2.getStudentID(), s2);
        studentMap.put(s3.getStudentID(), s3);
        
        // Sample riders going home
        Driver d1 = new Driver(201, "Ahmed Ali", "03002222222", "Bike", "ABC-123");
        Driver d2 = new Driver(202, "Bilal Khan", "03003333333", "Car", "XYZ-456");
        Driver d3 = new Driver(203, "Imran Shah", "03004444444", "Bike", "DEF-789");
        
        d1.setCurrentLocation("Gate");
        d2.setCurrentLocation("Gate");
        d3.setCurrentLocation("Gate");
        
        driverMap.put(d1.getDriverID(), d1);
        driverMap.put(d2.getDriverID(), d2);
        driverMap.put(d3.getDriverID(), d3);
        
        // Setup campus routes
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
        
        System.out.println("System ready!");
        System.out.println("Sample users loaded (3 students, 3 riders)");
        System.out.println("Campus routes configured\n");
    }
    
    // Rider: Register as going home
    private static void registerRiderGoingHome() {
        System.out.println("\n=== I AM GOING HOME ===");
        
        int id = getIntInput("Enter your ID: ");
        
        Driver driver = driverMap.get(id);
        
        if (driver == null) {
            System.out.println("ID not found. Let's register you first.");
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter Vehicle Type (Bike/Car): ");
            String vehicleType = scanner.nextLine();
            
            System.out.print("Enter Vehicle Number: ");
            String vehicleNumber = scanner.nextLine();
            
            driver = new Driver(id, name, phone, vehicleType, vehicleNumber);
            driverMap.put(id, driver);
            
            actionHistory.push("REGISTER_DRIVER:" + id);
        }
        
        System.out.print("Where are you going? (e.g., Gulshan, Clifton, DHA): ");
        String destination = scanner.nextLine();
        
        driver.setCurrentLocation("Gate");
        driver.setAvailable(true);
        
        System.out.println("\nYou are now ACTIVE and going to " + destination);
        System.out.println("Students can now see you in available riders list!");
        driver.displayInfo();
    }
    
    // Student: I need a ride
    private static void studentNeedsRide() {
        System.out.println("\n=== I NEED A RIDE ===");
        
        int studentID = getIntInput("Enter your Student ID: ");
        
        Student student = studentMap.get(studentID);
        
        if (student == null) {
            System.out.println("ID not found. Let's register you first.");
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            
            student = new Student(studentID, name, phone);
            studentMap.put(studentID, student);
            
            actionHistory.push("REGISTER_STUDENT:" + studentID);
        }
        
        System.out.print("Where do you want to go? ");
        String destination = scanner.nextLine();
        
        System.out.println("\nSelect urgency:");
        System.out.println("1. Normal (going at regular time)");
        System.out.println("2. Medium (finishing early)");
        System.out.println("3. High (need to leave soon)");
        System.out.println("4. Emergency (missed transport)");
        int priority = getIntInput("Enter urgency (1-4): ");
        
        if (priority < 1 || priority > 4) {
            priority = 1;
        }
        
        Request request = new Request(requestCounter++, studentID, "Gate", destination, priority);
        requestMap.put(request.getRequestID(), request);
        
        actionHistory.push("GENERATE_REQUEST:" + request.getRequestID());
        
        System.out.println("\nRide request created successfully!");
        System.out.println("Request ID: " + request.getRequestID());
        System.out.println("\nNext step: View available riders going your way (Option 5)");
    }
    
    // Student: View available riders
    private static void viewAvailableRidersForStudent() {
        System.out.println("\n=== RIDERS GOING YOUR WAY ===");
        
        System.out.print("Where do you want to go? ");
        String destination = scanner.nextLine();
        
        System.out.println("\nAvailable riders:");
        System.out.println("------------------------------------------------");
        
        boolean found = false;
        for (int id : knownDriverIDs) {
            Driver d = driverMap.get(id);
            if (d != null && d.isAvailable()) {
                System.out.println("\n[ID: " + id + "] " + d.getName());
                System.out.println("   Vehicle: " + d.getVehicleType() + " (" + d.getVehicleNumber() + ")");
                System.out.println("   Phone: " + d.getPhoneNumber());
                System.out.println("   Rating: " + String.format("%.1f", d.getRating()) + "/5.0");
                System.out.println("   Total Rides: " + d.getTotalRides());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No riders available right now.");
            System.out.println("Try again in a few minutes or create a ride request.");
        }
        
        System.out.println("------------------------------------------------");
        System.out.println("\nRemember the ID and use Option 6 to book!");
    }
    
    // Student: Book a rider
    private static void bookARider() {
        System.out.println("\n=== BOOK A RIDER ===");
        
        int requestID = getIntInput("Enter your Request ID: ");
        Request request = requestMap.get(requestID);
        
        if (request == null) {
            System.out.println("Request not found! Please create a ride request first (Option 4)");
            return;
        }
        
        if (!request.getStatus().equals("Pending")) {
            System.out.println("This request is already " + request.getStatus());
            return;
        }
        
        System.out.println("\nYour request details:");
        request.displayInfo();
        
        System.out.println("\nAvailable riders:");
        viewAvailableDriversShort();
        
        int driverID = getIntInput("\nEnter Rider ID you want to book: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Rider not found!");
            return;
        }
        
        if (!driver.isAvailable()) {
            System.out.println("Rider is not available anymore!");
            return;
        }
        
        request.setAssignedDriverID(driverID);
        request.setStatus("Accepted");
        driver.setAvailable(false);
        driver.incrementTotalRides();
        driver.addAcceptedRequest(requestID);
        
        Student student = studentMap.get(request.getStudentID());
        if (student != null) {
            student.addRideToHistory(requestID);
        }
        
        actionHistory.push("MATCH_DRIVER:" + requestID + ":" + driverID);
        
        System.out.println("\n=== RIDE CONFIRMED ===");
        System.out.println("Rider: " + driver.getName());
        System.out.println("Phone: " + driver.getPhoneNumber());
        System.out.println("Vehicle: " + driver.getVehicleType() + " " + driver.getVehicleNumber());
        System.out.println("\nContact your rider and meet at the pickup point!");
    }
    
    // View all students
    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        
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
                System.out.println("   Total Rides: " + s.getTotalRides());
            }
        }
        System.out.println("------------------------------------------------");
    }
    
    // View all drivers
    private static void viewAllDrivers() {
        System.out.println("\n=== ALL RIDERS ===");
        
        if (driverMap.isEmpty()) {
            System.out.println("No riders registered yet!");
            return;
        }
        
        System.out.println("Total Riders: " + driverMap.size());
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
                System.out.println("   Going to: " + req.getDropLocation());
                System.out.println("   Urgency: " + req.getPriority());
                System.out.println("   Status: " + req.getStatus());
                if (req.getAssignedDriverID() != null) {
                    System.out.println("   Rider ID: " + req.getAssignedDriverID());
                }
            }
        }
        System.out.println("------------------------------------------------");
    }
    
    // View student ride history
    private static void viewStudentRideHistory() {
        System.out.println("\n=== MY RIDE HISTORY ===");
        
        int studentID = getIntInput("Enter your Student ID: ");
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
        System.out.println("\n=== MY ACCEPTED RIDES ===");
        
        int driverID = getIntInput("Enter your Rider ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Rider not found!");
            return;
        }
        
        driver.displayInfo();
        driver.displayAcceptedRequests();
    }
    
    // Update driver location
    private static void updateDriverLocation() {
        System.out.println("\n=== UPDATE MY LOCATION ===");
        
        int driverID = getIntInput("Enter your Rider ID: ");
        Driver driver = driverMap.get(driverID);
        
        if (driver == null) {
            System.out.println("Rider not found!");
            return;
        }
        
        System.out.println("Current location: " + driver.getCurrentLocation());
        System.out.print("Enter new location: ");
        String newLocation = scanner.nextLine();
        
        driver.setCurrentLocation(newLocation);
        System.out.println("Location updated successfully!");
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