// PremiumCourseDecorator.java

public class PremiumCourseDecorator implements Course {
    private final Course decoratedCourse;

    // Constructor to wrap an existing course with premium features
    public PremiumCourseDecorator(Course decoratedCourse) {
        this.decoratedCourse = decoratedCourse;
    }

    @Override
    public void registerStudent() {
        decoratedCourse.registerStudent(); // Register as normal
        addPremiumFeatures();              // Add premium-specific features
    }

    // Additional features exclusive to premium courses
    private void addPremiumFeatures() {
        System.out.println("Adding premium features to the course...");
        // Implement any extra premium functionality here, such as access to exclusive resources
    }

    @Override
    public String getName() {
        return decoratedCourse.getName() + " (Premium)";
    }
}
