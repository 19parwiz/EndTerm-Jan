public class RegistrationView {
    public void displayWelcomeMessage() {
        System.out.println("✨ Welcome to the Ultimate Student Registration System! ✨");
        System.out.println("Developed with care by Ali Parwiz Baktash 🌟");
        System.out.println("---------------------------------------------------");
        System.out.println("📚 Ready to sign up for your next adventure in learning? Follow the prompts, and let's get you registered for your course!");
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayInvalidInputMessage(String message) {
        System.out.println("❌ " + message);
    }

    public void displaySuccessMessage(String message) {
        System.out.println("✅ " + message);
    }
}
