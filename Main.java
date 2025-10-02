import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create shared lists
        List<Person> people = new ArrayList<>();
        List<Course> courseCatalog = new ArrayList<>();

        // Create Admin, Teachers, Students
        Admin admin = new Admin("A-001", "Aisha", "aisha@campus.com");
        Teacher t1 = new Teacher("T-100", "Mr. John", "john@campus.com", "Mathematics", 1200.0);
        Teacher t2 = new Teacher("T-101", "Ms. Mary", "mary@campus.com", "Physics", 1300.0);
        Student s1 = new Student("S-200", "Nora", "nora@student.com", 11);
        Student s2 = new Student("S-201", "Sam", "sam@student.com", 12);

        // Add to people list
        people.add(admin);
        people.add(t1);
        people.add(t2);
        people.add(s1);
        people.add(s2);

        // Admin adds courses
        admin.addCourse(courseCatalog, new Course("MATH101", "Calculus"));
        admin.addCourse(courseCatalog, new Course("PHYS101", "Mechanics"));
        admin.addCourse(courseCatalog, new Course("CS101", "Algorithms"));

        // Assign teachers
        admin.assignTeacherToCourse(courseCatalog, "MATH101", t1);
        admin.assignTeacherToCourse(courseCatalog, "PHYS101", t2);

        // Students enroll
        s1.enrollCourse(findCourse(courseCatalog, "MATH101"));
        s1.enrollCourse(findCourse(courseCatalog, "CS101"));
        s2.enrollCourse(findCourse(courseCatalog, "PHYS101"));

        // Polymorphism demo
        System.out.println("\n=== PEOPLE INFO ===");
        for (Person person : people) {
            person.displayInfo();
            System.out.println();
        }

        // Interface demo
        System.out.println("=== PAYABLES ===");
        List<Payable> payables = new ArrayList<>();
        payables.add(t1);
        payables.add(t2);
        payables.add(s1);
        payables.add(s2);

        for (Payable pay : payables) {
            System.out.println(pay.getClass().getSimpleName() + " payment: " + pay.calculatePayment());
        }

        // Show courses
        System.out.println("\n=== COURSE CATALOG ===");
        for (Course c : courseCatalog) {
            System.out.println(c);
        }

        // Admin removes a course
        admin.removeCourse(courseCatalog, "CS101");

        System.out.println("\nAfter removal:");
        for (Course c : courseCatalog) {
            System.out.println(c);
        }

        // Casting example
        System.out.println("\n=== Casting Example ===");
        Person somePerson = s2;
        System.out.println("somePerson is instance of Student? " + (somePerson instanceof Student));
        Student casted = (Student) somePerson;
        System.out.println("Casted student enrolled courses: " + casted.getEnrolledCourses().size());
    }

    // Helper method
    private static Course findCourse(List<Course> catalog, String code) {
        for (Course c : catalog) {
            if (c.getCourseCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }
}
