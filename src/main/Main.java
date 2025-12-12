package main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--------- Welcome to Hamdard Connect ---------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        option = sc.nextInt();

        switch(option) {
             case 1:
                 
                 break;
             default:
                 throw new AssertionError();
        }
    }
}