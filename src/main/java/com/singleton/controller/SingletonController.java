package com.singleton.controller;

import com.singleton.pattern.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/singleton")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SingletonController {

    /**
     * Get information about Eager Singleton
     */
    @GetMapping("/eager")
    public ResponseEntity<?> getEagerSingleton() {
        try {
            EagerSingleton singleton = EagerSingleton.getInstance();
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Eager Initialization Singleton");
            response.put("message", singleton.getInfo());
            response.put("instance", System.identityHashCode(singleton));
            response.put("description", "Instance created at class loading time. Always exists in memory.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get information about Lazy Singleton
     */
    @GetMapping("/lazy")
    public ResponseEntity<?> getLazySingleton() {
        try {
            LazySingleton singleton1 = LazySingleton.getInstance();
            LazySingleton singleton2 = LazySingleton.getInstance();
            
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Lazy Initialization Singleton (Synchronized)");
            response.put("message", singleton1.getInfo());
            response.put("instance1", System.identityHashCode(singleton1));
            response.put("instance2", System.identityHashCode(singleton2));
            response.put("isSameInstance", singleton1 == singleton2);
            response.put("description", "Instance created on first use. Thread-safe with synchronization overhead.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get information about Double-Checked Locking Singleton
     */
    @GetMapping("/double-checked")
    public ResponseEntity<?> getDoubleCheckedLockingSingleton() {
        try {
            DoubleCheckedLockingSingleton singleton1 = DoubleCheckedLockingSingleton.getInstance();
            DoubleCheckedLockingSingleton singleton2 = DoubleCheckedLockingSingleton.getInstance();
            
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Double-Checked Locking Singleton");
            response.put("message", singleton1.getInfo());
            response.put("instance1", System.identityHashCode(singleton1));
            response.put("instance2", System.identityHashCode(singleton2));
            response.put("isSameInstance", singleton1 == singleton2);
            response.put("description", "Lazy loading with optimized locking using volatile keyword.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get information about Bill Pugh Singleton
     */
    @GetMapping("/bill-pugh")
    public ResponseEntity<?> getBillPughSingleton() {
        try {
            BillPughSingleton singleton1 = BillPughSingleton.getInstance();
            BillPughSingleton singleton2 = BillPughSingleton.getInstance();
            
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Bill Pugh Singleton (Static Inner Class)");
            response.put("message", singleton1.getInfo());
            response.put("instance1", System.identityHashCode(singleton1));
            response.put("instance2", System.identityHashCode(singleton2));
            response.put("isSameInstance", singleton1 == singleton2);
            response.put("description", "Uses class loader mechanism for thread safety. Best practice approach.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get information about Enum Singleton
     */
    @GetMapping("/enum")
    public ResponseEntity<?> getEnumSingleton() {
        try {
            EnumSingleton singleton1 = EnumSingleton.INSTANCE;
            EnumSingleton singleton2 = EnumSingleton.INSTANCE;
            
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Enum Singleton");
            response.put("message", singleton1.getInfo());
            response.put("instance1", System.identityHashCode(singleton1));
            response.put("instance2", System.identityHashCode(singleton2));
            response.put("isSameInstance", singleton1 == singleton2);
            response.put("description", "Most robust approach. Thread-safe, serialization-proof, reflection-proof.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get information about Database Connection Singleton
     */
    @GetMapping("/database")
    public ResponseEntity<?> getDatabaseSingleton() {
        try {
            DatabaseConnection db1 = DatabaseConnection.getInstance();
            DatabaseConnection db2 = DatabaseConnection.getInstance();
            
            db1.connect("jdbc:mysql://localhost:3306/mydb");
            
            Map<String, Object> response = new HashMap<>();
            response.put("type", "Database Connection Singleton");
            response.put("instance1", System.identityHashCode(db1));
            response.put("instance2", System.identityHashCode(db2));
            response.put("isSameInstance", db1 == db2);
            response.put("isConnected", db1.isConnected());
            response.put("connectionString", db1.getConnectionString());
            response.put("description", "Practical use case - ensures single database connection throughout application.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Get all singleton patterns overview
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllPatterns() {
        Map<String, Object> patterns = new HashMap<>();
        
        patterns.put("eager", new HashMap<String, String>() {{
            put("description", "Instance created at class loading time");
            put("advantage", "Simple and thread-safe");
            put("disadvantage", "Always consumes memory even if not used");
        }});
        
        patterns.put("lazy", new HashMap<String, String>() {{
            put("description", "Instance created on first use with synchronization");
            put("advantage", "Memory efficient");
            put("disadvantage", "Synchronization overhead on every call");
        }});
        
        patterns.put("doubleChecked", new HashMap<String, String>() {{
            put("description", "Lazy loading with optimized locking");
            put("advantage", "Better performance than pure synchronized");
            put("disadvantage", "More complex code");
        }});
        
        patterns.put("billPugh", new HashMap<String, String>() {{
            put("description", "Uses static inner class loader");
            put("advantage", "Thread-safe without synchronization overhead");
            put("disadvantage", "Less readable for beginners");
        }});
        
        patterns.put("enum", new HashMap<String, String>() {{
            put("description", "Uses Java enum");
            put("advantage", "Most robust - thread-safe, serialization-proof, reflection-proof");
            put("disadvantage", "Cannot be extended");
        }});
        
        patterns.put("database", new HashMap<String, String>() {{
            put("description", "Real-world example of singleton usage");
            put("advantage", "Prevents multiple database connections");
            put("disadvantage", "Not suitable for multi-database scenarios");
        }});
        
        Map<String, Object> response = new HashMap<>();
        response.put("patterns", patterns);
        return ResponseEntity.ok(response);
    }
}
