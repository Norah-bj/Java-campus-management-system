public class Teacher extends Person implements Payable {
    private String subject;
    private double salary;

    public Teacher(String id, String name, String email, String subject, double salary) {
        super(id, name, email);

        // Validate teacher ID format
        if (!ValidationUtils.isValidTeacherId(id)) {
            throw new IllegalArgumentException("Invalid teacher ID format: '" + id
                    + "'. Teacher ID must start with 'T' followed by at least 3 digits (e.g., T101).");
        }

        // Validate subject format
        if (!ValidationUtils.isValidSubject(subject)) {
            throw new IllegalArgumentException(
                    "Invalid subject format: '" + subject + "'. Subject must contain only letters and spaces.");
        }

        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displayInfo() {
        System.out.println("Teacher: " + toString());
        System.out.println("Subject: " + subject + ", Salary: " + salary);
    }

    @Override
    public double calculatePayment() {
        return salary;
    }
}
