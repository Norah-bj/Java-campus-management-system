public class Teacher extends Person implements Payable {
    private String subject;
    private double salary;

    public Teacher(String id, String name, String email, String subject, double salary) {
        super(id, name, email);
        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() { return subject; }

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
