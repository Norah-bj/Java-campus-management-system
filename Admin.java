import java.util.List;

public class Admin extends Person {
    public Admin(String id, String name, String email) {
        super(id, name, email);

        // Validate admin ID format
        if (!ValidationUtils.isValidAdminId(id)) {
            throw new IllegalArgumentException("Invalid admin ID format: '" + id
                    + "'. Admin ID must start with 'A' followed by at least 3 digits (e.g., A001).");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + toString());
    }

    public void addCourse(List<Course> courseList, Course course) {
        courseList.add(course);
        System.out.println("Course " + course.getCourseCode() + " added successfully!");
    }

    public void removeCourse(List<Course> courseList, String courseCode) {
        courseList.removeIf(c -> c.getCourseCode().equalsIgnoreCase(courseCode));
        System.out.println("Course " + courseCode + " removed successfully!");
    }

    public boolean assignTeacherToCourse(List<Course> courseList, String courseCode, Teacher t) {
        for (Course c : courseList) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                c.setTeacherAssigned(t);
                System.out.println("Assigned " + t.getName() + " to " + c.getCourseCode());
                return true;
            }
        }
        return false;
    }
}
