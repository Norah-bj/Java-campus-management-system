import java.util.List;

public class Admin extends Person {
    public Admin(String id, String name, String email){
        super(id, name, email);
    }

    @Override
    public void displayInfo(){
        System.out.println("Admin: " + toString());
    }

    public void addCourse(List<Course> courseList, Course course){
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
