package com.restaurant.presentation;

import java.util.Scanner;

public class ChefMenu {
    static int choice = 0;
    public static void printMenu(Scanner sc){
        do{
            System.out.println("\n========================================================");
            System.out.println("       CHEF SYSTEM");
            System.out.println("========================================================");
            System.out.println("1. Menu Items Management");
            System.out.println("2. Exit");
            System.out.print("\nNhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
        }while(choice != 2);
    }
}
