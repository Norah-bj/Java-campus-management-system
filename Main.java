import java.util.*;
import java.util.concurrent.*;
import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Course> courseCatalog = new ArrayList<>();
        Admin admin = null;

        System.out.println("=== CAMPUS MANAGEMENT SYSTEM (UPGRADED) ===");

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
            
            // 1. ENUM Usage
            System.out.println("Select Difficulty (1-EASY, 2-MEDIUM, 3-HARD): ");
            int diffChoice = input.nextInt();
            input.nextLine();
            Difficulty diff = Difficulty.EASY;
            if(diffChoice == 2) diff = Difficulty.MEDIUM;
            else if(diffChoice == 3) diff = Difficulty.HARD;

            Course course = new Course(code, name, diff);
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
                    
                    // 2. Exception Handling
                    try {
                        s.enrollCourse(course);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }

        // 3. Streams - Sort Students
        System.out.println("\n=== Sorted Students (Streams) ===");
        people.stream()
              .filter(p -> p instanceof Student)
              .sorted(Comparator.comparing(Person::getName))
              .forEach(p -> System.out.println(p.getName()));

        // 4. Formatting
        System.out.println("\n=== Formatting Examples ===");
        DecimalFormat df = new DecimalFormat("#,###.00");
        for(Person p : people) {
            if(p instanceof Payable payable) {
                System.out.println("Payment for " + p.getName() + ": " + df.format(payable.calculatePayment()));
            }
            if(p instanceof Student s) {
                System.out.printf("Student %-10s | Grade %d%n", s.getName(), s.getGradeLevel()); // printf
            }
        }

        // 5. Wildcards
        System.out.println("\n=== Wildcards Example (Printing all people) ===");
        printList(people);

        // 6. Multithreading & Executor
        System.out.println("\n=== Multithreading Payment Processing ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Payable> payables = new ArrayList<>();
        for(Person p : people) {
            if(p instanceof Payable) payables.add((Payable)p);
        }
        
        for (Payable p : payables) {
            executor.submit(() -> {
                System.out.println(p.getClass().getSimpleName() + 
                    " processed payment: " + df.format(p.calculatePayment()) + " [Thread: " + Thread.currentThread().getName() + "]");
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 7. Merging Lists
        System.out.println("\n=== Merging Lists (Teachers + Students) ===");
        List<Person> teachers = people.stream().filter(p -> p instanceof Teacher).collect(Collectors.toList());
        List<Person> students = people.stream().filter(p -> p instanceof Student).collect(Collectors.toList());
        
        List<Person> merged = Stream.concat(teachers.stream(), students.stream())
                                    .collect(Collectors.toList());
        merged.forEach(p -> System.out.println(p.getName() + " (" + p.getClass().getSimpleName() + ")"));

        input.close();
    }

    // Updated findCourse using Streams
    private static Course findCourse(List<Course> catalog, String code) {
        return catalog.stream()
                .filter(course -> course.getCourseCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    // Updated findTeacherById using Streams
    private static Teacher findTeacherById(List<Person> people, String id) {
        return people.stream()
                .filter(p -> p instanceof Teacher && p.getId().equalsIgnoreCase(id))
                .map(p -> (Teacher) p)
                .findFirst()
                .orElse(null);
    }

    // Wildcard method
    public static void printList(List<? extends Person> list) {
        for (Person p : list) {
            System.out.println(p.getName());
        }
    }
}
