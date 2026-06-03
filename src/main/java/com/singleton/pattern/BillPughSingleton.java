package com.singleton.pattern;

/**
 * Bill Pugh Singleton Pattern (Static Inner Class)
 * Uses class loader mechanism for thread safety.
 * The inner class is loaded only when getInstance() is called.
 * This is considered the best approach for Java singletons.
 */
public class BillPughSingleton {

    // Private constructor
    private BillPughSingleton() {
        System.out.println("BillPughSingleton instance created");
    }

    // Static inner class holder
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // Public method to get instance
    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getInfo() {
        return "Bill Pugh Singleton - Static inner class loader pattern (best practice)";
    }
}
