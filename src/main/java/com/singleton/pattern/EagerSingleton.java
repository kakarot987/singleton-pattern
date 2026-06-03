package com.singleton.pattern;

/**
 * Eager Initialization Singleton Pattern
 * The instance is created at class loading time.
 * This is thread-safe and simple but the instance is always created.
 */
public class EagerSingleton {

    // Create the instance at class loading time
    private static final EagerSingleton instance = new EagerSingleton();

    // Private constructor to prevent instantiation
    private EagerSingleton() {
        System.out.println("EagerSingleton instance created");
    }

    // Public method to get the instance
    public static EagerSingleton getInstance() {
        return instance;
    }

    public String getInfo() {
        return "Eager Singleton - Instance created at class load time";
    }
}
