package com.singleton.pattern;

/**
 * Double-Checked Locking Singleton Pattern
 * The instance is created on first use with minimal synchronization overhead.
 * Uses volatile keyword to ensure thread safety without excessive locking.
 */
public class DoubleCheckedLockingSingleton {

    // volatile ensures visibility across threads
    private static volatile DoubleCheckedLockingSingleton instance;

    // Private constructor to prevent instantiation
    private DoubleCheckedLockingSingleton() {
        System.out.println("DoubleCheckedLockingSingleton instance created");
    }

    // Double-checked locking pattern
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public String getInfo() {
        return "Double-Checked Locking Singleton - Lazy loading with optimized locking";
    }
}
