public class RegularStudent implements Student {
    private String name;
    private Course course;
    private String email;

    public RegularStudent(String name, Course course, String email) {
        this.name = name;
        this.course = course;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Course getCourse() {
        return course;
    }

    @Override
    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "RegularStudent{" +
                "name='" + name + '\'' +
                ", course=" + course.getName() +
                '}';
    }
}
