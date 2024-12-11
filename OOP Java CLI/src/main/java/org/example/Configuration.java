package org.example;

import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Validate and prompt input with a default value
    private static int validateInput(String prompt, int defaultValue) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while (input <= 0) {
            System.out.printf("%s (default: %d): ", prompt, defaultValue);
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty()) {
                input = defaultValue; // Use default if input is empty
            } else {
                try {
                    input = Integer.parseInt(userInput);
                    if (input <= 0) {
                        System.out.println("Input must be a positive integer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
        }
        return input;
    }

    // Input parameters interactively
    public void inputParameters() {
        this.totalTickets = validateInput("Enter total tickets", 50);
        this.ticketReleaseRate = validateInput("Enter ticket release rate", 5);
        this.customerRetrievalRate = validateInput("Enter customer retrieval rate", 3);
        this.maxTicketCapacity = validateInput("Enter max ticket capacity", 100);
    }

    // Save configuration to a file
    public void saveConfig(String filename) {
        try {
            JSONObject configJson = new JSONObject();
            configJson.put("totalTickets", this.totalTickets);
            configJson.put("ticketReleaseRate", this.ticketReleaseRate);
            configJson.put("customerRetrievalRate", this.customerRetrievalRate);
            configJson.put("maxTicketCapacity", this.maxTicketCapacity);

            try (FileWriter file = new FileWriter(filename)) {
                file.write(configJson.toString(4)); // Indent JSON for readability
                System.out.println("Configuration saved successfully to " + filename);
            }
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Load configuration from a file
    public static Configuration loadConfig(String filename) {
        Configuration config = new Configuration();
        File configFile = new File(filename);

        if (!configFile.exists()) {
            System.out.println("Configuration file not found. Creating a new configuration...");
            return createDefaultConfig(filename);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            JSONObject configJson = new JSONObject(content.toString());

            config.setTotalTickets(configJson.getInt("totalTickets"));
            config.setTicketReleaseRate(configJson.getInt("ticketReleaseRate"));
            config.setCustomerRetrievalRate(configJson.getInt("customerRetrievalRate"));
            config.setMaxTicketCapacity(configJson.getInt("maxTicketCapacity"));

            System.out.println("Configuration loaded successfully from " + filename);
        } catch (IOException | org.json.JSONException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            System.out.println("Using default configuration instead...");
            return createDefaultConfig(filename);
        }
        return config;
    }

    // Create a default configuration
    private static Configuration createDefaultConfig(String filename) {
        Configuration defaultConfig = new Configuration();
        defaultConfig.setTotalTickets(50);
        defaultConfig.setTicketReleaseRate(5);
        defaultConfig.setCustomerRetrievalRate(3);
        defaultConfig.setMaxTicketCapacity(100);
        defaultConfig.saveConfig(filename);
        return defaultConfig;
    }

    // Display current configuration
    public void displayConfig() {
        System.out.println("Current Configuration:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Max Ticket Capacity: " + maxTicketCapacity);
    }
}
