package Controller;

import Entity.Student;
import Entity.Course;
import File.FileIO;

public class StudentController {
    private Student[] students;
    private int studentCount;
    
    private Course[] courses;
    private int courseCount;

    public StudentController() {
        this.students = new Student[100];
        this.courses = new Course[50];
        this.studentCount = 0;
        this.courseCount = 0;
        
        loadData();
        
        // If no courses loaded, add defaults
        if (courseCount == 0) {
            insertCourse(new Course("CSE110", "Java Programming", 3));
            insertCourse(new Course("ENG101", "English 1", 3));
            insertCourse(new Course("MAT120", "Calculus", 3));
            insertCourse(new Course("PHY101", "Physics", 3));
        }
    }

    public void insertStudent(Student s) {
        if(studentCount < students.length) {
            students[studentCount] = s;
            studentCount++;
            saveData();
        } else {
            System.out.println("Database full!");
        }
    }
    
    public void updateStudent(String id, double cgpa) {
        Student s = getStudent(id);
        if(s != null) {
            s.setCgpa(cgpa);
            saveData();
        }
    }
    
    public void deleteStudent(String id) {
        int index = -1;
        for(int i = 0; i < studentCount; i++) {
            if(students[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        
        if(index != -1) {
            for(int i = index; i < studentCount - 1; i++) {
                students[i] = students[i+1];
            }
            students[studentCount - 1] = null;
            studentCount--;
            saveData();
        }
    }
    
    public Student getStudent(String id) {
        for(int i = 0; i < studentCount; i++) {
            if(students[i].getId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }
    
    public void insertCourse(Course c) {
        if(courseCount < courses.length) {
            // check duplicate
            for(int i=0; i<courseCount; i++) {
                if(courses[i].getCourseCode().equals(c.getCourseCode())) {
                    return; // Duplicate
                }
            }
            courses[courseCount] = c;
            courseCount++;
            saveData();
        }
    }
    
    public void enrollStudent(String studentId, String courseCode) {
        Student s = getStudent(studentId);
        Course c = null;
        for(int i = 0; i < courseCount; i++) {
            if(courses[i].getCourseCode().equals(courseCode)) {
                c = courses[i];
                break;
            }
        }
        
        if(s != null && c != null) {
            s.addCourse(c);
        }
    }
    
    public Student[] getAllStudents() {
        return students;
    }
    
    public void saveData() {
        FileIO.save(students, studentCount);
        FileIO.saveCourses(courses, courseCount);
    }
    
    public void loadData() {
        Student[] loaded = FileIO.load();
        
        // Recalculate count
        int count = 0;
        for(int i=0; i<loaded.length; i++) {
            if(loaded[i] != null) {
                count++;
            }
        }
        
        this.students = loaded;
        this.studentCount = count;
        
        // Load courses
        Course[] loadedCourses = FileIO.loadCourses();
        int cCount = 0;
        for(int i=0; i<loadedCourses.length; i++) {
            if(loadedCourses[i] != null) {
                cCount++;
            }
        }
        if (cCount > 0) {
            this.courses = loadedCourses;
            this.courseCount = cCount;
        }
    }
    
    public Course[] getAllCourses() {
        return courses; 
    }
    
    public int getCourseCount() {
        return courseCount;
    }
}
