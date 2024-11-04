public class DesignCourseFactory implements CourseFactory {
    @Override
    public Course createCourse() {
        return new DesignCourse();
    }
}
