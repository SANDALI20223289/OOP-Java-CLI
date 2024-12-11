package org.example;

import java.util.logging.*;

public class Supplier implements Runnable {
    private static final Logger logger = Logger.getLogger(Supplier.class.getName());
    private final ResourcePool resourcePool;
    private final int supplyRate; // Number of resources to supply at a time
    private boolean active;

    public Supplier(ResourcePool resourcePool, int supplyRate) {
        this.resourcePool = resourcePool;
        this.supplyRate = supplyRate;
        this.active = true;
    }

    @Override
    public void run() {
        while (active) {
            try {
                Thread.sleep(1000); // Simulate time delay for supplying resources
                resourcePool.addResources(supplyRate); // Add resources to the pool
                logger.info("Supplier added " + supplyRate + " resources to the pool.");
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Supplier thread was interrupted", e);
                Thread.currentThread().interrupt();
                break; // Exit loop gracefully if interrupted
            }
        }
    }

    // Method to stop the supplier thread gracefully
    public void stop() {
        active = false;
    }
}
