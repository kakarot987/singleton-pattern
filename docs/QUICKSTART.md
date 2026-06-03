# Singleton Pattern Demo - Quick Start Guide

## 🎯 What You Have

A complete full-stack application demonstrating the **Singleton Design Pattern** with:

- ✅ **6 different Singleton implementations** in Java
- ✅ **Spring Boot REST API** backend
- ✅ **React interactive frontend** 
- ✅ **Gradle build system**
- ✅ **Complete documentation**

## ⚡ Quick Start (5 minutes)

### Step 1: Start Backend
```bash
cd singleton-demo
./gradlew bootRun
```
✓ Backend runs on http://localhost:8080

### Step 2: Start Frontend (New Terminal)
```bash
cd singleton-demo
npm install
npm start
```
✓ Frontend opens at http://localhost:3000

### Step 3: Explore
- Click buttons to see each singleton pattern
- Observe instance hash codes (same = singleton working!)
- Read descriptions for each pattern

## 📁 Project Structure

```
singleton-demo/
├── Backend (Java/Spring Boot)
│   ├── src/main/java/com/example/singleton/
│   │   ├── SingletonDemoApplication.java (entry point)
│   │   ├── controller/SingletonController.java (REST API)
│   │   └── pattern/ (6 singleton implementations)
│   ├── build.gradle (Gradle config)
│   └── src/main/resources/application.properties
│
├── Frontend (React)
│   ├── src/main/frontend/
│   │   ├── App.jsx (main component)
│   │   ├── App.css (styling)
│   │   └── index.jsx (entry point)
│   ├── public/index.html
│   └── package.json
│
└── Documentation
    ├── README.md (project overview)
    ├── RUNNING.md (how to run)
    └── ARCHITECTURE.md (detailed design)
```

## 🏗️ 6 Singleton Implementations

| # | Type | Best For | Thread-Safe |
|---|------|----------|-------------|
| 1 | Eager Initialization | Simple cases | ✓ |
| 2 | Lazy Synchronized | Memory critical | ✓ |
| 3 | Double-Checked Lock | Performance needed | ✓ |
| 4 | **Bill Pugh** | **Production code** | ✓ |
| 5 | **Enum** | **Most robust** | ✓ |
| 6 | Database Connection | Real-world example | ✓ |

## 🔌 REST API Endpoints

```
GET /api/singleton/eager
GET /api/singleton/lazy
GET /api/singleton/double-checked
GET /api/singleton/bill-pugh
GET /api/singleton/enum
GET /api/singleton/database
GET /api/singleton/all
```

## 💻 Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3.1.5**
- **Gradle 7.0+**
- **H2 Database** (optional)

### Frontend
- **React 18.2**
- **Node.js 14+**
- **npm**
- **Vanilla CSS** (no frameworks)

## 🧪 Key Concepts Demonstrated

### 1. Singleton Pattern
- Private constructor prevents instantiation
- Static instance holder
- Public access method returns same instance

### 2. Thread Safety
- Synchronized methods
- Volatile keyword
- Class loader guarantees
- Double-checked locking

### 3. Spring Boot Integration
- REST controllers
- CORS configuration
- Application properties
- Dependency injection

### 4. Frontend Integration
- Fetch API for HTTP requests
- Component state management
- Dynamic UI updates
- Error handling

## 📊 Instance Hash Comparison

When you click buttons, you'll see:
```
First Call Hash:  1234567890
Second Call Hash: 1234567890
Same Instance:    ✓ Yes
```

This proves the singleton is working - same hash means same object!

## 🔍 Important Files

### Java Implementations
- `EagerSingleton.java` - Simplest, always in memory
- `LazySingleton.java` - Simple lazy loading
- `DoubleCheckedLockingSingleton.java` - Optimized locking
- `BillPughSingleton.java` - **Recommended approach**
- `EnumSingleton.java` - **Most robust approach**
- `DatabaseConnection.java` - Real-world use case

### API
- `SingletonController.java` - REST endpoints
- `SingletonDemoApplication.java` - Spring Boot entry point

### Frontend
- `App.jsx` - Main React component (all UI logic)
- `App.css` - Responsive styling

## 🚨 Troubleshooting

### Port conflicts?
```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9

# Kill process on port 3000
lsof -ti:3000 | xargs kill -9
```

### npm issues?
```bash
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

### Java version?
```bash
java -version  # Must be 17+
```

## 📚 Learning Path

1. **Start Here:** README.md - Understand the pattern
2. **Run It:** RUNNING.md - Get it working
3. **Explore Code:** Study the 6 implementations
4. **Deep Dive:** ARCHITECTURE.md - Understand design
5. **Experiment:** Modify code, add features

## 💡 Recommended Next Steps

1. Run the application
2. Click each singleton button to see them in action
3. Open browser DevTools (F12) → Network tab
4. Watch API calls to backend
5. Check the instance hashes - they prove it's working!
6. Read ARCHITECTURE.md for deep understanding
7. Study the code comments in each implementation

## 🎯 Key Takeaways

✓ **Singleton Pattern** - Restricts instantiation to single object
✓ **Multiple Approaches** - Each has trade-offs
✓ **Bill Pugh & Enum** - Best practices in Java
✓ **Thread Safety** - Critical in concurrent applications
✓ **Spring Integration** - Beans are singletons by default
✓ **Full Stack** - Backend API + Frontend UI

## 📞 Need Help?

1. Check RUNNING.md for detailed setup instructions
2. Review ARCHITECTURE.md for technical details
3. Check error messages in console (browser F12 or terminal)
4. Verify both backend and frontend are running
5. Test API manually: `curl http://localhost:8080/api/singleton/all`

## 🎓 Educational Value

This project teaches:
- ✓ Design patterns in practice
- ✓ Java OOP principles
- ✓ Thread safety and concurrency
- ✓ Spring Boot framework
- ✓ Full-stack development
- ✓ REST API design
- ✓ React fundamentals
- ✓ Frontend-backend integration

---

**Ready to explore Singletons? Start with `./gradlew bootRun` and `npm start`! 🚀**
