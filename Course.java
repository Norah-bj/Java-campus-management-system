public class Course {
    public static final int MAX_STUDENTS = 30;

    private String courseCode;
    private String courseName;
    private Teacher teacherAssigned;

    public Course(String courseCode,String courseName){
        this.courseCode=courseCode;
        this.courseName=courseName;
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

    public Teacher getTeacherAssigned(){
        return teacherAssigned;
    }

    public void setTeacherAssigned(Teacher teacherAssigned){
        this.teacherAssigned = teacherAssigned;
    }

    @Override
    public String toString() {
        String t = (teacherAssigned == null) ? "Unassigned" : teacherAssigned.getName();
        return courseCode + " - " + courseName + " | Teacher: " + t;
    }
}
