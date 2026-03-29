package com.restaurant.presentation;

import java.util.Scanner;

public class CustomerMenu {
    static int choice = 0;
    public static void printMenu(Scanner sc){
        do{
            System.out.println("\n========================================================");
            System.out.println("       CUSTOMER SYSTEM");
            System.out.println("========================================================");
            System.out.println("1. Reserve Table");
            System.out.println("2. Update Profile");
            System.out.println("3. Exit");
            System.out.print("\nNhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
        }while(choice != 3);
    }
}
