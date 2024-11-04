public class ProgrammingCourseCopy implements Course, Cloneable {
    @Override
    public String getName() {
        return "Programming Course Copy";
    }

    @Override
    public ProgrammingCourseCopy clone() throws CloneNotSupportedException {
        return (ProgrammingCourseCopy) super.clone();
    }
}
