// DesignCourse.java

public class DesignCourse implements Course {

    @Override
    public void registerStudent() {
        System.out.println("Registering student in Design Course.");
        // Additional logic for registering a student in the Design Course
    }

    @Override
    public String getName() {
        return "Design Course";
    }
}
