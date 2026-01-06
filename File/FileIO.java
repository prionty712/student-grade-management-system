package File;

import Entity.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public static void save(Student[] students, int count) {
        try {
            FileWriter writer = new FileWriter("data.txt");
            for (int i = 0; i < count; i++) {
                Student s = students[i];
                // Format: ID,Name,CGPA
                writer.write(s.getId() + "," + s.getName() + "," + s.getCgpa() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static Student[] load() {
        Student[] students = new Student[100];
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String name = parts[1];
                    try {
                        double cgpa = Double.parseDouble(parts[2]);
                        Student s = new Student(id, name, cgpa);
                        if (count < students.length) {
                            students[count] = s;
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        // Skip bad data
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
        return students;
    }

    public static void saveCourses(Entity.Course[] courses, int count) {
        try {
            FileWriter writer = new FileWriter("courses.txt");
            for (int i = 0; i < count; i++) {
                Entity.Course c = courses[i];
                // Format: Code,Title,Credit
                writer.write(c.getCourseCode() + "," + c.getTitle() + "," + c.getCredit() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    public static Entity.Course[] loadCourses() {
        Entity.Course[] courses = new Entity.Course[50];
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("courses.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String code = parts[0];
                    String title = parts[1];
                    try {
                        int credit = Integer.parseInt(parts[2]);
                        Entity.Course c = new Entity.Course(code, title, credit);
                        if (count < courses.length) {
                            courses[count] = c;
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        // Skip bad data
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("No previous course data found.");
        }
        return courses;
    }
}
