import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Payable {
    private int gradeLevel;
    private List<Course> enrolledCourses;

    public Student(String id, String name, String email, int gradeLevel) {
        super(id, name, email);
        this.gradeLevel = gradeLevel;
        this.enrolledCourses = new ArrayList<>();
    }

    public void enrollCourse(Course c) {
        if (c != null && enrolledCourses.size() < Course.MAX_STUDENTS) {
            enrolledCourses.add(c);
            System.out.println(getName() + " enrolled in " + c.getCourseName());
        }
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + toString());
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Enrolled Courses: ");
        for (Course c : enrolledCourses) {
            System.out.println(" - " + c.getCourseName());
        }
    }

    @Override
    public double calculatePayment() {
        // Example: tuition = 100 per course
        return enrolledCourses.size() * 100;
    }
}
