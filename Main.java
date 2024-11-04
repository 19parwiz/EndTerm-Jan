import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        RegistrationManager manager = RegistrationManager.getInstance();
        manager.addObserver(new LoggingObserver());
        manager.addObserver(new EmailNotificationObserver());
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);

        System.out.println("✨ Welcome to the Ultimate Student Registration System! ✨");
        System.out.println("Developed with care by Ali Parwiz Baktash 🌟");
        System.out.println("---------------------------------------------------");
        System.out.println("📚 Ready to sign up for your next adventure in learning? Follow the prompts, and let's get you registered for your course!");

        while (true) {
            String name = getInputString(scanner, "Enter your name, please: ");

            int courseChoice = getInputInt(scanner, "Choose a course to enroll in: 1 - Programming Course 💻 2 - Design Course 🎨", 1, 2);

            CourseFactory courseFactory = (courseChoice == 1) ? new ProgrammingCourseFactory() : new DesignCourseFactory();

            boolean isPremium = getInputBoolean(scanner, "Are you registering as a premium student? (true/false): ");

            String email = getInputEmail(scanner);

            StudentFactory studentFactory = (isPremium) ? new PremiumStudentFactory() : new RegularStudentFactory();

            Student student = new StudentBuilder()
                    .setName(name)
                    .setCourse(courseFactory.createCourse())
                    .setIsPremium(isPremium)
                    .setEmail(email)
                    .build(studentFactory);

            manager.registerStudent(student);
            database.saveStudent(student);
            manager.displayRegisteredStudents();

            String continueChoice = getInputString(scanner, "Would you like to register another student? (yes/no): ");
            if (!continueChoice.equalsIgnoreCase("yes")) {
                break; // Exit the loop if the user doesn't want to continue
            }
        }
        scanner.close();
    }

    private static String getInputString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getInputInt(Scanner scanner, String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt);
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice < min || choice > max) {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static boolean getInputBoolean(Scanner scanner, String prompt) {
        boolean value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

    private static String getInputEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("Please enter your email (e.g., name@gmail.com): ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid email format. Please ensure it is in the format 'name@gmail.com'.");
            }
        }
    }

    private static boolean isValidEmail(String email) {
        // Basic email regex for validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
