package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool {
    private final List<Integer> resourcePool;
    private final int maxCapacity;

    // Constructor to initialize the resource pool and max capacity
    public ResourcePool(int initialResources, int maxCapacity) {
        this.resourcePool = new ArrayList<>();
        this.maxCapacity = maxCapacity;

        // Initially populate the pool with resources
        for (int i = 0; i < initialResources; i++) {
            resourcePool.add(1); // Each resource is represented by '1'
        }
    }

    // Synchronized method for adding resources to the pool
    public synchronized void addResources(int resourcesToAdd) {
        int addedCount = 0;
        for (int i = 0; i < resourcesToAdd; i++) {
            if (resourcePool.size() < maxCapacity) {
                resourcePool.add(1); // Add a resource
                addedCount++;
            } else {
                break; // Stop adding if capacity is reached
            }
        }
        System.out.println("Supplier added " + addedCount + " resources. Total resources in pool: " + resourcePool.size());
    }

    // Synchronized method for removing a resource from the pool
    public synchronized boolean removeResource() {
        if (!resourcePool.isEmpty()) {
            resourcePool.remove(resourcePool.size() - 1); // Remove a resource
            System.out.println("Consumer retrieved 1 resource. Remaining resources in pool: " + resourcePool.size());
            return true;
        }
        System.out.println("No resources available for retrieval.");
        return false; // No resources available to retrieve
    }

    // Synchronized method to get the number of available resources
    public synchronized int getAvailableResources() {
        return resourcePool.size();
    }

    // Method to display the current pool status
    public synchronized void displayPoolStatus() {
        System.out.println("Current pool size: " + resourcePool.size() + " / " + maxCapacity);
    }
}
