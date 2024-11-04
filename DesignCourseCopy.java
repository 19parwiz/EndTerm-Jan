public class DesignCourseCopy implements Course, Cloneable {
    @Override
    public String getName() {
        return "Design Course Copy";
    }

    @Override
    public DesignCourseCopy clone() throws CloneNotSupportedException {
        return (DesignCourseCopy) super.clone();
    }
}
