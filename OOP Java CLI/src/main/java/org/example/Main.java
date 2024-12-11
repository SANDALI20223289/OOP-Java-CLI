package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input parameters interactively
        System.out.println("Enter Configuration Parameters:");
        int totalTickets = validateInput(scanner, "Total Tickets: ");
        int ticketReleaseRate = validateInput(scanner, "Ticket Release Rate: ");
        int customerRetrievalRate = validateInput(scanner, "Customer Retrieval Rate: ");
        int maxTicketCapacity = validateInput(scanner, "Max Ticket Capacity: ");

        // Display the entered configuration
        System.out.println("\nConfiguration Loaded:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Max Ticket Capacity: " + maxTicketCapacity);

        // Initialize ResourcePool and other components based on the input
        ResourcePool resourcePool = new ResourcePool(maxTicketCapacity, totalTickets);
        Consumer consumer = new Consumer(resourcePool, customerRetrievalRate);
        Supplier supplier = new Supplier(resourcePool, ticketReleaseRate);

        // Start threads for Consumer and Supplier
        Thread consumerThread = new Thread(consumer);
        Thread supplierThread = new Thread(supplier);

        consumerThread.start();
        supplierThread.start();

        // Run the simulation for 10 seconds and then stop
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the Consumer and Supplier threads
        consumer.stop();
        supplier.stop();

        try {
            consumerThread.join();
            supplierThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the final status of the resource pool
        resourcePool.displayPoolStatus();
        System.out.println("Program finished.");
    }

    // Method to validate and retrieve user input
    private static int validateInput(Scanner scanner, String prompt) {
        int input = -1;
        while (input <= 0) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Input must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return input;
    }
}