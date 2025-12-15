package main;

import java.util.*;
import service.Register;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        
        System.out.println("--------- Welcome to Hamdard Connect ---------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        sc.nextLine();

        switch(option) {
             case 1:
                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter CMS ID: ");
                String cms = sc.nextLine();

                System.out.print("Enter Phone Number: ");
                String phone = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                System.out.print("Enter Department: ");
                String dept = sc.nextLine();

                System.out.print("Enter Program: ");
                String program = sc.nextLine();

                System.out.print("Enter Gender: ");
                String gender = sc.nextLine();

                System.out.println("Select Role:");
                System.out.println("1. Normal Student");
                System.out.println("2. Rider");
                System.out.print("Choice: ");
                int roleChoice = sc.nextInt();
                sc.nextLine(); // buffer clear

                String role;
                Register user;

                if (roleChoice == 2) {
                    role = "Rider";

                    // -------- CREATE USER --------
                    user = new Register(cms, phone, name, password, dept, program, gender, role);

                    // -------- VEHICLE DETAILS --------
                    System.out.print("Enter Vehicle Type (Bike/Car): ");
                    String vType = sc.nextLine();

                    System.out.print("Enter Vehicle Model: ");
                    String vModel = sc.nextLine();

                    System.out.print("Enter Vehicle Number: ");
                    String vNumber = sc.nextLine();

                    System.out.print("Enter Vehicle Color: ");
                    String color = sc.nextLine();

                    user.addVehicle(vType, vModel, vNumber, color);

                    System.out.println("\n--- Registration Successful ---");
                    System.out.println("Name: " + name);
                    System.out.println("Role: Rider");
                    System.out.println("Vehicle: " + vType + " | " + vModel + " | " + vNumber);
                } 
                else {
                    role = "Normal Student";

                    user = new Register(cms, phone, name, password, dept, program, gender, role);

                    System.out.println("\n--- Registration Successful ---");
                    System.out.println("Name: " + name);
                    System.out.println("Role: Normal Student");
                }
                break;
             default:
                System.out.println("Invalid option");
        }
    }
}