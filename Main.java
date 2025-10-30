import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Course> courseCatalog = new ArrayList<>();
        Admin admin = null;

        System.out.println("=== CAMPUS MANAGEMENT SYSTEM ===");

        // Create Admin
        System.out.print("Enter Admin ID: ");
        String adminId = input.nextLine();
        System.out.print("Enter Admin Name: ");
        String adminName = input.nextLine();
        System.out.print("Enter Admin Email: ");
        String adminEmail = input.nextLine();
        admin = new Admin(adminId, adminName, adminEmail);
        people.add(admin);

        // Add teachers
        System.out.print("\nHow many teachers to register? ");
        int numTeachers = input.nextInt();
        input.nextLine(); // consume newline
        for (int i = 0; i < numTeachers; i++) {
            System.out.println("\nEnter Teacher " + (i + 1) + " details:");
            System.out.print("ID: ");
            String id = input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Email: ");
            String email = input.nextLine();
            System.out.print("Subject: ");
            String subject = input.nextLine();
            System.out.print("Salary: ");
            double salary = input.nextDouble();
            input.nextLine();
            Teacher teacher = new Teacher(id, name, email, subject, salary);
            people.add(teacher);
        }

        // Add students
        System.out.print("\nHow many students to register? ");
        int numStudents = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter Student " + (i + 1) + " details:");
            System.out.print("ID: ");
            String id = input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Email: ");
            String email = input.nextLine();
            System.out.print("Grade: ");
            int grade = input.nextInt();
            input.nextLine();
            Student student = new Student(id, name, email, grade);
            people.add(student);
        }

        // Add courses
        System.out.print("\nHow many courses to add? ");
        int numCourses = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numCourses; i++) {
            System.out.println("\nEnter Course " + (i + 1) + " details:");
            System.out.print("Course Code: ");
            String code = input.nextLine();
            System.out.print("Course Name: ");
            String name = input.nextLine();
            Course course = new Course(code, name);
            admin.addCourse(courseCatalog, course);
        }

        // Assign teachers
        System.out.println("\n=== Assign Teachers to Courses ===");
        for (Course c : courseCatalog) {
            System.out.print("Assign teacher ID for " + c.getCourseName() + ": ");
            String tid = input.nextLine();
            Teacher teacher = findTeacherById(people, tid);
            if (teacher != null) {
                admin.assignTeacherToCourse(courseCatalog, c.getCourseCode(), teacher);
            } else {
                System.out.println("Teacher not found.");
            }
        }

        // Students enroll
        System.out.println("\n=== Student Course Enrollment ===");
        for (Person p : people) {
            if (p instanceof Student s) {
                System.out.println("Student: " + s.getName());
                System.out.print("How many courses to enroll? ");
                int n = input.nextInt();
                input.nextLine();
                for (int i = 0; i < n; i++) {
                    System.out.print("Enter course code: ");
                    String code = input.nextLine();
                    Course course = findCourse(courseCatalog, code);
                    if (course != null) s.enrollCourse(course);
                    else System.out.println("Course not found!");
                }
            }
        }

        // Display everything
        System.out.println("\n=== PEOPLE INFO ===");
        for (Person person : people) {
            person.displayInfo();
            System.out.println();
        }

        System.out.println("\n=== COURSE CATALOG ===");
        for (Course c : courseCatalog) System.out.println(c);

        input.close();
    }

    private static Course findCourse(List<Course> catalog, String code) {
        for (Course c : catalog)
            if (c.getCourseCode().equalsIgnoreCase(code)) return c;
        return null;
    }

    private static Teacher findTeacherById(List<Person> people, String id) {
        for (Person p : people)
            if (p instanceof Teacher && p.getId().equalsIgnoreCase(id))
                return (Teacher) p;
        return null;
    }
}
