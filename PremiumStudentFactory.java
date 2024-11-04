public class PremiumStudentFactory implements StudentFactory {
    @Override
    public Student createStudent(String name, Course course, String email) {
        return new PremiumStudent(name, course, email);
    }
}
