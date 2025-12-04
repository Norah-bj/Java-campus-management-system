import java.util.regex.Pattern;

public class ValidationUtils {
    // Email pattern: standard email format
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    // ID patterns
    private static final Pattern STUDENT_ID_PATTERN = Pattern.compile("^S\\d{3,}$");
    private static final Pattern TEACHER_ID_PATTERN = Pattern.compile("^T\\d{3,}$");
    private static final Pattern ADMIN_ID_PATTERN = Pattern.compile("^A\\d{3,}$");

    // Name pattern: letters and spaces only
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");

    // Course code pattern: 2-4 uppercase letters followed by 3 digits (e.g., CS101,
    // MATH201)
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[A-Z]{2,4}\\d{3}$");

    // Subject pattern: letters and spaces only
    private static final Pattern SUBJECT_PATTERN = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");

    /**
     * Validates email format
     * 
     * @param email the email to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validates student ID format (S followed by 3+ digits)
     * 
     * @param id the student ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidStudentId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return STUDENT_ID_PATTERN.matcher(id.trim()).matches();
    }

    /**
     * Validates teacher ID format (T followed by 3+ digits)
     * 
     * @param id the teacher ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidTeacherId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return TEACHER_ID_PATTERN.matcher(id.trim()).matches();
    }

    /**
     * Validates admin ID format (A followed by 3+ digits)
     * 
     * @param id the admin ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAdminId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return ADMIN_ID_PATTERN.matcher(id.trim()).matches();
    }

    /**
     * Validates name format (letters and spaces only)
     * 
     * @param name the name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return NAME_PATTERN.matcher(name.trim()).matches();
    }

    /**
     * Validates course code format (2-4 uppercase letters + 3 digits)
     * 
     * @param code the course code to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCourseCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        return COURSE_CODE_PATTERN.matcher(code.trim()).matches();
    }

    /**
     * Validates subject format (letters and spaces only)
     * 
     * @param subject the subject to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            return false;
        }
        return SUBJECT_PATTERN.matcher(subject.trim()).matches();
    }
}
