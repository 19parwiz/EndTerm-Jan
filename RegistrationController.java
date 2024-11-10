import java.util.InputMismatchException; // Allows handling of input errors when a user enters non-integer values
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegistrationController {
    private final RegistrationManager manager;
    private final Database database;
    private final RegistrationView view;

    // Constructor to set up the manager, database, and view components
    public RegistrationController(RegistrationManager manager, Database database, RegistrationView view) {
        this.manager = manager;
        this.database = database;
        this.view = view;
    }

    // This method starts the whole registration process
    public void startRegistrationProcess(Scanner scanner) {
        view.displayWelcomeMessage(); // Show a welcoming message to the user

        while (true) {
            // Prompt for student's name
            String name = getInputString(scanner, "Enter your name, please: ");

            // Prompt for course choice (1 for Programming, 2 for Design)
            int courseChoice = getInputInt(scanner, "Choose a course to enroll in: 1 - Programming Course 💻 2 - Design Course 🎨", 1, 2);
            CourseFactory courseFactory = (courseChoice == 1) ? new ProgrammingCourseFactory() : new DesignCourseFactory();

            // Ask if the student is registering as a premium student
            boolean isPremium = getInputBoolean(scanner, "Are you registering as a premium student? (true/false): ");

            // Get a valid email address from the user
            String email = getInputEmail(scanner);

            // Create the appropriate student based on their premium status
            StudentFactory studentFactory = (isPremium) ? new PremiumStudentFactory() : new RegularStudentFactory();
            Student student = new StudentBuilder()
                    .setName(name)
                    .setCourse(courseFactory.createCourse())
                    .setIsPremium(isPremium)
                    .setEmail(email)
                    .build(studentFactory);

            // Register and save the student
            manager.registerStudent(student);
            database.saveStudent(student);
            manager.displayRegisteredStudents();

            // Confirm successful registration
            view.displaySuccessMessage("Student registered successfully!");

            // Ask if the user wants to register another student or exit
            String continueChoice = getInputString(scanner, "Would you like to register another student? (yes/no): ");
            if (!continueChoice.equalsIgnoreCase("yes")) {
                break; // Exit the loop if the user says "no"
            }
        }
    }

    // Helper method to get a string input from the user
    private String getInputString(Scanner scanner, String prompt) {
        view.displayMessage(prompt);
        return scanner.nextLine().trim(); // Trims any whitespace around the input
    }

    // Helper method to get an integer input within a specified range
    private int getInputInt(Scanner scanner, String prompt, int min, int max) {
        int choice;
        while (true) {
            view.displayMessage(prompt);
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clears the newline character after the integer
                if (choice < min || choice > max) {
                    view.displayInvalidInputMessage("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                } else {
                    return choice; // Valid choice, so we return it
                }
            } catch (InputMismatchException e) {
                view.displayInvalidInputMessage("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input to avoid an infinite loop
            }
        }
    }

    // Helper method to get a boolean input (true or false) from the user
    private boolean getInputBoolean(Scanner scanner, String prompt) {
        while (true) {
            view.displayMessage(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                view.displayInvalidInputMessage("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

    // Helper method to get a valid email input
    private String getInputEmail(Scanner scanner) {
        String email;
        while (true) {
            view.displayMessage("Please enter your email (e.g., name@gmail.com): ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                return email; // Valid email, so we return it
            } else {
                view.displayInvalidInputMessage("Invalid email format. Please ensure it is in the format 'name@gmail.com'.");
            }
        }
    }

    // Simple email validation method using a regex pattern
    private boolean isValidEmail(String email) {
        // This regex checks that the email has valid characters and ends with '.com'
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
