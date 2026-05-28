# Console-Based Library Management System

A production-grade, command-line interface (CLI) Library Management System built using **Java (v17)** and structured with the **Maven** project object design model. This application provides librarians with robust computational mechanisms to seamlessly manage book inventories, handle member enrollments, process core transaction routines, and maintain absolute state tracking across runtime instances.

---

## 📋 Project Overview

The core objective of this project is to implement an enterprise-standard terminal system that replaces manual bookkeeping with structured computer automation. 

### Goals & Objectives:
- **Absolute Encapsulation:** Enforce strict data security boundaries across distinct entity layers using data hiding and access control.
- **Fail-Safe Processing:** Guard application stability by gracefully trapping erroneous system conditions, parsing string boundaries, and protecting against data corruption.
- **Data Lifecycle Maintenance:** Establish persistent state management that automatically commits system memory configurations directly to physical flat-file snapshots upon change vectors.
- **Computational Tracking:** Provide a mathematical tracking engine capable of parsing dates, evaluating chronos-intervals, and calculating dynamic monetary fines for overdue items.

---

## 🛠️ Setup & Configuration Instructions

Follow these step-by-step instructions to initialize your local execution workspace.

### Prerequisites
Ensure that the **Java Development Kit (JDK 17)** or a newer version is installed on your host system environment variables pathing. Verify using your terminal:
```bash
java -version
```

## Installation & Run Steps
Open your terminal interface (such as Windows PowerShell or VS Code Integrated Terminal).

Navigate directly into the project root directory where your code is hosted:

```Bash
cd D:\Projects\week3-library-system
```
Compile all source code files explicitly using UTF-8 text encoding to ensure smooth cross-platform representation:

```Bash
javac -encoding utf-8 -d bin src/main/java/library/*.java
```
Launch the fully compiled application class binary using the command below:

```Bash
java -cp bin library.Main
```

## 📂 Project Directory Structure
The project conforms to the strict architectural standards demanded by automated software compilation engines:
```Bash
week3-library-system/
│── data/
│   ├── books.txt
│   └── members.txt
│── src/
│   ├── main/
│   │   └── java/
│   │       └── library/
│   │           ├── Book.java
│   │           ├── FileHandler.java
│   │           ├── Library.java
│   │           ├── Main.java
│   │           └── Member.java
│   └── resources/
│── .gitignore
│── pom.xml
└── README.md
```

## 📸 Visual Documentation
Below is the expected behavioral framework of the terminal interface layout during execution:

1. Main Terminal System Menu Navigation
<img width="1236" height="615" alt="image" src="https://github.com/user-attachments/assets/901c1069-2ee4-4f3e-8a3a-a43d356ede1b" />

2. Active Inventory Retrieval Matrix
<img width="1239" height="424" alt="image" src="https://github.com/user-attachments/assets/d6ed0fd4-1bf5-4bcc-b165-bd7a54c9b576" />

3. Library Statistics
<img width="1248" height="384" alt="image" src="https://github.com/user-attachments/assets/b8145735-61d1-4660-b175-c5bf0d87b7a8" />

## ⚙️ Technical Details & Architecture

### 1. Object-Oriented Programming (OOP) Blueprint
Encapsulation: All attributes across entity models (Book.java, Member.java) use the private modifier access control layout. Safe data propagation is achieved exclusively via exposed accessor methods (Getters/Setters) checking validation vectors.

Robust Abstraction: The entry routine handles menu logic by communicating with the high-level API methods of Library.java while remaining totally agnostic of internal lookup logic or serialization file mechanics.

### 2. Core Data Structures & Search Mechanics
Collections Mapping: Memory-bound entities are mapped using sequential java.util.List arrays backed by dynamically resizing concrete ArrayList instantiations.

Java Streams & Lambda Implementations: Instead of heavy iterative looping, searches filter arrays concurrently using computational stream pipelines matching partial lower-case attributes:

Java
```Bash
public List<Book> searchBooks(String keyword) {
    return books.stream()
        .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                     b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                     b.getIsbn().contains(keyword))
        .collect(Collectors.toList());
}
```

### 3. State Continuity Engine (File I/O Serialization)
Data durability is backed by Java Object Serialization implementing the java.io.Serializable tag interface. The system maps operational objects down to binary files via ObjectOutputStream wraps.

When the console initializes, FileHandler safely reads these file handles back via ObjectInputStream down-casts. If the files are absent on a clean build, it intercepts the exception to automatically build a data/ folder directory seamlessly.

## 📋 Testing Evidence & Validation Matrix

The application handles operational inputs through strict validation layers. The table below documents the core test cases executed to verify system stability, error interception, and transactional business logic:

| Test Case ID | Feature Scope | Input Scenario / Test Action | Expected System Validation Response | Status |
| :--- | :--- | :--- | :--- | :---: |
| **TC-001** | Main Menu Routing | User enters an invalid choice string (e.g., `"abc"` or `9`) | Intercepts `NumberFormatException`; displays `"Enter a valid integer."` or `"Invalid choice (1-8)."` without crashing. | **PASSED** |
| **TC-002** | Book Inventory | Adding a new book with an already existing `ISBN` key | Aborts operation; displays `"Error: A book with this ISBN already exists."` to maintain database integrity. | **PASSED** |
| **TC-003** | Member Directory | Registering a new member profile with blank fields for `ID` or `Name` | Triggers structural validation check; ignores empty inputs and safely returns back to the main menu. | **PASSED** |
| **TC-004** | Transaction Logic | Attempting to borrow a book that is already checked out (`Available = false`) | Verifies record state; flags error and prints that the book is currently unavailable. | **PASSED** |
| **TC-005** | Transaction Logic | Issuing a checkout for a non-existent `ISBN` or un-registered `Member ID` | Validates entity arrays; prints explicit missing entity warning and completely rolls back the transaction. | **PASSED** |
| **TC-006** | Fine Calculation | Checking in an overdue book (Return Date > Predefined 14-day threshold) | Activates `ChronoUnit.DAYS` calculator; dynamically multiplies extra days by `FINE_PER_DAY` and prints fine alert. | **PASSED** |
| **TC-007** | State Continuity | Terminating the application using menu Option `8` | Triggers the `FileHandler` serialization engine, safely writing all memory objects down to `books.txt` and `members.txt`. | **PASSED** |
| **TC-008** | Cold Boot Setup | Launching the system when local data flat-files are missing on disk | Catches `FileNotFoundException`; automatically initializes an empty database structure and creates the data folder. | **PASSED** |


## 🎓 About the Developer

Name: Gaurav Chauhan   


Education: BCA+MCA Dual Degree, Amity University Noida (2027)   

Focus: Full-Stack Development, Data Analytics, and Cloud Computing
