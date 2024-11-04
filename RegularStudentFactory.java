public class RegularStudentFactory implements StudentFactory {
    @Override
    public Student createStudent(String name, Course course, String email) {
        return new RegularStudent(name, course, email);
    }
}
