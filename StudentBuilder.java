public class StudentBuilder {
    private String name;
    private Course course;
    private boolean isPremium;
    private String email;

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public StudentBuilder setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
        return this;
    }

    public StudentBuilder setEmail(String email) { //
        this.email = email;
        return this;
    }

    public Student build(StudentFactory studentFactory) {
        if (isPremium) {
            return studentFactory.createStudent(name, course, email);
        } else {
            return studentFactory.createStudent("Regular " + name, course, email);
        }
    }
}
