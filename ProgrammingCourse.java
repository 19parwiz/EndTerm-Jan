// ProgrammingCourse.java

public class ProgrammingCourse implements Course {

    @Override
    public void registerStudent() {
        System.out.println("Registering student in Programming Course.");
        // Additional logic for registering a student in the Programming Course
    }

    @Override
    public String getName() {
        return "Programming Course";
    }
}
