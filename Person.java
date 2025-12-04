public abstract class Person {
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        // Validate name
        if (!ValidationUtils.isValidName(name)) {
            throw new IllegalArgumentException(
                    "Invalid name format: '" + name + "'. Name must contain only letters and spaces.");
        }

        // Validate email
        if (!ValidationUtils.isValidEmail(email)) {
            throw new IllegalArgumentException(
                    "Invalid email format: '" + email + "'. Please provide a valid email address.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Email: %s", id, name, email);
    }
}
