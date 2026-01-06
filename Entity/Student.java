package Entity;

public class Student extends Person {
    private double cgpa;
    private Course[] courses;
    private int courseCount;

    public static int totalStudents;

    public Student() {
        super();
        this.courses = new Course[10]; // Basic fixed size array
        this.courseCount = 0;
        totalStudents++;
    }

    public Student(String id, String name, double cgpa) {
        super(id, name);
        this.cgpa = cgpa;
        this.courses = new Course[10];
        this.courseCount = 0;
        totalStudents++;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void addCourse(Course c) {
        if (courseCount < courses.length) {
            courses[courseCount] = c;
            courseCount++;
        }
    }

    public String getDetails() {
        return "Student ID: " + id + ", Name: " + name + ", CGPA: " + cgpa;
    }

    @Override
    public String toString() {
        return getDetails();
    }
    
    public Course[] getCourses() {
        return courses;
    }
    
    public int getCourseCount() {
        return courseCount;
    }
}
