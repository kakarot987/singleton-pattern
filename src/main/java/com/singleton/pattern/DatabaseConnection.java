package com.singleton.pattern;

/**
 * Educational singleton holding *simulated* database-connection metadata.
 *
 * No JDBC connection is created, opened, or closed here. Production Spring
 * applications should normally obtain connections from a managed DataSource
 * pool rather than sharing a single JDBC Connection across threads.
 */
public final class DatabaseConnection {

    // Double-checked locking requires volatile for safe publication.
    private static volatile DatabaseConnection instance;
    private String connectionString;
    private boolean connected;

    private DatabaseConnection() {
        System.out.println("Simulated DatabaseConnection singleton created");
    }

    // Double-checked locking (NOT the Bill Pugh holder pattern).
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

    /** Only updates demo state; does not establish a network/database connection. */
    public synchronized void connect(String connectionString) {
        if (connectionString == null || connectionString.isBlank()) {
            throw new IllegalArgumentException("Connection string is required");
        }
        this.connectionString = connectionString;
        this.connected = true;
    }

    public synchronized void disconnect() {
        this.connected = false;
    }

    public synchronized boolean isConnected() {
        return connected;
    }

    public synchronized String getConnectionString() {
        return connectionString;
    }
}
