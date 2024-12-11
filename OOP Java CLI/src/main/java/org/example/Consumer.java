package org.example;

import java.util.logging.*;

public class Consumer implements Runnable {
    private static final Logger logger = Logger.getLogger(Consumer.class.getName());
    private final ResourcePool resourcePool;
    private final int retrievalInterval; // Retrieval interval in milliseconds
    private boolean active;

    public Consumer(ResourcePool resourcePool, int retrievalInterval) {
        this.resourcePool = resourcePool;
        this.retrievalInterval = retrievalInterval;
        this.active = true;
    }

    @Override
    public void run() {
        while (active) {
            if (!resourcePool.removeResource()) {
                // Log that the consumer attempted retrieval with no resources available
                logger.warning("Consumer attempted retrieval, but no resources are available.");
            } else {
                // Log successful resource retrieval
                logger.info("Consumer successfully retrieved a resource.");
            }

            try {
                Thread.sleep(retrievalInterval); // Wait before the next retrieval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.SEVERE, "Consumer thread was interrupted", e);
                break; // Exit loop gracefully if interrupted
            }
        }
    }

    // Method to stop the consumer thread gracefully
    public void stop() {
        active = false;
    }
}
