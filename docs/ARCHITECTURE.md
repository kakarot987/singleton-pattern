# Singleton Design Pattern - Architecture & Design Documentation

## 📐 Project Architecture

### System Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                         CLIENT SIDE                             │
│                        (React Frontend)                         │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │                    React Components                      │  │
│  │  • App.jsx (Main component)                              │  │
│  │  • Pattern buttons for each implementation              │  │
│  │  • Result display cards                                  │  │
│  └──────────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │                   CSS Styling                            │  │
│  │  • App.css (Responsive design)                           │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
                            │
                     HTTP (REST API)
                            │
                            ▼
┌─────────────────────────────────────────────────────────────────┐
│                         SERVER SIDE                             │
│                   (Spring Boot Backend)                         │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │                    REST Controller                       │  │
│  │  SingletonController.java                               │  │
│  │  • /api/singleton/eager                                  │  │
│  │  • /api/singleton/lazy                                   │  │
│  │  • /api/singleton/double-checked                         │  │
│  │  • /api/singleton/bill-pugh                              │  │
│  │  • /api/singleton/enum                                   │  │
│  │  • /api/singleton/database                               │  │
│  │  • /api/singleton/all                                    │  │
│  └──────────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │                  Singleton Classes                       │  │
│  │  • EagerSingleton.java                                   │  │
│  │  • LazySingleton.java                                    │  │
│  │  • DoubleCheckedLockingSingleton.java                    │  │
│  │  • BillPughSingleton.java                                │  │
│  │  • EnumSingleton.java                                    │  │
│  │  • DatabaseConnection.java                               │  │
│  └──────────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              Spring Boot Framework                       │  │
│  │  • Embedded Tomcat Server (Port 8080)                   │  │
│  │  • CORS Configuration                                    │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

## 🔄 Request/Response Flow

```
1. User Interaction
   └── Click "Eager Singleton" button
   
2. Frontend Action
   └── fetchSingletonData('eager')
   └── GET http://localhost:8080/api/singleton/eager
   
3. Backend Processing
   └── SingletonController.getEagerSingleton()
   └── EagerSingleton.getInstance()
   └── Create JSON response with instance info
   
4. Response Delivery
   └── JSON response sent to frontend
   
5. Frontend Display
   └── Update UI with singleton information
   └── Show instance hash codes
   └── Display pattern description
```

## 📦 Singleton Pattern Implementations

### 1. Eager Singleton

**File:** `EagerSingleton.java`

```java
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

**Characteristics:**
- Instance created at class loading time
- Thread-safe by default (JVM guarantees)
- No lazy loading
- Simple and straightforward

**Use Case:** When the object is always needed

---

### 2. Lazy Singleton

**File:** `LazySingleton.java`

```java
public class LazySingleton {
    private static LazySingleton instance;
    
    private LazySingleton() {}
    
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

**Characteristics:**
- Instance created on first use
- Thread-safe with synchronization
- Performance overhead on every call
- Good for memory-constrained environments

**Use Case:** When object creation is expensive and may not be needed

---

### 3. Double-Checked Locking Singleton

**File:** `DoubleCheckedLockingSingleton.java`

```java
public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;
    
    private DoubleCheckedLockingSingleton() {}
    
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
}
```

**Characteristics:**
- Lazy loading with optimized locking
- Volatile keyword ensures visibility
- Checks instance twice (before and after lock)
- Better performance than pure synchronization

**Use Case:** Performance-critical applications with lazy loading requirement

---

### 4. Bill Pugh Singleton

**File:** `BillPughSingleton.java`

```java
public class BillPughSingleton {
    private BillPughSingleton() {}
    
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

**Characteristics:**
- Uses static inner class
- Thread-safe without synchronization
- Lazy loading via class loader
- Best practice approach (Joshua Bloch)

**Use Case:** Recommended for most production code

---

### 5. Enum Singleton

**File:** `EnumSingleton.java`

```java
public enum EnumSingleton {
    INSTANCE;
    
    EnumSingleton() {}
    
    public String getInfo() {
        return "Enum Singleton";
    }
}
```

**Characteristics:**
- Java enum guarantee of single instance
- Thread-safe
- Serialization-proof
- Reflection-proof
- Most robust approach

**Use Case:** When robustness against reflection and serialization is critical

---

### 6. Database Connection Singleton

**File:** `DatabaseConnection.java`

```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {}
    
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
    
