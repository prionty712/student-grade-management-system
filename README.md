# Student Course Management System

A simple desktop application built with Java to manage student records and course enrollments. This project is designed to follow basic Object-Oriented Programming (OOP) principles
## Features

* **Student Management**: Add, search, update, and delete student records (ID, Name, CGPA).
* **Course Management**: Create new courses with codes, titles, and credit hours.
* **Enrollment**: Enroll specific students into available courses.
* **File Persistence**: Automatically saves and loads data using `.txt` files.
* **Custom Validation**: Includes a custom exception to ensure CGPA is between 0.0 and 4.0.
* **GUI**: User-friendly interface built using Java Swing.

## Project Structure

* **Start.java**: The main entry point of the application.
* **Entity**: Contains data models like `Person`, `Student`, and `Course`.
* **Controller**: Handles the logic for managing students and courses.
* **GUI**: Contains the `StudentCourseManagerPage` for the visual interface.
* **File**: Manages reading and writing data to `data.txt` and `courses.txt`.
* **Utility/Exception**: Handles input validation and custom error messages.



## OOP Concepts Used

* **Inheritance**: `Student` extends `Person`.
* **Encapsulation**: Private fields with public getters and setters.
* **Abstraction**: Uses the `IStudentOperations` interface.
* **Polymorphism**: Method overriding (e.g., `toString`).
* **Association**: "Has-A" relationship (Student has an array of Courses).
* **Exception Handling**: Custom `InvalidCGPAException` and `try-catch` blocks.

