public class Course {
    public static final int MAX_STUDENTS = 30;

    private String courseCode;
    private String courseName;
    private Difficulty difficulty;
    private Teacher teacherAssigned;

    public Course(String courseCode, String courseName, Difficulty difficulty) {
        // Validate course code format
        if (!ValidationUtils.isValidCourseCode(courseCode)) {
            throw new IllegalArgumentException("Invalid course code format: '" + courseCode
                    + "'. Course code must be 2-4 uppercase letters followed by 3 digits (e.g., CS101, MATH201).");
        }

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.difficulty = difficulty;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Teacher getTeacherAssigned() {
        return teacherAssigned;
    }

    public void setTeacherAssigned(Teacher teacherAssigned) {
        this.teacherAssigned = teacherAssigned;
    }

    @Override
    public String toString() {
        String t = (teacherAssigned == null) ? "Unassigned" : teacherAssigned.getName();
        return String.format("%-10s | %-20s | %-6s | Teacher: %s", courseCode, courseName, difficulty, t);
    }
}
