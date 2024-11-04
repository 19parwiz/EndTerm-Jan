import java.util.ArrayList;
import java.util.List;
//Singleton for managing student registration
public class RegistrationManager {
    private static RegistrationManager instance;
    private List<Student> students;

    private RegistrationManager() {
        students = new ArrayList<>();
    }

    public static RegistrationManager getInstance() {
        if (instance == null) {
            instance = new RegistrationManager();
        }
        return instance;
    }

    public void registerStudent(Student student) {
        students.add(student);
        notifyObservers("New student registered: " + student.getName());
    }

    public void displayRegisteredStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Observer pattern for event notifications
    private List<RegistrationObserver> observers = new ArrayList<>();

    public void addObserver(RegistrationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RegistrationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (RegistrationObserver observer : observers) {
            observer.update(event);
        }
    }
}

