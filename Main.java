import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("✨ Welcome to the Student Registration System! ✨");
        System.out.println("Developed with Ali Parwiz Baktash 🌟");
        System.out.println("Under the guidance of Dr. Maksutov Akylbek 🌟");
        System.out.println("---------------------------------------------------");

        boolean continueRegistration = true;

        while (continueRegistration) {
            try {
                System.out.print("Hello! Are you a student looking to register for a course? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (!response.equals("yes") && !response.equals("no")) {
                    throw new IllegalArgumentException("Please enter 'yes' or 'no'.");
                }
                if (response.equals("no")) {
                    System.out.println("Thank you for visiting!");
                    break;
                }

                System.out.println("--- Register a New Student ---");
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                System.out.print("Choose a course: 1 - Programming Course, 2 - Design Course: ");
                int courseChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (courseChoice != 1 && courseChoice != 2) {
                    throw new IllegalArgumentException("Invalid course choice. Choose 1 or 2.");
                }

                System.out.print("Are you registering as a premium student? (true/false): ");
                boolean isPremium = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                if (!isValidEmail(email)) {
                    throw new IllegalArgumentException("Invalid email format.");
                }

                System.out.println("The student has been successfully registered! ✅");
                System.out.println("Registration successful for " + name + "!");

                System.out.print("Would you like to register another student? (yes/no): ");
                response = scanner.nextLine().trim().toLowerCase();
                continueRegistration = response.equals("yes");

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear invalid input
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input type. Please enter the correct type of data.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }
}
