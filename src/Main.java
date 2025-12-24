import datastructures.HashMap;
import models.Driver;
import models.Student;

public class Main {
    public static void main(String[] args) {
        // Test 1: Simple Integer HashMap
        System.out.println("=== Test 1: Integer HashMap ===");
        HashMap<Integer, String> map1 = new HashMap<>();
        
        map1.put(3285, "Farjaz Kashif");
        map1.put(3286, "Ali Ahmed");
        map1.put(3287, "Sara Khan");
        
        map1.display();
        
        System.out.println("\nSearch 3285: " + map1.get(3285));
        System.out.println("Search 9999: " + map1.get(9999));
        
        // Test 2: Student HashMap
        System.out.println("\n\n=== Test 2: Student HashMap ===");
        HashMap<Integer, Student> studentMap = new HashMap<>();
        
        Student s1 = new Student(3285, "Farjaz Kashif", "03001234567");
        Student s2 = new Student(3286, "Ali Ahmed", "03009876543");
        Student s3 = new Student(3287, "Sara Khan", "03001111111");
        
        studentMap.put(s1.getStudentID(), s1);
        studentMap.put(s2.getStudentID(), s2);
        studentMap.put(s3.getStudentID(), s3);
        
        System.out.println("Total students: " + studentMap.size());
        
        // Search student
        Student found = studentMap.get(3285);
        if (found != null) {
            found.displayInfo();
        }
        
        // Test 3: Driver HashMap
        System.out.println("\n\n=== Test 3: Driver HashMap ===");
        HashMap<Integer, Driver> driverMap = new HashMap<>();
        
        Driver d1 = new Driver(101, "Ahmed Ali", "03001234567", "Bike", "ABC-123");
        Driver d2 = new Driver(102, "Hassan Khan", "03009876543", "Car", "XYZ-456");
        
        driverMap.put(d1.getDriverID(), d1);
        driverMap.put(d2.getDriverID(), d2);
        
        driverMap.display();
        
        // Test 4: Remove operation
        System.out.println("\n\n=== Test 4: Remove Operation ===");
        map1.remove(3286);
        System.out.println("After removing 3286:");
        map1.display();
        
        // Test 5: Update operation
        System.out.println("\n\n=== Test 5: Update Operation ===");
        map1.put(3285, "Farjaz Updated");
        System.out.println("After updating 3285:");
        map1.display();
    }
}