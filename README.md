Java CLI Ticket Handling System
Introduction

This Java-based Command-Line Interface (CLI) project is a simulation of a ticket handling system, designed to demonstrate key programming concepts such as multithreading, synchronization, and real-time logging. The system efficiently handles tickets using the producer-consumer model.
Features

    System Configuration
        Customize parameters like:
            Total tickets to be handled.
            Ticket release rate.
            Customer retrieval rate.
            Maximum ticket capacity.
    Robust Input Validation
        Ensures user inputs are valid with clear error messages and re-prompting.
    Real-Time Monitoring
        Tracks ticket status in real time and logs transactions directly in the CLI.
    Command Execution
        Start and stop operations with simple commands.
    Multithreaded Operation
        Implements the Producer-Consumer Pattern with synchronization (synchronized blocks and Lock classes).

System Requirements

    Java Version: JDK 8 or newer.
    Development Environment: Any text editor or IDE that supports Java (e.g., IntelliJ IDEA, Eclipse, VS Code).
    Platform: Works on Windows, macOS, or Linux with a terminal/command prompt.

Setup Instructions
Step 1: Clone the Repository

git clone <repository-url>

Step 2: Compile the Project

Navigate to the project folder and compile the Java files:

cd <project-directory>
javac Main.java

Step 3: Run the Program

Execute the program with:

java Main

Usage Guide
Step 1: Configure the System

    Prompts: Enter values for the following parameters:
        totalTickets: Total number of tickets available.
        ticketReleaseRate: Rate at which tickets are produced (tickets/second).
        customerRetrievalRate: Rate at which tickets are consumed (tickets/second).
        maxTicketCapacity: Maximum number of tickets the system can store at one time.
    Validation: The program ensures all inputs are valid and will prompt again for corrections.

Step 2: Control the Operations

    Commands:
        Type start to begin ticket production and consumption.
        Type stop to terminate operations gracefully.

Step 3: Monitor in Real Time

    Live Updates:
        Watch tickets being added and removed.
        View logs of every transaction directly in the CLI.