    public void connect(String connectionString) { /* ... */ }
    public void disconnect() { /* ... */ }
    public boolean isConnected() { /* ... */ }
}
```

**Use Case:** Real-world example managing database connections

---

## 🏆 Comparison Matrix

| Feature | Eager | Lazy | Double-Checked | Bill Pugh | Enum | Database |
|---------|-------|------|-----------------|-----------|------|----------|
| Thread-Safe | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Lazy Loading | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Synchronized | ✗ | ✓ | (Partial) | ✗ | ✗ | ✓ |
| Serializable | ✗ | ✗ | ✗ | ✗ | ✓ | ✗ |
| Reflection Proof | ✗ | ✗ | ✗ | ✗ | ✓ | ✗ |
| Complexity | Low | Low | Medium | Medium | Low | Medium |
| Recommended | No | No | Maybe | **Yes** | **Yes** | Depends |

## 🔐 Security Vulnerabilities

### Serialization Attack

**Problem:** Deserializing a singleton can create new instances

**Solution:**
```java
private Object readResolve() {
    return getInstance();
}
```

### Reflection Attack

**Problem:** Using reflection to call private constructor

**Solution:** Use Enum (cannot be bypassed)

### Cloning Attack

**Problem:** Implementing Cloneable creates copy

**Solution:** Override `clone()` to return singleton instance

## 🎯 Design Patterns Integration

### Spring Beans as Singletons

In Spring Boot, beans are singletons by default:

```java
@Component
public class MyService {
    // Automatically a singleton in Spring
}
```

### Factory Pattern with Singleton

```java
public class SingletonFactory {
    public static MyObject createSingleton() {
        return MySingleton.getInstance();
    }
}
```

## 📊 Performance Implications

### Memory Usage
- **Eager:** High (always in memory)
- **Lazy:** Low (only when needed)
- **Double-Checked:** Low (lazy with optimization)
- **Bill Pugh:** Low (lazy with optimization)
- **Enum:** Medium (Java overhead)

### Access Speed
- **Eager:** O(1) - Direct access
- **Lazy:** O(n) - Synchronized overhead
- **Double-Checked:** O(1) average, O(n) on initialization
- **Bill Pugh:** O(1) - Direct class access
- **Enum:** O(1) - Direct enum access

## 🧵 Thread Safety Analysis

### Eager Singleton (Thread-Safe)
```
Class Loader → Creates instance once → All threads get same instance
```

### Lazy Singleton (Thread-Safe)
```
Thread 1: Acquires lock → Checks null → Creates instance → Releases lock
Thread 2: Waits for lock → Checks null (false) → Returns instance
```

### Double-Checked Locking (Thread-Safe)
```
Initial check (no lock) → Lock if needed → Second check → Create if null
Most of the time: No lock required (faster)
```

### Bill Pugh (Thread-Safe)
```
Class Loader → Creates SingletonHolder on first getInstance() call
→ Creates INSTANCE in nested class → Returns to caller
```

## 🚀 Spring Boot Integration

### Configuration
- **Port:** 8080 (configurable in `application.properties`)
- **Context Path:** / (root)
- **CORS:** Enabled for all origins
- **Database:** H2 in-memory (optional)

### Dependency Injection
Singletons can be injected into Spring beans:

```java
@RestController
public class MyController {
    private final MyService service = MyService.getInstance();
    // or
    @Autowired
    private MyService service; // If managed by Spring
}
```

## 📱 Frontend Architecture

### React Components
```
App (Main Component)
├── State Management
│   ├── activePattern
│   ├── singletonData
│   ├── loading
│   └── patterns
├── Effects
│   └── fetchAllPatterns() on mount
├── Sections
│   ├── Header
│   ├── Pattern Buttons
│   ├── Result Display
│   └── Patterns Overview
└── Functions
    └── fetchSingletonData(endpoint)
```

### HTTP Communication
- Uses native `fetch()` API
- CORS-enabled backend
- Error handling and loading states
- JSON response parsing

## 🔄 API Response Structure

```json
{
  "type": "Eager Initialization Singleton",
  "message": "Eager Singleton - Instance created at class load time",
  "instance": "1234567890",
  "description": "Instance created at class loading time. Always exists in memory."
}
```

## 🛠️ Development Workflow

### Backend Development Cycle
1. Edit Java files in `src/main/java/`
2. Rebuild with `./gradlew build`
3. Restart application
4. Test with API endpoint or frontend

### Frontend Development Cycle
1. Edit React files in `src/main/frontend/`
2. Changes auto-compile (hot reload)
3. Browser updates automatically
4. Check browser console for errors

## 📋 Code Organization

### Package Structure
```
com.singleton/
├── SingletonDemoApplication.java    (Entry point)
├── controller/
│   └── SingletonController.java     (REST endpoints)
└── pattern/
    ├── EagerSingleton.java
    ├── LazySingleton.java
    ├── DoubleCheckedLockingSingleton.java
    ├── BillPughSingleton.java
    ├── EnumSingleton.java
    └── DatabaseConnection.java
```

## 🎓 Learning Outcomes

After studying this project, you should understand:

1. ✓ What the Singleton pattern is and when to use it
2. ✓ Different implementations of Singleton in Java
3. ✓ Thread-safety concerns in multi-threaded environments
4. ✓ Performance trade-offs between implementations
5. ✓ Security vulnerabilities and mitigations
6. ✓ Integration with Spring Boot framework
7. ✓ How to build a full-stack application (Java + React)
8. ✓ REST API design and consumption

## 📚 Further Reading

- Joshua Bloch - "Effective Java" (Item 3: Enforce the singleton property)
- Gang of Four - "Design Patterns: Elements of Reusable Object-Oriented Software"
- Spring Framework Documentation - Bean Scope
- Java Language Specification - Class Initialization

---

**Created for educational purposes**
