package GUI;

import Controller.StudentController;
import Entity.Student;
import Entity.Course;
import Interfaces.IStudentOperations;
import Utility.Validator;
import Exception.InvalidCGPAException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentCourseManagerPage extends JFrame implements ActionListener, IStudentOperations {
    StudentController controller;

    // Student Panel Components
    JTextField idTF, nameTF, cgpaTF;
    JButton addStudentBtn, updateStudentBtn, deleteStudentBtn, searchBtn, viewBtn;

    // Course Panel Components
    JTextField cCodeTF, cTitleTF, cCreditTF;
    JButton addCourseBtn, viewCoursesBtn;

    // Enrollment Panel Components
    JTextField enrollIdTF, enrollCodeTF;
    JButton enrollBtn, viewStudentCoursesBtn;

    // General
    JButton saveBtn, loadBtn;
    JTextArea outputArea;

    public StudentCourseManagerPage() {
        super("Student Course Management System");
        this.controller = new StudentController();

        // Settings
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Rookie friendly absolute positioning
        getContentPane().setBackground(new Color(230, 245, 255));

        // --- 1. Student Panel ---
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentPanel.setBounds(20, 20, 400, 220);
        studentPanel.setBackground(new Color(230, 245, 255));
        studentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "Student Management", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        add(studentPanel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 30, 80, 25);
        studentPanel.add(idLabel);
        idTF = new JTextField();
        idTF.setBounds(80, 30, 120, 25);
        studentPanel.add(idTF);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 25);
        studentPanel.add(nameLabel);
        nameTF = new JTextField();
        nameTF.setBounds(80, 60, 120, 25);
        studentPanel.add(nameTF);

        JLabel cgpaLabel = new JLabel("CGPA:");
        cgpaLabel.setBounds(20, 90, 80, 25);
        studentPanel.add(cgpaLabel);
        cgpaTF = new JTextField();
        cgpaTF.setBounds(80, 90, 120, 25);
        studentPanel.add(cgpaTF);

        // Buttons row 1
        addStudentBtn = new JButton("Add");
        addStudentBtn.setBounds(220, 30, 80, 25);
        addStudentBtn.setBackground(new Color(66, 179, 255));
        addStudentBtn.addActionListener(this);
        studentPanel.add(addStudentBtn);

        updateStudentBtn = new JButton("Update");
        updateStudentBtn.setBounds(305, 30, 80, 25);
        updateStudentBtn.setBackground(new Color(66, 179, 255));
        updateStudentBtn.addActionListener(this);
        studentPanel.add(updateStudentBtn);

        // Buttons row 2
        deleteStudentBtn = new JButton("Delete");
        deleteStudentBtn.setBounds(220, 60, 80, 25);
        deleteStudentBtn.setBackground(new Color(66, 179, 255));
        deleteStudentBtn.addActionListener(this);
        studentPanel.add(deleteStudentBtn);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(305, 60, 80, 25);
        searchBtn.setBackground(new Color(66, 179, 255));
        searchBtn.addActionListener(this);
        studentPanel.add(searchBtn);

        viewBtn = new JButton("View All Students");
        viewBtn.setBounds(20, 130, 360, 30);
        viewBtn.setBackground(new Color(66, 179, 255));
        viewBtn.addActionListener(this);
        studentPanel.add(viewBtn);
        
        saveBtn = new JButton("Save Data");
        saveBtn.setBounds(20, 170, 170, 30);
        saveBtn.setBackground(new Color(100, 200, 100));
        saveBtn.addActionListener(this);
        studentPanel.add(saveBtn);

        loadBtn = new JButton("Load Data");
        loadBtn.setBounds(210, 170, 170, 30);
        loadBtn.setBackground(new Color(100, 200, 100));
        loadBtn.addActionListener(this);
        studentPanel.add(loadBtn);


        // --- 2. Course Panel ---
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(null);
        coursePanel.setBounds(20, 250, 400, 150);
        coursePanel.setBackground(new Color(230, 245, 255));
        coursePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "Course Management", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        add(coursePanel);

        JLabel ccLabel = new JLabel("Code:");
        ccLabel.setBounds(20, 30, 60, 25);
        coursePanel.add(ccLabel);
        cCodeTF = new JTextField();
        cCodeTF.setBounds(70, 30, 80, 25);
        coursePanel.add(cCodeTF);
        
        JLabel ctLabel = new JLabel("Title:");
        ctLabel.setBounds(160, 30, 40, 25);
        coursePanel.add(ctLabel);
        cTitleTF = new JTextField();
        cTitleTF.setBounds(200, 30, 180, 25);
        coursePanel.add(cTitleTF);

        JLabel credLabel = new JLabel("Crd:");
        credLabel.setBounds(20, 65, 60, 25);
        coursePanel.add(credLabel);
        cCreditTF = new JTextField();
        cCreditTF.setBounds(70, 65, 80, 25);
        coursePanel.add(cCreditTF);
        
        addCourseBtn = new JButton("Add Course");
        addCourseBtn.setBounds(170, 65, 100, 25);
        addCourseBtn.setBackground(new Color(66, 179, 255));
        addCourseBtn.addActionListener(this);
        coursePanel.add(addCourseBtn);
        
        viewCoursesBtn = new JButton("View All Available Courses");
        viewCoursesBtn.setBounds(20, 105, 360, 30);
        viewCoursesBtn.setBackground(new Color(66, 179, 255));
        viewCoursesBtn.addActionListener(this);
        coursePanel.add(viewCoursesBtn);


        // --- 3. Enrollment Panel ---
        JPanel enrollPanel = new JPanel();
        enrollPanel.setLayout(null);
        enrollPanel.setBounds(20, 410, 400, 120);
        enrollPanel.setBackground(new Color(230, 245, 255));
        enrollPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "Enrollment", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        add(enrollPanel);
        
        JLabel eIdLabel = new JLabel("Std ID:");
        eIdLabel.setBounds(20, 30, 60, 25);
        enrollPanel.add(eIdLabel);
        enrollIdTF = new JTextField();
        enrollIdTF.setBounds(80, 30, 100, 25);
        enrollPanel.add(enrollIdTF);
        
        JLabel eCodeLabel = new JLabel("C. Code:");
        eCodeLabel.setBounds(190, 30, 60, 25);
        enrollPanel.add(eCodeLabel);
        enrollCodeTF = new JTextField();
        enrollCodeTF.setBounds(250, 30, 100, 25);
        enrollPanel.add(enrollCodeTF);
        
        enrollBtn = new JButton("Enroll Student in Course");
        enrollBtn.setBounds(20, 70, 360, 30);
        enrollBtn.setBackground(new Color(66, 179, 255));
        enrollBtn.addActionListener(this);
        enrollPanel.add(enrollBtn);


        // --- Output Area ---
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBounds(440, 25, 420, 510);
        add(scroll);

        // Initial welcome
        outputArea.setText("Welcome to Student Course Management System!\n");
        outputArea.append("Load data to begin or start adding new records.\n");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentBtn) addStudent();
        else if (e.getSource() == viewBtn) viewAll();
        else if (e.getSource() == searchBtn) searchStudent();
        else if (e.getSource() == updateStudentBtn) updateStudent();
        else if (e.getSource() == deleteStudentBtn) deleteStudent();
        
        else if (e.getSource() == addCourseBtn) addCourse();
        else if (e.getSource() == viewCoursesBtn) viewAllCourses();
        
        else if (e.getSource() == enrollBtn) enrollStudent();
        
        else if (e.getSource() == saveBtn) {
            controller.saveData();
            outputArea.setText("All Data Saved Successfully!");
        } else if (e.getSource() == loadBtn) {
            controller.loadData();
            outputArea.setText("Data Loaded Successfully!");
            viewAll();
        }
    }

    @Override
    public void addStudent() {
        try {
            String id = idTF.getText();
            String name = nameTF.getText();
            if(id.isEmpty() || name.isEmpty()) {
                showError("ID and Name cannot be empty.");
                return;
            }
            double cgpa = Double.parseDouble(cgpaTF.getText());
            Validator.validateCGPA(cgpa);
            
            Student s = new Student(id, name, cgpa);
            controller.insertStudent(s);
            
            showSuccess("Student Added:\n" + s.toString());
            clearStudentFields();
        } catch (NumberFormatException ex) {
            showError("CGPA must be a valid number.");
        } catch (InvalidCGPAException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    public void addCourse() {
        try {
            String code = cCodeTF.getText();
            String title = cTitleTF.getText();
            if(code.isEmpty() || title.isEmpty()) {
                showError("Course Code and Title required.");
                return;
            }
            int credit = Integer.parseInt(cCreditTF.getText());
            
            Course c = new Course(code, title, credit);
            controller.insertCourse(c);
            
            showSuccess("Course Added:\n" + c.toString());
            cCodeTF.setText("");
            cTitleTF.setText("");
            cCreditTF.setText("");
        } catch (NumberFormatException ex) {
            showError("Credit must be an integer.");
        }
    }

    @Override
    public void searchStudent() {
        String id = idTF.getText();
        if(id.isEmpty()) {
             showError("Enter ID to search.");
             return;
        }
        Student s = controller.getStudent(id);
        if(s != null) {
            outputArea.setText("Student Found:\nResults:\n" + s.getDetails());
            outputArea.append("\n------------------\nEnrolled in:\n");
            Course[] courses = s.getCourses();
            for(int i=0; i<s.getCourseCount(); i++) {
                if(courses[i] != null) outputArea.append(courses[i].toString() + "\n");
            }
        } else {
            showError("Student not found: " + id);
        }
    }

    public void viewAll() {
        Student[] students = controller.getAllStudents();
        outputArea.setText("--- ALL STUDENTS ---\n");
        boolean found = false;
        for(int i=0; i<students.length; i++) {
            if(students[i] != null) {
                outputArea.append(students[i].toString() + "\n");
                found = true;
            }
        }
        if(!found) outputArea.append("No students.");
    }

    public void viewAllCourses() {
        Course[] courses = controller.getAllCourses();
        outputArea.setText("--- AVAILABLE COURSES ---\n");
        boolean found = false;
        for(int i=0; i<controller.getCourseCount(); i++) {
            if(courses[i] != null) {
                outputArea.append(courses[i].toString() + "\n");
                found = true;
            }
        }
        if(!found) outputArea.append("No courses available.");
    }
    
    public void updateStudent() {
        try {
            String id = idTF.getText();
            double cgpa = Double.parseDouble(cgpaTF.getText());
            Validator.validateCGPA(cgpa);
            controller.updateStudent(id, cgpa);
            showSuccess("Updated student " + id);
        } catch (Exception ex) {
             showError("Update failed: " + ex.getMessage());
        }
    }
    
    public void deleteStudent() {
        String id = idTF.getText();
        if(!id.isEmpty()) {
            controller.deleteStudent(id);
            showSuccess("Deleted student " + id);
        } else {
            showError("Enter ID to delete.");
        }
    }
    
    public void enrollStudent() {
         String id = enrollIdTF.getText();
         String code = enrollCodeTF.getText();
         
         if(id.isEmpty() || code.isEmpty()) {
             showError("Enter both Student ID and Course Code.");
             return;
         }
         
         // Basic pre-check validation could go here, but controller handles logic
         controller.enrollStudent(id, code);
         
         // Only way to know if it worked without return type is to check, 
         // but for rookie project we just assume success or print generic message.
         // Let's improve controller feedback next time, but for now:
         showSuccess("Enrollment processed for " + id + " in " + code + ".\n(Check 'Search' to verify)");
    }
    
    private void showError(String msg) {
        outputArea.setForeground(Color.RED);
        outputArea.setText("ERROR: " + msg);
    }
    
    private void showSuccess(String msg) {
        outputArea.setForeground(new Color(0, 100, 0));
        outputArea.setText("SUCCESS: " + msg);
    }
    
    private void clearStudentFields() {
        idTF.setText("");
        nameTF.setText("");
        cgpaTF.setText("");
    }
}
