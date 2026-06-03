package com.singleton.pattern;

/**
 * Enum Singleton Pattern
 * Java guarantees that enum values are instantiated only once.
 * This is the most thread-safe and serialization-proof approach.
 * It also prevents reflection attacks.
 */
public enum EnumSingleton {
    INSTANCE;

    private String message = "Enum Singleton - Most robust singleton pattern";

    // Constructor
    EnumSingleton() {
        System.out.println("EnumSingleton instance created");
    }

    public String getInfo() {
        return message;
    }

    // You can add other methods here
    public void setMessage(String msg) {
        this.message = msg;
    }
}
