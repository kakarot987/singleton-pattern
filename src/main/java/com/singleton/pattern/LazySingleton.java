package com.singleton.pattern;

/**
 * Lazy Initialization Singleton Pattern with Synchronization
 * The instance is created on first use.
 * This is thread-safe but has synchronization overhead.
 */
public class LazySingleton {

    private static LazySingleton instance;

    // Private constructor to prevent instantiation
    private LazySingleton() {
        System.out.println("LazySingleton instance created");
    }

    // Synchronized method to ensure thread safety
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public String getInfo() {
        return "Lazy Singleton - Instance created on first use (with synchronization)";
    }
}
