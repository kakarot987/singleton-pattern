package com.singleton.pattern;

/**
 * Database Connection Singleton
 * Demonstrates a practical use case of the Singleton pattern.
 * Ensures only one database connection is used throughout the application.
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private String connectionString;
    private boolean isConnected = false;

    // Private constructor
    private DatabaseConnection() {
        System.out.println("DatabaseConnection instance created");
    }

    // Bill Pugh pattern for thread-safe lazy loading
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect(String connectionString) {
        this.connectionString = connectionString;
        this.isConnected = true;
        System.out.println("Connected to: " + connectionString);
    }

    public void disconnect() {
        this.isConnected = false;
        System.out.println("Disconnected from database");
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getConnectionString() {
        return connectionString;
    }
}
