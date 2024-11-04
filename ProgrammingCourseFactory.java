public class ProgrammingCourseFactory implements CourseFactory {
    @Override
    public Course createCourse() {
        return new ProgrammingCourse();
    }
}
